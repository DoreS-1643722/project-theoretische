import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Automaton {
    private ArrayList<String> m_states;
    private ArrayList<Boolean> m_state_already_used;
    private ArrayList<ArrayList<String>> m_relations;
    private String start_state;
    private String accept_state;
    private Boolean check;

    /**
     * constructor
     */
    public Automaton(){
        m_states = new ArrayList<String>();
        m_relations = new ArrayList<ArrayList<String>>();
        m_state_already_used = new ArrayList<Boolean>();
        check = false;
    }

    /**
     *
     * Returns the shortest string that the automaton accepts.
     * @param accept returns the shortest string that the automaton accepts if
     *               accept is true
     * @return
     */
    public String getShortestExample(Boolean accept){
        String shortest_string = "";
        shortest_string = getShortestExampleHulp(start_state, null);
        return shortest_string;
    }

    /**
     * Helpfunction for getShortestExample recursively generates the shortest
     * string accepted by the automaton.
     * @param current_state the state the automaton current is running over.
     * @param previous_state the state the automaton was in in the previous iteration.
     * @return the current found string
     */
    private String getShortestExampleHulp(String current_state, String previous_state){
        String temp_shortest_str = "";
        if(current_state.equals(accept_state)){
            check = true;
            return temp_shortest_str;
        }
        for(int i = 0; i < m_relations.size(); i++){
            if(m_relations.get(i).get(0).equals(current_state)){
                String new_state = m_relations.get(i).get(2);
                String last_str = temp_shortest_str;
                if(m_state_already_used.get(m_states.indexOf(new_state)) == false) {
                    m_state_already_used.set(m_states.indexOf(current_state), true);
                    temp_shortest_str += m_relations.get(i).get(1) + getShortestExampleHulp(new_state, current_state);
                    if(check == false){
                        temp_shortest_str = temp_shortest_str.substring(0, temp_shortest_str.length() - 1);
                    }
                    else{
                        if(last_str.equals("")){
                            last_str = temp_shortest_str;
                        }
                        else if (!(last_str.equals("")) && last_str.length() < temp_shortest_str.length()) {
                            temp_shortest_str = last_str;
                        }
                    }
                }
            }
        }
        return temp_shortest_str;
    }

    /**
     * Calculates the intersection of two automatons
     * @param aut the second automaton that is to be intersected on
     *            this one
     * @return the intersected automaton
     */
    public Automaton intersection(Automaton aut){

        return aut;
    }

    /**
     * Reads the new line given as a parameter and puts the content
     * in the right lists
     * @param new_line the new line given by the parser
     */
    public void readNewLine(String new_line) throws Exception {
        if(new_line.length() >= 11 && new_line.substring(0, 11).equals("(START) |- ")){
            String new_state = new_line.substring(11);
            start_state = new_state;
            addNewState(new_state);
        }
        else{
            int i = findIndexNextWhitespace(new_line);
            String new_state = new_line.substring(0, i);
            if(new_line.length() >= 11 && new_line.substring(i, i + 11).equals(" -| (FINAL)")){
                accept_state = new_state;
                addNewState(new_state);
            }
            else{
                int j = findIndexNextWhitespace(new_line.substring(i+1));
                if(j == 0 || j == new_line.substring(i+1).length()){
                    throw new Exception("File not in the write format");
                }
                new_state = new_line.substring(0, i);
                String new_state2 = new_line.substring(i+j+2);
                addNewState(new_state);
                addNewState(new_state2);
                String relation = new_line.substring(i+1, i+j+1);
                addNewRelation(new_state, new_state2, relation);
            }
        }
    }

    /**
     * Adds a new relation to the relation arraylist
     * @param state1 first state
     * @param state2 second state
     * @param relation the relation to go from the first to the second state
     */
    private void addNewRelation(String state1, String state2, String relation){
        ArrayList<String> temp_list = new ArrayList<String>();
        temp_list.add(state1);
        temp_list.add(relation);
        temp_list.add(state2);
        m_relations.add(temp_list);
    }

    /**
     * finds the next whitespace in the given line
     * @param new_line the line to find the whitespace in
     * @return the index at which the whitespace is found
     */
    private int findIndexNextWhitespace(String new_line){
        int i = 0;
        char current_char = new_line.charAt(i);
        while(current_char != ' '){
            i++;
            current_char = new_line.charAt(i);
        }
        return i;
    }

    /**
     * Adds a new state to the m_states arraylist if the state
     * isn't already in it
     * @param new_state
     */
    private void addNewState(String new_state){
        if(m_states.indexOf(new_state) == -1){
            m_states.add(new_state);
            m_state_already_used.add(false);
        }
    }
}

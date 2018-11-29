import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AutomatonParser {
    private Automaton m_automaton;
    private ArrayList<String> m_file_contents;

    /**
     * Constructor
     * @param filename File to be read
     */
    public AutomatonParser(String filename){
        m_file_contents = new ArrayList<String>();
        try{
        FileInputStream new_file = new FileInputStream(filename);
        readAllLines(new_file);
        } catch(IOException e){
            System.out.println("Couldn't open file: " + filename);
        }
        m_automaton = new Automaton();
    }

    /**
     * Reads every line from the file and puts it into
     * m_file_contents
     * @param file the opened file
     */
    private void readAllLines(FileInputStream file){
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
        String line = null;
        try{
            while ((line = br.readLine()) != null){
                m_file_contents.add(line);
            }
        } catch (IOException e){
            System.err.println("Error reading file!");
        }
    }

    /**
     * Parses the file into a automaton by feeding
     * by calling the readNewLine function of automaton
     * line by line
     */
    public void parse() throws Exception{
        try{
            for(int i = 0; i < m_file_contents.size(); i++){
                m_automaton.readNewLine(m_file_contents.get(i));
            }
        } catch (Exception e){
            throw new Exception("File not in the right format");
        }
    }

    /**
     * @return the automaton in m_automaton
     */
    public Automaton automaton(){
        return m_automaton;
    }

    /**
     * Main function
     * @param args arguments that can be given through the command line
     *             not needed in this project
     */
    public static void main(String[] args){
        AutomatonParser parser = new AutomatonParser("src/adventure1.aut");
        try{
            parser.parse();
        } catch(Exception e){
            System.err.println("File not in the right format");
        }
        Automaton result = parser.automaton();

        AutomatonParser parser2 = new AutomatonParser("src/adventure2.aut");
        try {
            parser2.parse();
        } catch (Exception e){
            System.err.println("File not in the right format");
        }
        Automaton result2 = parser2.automaton();

        Automaton intersect_automaton = result.intersection(result2);


        System.out.println(result.getShortestExample(true));

        System.out.println(intersect_automaton.getShortestExample(true));
    }
}
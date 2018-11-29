public class Level0 {
    public static void main(String[] args){
        AutomatonParser automaton_parser = new AutomatonParser("src/adventure.aut");
        try{
            automaton_parser.parse();
        } catch (Exception e){
            System.out.println("Error: ");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
        Automaton result = automaton_parser.automaton();
        System.out.println(result.getShortestExample(true));
    }
}

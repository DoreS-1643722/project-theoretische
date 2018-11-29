public class Level2 {
    /**
     * Intersects the constraint automatons
     * @return actualResult, the intersected constraint automaton
     */
    public Automaton makeCohan(){
        AutomatonParser treasureparser = new AutomatonParser("src/twoTreasuresWithArches.aut");
        AutomatonParser keyparser = new AutomatonParser("src/keyFinder.aut");
        AutomatonParser burningManparser = new AutomatonParser("src/burningMan.aut");
        Automaton actualResult = new Automaton();
        try{
            treasureparser.parse();
            keyparser.parse();
            burningManparser.parse();
            Automaton aut1 = treasureparser.automaton();
            Automaton aut2 = keyparser.automaton();
            Automaton aut3 = burningManparser.automaton();

            Automaton result = aut1.intersection(aut2);
            actualResult = aut3.intersection(result);
        } catch (Exception e){
            System.out.println("Error: ");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
        return actualResult;
    }

    /**
     * main function
     * @param args
     */
    public static void main(String[] args){
        try{
            AutomatonParser parse1 = new AutomatonParser("src/adventure.aut");
            Level2 level_constraints = new Level2();

            parse1.parse();
            Automaton aut1 = parse1.automaton();
            Automaton aut2 = level_constraints.makeCohan();
            Automaton result = aut1.intersection(aut2);

            System.out.println(result.getShortestExample(true));
        } catch (Exception e){
            System.out.println("Error: ");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
    }
}

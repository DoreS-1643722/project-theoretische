public class Level1 {
    public Automaton makeRincewind(){
        AutomatonParser treasureparser = new AutomatonParser("src/twoTreasures.aut");
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

    public static void main(String[] args){
        try{
            AutomatonParser parse1 = new AutomatonParser("src/adventure.aut");
            Level1 level_constraints = new Level1();

            parse1.parse();
            Automaton aut1 = parse1.automaton();
            Automaton aut2 = level_constraints.makeRincewind();
            Automaton result = aut1.intersection(aut2);

            System.out.println(result.getShortestExample(true));
        } catch (Exception e){
            System.out.println("Error: ");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
        }
    }
}

import java.util.HashMap;

public class Rankings {

    private static final HashMap<String, Integer> ranking;

    static {
        ranking= new HashMap<String, Integer>();
        ranking.put("2",2);
        ranking.put("3",3);
        ranking.put("4",4);
        ranking.put("5",5);
        ranking.put("6",6);
        ranking.put("7",7);
        ranking.put("8",8);
        ranking.put("9",9);
        ranking.put("T",10);
        ranking.put("J",11);
        ranking.put("Q",12);
        ranking.put("K",13);
        ranking.put("A",14);
    }

    public static HashMap<String, Integer> getRanking() {
        return ranking;
    }
}
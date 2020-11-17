import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        HandEvaluator he = new HandEvaluator();
        System.out.println(he.evaluateHands("poker.txt"));
    }
}
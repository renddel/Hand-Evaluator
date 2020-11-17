import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HandEvaluator {
    private long counterPlayer1=0;

    protected String evaluateHands(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner input = new Scanner(file);

        while (input.hasNextLine()) {
            String line = input.nextLine();
            String elements[] = line.split(" ");
            Card hand[] = new Card[5];
            for(int i=0;i<5;i++){
                Card card = new Card(new Rank(elements[i].substring(0, 1)),
                        Suit.valueOf(elements[i].substring(1, 2)));
                hand[i]=card;
            }
            Card hand2[] = new Card[5];
            for(int i=0;i<5;i++){
                Card card = new Card(new Rank(elements[i+5].substring(0, 1)),
                        Suit.valueOf(elements[i+5].substring(1, 2)));
                hand2[i]=card;
            }
            Hand player1 = new Hand(hand);
            Hand player2 = new Hand(hand2);
            player1.evaluateHand();
            player2.evaluateHand();
            calculateVictor(player1, player2);
        }
        return getPlayer1Wins();
    }

    private void calculateVictor(Hand player1, Hand player2){
        if(player1.getHandValue()>player2.getHandValue()){
            counterPlayer1++;
        }
    }

    private String getPlayer1Wins(){
        return "Player 1 won with " + counterPlayer1+ " hands";
    }
}
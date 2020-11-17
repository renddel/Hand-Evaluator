import java.util.Arrays;
import java.util.Comparator;

public class Hand {
    private Card[] hand;
    private int highCardValue;
    private boolean pair;
    private boolean twoPairs;
    private boolean threeOfTheKind;
    private boolean fourOfTheKind;
    private boolean fullHouse;
    private boolean flush;
    private boolean straight;
    private int handValue;

    private static final int STRAIGHT_FLUSH  = 8000000;
    private static final int FOUR_OF_A_KIND  = 7000000;
    private static final int FULL_HOUSE      = 6000000;
    private static final int FLUSH           = 5000000;
    private static final int STRAIGHT        = 4000000;
    private static final int THREE_OF_A_KIND = 3000000;
    private static final int TWO_PAIRS       = 2000000;
    private static final int ONE_PAIR        = 1000000;

    public Hand(Card[] hand) {
        this.hand = hand;
        highCardValue =0;
        handValue=0;
    }

    public Card[] getHand() {
        return hand;
    }

    public void setFlush(boolean flush) {
        this.flush = flush;
    }

    public boolean isFlush(){
        Suit mainSuit = hand[0].getSuit();
        setFlush(true);
        for(int i=1;i<hand.length;i++){
            if(hand[i].getSuit()!=mainSuit){
                setFlush(false);
                break;
            }
        }
        return flush;
    }

    public boolean isStraight(){
        straight=true;
        int prevVal = hand[0].getRank().getValue();
        for(int i=1;i<hand.length;i++){
            if(prevVal+1!=hand[i].getRank().getValue()){
                straight=false;
                break;
            }
            prevVal=hand[i].getRank().getValue();
        }
        return straight;
    }

    public boolean isFourOfTheKind() {
        boolean a1, a2;

        a1 = hand[0].getRank().getValue() == hand[1].getRank().getValue() &&
                hand[1].getRank().getValue() == hand[2].getRank().getValue() &&
                hand[2].getRank().getValue() == hand[3].getRank().getValue();


        a2 = hand[1].getRank().getValue() == hand[2].getRank().getValue() &&
                hand[2].getRank().getValue() == hand[3].getRank().getValue() &&
                hand[3].getRank().getValue() == hand[4].getRank().getValue();

        fourOfTheKind =( a1 || a2 );
        return fourOfTheKind;
    }

    public boolean isFullHouse() {
        boolean a1, a2;

        a1 = hand[0].getRank().getValue() == hand[1].getRank().getValue() &&
                hand[1].getRank().getValue() == hand[2].getRank().getValue() &&
                hand[3].getRank().getValue() == hand[4].getRank().getValue();

        a2 = hand[0].getRank().getValue() == hand[1].getRank().getValue() &&
                hand[2].getRank().getValue() == hand[3].getRank().getValue() &&
                hand[3].getRank().getValue() == hand[4].getRank().getValue();

        fullHouse = (a1 || a2);
        return fullHouse;
    }

    public boolean isThreeOfTheKind() {
        boolean a1, a2, a3;

        if (fourOfTheKind || fullHouse )
            return(false);

        a1 = hand[0].getRank().getValue() == hand[1].getRank().getValue() &&
                hand[1].getRank().getValue() == hand[2].getRank().getValue() ;

        a2 = hand[1].getRank().getValue() == hand[2].getRank().getValue()&&
                hand[2].getRank().getValue() == hand[3].getRank().getValue() ;

        a3 = hand[2].getRank().getValue() == hand[3].getRank().getValue() &&
                hand[3].getRank().getValue() == hand[4].getRank().getValue();

        threeOfTheKind = ( a1 || a2 || a3 );
        return threeOfTheKind;
    }

    public boolean isTwoPairs(){
        boolean a1, a2, a3;

        if ( fourOfTheKind || fullHouse || threeOfTheKind )
            return(false);

        a1 = hand[0].getRank().getValue() == hand[1].getRank().getValue() &&
                hand[2].getRank().getValue() == hand[3].getRank().getValue() ;

        a2 = hand[0].getRank().getValue() == hand[1].getRank().getValue() &&
                hand[3].getRank().getValue() == hand[4].getRank().getValue() ;

        a3 = hand[1].getRank().getValue() == hand[2].getRank().getValue() &&
                hand[3].getRank().getValue() == hand[4].getRank().getValue() ;

        twoPairs =( a1 || a2 || a3 );
        return twoPairs;
    }

    public boolean isPair(){
        boolean a1, a2, a3, a4;

        if ( fourOfTheKind || fullHouse || threeOfTheKind || twoPairs)
            return(false);

        a1 = hand[0].getRank().getValue() == hand[1].getRank().getValue() ;

        a2 = hand[1].getRank().getValue() == hand[2].getRank().getValue() ;

        a3 = hand[2].getRank().getValue() == hand[3].getRank().getValue() ;

        a4 = hand[3].getRank().getValue() == hand[4].getRank().getValue() ;

        pair = ( a1 || a2 || a3 || a4 );
        return pair;
    }

    public int getHighCardValue() {
        return highCardValue;
    }

    public void setHighCardValue(int highCardValue) {
        this.highCardValue = highCardValue;
    }

    public void determinePowerOfHand(){
        sortTheHand();
        isFlush();
        isStraight();
        isFourOfTheKind();
        isFullHouse();
        isThreeOfTheKind();
        isTwoPairs();
        isPair();
        highCardValue =valueHighCard();
    }

    public int valueStraightFlush(){
        return STRAIGHT_FLUSH + getHighCardValue();
    }

    public int getHandValue() {
        return handValue;
    }

    public void setHandValue(int handValue) {
        this.handValue = handValue;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Card item: getHand()){
            sb.append(item.getSuit().getValue()).append(item.getRank().getValue()).append(" ");
        }
        return sb.toString();
    }

    public void sortTheHand(){
        Arrays.sort(hand, new SortByRank());
    }

    protected void evaluateHand(){
        determinePowerOfHand();
        handValue= valueOfTheHand();
    }

    private int valueOfTheHand() {
        if ( flush && straight)
            return valueStraightFlush();
        else if ( fourOfTheKind )
            return valueFourOfAKind();
        else if ( fullHouse )
            return valueFullHouse();
        else if ( flush)
            return valueFlush();
        else if ( straight )
            return valueStraight();
        else if ( threeOfTheKind )
            return valueThreeOfTheKind();
        else if ( twoPairs )
            return valueTwoPairs();
        else if ( pair )
            return valueOnePair();
        else
            return valueHighCard();
    }

    private int valueFlush(){
        return FLUSH + getHighCardValue();
    }

    private int valueStraight() {
        return STRAIGHT + getHighCardValue();
    }

    private int valueFourOfAKind() {
        sortTheHand();
        return FOUR_OF_A_KIND + getHand()[2].getRank().getValue();
    }

    private int valueFullHouse() {
        sortTheHand();
        return FULL_HOUSE + getHand()[2].getRank().getValue();
    }

    private int valueThreeOfTheKind(){
        sortTheHand();
        return THREE_OF_A_KIND + getHand()[2].getRank().getValue();
    }

    private int valueTwoPairs() {
        int value = 0;

        sortTheHand();

        if ( getHand()[0].getRank().getValue() == getHand()[1].getRank().getValue() &&
                getHand()[2].getRank().getValue() == getHand()[3].getRank().getValue() )
            value = 14*14*getHand()[2].getRank().getValue() + 14*getHand()[0].getRank().getValue() + getHand()[4].getRank().getValue();
        else if ( getHand()[0].getRank().getValue() == getHand()[1].getRank().getValue() &&
                getHand()[3].getRank().getValue() == getHand()[4].getRank().getValue() )
            value = 14*14*getHand()[3].getRank().getValue() + 14*getHand()[0].getRank().getValue() + getHand()[2].getRank().getValue();
        else
            value = 14*14*getHand()[3].getRank().getValue() + 14*getHand()[1].getRank().getValue() + getHand()[0].getRank().getValue();

        return TWO_PAIRS + value;
    }

    private int valueOnePair() {
        int value = 0;

        sortTheHand();

        if ( getHand()[0].getRank().getValue() == getHand()[1].getRank().getValue() )
            value = 14*14*14*getHand()[0].getRank().getValue() +
                    + getHand()[2].getRank().getValue() + 14*getHand()[3].getRank().getValue() + 14*14*getHand()[4].getRank().getValue();
        else if ( getHand()[1].getRank().getValue() == getHand()[2].getRank().getValue() )
            value = 14*14*14*getHand()[1].getRank().getValue() +
                    + getHand()[0].getRank().getValue() + 14*getHand()[3].getRank().getValue() + 14*14*getHand()[4].getRank().getValue();
        else if ( getHand()[2].getRank().getValue() == getHand()[3].getRank().getValue() )
            value = 14*14*14*getHand()[2].getRank().getValue() +
                    + getHand()[0].getRank().getValue() + 14*getHand()[1].getRank().getValue() + 14*14*getHand()[4].getRank().getValue();
        else
            value = 14*14*14*getHand()[3].getRank().getValue() +
                    + getHand()[0].getRank().getValue() + 14*getHand()[1].getRank().getValue() + 14*14*getHand()[2].getRank().getValue();

        return ONE_PAIR + value;
    }

    private int valueHighCard() {
        int value;

        sortTheHand();

        value =  getHand()[0].getRank().getValue() + 14*  getHand()[1].getRank().getValue() + 14*14*  getHand()[2].getRank().getValue()
                + 14*14*14*  getHand()[3].getRank().getValue() + 14*14*14*14*  getHand()[4].getRank().getValue();

        return value;
    }

    private class SortByRank implements Comparator<Card> {
        public int compare(Card a, Card b)
        {
            return a.getRank().getValue().compareTo(b.getRank().getValue());
        }
    }
}
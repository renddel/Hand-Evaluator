import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class HandEvaluatorTest {
    @Test
    public void evaluateHandsTest() throws FileNotFoundException {
        HandEvaluator he = new HandEvaluator();
        String answer = he.evaluateHands("test.txt");
        String expected = "Player 1 won with 3 hands";
        assertEquals(answer, expected);
    }

    @Test
    public void testEvaluateHand() {
        Card card = new Card(new Rank("T"), Suit.H);
        Card card2 = new Card(new Rank("J"), Suit.H);
        Card card3 = new Card(new Rank("Q"), Suit.H);
        Card card4 = new Card(new Rank("K"), Suit.H);
        Card card5 = new Card(new Rank("A"), Suit.H);
        Card tempArr[] = {card, card2, card3, card4, card5};
        Hand player1 = new Hand(tempArr);

        Card card11 = new Card(new Rank("2"), Suit.H);
        Card card22 = new Card(new Rank("3"), Suit.C);
        Card card33 = new Card(new Rank("4"), Suit.S);
        Card card44 = new Card(new Rank("5"), Suit.C);
        Card card55 = new Card(new Rank("6"), Suit.D);
        Card tempArr2[] = {card11, card22, card33, card44, card55};
        Hand player2 = new Hand(tempArr2);

        player1.evaluateHand();
        player2.evaluateHand();

        int expectedValue1 = 8576012;
        int expectedValue2 = 4245044;

        assertEquals(player1.getHandValue(),expectedValue1);
        assertEquals(player2.getHandValue(),expectedValue2);
    }

    @Test
    public void testFlush(){
        Card card = new Card(new Rank("9"), Suit.H);
        Card card2 = new Card(new Rank("J"), Suit.H);
        Card card3 = new Card(new Rank("Q"), Suit.H);
        Card card4 = new Card(new Rank("K"), Suit.H);
        Card card5 = new Card(new Rank("A"), Suit.H);
        Card tempArr[] = {card, card2, card3, card4, card5};
        Hand player1 = new Hand(tempArr);
        assertTrue(player1.isFlush());
        assertFalse(player1.isStraight());
    }

    @Test
    public void testStraight(){
        Card card = new Card(new Rank("T"), Suit.C);
        Card card2 = new Card(new Rank("J"), Suit.H);
        Card card3 = new Card(new Rank("Q"), Suit.S);
        Card card4 = new Card(new Rank("K"), Suit.H);
        Card card5 = new Card(new Rank("A"), Suit.D);
        Card tempArr[] = {card, card2, card3, card4, card5};
        Hand player1 = new Hand(tempArr);
        assertTrue(player1.isStraight());
        assertFalse(player1.isFlush());
    }

    @Test
    public void testFourOfTheKind(){
        Card card = new Card(new Rank("7"), Suit.C);
        Card card2 = new Card(new Rank("7"), Suit.H);
        Card card3 = new Card(new Rank("7"), Suit.S);
        Card card4 = new Card(new Rank("7"), Suit.H);
        Card card5 = new Card(new Rank("A"), Suit.D);
        Card tempArr[] = {card, card2, card3, card4, card5};
        Hand player1 = new Hand(tempArr);
        assertTrue(player1.isFourOfTheKind());
        assertFalse(player1.isThreeOfTheKind());
    }

    @Test
    public void testFullHouse(){
        Card card = new Card(new Rank("7"), Suit.C);
        Card card2 = new Card(new Rank("7"), Suit.H);
        Card card3 = new Card(new Rank("T"), Suit.S);
        Card card4 = new Card(new Rank("T"), Suit.H);
        Card card5 = new Card(new Rank("T"), Suit.D);
        Card tempArr[] = {card, card2, card3, card4, card5};
        Hand player1 = new Hand(tempArr);
        assertTrue(player1.isFullHouse());
        assertFalse(player1.isPair());
    }

    @Test
    public void testThreeOfTheKind(){
        Card card = new Card(new Rank("7"), Suit.C);
        Card card2 = new Card(new Rank("7"), Suit.S);
        Card card3 = new Card(new Rank("7"), Suit.S);
        Card card4 = new Card(new Rank("T"), Suit.H);
        Card card5 = new Card(new Rank("A"), Suit.D);
        Card tempArr[] = {card, card2, card3, card4, card5};
        Hand player1 = new Hand(tempArr);
        assertTrue(player1.isThreeOfTheKind());
        assertFalse(player1.isFourOfTheKind());
    }

    @Test
    public void testTwoPair(){
        Card card = new Card(new Rank("7"), Suit.C);
        Card card2 = new Card(new Rank("7"), Suit.H);
        Card card3 = new Card(new Rank("2"), Suit.C);
        Card card4 = new Card(new Rank("A"), Suit.H);
        Card card5 = new Card(new Rank("A"), Suit.D);
        Card tempArr[] = {card, card2, card3, card4, card5};
        Hand player1 = new Hand(tempArr);
        assertTrue(player1.isTwoPairs());
        assertFalse(player1.isPair());
    }

    @Test
    public void testOnePair(){
        Card card = new Card(new Rank("7"), Suit.C);
        Card card2 = new Card(new Rank("7"), Suit.H);
        Card card3 = new Card(new Rank("J"), Suit.S);
        Card card4 = new Card(new Rank("T"), Suit.D);
        Card card5 = new Card(new Rank("A"), Suit.D);
        Card tempArr[] = {card, card2, card3, card4, card5};
        Hand player1 = new Hand(tempArr);
        assertTrue(player1.isPair());
        assertFalse(player1.isTwoPairs());
    }

    @Test
    public void testSortTest(){
        Card card = new Card(new Rank("7"), Suit.C);
        Card card2 = new Card(new Rank("2"), Suit.H);
        Card card3 = new Card(new Rank("J"), Suit.S);
        Card card4 = new Card(new Rank("9"), Suit.H);
        Card card5 = new Card(new Rank("A"), Suit.D);
        Card tempArr[] = {card, card2, card3, card4, card5};
        Hand player1 = new Hand(tempArr);
        player1.sortTheHand();

        Card card11 = new Card(new Rank("2"), Suit.H);
        Card card22 = new Card(new Rank("7"), Suit.C);
        Card card33 = new Card(new Rank("9"), Suit.H);
        Card card44 = new Card(new Rank("J"), Suit.S);
        Card card55 = new Card(new Rank("A"), Suit.D);
        Card tempArr2[] = {card11, card22, card33, card44, card55};
        Hand expectedHand = new Hand(tempArr2);
        assertEquals(player1.toString(),expectedHand.toString());
    }
}

package pl.edu.agh.kis.pz1;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

public class HandTest extends TestCase {

    public void testPair() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.TWO));
        hand.getCards().add(new Card(Card.Suit.TREFL, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.TWO));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.SEVEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));

        assertEquals(Card.Rank.TWO, hand.pair());
    }

    public void testThree() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.TREFL, Card.Rank.SEVEN));
        hand.getCards().add(new Card(Card.Suit.KARO, Card.Rank.TEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.TEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.TEN));

        assertEquals(Card.Rank.TEN, hand.three());
    }

    public void testFull() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.TREFL, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.KARO, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.TEN));
        hand.getCards().add(new Card(Card.Suit.TREFL, Card.Rank.TEN));

        assertEquals(new ArrayList<>(Arrays.asList(Card.Rank.TEN, Card.Rank.EIGHT)), hand.full());
    }

    public void testTwoPairs() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.TREFL, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.TWO));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));

        assertEquals(new ArrayList<>(Arrays.asList(Card.Rank.EIGHT, Card.Rank.NINE)), hand.twoPairs());
    }

    public void testHelpPairs() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.TREFL, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));

        assertEquals(new ArrayList<>(Arrays.asList(Card.Rank.EIGHT, Card.Rank.NINE)), hand.helpPairs());
    }

    public void testHelpKolor() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.TWO));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));

        assertEquals(Card.Suit.PIK, hand.helpKolor());
    }

    public void testKareta() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.TREFL, Card.Rank.NINE));
        hand.getCards().add(new Card(Card.Suit.KARO, Card.Rank.NINE));
        hand.getCards().add(new Card(Card.Suit.KIER, Card.Rank.NINE));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.SEVEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));

        assertEquals(Card.Rank.NINE, hand.kareta());
    }

    public void testHighestRank() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.TREFL, Card.Rank.NINE));
        hand.getCards().add(new Card(Card.Suit.KARO, Card.Rank.KING));
        hand.getCards().add(new Card(Card.Suit.KIER, Card.Rank.NINE));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.TEN));

        assertEquals(Card.Rank.KING, hand.highestRank());
    }

    public void testHelpStreet() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.QUEEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.KING));
        hand.getCards().add(new Card(Card.Suit.KIER, Card.Rank.TEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.JACK));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));

        assertEquals(Card.Rank.KING, hand.helpStreet());
    }

    public void testContainByRank() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.TREFL, Card.Rank.QUEEN));
        hand.getCards().add(new Card(Card.Suit.KARO, Card.Rank.KING));
        hand.getCards().add(new Card(Card.Suit.KIER, Card.Rank.TEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.EIGHT));

        assertTrue(hand.containByRank(Card.Rank.EIGHT));
    }

    public void testStreet() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.QUEEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.KING));
        hand.getCards().add(new Card(Card.Suit.KIER, Card.Rank.TEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.JACK));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));

        assertEquals(Card.Rank.KING, hand.street());
    }

    public void testKolor() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.TWO));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));

        assertEquals(Card.Suit.PIK, hand.kolor());
    }

    public void testPoker() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.QUEEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.TEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.JACK));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));

        assertEquals(Card.Rank.QUEEN, hand.poker());
    }

    public void testPokerKrolewski() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.QUEEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.TEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.KING));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.JACK));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.ACE));

        assertEquals(Card.Rank.ACE, hand.pokerKrolewski());
    }

    public void testHelpThree() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.QUEEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.QUEEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.ACE));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.ACE));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.ACE));

        assertEquals(Card.Rank.ACE, hand.helpThree());
    }

    public void testBestFigure() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.QUEEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.QUEEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.ACE));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.ACE));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.ACE));

        assertEquals("6 [QUEEN, ACE] ACE", hand.bestFigure());
    }

    public void testTestBestFigure() {
        Hand hand = new Hand();
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.QUEEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.QUEEN));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.ACE));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.ACE));
        hand.getCards().add(new Card(Card.Suit.PIK, Card.Rank.ACE));

        assertEquals("6 [QUEEN, ACE] ACE", hand.bestFigure());
    }
}
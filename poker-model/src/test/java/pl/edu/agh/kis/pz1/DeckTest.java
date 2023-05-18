package pl.edu.agh.kis.pz1;

import junit.framework.TestCase;

public class DeckTest extends TestCase {

    public void testFabryki() {
        Deck deck = new Deck();
        deck.fabryki();
        assertEquals(new Card(Card.Suit.KARO, Card.Rank.ACE), deck.takeACard());
        assertEquals(new Card(Card.Suit.KARO, Card.Rank.KING), deck.takeACard());
        assertEquals(new Card(Card.Suit.KARO, Card.Rank.QUEEN), deck.takeACard());
        assertEquals(new Card(Card.Suit.KARO, Card.Rank.JACK), deck.takeACard());
        assertEquals(new Card(Card.Suit.KARO, Card.Rank.TEN), deck.takeACard());

    }

    public void testShuffle() {
        Deck deck = new Deck();
        deck.shuffle();
        assertEquals(52,deck.getCards().size());
    }

    public void testTakeACard() {
        Deck deck = new Deck();
        deck.fabryki();
        assertEquals(new Card(Card.Suit.KARO, Card.Rank.ACE), deck.takeACard());
    }
}
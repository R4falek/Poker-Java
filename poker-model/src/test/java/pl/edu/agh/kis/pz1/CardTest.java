package pl.edu.agh.kis.pz1;

import junit.framework.TestCase;

public class CardTest extends TestCase {

    public void testCompare() {
        Card card = new Card(Card.Suit.PIK, Card.Rank.EIGHT);
        Card card2 = new Card(Card.Suit.PIK, Card.Rank.SEVEN);

        assertEquals(1, card.compare(card2));
    }
}
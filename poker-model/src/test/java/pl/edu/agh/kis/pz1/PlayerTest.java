package pl.edu.agh.kis.pz1;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {

    public void testSwapCards() {
        Player player = new Player(100);
        Player player2 = new Player(100);
        Deck deck = new Deck();
        deck.fabryki();
        player.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        player.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.SEVEN));
        player.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.SIX));
        player.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.FIVE));
        player.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.FOUR));
        player2.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        player2.getHand().addCard(new Card(Card.Suit.KARO, Card.Rank.ACE));
        player2.getHand().addCard(new Card(Card.Suit.KARO, Card.Rank.KING));
        player2.getHand().addCard(new Card(Card.Suit.KARO, Card.Rank.QUEEN));
        player2.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.FOUR));
        player.swapCards(deck, "123");

        assertEquals(player2.getHand().getCards(), player.getHand().getCards());
    }

    public void testPass() {
        Player player = new Player(0);
        player.pass();
        assertTrue(player.getPass());
    }

    public void testAddCard() {
        Player player = new Player(0);
        Deck deck = new Deck();
        deck.fabryki();
        player.addCard(deck);
        assertEquals(new Card(Card.Suit.KARO, Card.Rank.ACE), player.getHand().getCards().get(0));
    }

    public void testBet() {
        Player player = new Player(100);
        player.bet(10);
        assertEquals(10,player.getBet());
        assertEquals(90, player.getMoney());
    }

    public void testAddReward() {
        Player player = new Player(100);
        player.addReward(1);
        assertEquals(101,player.getMoney());
    }

    public void testReset() {
        Player player = new Player(100);
        player.reset();
        assertEquals(0, player.getBet());
        assertFalse(player.getPass());
    }

    public void testGetPass() {
        Player player = new Player(100);
        player.pass();
        assertTrue(player.getPass());
    }

    public void testGetMoney() {
        Player player = new Player(100);
        assertEquals(100,player.getMoney());
    }

    public void testGetBet() {
        Player player = new Player(100);
        player.bet(1);
        assertEquals(1, player.getBet());
    }
}
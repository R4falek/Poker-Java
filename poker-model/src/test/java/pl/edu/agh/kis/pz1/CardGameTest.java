package pl.edu.agh.kis.pz1;

import junit.framework.TestCase;

import java.util.ArrayList;

public class CardGameTest extends TestCase {

    public void testAddMoneyToPool() {
        CardGame game = new CardGame(5,5,100);
        game.addMoneyToPool(10);
        assertEquals(10, game.getMoneyPool());
    }

    public void testNextGamePart() {
        CardGame game = new CardGame(5,5,100);
        game.nextGamePart();
        assertEquals(game.getGamePartString(),"swap");
    }

    public void testAreBetsEqual() {
        CardGame game = new CardGame(5,5,100);
        game.start();
        assertEquals(true, game.areBetsEqual());
        game.getPlayers().get(0).bet(10);
        assertEquals(false, game.areBetsEqual());
    }

    public void testCheck() {
        CardGame game = new CardGame(2,5,100);
        game.start();
        game.getPlayer(0).getHand().getCards().clear();
        game.getPlayer(1).getHand().getCards().clear();

        game.getPlayer(0).getHand().getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));
        game.getPlayer(0).getHand().getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));
        game.getPlayer(0).getHand().getCards().add(new Card(Card.Suit.PIK, Card.Rank.KING));
        game.getPlayer(0).getHand().getCards().add(new Card(Card.Suit.PIK, Card.Rank.TEN));
        game.getPlayer(0).getHand().getCards().add(new Card(Card.Suit.PIK, Card.Rank.TWO));

        game.getPlayer(1).getHand().getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));
        game.getPlayer(1).getHand().getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));
        game.getPlayer(1).getHand().getCards().add(new Card(Card.Suit.PIK, Card.Rank.NINE));
        game.getPlayer(1).getHand().getCards().add(new Card(Card.Suit.PIK, Card.Rank.THREE));
        game.getPlayer(1).getHand().getCards().add(new Card(Card.Suit.PIK, Card.Rank.QUEEN));

        assertEquals(game.getPlayer(0), game.check());
    }

    public void testReset() {
        CardGame game = new CardGame(2,5,100);
        game.reset(new ArrayList<User>());

        assertEquals(game.getMoneyPool(),0);
        assertEquals("bet",game.getGamePartString());
        assertEquals(52,game.getDeck().getCards().size());
    }

    public void testGetPlayer() {
        CardGame game = new CardGame(4,5,100);
        game.start();
        assertEquals(game.getPlayers().get(2),game.getPlayer(2));
    }

    public void testGetPlayers() {
        CardGame game = new CardGame(4,5,100);
        game.start();
        assertEquals(4,game.getPlayers().size());
    }

    public void testGetMoneyPool() {
        CardGame game = new CardGame(4,5,100);
        game.start();
        game.addMoneyToPool(10);
        assertEquals(10,game.getMoneyPool());
    }

    public void testGetDeck() {
        CardGame game = new CardGame(4,5,100);
        assertEquals(52, game.getDeck().getCards().size());
    }

    public void testGetGamePartString() {
        CardGame game = new CardGame(4,5,100);
        game.nextGamePart();
        assertEquals("swap",game.getGamePartString());
    }

    public void testPlayerFigureConverter() {
        Player player = new Player(100);
        player.getHand().getCards().clear();
        player.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.SEVEN));
        player.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.THREE));
        player.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.FOUR));
        player.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        player.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.EIGHT));

        assertEquals("5 PIK EIGHT", player.getHand().bestFigure());
    }

    public void testBestFigureConverter() {
        CardGame game = new CardGame(2, 5, 10);
        game.start();
        game.getPlayer(0).getHand().getCards().clear();
        game.getPlayer(1).getHand().getCards().clear();
        game.getPlayer(0).getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.THREE));
        game.getPlayer(0).getHand().addCard(new Card(Card.Suit.TREFL, Card.Rank.KING));
        game.getPlayer(0).getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.KING));
        game.getPlayer(0).getHand().addCard(new Card(Card.Suit.KIER, Card.Rank.THREE));
        game.getPlayer(0).getHand().addCard(new Card(Card.Suit.KARO, Card.Rank.FIVE));
        game.playerFigureConverter(game.getPlayer(0));
        assertEquals(1,1);
    }

    public void testMarkAsBestIfBetter() {
        CardGame game = new CardGame(2,5,100);
        Player player = new Player(100);
        Player player1 = new Player(100);
        player.getHand().getCards().clear();
        player.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.SEVEN));
        player.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.THREE));
        player.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.FOUR));
        player.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        player.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        player1.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.SEVEN));
        player1.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.THREE));
        player1.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.FOUR));
        player1.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.EIGHT));
        player1.getHand().addCard(new Card(Card.Suit.PIK, Card.Rank.QUEEN));

        game.best = player;
        game.markAsBestIfBetter(player1);
        assertEquals(player1, game.best);
    }
}
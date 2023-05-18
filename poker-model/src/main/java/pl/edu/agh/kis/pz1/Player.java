package pl.edu.agh.kis.pz1;

/**
 * The PLayer class implements a player in poker game
 *
 * @author  Rafał
 * @version 1.0
 */
public class Player {
    /**
     * parameters
     */
    private int money;
    private final Hand hand;
    private int bet = 0;
    private boolean pass = false;

    /**
     * constructor
     * @param m - money for player on start
     */
    public Player(int m){
        this.money = m;
        hand = new Hand();
    }

    /**
     * returns if player passed
     * @return boolean
     */
    public boolean getPass(){return pass;}

    /**
     * mark player that hr/she passed
     */
    public void pass(){pass = true;}

    /**
     * returns card in string format
     * @return String
     */
    public String getCardsString(){ return hand.getCards().get(0).to_string() + '\n' + hand.getCards().get(1).to_string() + '\n' + hand.getCards().get(2).to_string()
            + '\n' + hand.getCards().get(3).to_string() + '\n' + hand.getCards().get(4).to_string(); }

    /**
     * returns player's money
     * @return int
     */
    public int getMoney(){return money; }

    /**
     * add card to player's hand from deck
     */
    public void addCard(Deck deck){ this.hand.getCards().add(deck.takeACard()); }

    /**
     * removes chosen cards in param c and adds new ones
     * @param deck - deck from thake new cards
     * @param c - string of cards to swap
     */
    public void swapCards(Deck deck, String c){
        for(int i = 0 ; i < c.length() ; i++){
            this.hand.getCards().remove(c.charAt(i) - 48);
            this.hand.getCards().add(c.charAt(i) - 48, deck.takeACard());
        }
    }

    /**
     * pleyer bets money
     * @param m - money to bet
     */
    public void bet(int m){
        bet += m;
        money -= m;
    }

    /**
     * returns how much player bet
     * @return int
     */
    public int getBet(){return bet;}

    /**
     * adds reward to player's moneu
     * @param m - money to add (reward)
     */
    public void addReward(int m){
        money += m;
    }

    /**
     * returns player's hand
     * @return Hand
     */
    public Hand getHand(){return hand;}

    /**
     * resets player parameters after round
     */
    public void reset(){
        bet = 0;
        pass = false;
    }
}

package pl.edu.agh.kis.pz1;

import java.util.ArrayList;

/**
 * The CardGame class implements a poker game
 *
 * @author  Rafał
 * @version 1.0
 */
public class CardGame {
    /**
     * parameters
     */
    private final ArrayList<Player> players;
    private final int numberOfPlayers;
    private final int cardsPerPlayer;
    private final int moneyPerPlayer;
    private Deck deck;
    private int moneyPool;
    private int gamePart;
    private int bestScore;
    private int bestCardFigure;
    private int bestHighestRank;
    private int pScore;
    private int pCardFigure;
    private int pHighestRank;
    Player best;

    /**
     * constructor
     */
    public CardGame(int p, int cpp, int mpp){
        this.players = new ArrayList<>();
        this.numberOfPlayers = p;
        this.cardsPerPlayer = cpp;
        this.moneyPerPlayer = mpp;
        this.deck = new Deck();
        this.deck.shuffle();
        this.moneyPool = 0;
        this.gamePart = 0;
    }

    /**
     * initializes players and give theme cards
     */
    public void start() {
        for (int i = 0; i < this.numberOfPlayers; i++) {
            Player player = new Player(moneyPerPlayer);
            for(int j = 0; j < cardsPerPlayer; j++)
                player.addCard(this.deck);
            this.players.add(player);
        }
    }

    /**
     * returns player by index
     * @param i - index of player
     * @return Player
     */
    public Player getPlayer(int i){
        return players.get(i);
    }

    /**
     * returns all players
     * @return ArrayList<Player>
     */
    public ArrayList<Player> getPlayers(){ return players; }

    /**
     * returns money pool
     * @return int
     */
    public int getMoneyPool(){return moneyPool;}

    /**
     * add money to money pooll
     * @param m - money to add
     */
    public void addMoneyToPool(int m){ moneyPool += m; }

    /**
     * returns the deck
     * @return Deck
     */
    public Deck getDeck(){return deck; }

    /**
     * returns the game part
     * @return String
     */
    public String getGamePartString(){
        if(gamePart == 0 || gamePart == 2){ return "bet";}
        if(gamePart == 1){ return "swap";}
        if(gamePart == 3){ return "check";}
        if(gamePart == 4){ return "finish";}
        return null;
    }

    /**
     * sets game part to check
     */
    public void setGamePartToCheck(){ gamePart = 3;}

    /**
     * next game part
     */
    public void nextGamePart(){ if(gamePart < 4) gamePart++; }

    /**
     * checks if all bets are equal
     * returns true if bets are equal, else returns false
     * @return boolean
     */
    public boolean areBetsEqual(){
        int bets = -1;
        for(Player p : players){
            if(!p.getPass()){
                bets = p.getBet();
            }
        }
        if(bets == -1){
            return true;
        }
        for(Player p : players){
            if(!p.getPass() && p.getBet() != bets){
                return false;
            }
        }
        return true;
    }

    /**
     * sets current player's card figure
     * @param p - current player
     */
    public void playerFigureConverter(Player p){
        pScore = p.getHand().bestFigure().charAt(0) - 48;
        pCardFigure = -1;
        pHighestRank = -1;
        if(pScore != 2 && pScore != 5){
            String card = p.getHand().bestFigure().substring(2,p.getHand().bestFigure().indexOf(' ',2));
            pCardFigure = Card.Rank.valueOf(card).ordinal() + 2;
            card = p.getHand().bestFigure().substring(p.getHand().bestFigure().indexOf(' ',2) + 1);
            pHighestRank = Card.Rank.valueOf(card).ordinal() + 2;
        }
        if(pScore == 2){
            String s = p.getHand().bestFigure().substring(2);
            s = s.replaceAll("[\\[\\],]","");
            String tmp = s.substring(0, s.indexOf(' '));
            pCardFigure = Card.Rank.valueOf(tmp).ordinal() + 2;
            s = s.substring(s.indexOf(' ') + 1);
            tmp = s.substring(0, s.indexOf(' '));
            pCardFigure += Card.Rank.valueOf(tmp).ordinal() + 2;
            s = s.substring(s.indexOf(' ') + 1);
            pHighestRank = Card.Rank.valueOf(s).ordinal() + 2;
        }
        if(pScore == 5){
            String card = p.getHand().bestFigure().substring(2,p.getHand().bestFigure().indexOf(' ',2));
            pCardFigure = Card.Suit.valueOf(card).ordinal() + 1;
            card = p.getHand().bestFigure().substring(p.getHand().bestFigure().indexOf(' ',2) + 1);
            pHighestRank = Card.Rank.valueOf(card).ordinal() + 2;
        }
    }

    /**
     * sets current best player's card figure
     * @param p - current best player
     */
    public void bestFigureConverter(Player p){
        bestScore = best.getHand().bestFigure().charAt(0) - 48;
        bestCardFigure = -1;
        bestHighestRank = -1;
        if(bestScore != 2 && bestScore != 5){
            String card = best.getHand().bestFigure().substring(2,best.getHand().bestFigure().indexOf(' ',2));
            bestCardFigure = Card.Rank.valueOf(card).ordinal() + 2;
            card = best.getHand().bestFigure().substring(p.getHand().bestFigure().indexOf(' ',2) + 1);
            bestHighestRank = Card.Rank.valueOf(card).ordinal() + 2;
        }
        if(bestScore == 2){
            String s = best.getHand().bestFigure().substring(2);
            s = s.replaceAll("[\\[\\],]","");
            String tmp = s.substring(0, s.indexOf(' '));
            bestCardFigure = Card.Rank.valueOf(tmp).ordinal() + 2;
            s = s.substring(s.indexOf(' ') + 1);
            tmp = s.substring(0, s.indexOf(' '));
            bestCardFigure += Card.Rank.valueOf(tmp).ordinal() + 2;
            s = s.substring(s.indexOf(' ') + 1);
            bestHighestRank = Card.Rank.valueOf(s).ordinal() + 2;
        }
        if(bestScore == 5){
            String card = p.getHand().bestFigure().substring(2,p.getHand().bestFigure().indexOf(' ',2));
            bestCardFigure = Card.Suit.valueOf(card).ordinal() + 1;
            card = p.getHand().bestFigure().substring(p.getHand().bestFigure().indexOf(' ',2) + 1);
            bestHighestRank = Card.Rank.valueOf(card).ordinal() + 2;
        }
    }

    /**
     * sets current player's card figure
     * @param p - current player
     */
    public void markAsBestIfBetter(Player p){
        if(!p.getPass()){
            playerFigureConverter(p);
            if(bestScore < pScore){
                best = p;
            }
            if(bestScore == pScore){
                if(bestCardFigure < pCardFigure){
                    best = p;
                }
                if(bestCardFigure == pCardFigure && bestHighestRank < pHighestRank){
                    best = p;
                }
            }
        }
    }

    /**
     * checks which player has the best cards
     */
    public Player check(){
        best = null;
        for(Player p : players){
            if(!p.getPass()){
                best = p;
            }
        }
        if(best == null){
            return null;
        }
        for(Player p : players){
            bestFigureConverter(best);
            markAsBestIfBetter(p);
        }
        return best;
    }

    /**
     * resets game before next turn
     * @param users - players in last turn
     */
    public void reset(ArrayList<User> users){
        deck = new Deck();
        deck.shuffle();
        for(User u : users){
            u.reset();
        }
        for(Player p : players){
            p.reset();
            p.getHand().getCards().clear();

            for(int j = 0; j < cardsPerPlayer; j++)
                p.addCard(this.deck);
        }
        moneyPool = 0;
        gamePart = 0;
    }
}

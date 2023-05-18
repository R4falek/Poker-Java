package pl.edu.agh.kis.pz1;

import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * The Deck class implements a deck of cards
 *
 * @author  Rafał
 * @version 1.0
 */
public class Deck {

    /**
     * parameters
     */
    private ArrayList<Card> cards;

    /**
     * constructor
     */
    public Deck(){
        this.cards = new ArrayList<>();

        for(int i = 0 ; i < 4 ; i++)
            for(int j = 0 ; j < 13 ; j++){
                Card card = new Card(Card.Suit.values()[i], Card.Rank.values()[j]);
                cards.add(card);
            }
    }

    /**
     * returns all cards
     * @return ArrayList<Card>
     */
    public ArrayList<Card> getCards(){return cards;}

    /**
     * Layouts deck of cards by rank and suit
     */
    public void fabryki(){
        ArrayList<Card> sortedDeck = new ArrayList<>();

        while(this.cards.size() != 0){
            Card i = this.cards.get(0);
            for(Card c: this.cards)
                if(i.compare(c) < 0)
                    i = c;
            sortedDeck.add(i);
            this.cards.remove(i);
        }
        this.cards = sortedDeck;
    }

    /**
     * shuffles deck of cards
     */
    public void shuffle(){
        ArrayList<Card> shuffledDeck = new ArrayList<>();
        SecureRandom random = new SecureRandom();

        while(this.cards.size() != 0){
            int i = random.nextInt(this.cards.size());
            shuffledDeck.add(this.cards.get(i));
            this.cards.remove(i);
        }
        this.cards = shuffledDeck;
    }

    /**
     * removes the first card from the deck
     * it's like taking a card from the top of the deck
     */
    public Card takeACard(){
        Card card = this.cards.get(0);
        this.cards.remove(0);
        return card;
    }
}

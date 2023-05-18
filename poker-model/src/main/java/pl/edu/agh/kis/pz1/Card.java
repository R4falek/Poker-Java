package pl.edu.agh.kis.pz1;
import java.util.Objects;

/**
 * The Card class implements a card game object
 *
 * @author  Rafał
 * @version 1.0
 */
public class Card {

    /**
     * suit enum
     */
    public enum Suit{
        PIK(1), TREFL(2), KIER(3), KARO(4);

        private final int value;

        private Suit(int v){
            this.value = v;
        }
    }

    /**
     * rank enum
     */
    public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);

        private int value;

        private Rank(int v) {
            this.value = v;
        }

        /**
         * returns lower card by 1 value
         * @param r - rank of a card
         * @return Rank
         */
        public static Rank getLowerRank(Rank r){
            if(r.equals(Rank.TWO)){
                return null;
            }
            int i = r.ordinal() - 1;
            return Rank.values()[i];
        }
    }

    /**
     * parameters
     */
    private final Suit suit;
    private final Rank rank;

    /**
     * constructor
     * @param s - suit
     * @param r - rank
     */
    public Card(Suit s, Rank r){
        this.suit = s;
        this.rank = r;
    }

    /**
     * returns suit and rank of the card
     * @return String
     */
    public String to_string(){ return this.suit + " "+ this.rank; }

    /**
     * returns the suit of the card
     * @return Suit
     */
    public Suit getSuit(){ return this.suit; }

    /**
     * returns the rank of the card
     * @return Rank
     */
    public Rank getRank(){ return this.rank; }

    /**
     * compares the card by rank and suit
     * returns 1 if another card is higher, -1 if another card is lower, 0 if cards are equal
     * @param another - another card to compare
     * @return int
     */
    public int compare(Card another){
        if(this.suit.compareTo(another.getSuit()) > 0)
            return 1;
        if(this.suit.compareTo(another.getSuit()) < 0)
            return -1;
        if(this.suit.compareTo(another.getSuit()) == 0){
            if(this.rank.compareTo(another.getRank()) > 0)
                return 1;
            if(this.rank.compareTo(another.getRank()) < 0)
                return -1;
        }
        return 0;
    }

    /**
     * overridden equal method
     * @param o - another card to compare
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}

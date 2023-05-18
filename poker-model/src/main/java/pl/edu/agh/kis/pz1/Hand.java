package pl.edu.agh.kis.pz1;

import java.util.ArrayList;

/**
 * The Hand class implements a hand of cards
 *
 * @author  Rafał
 * @version 1.0
 */
public class Hand {

    /**
     * parameters
     */
    private final ArrayList<Card> cards;

    /**
     * constructor
     */
    public Hand(){
        cards = new ArrayList<>();
    }

    /**
     * returns all cards in hand
     * @return ArrayList<Card>
     */
    public ArrayList<Card> getCards(){return cards;}

    /**
     * adds card to hand
     * @param card - card to add
     */
    public void addCard(Card card){
        cards.add(card);
    }

    /**
     * checks if given card is in hand
     * returns true if card is in hand otherwise returns false
     * @param r - card to check
     * @return boolean
     */
    public boolean containByRank(Card.Rank r){
        for(Card c : cards){
            if(r.equals(c.getRank())){
                return true;
            }
        }
        return false;
    }

    /**
     * returns highest rank from cards in hand
     * @return Rank
     */
    public Card.Rank highestRank(){
        Card.Rank rank = null;
        for(Card c : cards){
            if(rank == null || c.getRank().compareTo(rank) > 0){
                rank = c.getRank();
            }
        }
        return rank;
    }

    /**
     * help method to find pairs in hand
     * returns found pairs
     * @return ArrayList<Card.Rank>
     */
    public ArrayList<Card.Rank> helpPairs(){
        ArrayList<Card.Rank> ranks = new ArrayList<>();
        for(Card c: cards){
            for(Card cc: cards){
                if(c != cc && c.getRank().equals(cc.getRank()) && !ranks.contains(c.getRank())){
                    ranks.add(c.getRank());
                }
            }
        }
        return ranks;
    }

    /**
     * returns rank if hand has a pair otherwise returns null
     * @return Rank
     */
    public Card.Rank pair(){
        if(helpPairs().size() == 1)
            return helpPairs().get(0);
        return null;
    }

    /**
     * returns ranks of pairs found in hand
     * returns null is there is no two pairs
     * @return ArrayList<Card.Rank>
     */
    public ArrayList<Card.Rank> twoPairs(){
        if(three() == null && helpPairs().size() == 2)
            return helpPairs();
        return null;
    }

    /**
     * help method to find three-of-kind
     * @param rank - rank to check if there is another card with that rank
     * @param helpRanks - cards to check
     */
    public void threeHelp(Card.Rank rank, ArrayList<Card.Rank> helpRanks){
        for (Card card : cards) {
            if (!card.getRank().equals(rank)) {
                helpRanks.add(card.getRank());
            }
        }
    }

    /**
     * counts how many cards in hand with given rank
     * @param rank - rank to count
     */
    public int threeCounter(Card.Rank rank){
        int i = 0;
        for(Card c : cards){
            if(c.getRank().equals(rank)){
                i++;
            }
        }
        return i;
    }

    /**
     * returns rank if found three-of-kind
     * @return Rank
     */
    public Card.Rank three(){
        Card.Rank rank = null;
        ArrayList<Card.Rank> helpRanks = new ArrayList<>();
        for(int i = 0 ; i < cards.size() ; i++){
            for(int j = 0 ; j < cards.size() ; j++){
                for(int k = 0 ; k < cards.size() ; k++){
                    if(i != j && j != k && k != i && cards.get(i).getRank().equals(cards.get(j).getRank()) && cards.get(j).getRank().equals(cards.get(k).getRank())){
                        rank = cards.get(i).getRank();
                        threeHelp(rank, helpRanks);
                        break;
                    }
                }
            }
        }
        if(threeCounter(rank) == 3 && !helpRanks.get(0).equals(helpRanks.get(1))){
            return rank;
        }
        return null;
    }

    /**
     * help method to find three-of-kind
     */
    public Card.Rank helpThree(){
        Card.Rank rank = null;
        for(int i = 0 ; i < cards.size() ; i++){
            for(int j = 0 ; j < cards.size() ; j++){
                for(int k = 0 ; k < cards.size() ; k++){
                    if(i != j && j != k && k != i && cards.get(i).getRank().equals(cards.get(j).getRank()) && cards.get(j).getRank().equals(cards.get(k).getRank())){
                        rank = cards.get(i).getRank();
                        break;
                    }
                }
            }
        }
        return rank;
    }

    /**
     * help method to find street
     * @return Rank - highest rank if card in order found otherwise null
     */
    public Card.Rank helpStreet(){
        Card.Rank highest = highestRank();
        Card.Rank rank = highest;
        for(int i = 0 ; i < 4 ; i++){
            rank = Card.Rank.getLowerRank(rank);
            if(!containByRank(rank)){
                return null;
            }
        }
        return highest;
    }

    /**
     * returns highest rank if street found otherwise null
     * @return Rank
     */
    public Card.Rank street(){
        if(helpStreet() != null && helpKolor() == null){
            return highestRank();
        }
        return null;
    }

    /**
     * help method to find kolor
     * @return Suit - suit if kolor found
     */
    public Card.Suit helpKolor(){
        Card.Suit suit = cards.get(0).getSuit();
        for(Card c : cards){
            if(!c.getSuit().equals(suit)){
                return null;
            }
        }
        return suit;
    }

    /**
     * returns suit if kolor found otherwise null
     * @return Suit
     */
    public Card.Suit kolor(){
        if(helpKolor() != null && helpStreet() == null){
            return helpKolor();
        }
        return null;
    }

    /**
     * returns rank of pair and three-of-kind otherwise null
     * @return ArrayList<Card.Rank>
     */
    public ArrayList<Card.Rank> full(){
        if(helpThree() != null){
            ArrayList<Card.Rank> ret = new ArrayList<>();
            for(Card c : cards){
                if(!c.getRank().equals(helpThree())){
                    ret.add(c.getRank());
                }
            }
            if(ret.get(0).equals(ret.get(1))){
                ret.remove(1);
                ret.add(helpThree());
                return ret;
            }
        }
        return null;
    }

    /**
     * returns rank if kareta found otherwise null
     * @return Rank
     */
    public Card.Rank kareta(){
        if(helpThree() != null){
            Card.Rank rank = helpThree();
            int i = 0;
            for(Card c : cards){
                if(c.getRank() == rank){
                    i++;
                }
            }
            if(i == 4){
                return rank;
            }
        }
        return null;
    }

    /**
     * returns rank of highest card in hand
     * @return Rank
     */
    public Card.Rank poker(){
        if(helpStreet() != null && helpKolor() != null && highestRank() != Card.Rank.ACE){
            return highestRank();
        }
        return null;
    }

    /**
     * returns rank of highest card in hand
     * @return Rank
     */
    public Card.Rank pokerKrolewski(){
        if(helpStreet() != null && helpKolor() != null && highestRank() == Card.Rank.ACE){
            return highestRank();
        }
        return null;
    }

    /**
     * returns card's score as "figure figureRank higestCardInHand"
     * @return String
     */
    public String bestFigure(){
        if(pokerKrolewski() != null){ return "9 " + pokerKrolewski() + " " + highestRank();}
        if(poker() != null){ return "8 " + poker() + " " + highestRank();}
        if(kareta() != null){ return "7 " + kareta() + " " + highestRank();}
        if(full() != null){ return "6 " + full() + " " + highestRank();}
        if(kolor() != null){return "5 " + kolor() + " " + highestRank();}
        if(street() != null){ return "4 " + street() + " " + highestRank();}
        if(three() != null){return "3 " + three() + " " + highestRank();}
        if(twoPairs() != null){ return "2 " + twoPairs() + " " + highestRank();}
        if(pair() != null){ return "1 " + pair() + " " + highestRank();}
        return "0 " + highestRank() + " " + highestRank();
    }
}

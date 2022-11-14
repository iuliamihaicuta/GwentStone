package table;

import cards.Card;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public void removeFromHand(int cardIndex) {
        hand.remove(cardIndex);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
}

package table;

import cards.Card;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    public static void removeFromHand(int cardIndex) {

    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
}

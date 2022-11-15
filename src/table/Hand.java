package table;

import cards.Card;
import cards.environment.Environment;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public void removeFromHand(int cardIndex) {
        hand.remove(cardIndex);
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    public ArrayList<Card> getEnvironmentCards() {
        ArrayList<Card> environmentCards = new ArrayList<Card>();
        for (Card card : hand) {
            if (Environment.environmentCardList.contains(card.getName()))
                environmentCards.add(card);
        }

        return environmentCards;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
}

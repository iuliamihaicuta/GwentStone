package table;

import cards.Card;
import cards.environment.Environment;

import java.util.ArrayList;

/**
 * The type Hand.
 */
public class Hand {
    private final ArrayList<Card> cards;

    /**
     * Instantiates a new Hand.
     */
    public Hand() {
        cards = new ArrayList<>();
    }

    /**
     * remove card from hand
     *
     * @param cardIndex the card index
     */
    public void removeFromHand(final int cardIndex) {
        cards.remove(cardIndex);
    }

    /**
     * add card to hand
     *
     * @param card the card
     */
    public void addToHand(final Card card) {
        cards.add(card);
    }

    /**
     * return list of current environment cards in hand
     *
     * @return the environment cards
     */
    public ArrayList<Card> getEnvironmentCards() {
        ArrayList<Card> environmentCards = new ArrayList<>();
        for (Card card : cards) {
            if (Environment.environmentCardList.contains(card.getName())) {
                environmentCards.add(card);
            }
        }

        return environmentCards;
    }

    /**
     * return list of current cards in hand
     *
     * @return the cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

}

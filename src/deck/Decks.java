package deck;

import cards.Card;
import fileio.DecksInput;

import java.util.ArrayList;

/**
 * The type Decks.
 */
public class Decks {
    private final int nrCardsInDeck;
    private final int nrDecks;
    private final ArrayList<ArrayList<Card>> decks;

    /**
     * Instantiates a new Decks.
     *
     * @param decksInput the decks input
     */
    public Decks(final DecksInput decksInput) {
        this.decks = new ArrayList<>();

        for (int i = 0; i < decksInput.getNrDecks(); ++i) {
            decks.add(new ArrayList<>());
            for (int j = 0; j < decksInput.getDecks().get(i).size(); ++j) {
                decks.get(i).add(new Card(decksInput.getDecks().get(i).get(j)));
            }
        }

        this.nrCardsInDeck = decksInput.getNrCardsInDeck();
        this.nrDecks = decksInput.getNrDecks();
    }

    /**
     * get number of cards in deck
     *
     * @return the nr cards in deck
     */
    public int getNrCardsInDeck() {
        return nrCardsInDeck;
    }

    /**
     * get number of decks
     *
     * @return the nr decks
     */
    public int getNrDecks() {
        return nrDecks;
    }

    /**
     * get player's decks
     *
     * @return the decks
     */
    public ArrayList<ArrayList<Card>> getDecks() {
        return decks;
    }

    /**
     * override toString() method
     */
    @Override
    public String toString() {
        return "InfoInput{"
                + "nr_cards_in_deck="
                + nrCardsInDeck
                +  ", nr_decks="
                + nrDecks
                + ", decks="
                + decks
                + '}';
    }
}

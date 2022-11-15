package cards.hero;

import cards.Card;
import cards.minion.Minion;
import fileio.CardInput;
import fileio.Coordinates;

import static table.Table.getTable;
import static table.Table.removeFromRow;

/**
 * The type Empress thorina.
 */
public final class EmpressThorina extends Hero {
    /**
     * Instantiates a new Empress thorina.
     *
     * @param card the card
     */
    public EmpressThorina(final Card card) {
        super(card);
    }

    /**
     * Instantiates a new Empress thorina.
     *
     * @param card the card
     */
    public EmpressThorina(final CardInput card) {
        super(card);
    }

    /**
     * set card ability
     */
    @Override
    public void ability(final int row) {
        int cardIndex = 0;
        int maxHealth = 0;

        for (int i = 0; i < getTable().get(row).size(); ++i) {
            Minion card = getTable().get(row).get(i);

            if (card.getHealth() > maxHealth) {
                maxHealth = card.getHealth();
                cardIndex = i;
            }
        }

        removeFromRow(new Coordinates(row, cardIndex));
        setHasAttacked(true);
    }
}

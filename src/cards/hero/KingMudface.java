package cards.hero;

import cards.minion.Minion;
import fileio.CardInput;

import static table.Table.getTable;

/**
 * The type King mudface.
 */
public final class KingMudface extends Hero {
    /**
     * Instantiates a new King mudface.
     *
     * @param card the card
     */
    public KingMudface(final CardInput card) {
        super(card);
    }

    /**
     * set card ability
     */
    @Override
    public void ability(final int row) {
        for (Minion card : getTable().get(row)) {
            card.setHealth(card.getHealth() + 1);
        }

        setHasAttacked(true);
    }
}

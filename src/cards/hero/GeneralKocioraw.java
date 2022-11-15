package cards.hero;

import cards.Card;
import cards.minion.Minion;
import fileio.CardInput;

import static table.Table.getTable;

/**
 * The type General kocioraw.
 */
public final class GeneralKocioraw extends Hero {
    /**
     * Instantiates a new General kocioraw.
     *
     * @param card the card
     */
    public GeneralKocioraw(final Card card) {
        super(card);
    }

    /**
     * Instantiates a new General kocioraw.
     *
     * @param card the card
     */
    public GeneralKocioraw(final CardInput card) {
        super(card);
    }

    /**
     * set card ability
     */
    @Override
    public void ability(final int row) {
        for (Minion card : getTable().get(row)) {
            card.setAttackDamage(card.getAttackDamage() + 1);
        }

        setHasAttacked(true);
    }
}

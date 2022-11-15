package cards.minion;

import cards.Card;
import fileio.Coordinates;

import static table.Table.getTable;

/**
 * The type The ripper.
 */
public final class TheRipper extends Minion implements SpecialAbility {
    /**
     * Instantiates a new The ripper.
     *
     * @param card the card
     */
    public TheRipper(final Card card) {
        super(card);
    }

    /**
     * set card ability
     */
    @Override
    public void ability(final int indexPlayer, final Coordinates cardAttacked) {
        Minion card = getTable().get(cardAttacked.getX()).get(cardAttacked.getY());
        card.setAttackDamage(card.getAttackDamage() - 2);

        if (card.getAttackDamage() <= 0) {
            card.setAttackDamage(0);
        }

        setHasAttacked(true);
    }
}

package cards.minion;

import cards.Card;
import fileio.Coordinates;

import static table.Table.getTable;
import static table.Table.removeFromRow;

/**
 * The type The cursed one.
 */
public final class TheCursedOne extends Minion implements SpecialAbility {
    /**
     * Instantiates a new The cursed one.
     *
     * @param card the card
     */
    public TheCursedOne(final Card card) {
        super(card);
    }

    /**
     * set card ability
     */
    @Override
    public void ability(final int indexPlayer, final Coordinates cardAttacked) {
        Minion card = getTable().get(cardAttacked.getX()).get(cardAttacked.getY());
        int temp = card.getHealth();
        card.setHealth(card.getAttackDamage());
        card.setAttackDamage(temp);

        if (card.getHealth() <= 0) {
            removeFromRow(cardAttacked);
        }

        setHasAttacked(true);
    }
}

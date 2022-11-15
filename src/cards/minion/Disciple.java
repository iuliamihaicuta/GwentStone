package cards.minion;

import cards.Card;
import fileio.Coordinates;

import static table.Table.getTable;

/**
 * The type Disciple.
 */
public final class Disciple extends Minion implements SpecialAbility {
    /**
     * Instantiates a new Disciple.
     *
     * @param card the card
     */
    public Disciple(final Card card) {
        super(card);
    }

    /**
     * set card ability
     */
    @Override
    public void ability(final int indexPlayer, final Coordinates cardAttacked) {
        Minion card = getTable().get(cardAttacked.getX()).get(cardAttacked.getY());
        card.setHealth(card.getHealth() + 2);

        setHasAttacked(true);
    }
}

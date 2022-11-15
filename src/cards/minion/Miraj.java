package cards.minion;

import cards.Card;
import fileio.Coordinates;

import static table.Table.getTable;

/**
 * The type Miraj.
 */
public final class Miraj extends Minion implements SpecialAbility {

    /**
     * Instantiates a new Miraj.
     *
     * @param card the card
     */
    public Miraj(final Card card) {
        super(card);
    }

    /**
     * set card ability
     */
    @Override
    public void ability(final int indexPlayer, final Coordinates cardAttacked) {
        Minion card = getTable().get(cardAttacked.getX()).get(cardAttacked.getY());
        int temp = this.getHealth();
        this.setHealth(card.getHealth());
        card.setHealth(temp);

        setHasAttacked(true);
    }
}

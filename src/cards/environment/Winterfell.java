package cards.environment;

import cards.Card;
import cards.minion.Minion;
import player.Player;

import static table.Table.getTable;

/**
 * The type Winterfell.
 */
public final class Winterfell extends Environment {
    /**
     * Instantiates a new Winterfell.
     *
     * @param card the card
     */
    public Winterfell(final Card card) {
        super(card);
    }

    /**
     * set card ability
     */
    @Override
    public void ability(final Player player, final int row) {
        for (Minion card : getTable().get(row)) {
            card.setFrozen(true);
        }
    }
}

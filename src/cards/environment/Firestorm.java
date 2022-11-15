package cards.environment;

import cards.Card;
import cards.minion.Minion;
import player.Player;

import java.util.ArrayList;

import static table.Table.getTable;

/**
 * The type Firestorm.
 */
public final class Firestorm extends Environment {

    /**
     * Instantiates a new Firestorm.
     *
     * @param card the card
     */
    public Firestorm(final Card card) {
        super(card);
    }

    /**
     * set card ability
     */
    @Override
    public void ability(final Player player, final int row) {
        ArrayList<Minion> copyList = new ArrayList<>(getTable().get(row));

        for (Minion card : copyList) {
            card.setHealth(card.getHealth() - 1);

            if (card.getHealth() <= 0) {
                getTable().get(row).remove(card);
            }
        }
    }
}

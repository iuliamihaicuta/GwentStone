package cards.environment;

import cards.Card;
import cards.minion.Minion;
import fileio.Coordinates;
import player.Player;

import static constants.Constants.NUMBER_OF_ROWS;
import static table.Table.getTable;
import static table.Table.addToRow;
import static table.Table.removeFromRow;

/**
 * The type Heart hound.
 */
public final class HeartHound extends Environment {
    /**
     * Instantiates a new Heart hound.
     *
     * @param card the card
     */
    public HeartHound(final Card card) {
        super(card);
    }

    /**
     * set card ability
     */
    @Override
    public void ability(final Player player, final int row) {
        int myRow = NUMBER_OF_ROWS - row - 1;

        int maxHealth = 0;
        int cardIndex = 0;

        for (int i = 0; i < getTable().get(row).size(); ++i) {
            Minion card = getTable().get(row).get(i);

            if (card.getHealth() > maxHealth) {
                maxHealth = card.getHealth();
                cardIndex = i;
            }
        }

        addToRow(myRow, getTable().get(row).get(cardIndex));
        removeFromRow(new Coordinates(row, cardIndex));
    }
}

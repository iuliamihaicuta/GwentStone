package cards.environment;

import cards.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;
import player.Player;

import java.util.List;

import static constants.Constants.MAX_LENGTH_OF_ROW;
import static constants.Constants.NUMBER_OF_ROWS;
import static table.Table.getTable;

/**
 * The type Environment.
 */
public class Environment extends Card {
    @JsonIgnore
    private int attackDamage;
    @JsonIgnore
    private int health;

    /**
     * The constant environmentCardList.
     */
    public static final List<String> environmentCardList = List.of("Firestorm", "Winterfell",
            "Heart Hound");

    /**
     * Instantiates a new Environment.
     *
     * @param card the card
     */
    public Environment(final CardInput card) {
        super(card);
    }

    /**
     * Instantiates a new Environment.
     *
     * @param card the card
     */
    public Environment(final Card card) {
        super(card);
    }

    /**
     * check if attack row belongs to enemy
     *
     * @param indexPlayer the index player
     * @param indexRow    the index row
     * @return the boolean
     */
    public boolean isEnemyRow(final int indexPlayer, final int indexRow) {
        return indexPlayer == 1 && indexRow < 2 || indexPlayer == 2 && indexRow > 1;
    }

    /**
     * check if card can use ability
     *
     * @param player   the player
     * @param rowIndex the row index
     * @return the string
     */
    public String canUseAbility(final Player player, final int rowIndex) {
        String error = null;

        if (getMana() > player.getMana()) {
            error = "Not enough mana to use environment card.";
        } else if (getName().equals("Heart Hound")
                && getTable().get(NUMBER_OF_ROWS - rowIndex - 1).size() == MAX_LENGTH_OF_ROW) {
            error = "Cannot steal enemy card since the player's row is full.";
        } else if (!isEnemyRow(player.getId(), rowIndex)) {
            error = "Chosen row does not belong to the enemy.";
        }

        return error;
    }

    /**
     * set card ability
     *
     * @param player the player
     * @param row    the row
     */
    public void ability(final Player player, final int row) {
    }
}

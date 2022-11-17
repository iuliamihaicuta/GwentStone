package cards.minion;

import cards.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.Coordinates;

import static constants.Constants.PLAYER1_FIRST_ROW;
import static constants.Constants.PLAYER2_FIRST_ROW;
import static table.Table.enemyHasTankCards;
import static table.Table.getTable;

/**
 * The type Minion.
 */
public class Minion extends Card {

    @JsonIgnore
    private final boolean tank;
    @JsonIgnore
    private boolean frozen = false;
    @JsonIgnore
    private final boolean onTheFrontRow;
    @JsonIgnore
    private boolean hasAttacked = false;

    /**
     * Instantiates a new Minion.
     *
     * @param card the card
     */
    public Minion(final Card card) {
        super(card);
        tank = getName().equals("Goliath") || getName().equals("Warden");
        onTheFrontRow = getName().equals("The Ripper") || getName().equals("Miraj")
                || getName().equals("Goliath") || getName().equals("Warden");
    }

    /**
     * Instantiates a new Minion.
     *
     * @param card the card
     */
    public Minion(final Minion card) {
        super(card);
        this.tank = card.tank;
        this.frozen = card.frozen;
        this.onTheFrontRow = card.onTheFrontRow;
        this.hasAttacked = card.hasAttacked;
    }

    /**
     * check if card can use ability
     *
     * @param indexPlayer  the index player
     * @param cardAttacked the card attacked
     * @return the string
     */
    public String canUseAbility(final int indexPlayer, final Coordinates cardAttacked) {
        int row = cardAttacked.getX();

        if (frozen) {
            return "Attacker card is frozen.";
        } else if (hasAttacked) {
            return  "Attacker card has already attacked this turn.";
        } else if ((getName().equals("Disciple")
                && (indexPlayer == 1 && row < PLAYER1_FIRST_ROW
                || indexPlayer == 2 && row > PLAYER2_FIRST_ROW))) {
            return "Attacked card does not belong to the current player.";
        } else if (!getName().equals("Disciple")
                && (indexPlayer == 1 && row > PLAYER2_FIRST_ROW
                || indexPlayer == 2 && row < PLAYER1_FIRST_ROW)) {
            return "Attacked card does not belong to the enemy.";
        } else if (!getName().equals("Disciple")) {
            if (enemyHasTankCards(indexPlayer)
                    && !getTable().get(cardAttacked.getX()).get(cardAttacked.getY()).isTank()) {
                return "Attacked card is not of type 'Tank'.";
            }
        }

        return null;
    }

    /**
     * check if card can attack another minion
     *
     * @param indexPlayer  the index player
     * @param cardAttacked the card attacked
     * @return the string
     */
    public String canAttack(final int indexPlayer, final Coordinates cardAttacked) {
        int row = cardAttacked.getX();

        if ((indexPlayer == 1 && row > PLAYER2_FIRST_ROW
                || indexPlayer == 2 && row < PLAYER1_FIRST_ROW)) {
            return "Attacked card does not belong to the enemy.";
        } else if (hasAttacked) {
            return "Attacker card has already attacked this turn.";
        } else if (frozen) {
            return "Attacker card is frozen.";
        } else {
            if (enemyHasTankCards(indexPlayer)
                    && !getTable().get(cardAttacked.getX()).get(cardAttacked.getY()).isTank()) {
                return "Attacked card is not of type 'Tank'.";
            }
        }

        return null;
    }

    /**
     * check if card can attack hero
     *
     * @param indexPlayer the index player
     * @return the string
     */
    public String canAttackHero(final int indexPlayer) {
        if (frozen) {
            return "Attacker card is frozen.";
        } else if (hasAttacked) {
            return "Attacker card has already attacked this turn.";
        } else {
            if (enemyHasTankCards(indexPlayer)) {
                return "Attacked card is not of type 'Tank'.";
            }
        }

        return null;
    }

    /**
     * get card tank status
     *
     * @return the boolean
     */
    public boolean isTank() {
        return tank;
    }

    /**
     * get card frozen status
     *
     * @return the boolean
     */
    public boolean isFrozen() {
        return frozen;
    }

    /**
     * set card frozen status
     *
     * @param frozen the frozen
     */
    public void setFrozen(final boolean frozen) {
        this.frozen = frozen;
    }

    /**
     * get card isOnTheFrontRow status
     *
     * @return the boolean
     */
    public boolean isOnTheFrontRow() {
        return onTheFrontRow;
    }

    /**
     * set card hasAttacked status
     *
     * @param hasAttacked the has attacked
     */
    public void setHasAttacked(final boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

}

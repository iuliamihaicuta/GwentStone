package cards.minion;

import cards.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.Coordinates;

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
        String error = null;
        int row = cardAttacked.getX();

        if (frozen) {
            error = "Attacker card is frozen.";
        } else if (hasAttacked) {
            error = "Attacker card has already attacked this turn.";
        } else if ((getName().equals("Disciple")
                && (indexPlayer == 1 && row < 2 || indexPlayer == 2 && row > 1))) {
            error = "Attacked card does not belong to the current player.";
        } else if (!getName().equals("Disciple")
                && (indexPlayer == 1 && row > 1 || indexPlayer == 2 && row < 2)) {
            error = "Attacked card does not belong to the enemy.";
        } else if (!getName().equals("Disciple")) {
            if (enemyHasTankCards(indexPlayer)
                    && !getTable().get(cardAttacked.getX()).get(cardAttacked.getY()).isTank()) {
                error = "Attacked card is not of type 'Tank'.";
            }
        }

        return error;
    }

    /**
     * check if card can attack another minion
     *
     * @param indexPlayer  the index player
     * @param cardAttacked the card attacked
     * @return the string
     */
    public String canAttack(final int indexPlayer, final Coordinates cardAttacked) {
        String error  = null;
        int row = cardAttacked.getX();

        if ((indexPlayer == 1 && row > 1 || indexPlayer == 2 && row < 2)) {
            error = "Attacked card does not belong to the enemy.";
        } else if (hasAttacked) {
            error = "Attacker card has already attacked this turn.";
        } else if (frozen) {
            error = "Attacker card is frozen.";
        } else {
            if (enemyHasTankCards(indexPlayer)
                    && !getTable().get(cardAttacked.getX()).get(cardAttacked.getY()).isTank()) {
                error = "Attacked card is not of type 'Tank'.";
            }
        }

        return error;
    }

    /**
     * check if card can attack hero
     *
     * @param indexPlayer the index player
     * @return the string
     */
    public String canAttackHero(final int indexPlayer) {
        String error = null;

        if (frozen) {
            error = "Attacker card is frozen.";
        } else if (hasAttacked) {
            error = "Attacker card has already attacked this turn.";
        } else {
            if (enemyHasTankCards(indexPlayer)) {
                error = "Attacked card is not of type 'Tank'.";
            }
        }

        return error;
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

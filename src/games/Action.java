package games;

import fileio.ActionsInput;
import fileio.Coordinates;


/**
 * The type Action.
 */
public final class Action {
    private final String command;
    private final int handIdx;
    private final Coordinates cardAttacker;
    private final Coordinates cardAttacked;
    private final int affectedRow;
    private final int playerIdx;
    private final int x;
    private final int y;

    /**
     * Instantiates a new Action.
     *
     * @param actionsInput the actions input
     */
    public Action(final ActionsInput actionsInput) {
        this.command = actionsInput.getCommand();
        this.handIdx = actionsInput.getHandIdx();
        this.cardAttacker = actionsInput.getCardAttacker();
        this.cardAttacked = actionsInput.getCardAttacked();
        this.affectedRow = actionsInput.getAffectedRow();
        this.playerIdx = actionsInput.getPlayerIdx();
        this.x = actionsInput.getX();
        this.y = actionsInput.getY();
    }

    /**
     * get command
     *
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * get card in hand index
     *
     * @return the hand idx
     */
    public int getHandIdx() {
        return handIdx;
    }

    /**
     * get attacker card
     *
     * @return the card attacker
     */
    public Coordinates getCardAttacker() {
        return cardAttacker;
    }

    /**
     * get attacked card
     *
     * @return the card attacked
     */
    public Coordinates getCardAttacked() {
        return cardAttacked;
    }

    /**
     * get affected row
     *
     * @return the affected row
     */
    public int getAffectedRow() {
        return affectedRow;
    }

    /**
     * get player index
     *
     * @return the player idx
     */
    public int getPlayerIdx() {
        return playerIdx;
    }

    /**
     * get x coordinate of card
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * get y coordinate of card
     *
     * @return the y
     */
    public int getY() {
        return y;
    }
}

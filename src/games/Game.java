package games;

import fileio.ActionsInput;
import fileio.GameInput;

import java.util.ArrayList;

/**
 * The type Game.
 */
public class Game {
    private StartGame startGame;
    private ArrayList<Action> actions;

    /**
     * Instantiates a new Game.
     *
     * @param game the game
     */
    public Game(final GameInput game) {
        this.startGame = new StartGame(game.getStartGame());

        actions = new ArrayList<>();
        for (ActionsInput actionsInput : game.getActions()) {
            this.actions.add(new Action(actionsInput));
        }
    }

    /**
     * get the parameters of the new game
     *
     * @return the start game
     */
    public StartGame getStartGame() {
        return startGame;
    }

    /**
     * set the parameters of the new game
     *
     * @param startGame the start game
     */
    public void setStartGame(final StartGame startGame) {
        this.startGame = startGame;
    }

    /**
     * get the action list of the new game
     *
     * @return the actions
     */
    public ArrayList<Action> getActions() {
        return actions;
    }

    /**
     * set the action list of the new game
     *
     * @param actions the actions
     */
    public void setActions(final ArrayList<Action> actions) {
        this.actions = actions;
    }

}

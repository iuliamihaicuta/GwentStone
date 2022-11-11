package games;

import fileio.ActionsInput;
import fileio.GameInput;

import java.util.ArrayList;

public class Game {
    private StartGame startGame;
    private ArrayList<Action> actions;

    public Game(GameInput game) {
        this.startGame = new StartGame(game.getStartGame());

        actions = new ArrayList<Action>();
        for (ActionsInput actionsInput : game.getActions())
            this.actions.add(new Action(actionsInput));
    }

    public StartGame getStartGame() {
        return startGame;
    }

    public void setStartGame(final StartGame startGame) {
        this.startGame = startGame;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(final ArrayList<Action> actions) {
        this.actions = actions;
    }

}

package games;

import fileio.CardInput;
import fileio.StartGameInput;

/**
 * The type Start game.
 */
public final class StartGame {
    private final int playerOneDeckIdx;
    private final int playerTwoDeckIdx;
    private final int shuffleSeed;
    private final CardInput playerOneHero;
    private final CardInput playerTwoHero;
    private final int startingPlayer;

    /**
     * Instantiates a new Start game.
     *
     * @param startGameInput the start game input
     */
    public StartGame(final StartGameInput startGameInput) {
        this.playerOneDeckIdx = startGameInput.getPlayerOneDeckIdx();
        this.playerTwoDeckIdx = startGameInput.getPlayerTwoDeckIdx();
        this.shuffleSeed = startGameInput.getShuffleSeed();
        this.playerOneHero = startGameInput.getPlayerOneHero();
        this.playerTwoHero = startGameInput.getPlayerTwoHero();
        this.startingPlayer = startGameInput.getStartingPlayer();
    }

    /**
     * get player one deck index
     *
     * @return the player one deck idx
     */
    public int getPlayerOneDeckIdx() {
        return playerOneDeckIdx;
    }

    /**
     * get player two deck index
     *
     * @return the player two deck idx
     */
    public int getPlayerTwoDeckIdx() {
        return playerTwoDeckIdx;
    }

    /**
     * get shuffle seed
     *
     * @return the shuffle seed
     */
    public int getShuffleSeed() {
        return shuffleSeed;
    }

    /**
     * get player one deck hero
     *
     * @return the player one hero
     */
    public CardInput getPlayerOneHero() {
        return playerOneHero;
    }

    /**
     * get player two deck hero
     *
     * @return the player two hero
     */
    public CardInput getPlayerTwoHero() {
        return playerTwoHero;
    }

    /**
     * get starting player
     *
     * @return the starting player
     */
    public int getStartingPlayer() {
        return startingPlayer;
    }

}

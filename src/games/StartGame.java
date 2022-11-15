package games;

import fileio.CardInput;
import fileio.StartGameInput;

/**
 * The type Start game.
 */
public class StartGame {
    private int playerOneDeckIdx;
    private int playerTwoDeckIdx;
    private int shuffleSeed;
    private CardInput playerOneHero;
    private CardInput playerTwoHero;
    private int startingPlayer;

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
     * set player one deck index
     *
     * @param playerOneDeckIdx the player one deck idx
     */
    public void setPlayerOneDeckIdx(final int playerOneDeckIdx) {
        this.playerOneDeckIdx = playerOneDeckIdx;
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
     * set player two deck index
     *
     * @param playerTwoDeckIdx the player two deck idx
     */
    public void setPlayerTwoDeckIdx(final int playerTwoDeckIdx) {
        this.playerTwoDeckIdx = playerTwoDeckIdx;
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
     * set shuffle seed
     *
     * @param shuffleSeed the shuffle seed
     */
    public void setShuffleSeed(final int shuffleSeed) {
        this.shuffleSeed = shuffleSeed;
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
     * set player one deck hero
     *
     * @param playerOneHero the player one hero
     */
    public void setPlayerOneHero(final CardInput playerOneHero) {
        this.playerOneHero = playerOneHero;
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
     * set player two deck hero
     *
     * @param playerTwoHero the player two hero
     */
    public void setPlayerTwoHero(final CardInput playerTwoHero) {
        this.playerTwoHero = playerTwoHero;
    }

    /**
     * get starting player
     *
     * @return the starting player
     */
    public int getStartingPlayer() {
        return startingPlayer;
    }

    /**
     * set starting player
     *
     * @param startingPlayer the starting player
     */
    public void setStartingPlayer(final int startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

}

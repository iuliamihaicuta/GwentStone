package fileio;

/**
 * The type Start game input.
 */
public final class StartGameInput {
    private int playerOneDeckIdx;
    private int playerTwoDeckIdx;
    private int shuffleSeed;
    private CardInput playerOneHero;
    private CardInput playerTwoHero;
    private int startingPlayer;

    /**
     * Instantiates a new Start game input.
     */
    public StartGameInput() {
    }

    /**
     * Gets player one deck idx.
     *
     * @return the player one deck idx
     */
    public int getPlayerOneDeckIdx() {
        return playerOneDeckIdx;
    }

    /**
     * Sets player one deck idx.
     *
     * @param playerOneDeckIdx the player one deck idx
     */
    public void setPlayerOneDeckIdx(final int playerOneDeckIdx) {
        this.playerOneDeckIdx = playerOneDeckIdx;
    }

    /**
     * Gets player two deck idx.
     *
     * @return the player two deck idx
     */
    public int getPlayerTwoDeckIdx() {
        return playerTwoDeckIdx;
    }

    /**
     * Sets player two deck idx.
     *
     * @param playerTwoDeckIdx the player two deck idx
     */
    public void setPlayerTwoDeckIdx(final int playerTwoDeckIdx) {
        this.playerTwoDeckIdx = playerTwoDeckIdx;
    }

    /**
     * Gets shuffle seed.
     *
     * @return the shuffle seed
     */
    public int getShuffleSeed() {
        return shuffleSeed;
    }

    /**
     * Sets shuffle seed.
     *
     * @param shuffleSeed the shuffle seed
     */
    public void setShuffleSeed(final int shuffleSeed) {
        this.shuffleSeed = shuffleSeed;
    }

    /**
     * Gets player one hero.
     *
     * @return the player one hero
     */
    public CardInput getPlayerOneHero() {
        return playerOneHero;
    }

    /**
     * Sets player one hero.
     *
     * @param playerOneHero the player one hero
     */
    public void setPlayerOneHero(final CardInput playerOneHero) {
        this.playerOneHero = playerOneHero;
    }

    /**
     * Gets player two hero.
     *
     * @return the player two hero
     */
    public CardInput getPlayerTwoHero() {
        return playerTwoHero;
    }

    /**
     * Sets player two hero.
     *
     * @param playerTwoHero the player two hero
     */
    public void setPlayerTwoHero(final CardInput playerTwoHero) {
        this.playerTwoHero = playerTwoHero;
    }

    /**
     * Gets starting player.
     *
     * @return the starting player
     */
    public int getStartingPlayer() {
        return startingPlayer;
    }

    /**
     * Sets starting player.
     *
     * @param startingPlayer the starting player
     */
    public void setStartingPlayer(final int startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

    @Override
    public String toString() {
        return "StartGameInput{"
                + "playerOneDeckIdx="
                + playerOneDeckIdx
                + ", playerTwoDeckIdx="
                + playerTwoDeckIdx
                + ", shuffleSeed="
                + shuffleSeed
                +  ", playerOneHero="
                + playerOneHero
                + ", playerTwoHero="
                + playerTwoHero
                + ", startingPlayer="
                + startingPlayer
                + '}';
    }
}

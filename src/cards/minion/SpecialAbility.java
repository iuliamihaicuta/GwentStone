package cards.minion;

import fileio.Coordinates;

/**
 * The interface Special ability.
 */
public interface SpecialAbility {

    /**
     * set card ability
     *
     * @param indexPlayer  the index player
     * @param cardAttacked the card attacked
     */
    void ability(int indexPlayer, Coordinates cardAttacked);
}

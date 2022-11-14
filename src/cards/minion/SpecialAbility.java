package cards.minion;

import fileio.Coordinates;

public interface SpecialAbility {
    void ability(int indexPlayer, Coordinates cardAttacker, Coordinates cardAttacked);
}

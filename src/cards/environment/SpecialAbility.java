package cards.environment;

import cards.Card;
import cards.minion.Minion;

import java.util.ArrayList;

public interface SpecialAbility {
    // TODO if ability == true remove from hand
    boolean ability(int row);
}

package cards.environment;

import cards.Card;
import fileio.CardInput;

import java.util.List;

public abstract class Environment extends Card {
    private static final List<String> environmentCardList = List.of("Firestorm", "Winterfell", "Heart Hound");

    public Environment(CardInput card) {
        super(card);
    }

    public boolean canAttack(int indexPlayer, int indexRow) {
        return indexPlayer == 1 && indexRow > 2 || indexPlayer == 2 && indexRow < 3;
    }
}

package cards.environment;

import cards.Card;
import fileio.CardInput;
import player.Player;

import java.util.List;

public abstract class Environment extends Card {
    private static final List<String> environmentCardList = List.of("Firestorm", "Winterfell", "Heart Hound");

    public Environment(CardInput card) {
        super(card);
    }

    public boolean isEnemyRow(int indexPlayer, int indexRow) {
        return indexPlayer == 1 && indexRow < 3 || indexPlayer == 2 && indexRow > 2;
    }

    // TODO if ability == true remove from hand
    public abstract boolean ability(Player player, int row);
}

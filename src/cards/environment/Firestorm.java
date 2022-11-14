package cards.environment;

import cards.Card;
import cards.minion.Minion;
import fileio.CardInput;
import player.Player;

import java.util.ArrayList;

import static table.Table.getTable;

public class Firestorm extends Environment implements SpecialAbility {

    public Firestorm(Card card) {
        super(card);
    }

    @Override
    public void ability(Player player, int row) {
        for (Minion card : getTable().get(row)) {
            card.setHealth(card.getHealth() - 1);
        }
    }
}

package cards.environment;

import cards.Card;
import cards.minion.Minion;
import player.Player;

import static table.Table.getTable;

public class Winterfell extends Environment implements SpecialAbility {
    public Winterfell(Card card) {
        super(card);
    }

    @Override
    public void ability(Player player, int row) {
        for (Minion card : getTable().get(row)) {
            card.setFrozen(true);
        }
    }
}

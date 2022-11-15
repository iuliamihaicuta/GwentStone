package cards.environment;

import cards.Card;
import cards.Minion;
import player.Player;

import java.util.ArrayList;

import static table.Table.getTable;

public class Firestorm extends Environment{

    public Firestorm(Card card) {
        super(card);
    }

    @Override
    public void ability(Player player, int row) {
        ArrayList<Minion> copyList = new ArrayList<>(getTable().get(row));

        for (Minion card : copyList) {
            card.setHealth(card.getHealth() - 1);

            if (card.getHealth() <= 0)
                getTable().get(row).remove(card);
        }
    }
}

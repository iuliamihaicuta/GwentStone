package cards.environment;

import cards.Card;
import cards.Minion;
import fileio.Coordinates;
import player.Player;

import static table.Table.*;

public class HeartHound extends Environment{
    public HeartHound(Card card) {
        super(card);
    }

    @Override
    public void ability(Player player, int row) {
        int myRow = 3 - row;

        int maxHealth = 0;
        int cardIndex = 0;

        for (int i = 0; i < getTable().get(row).size(); ++i) {
            Minion card = getTable().get(row).get(i);

            if (card.getHealth() > maxHealth) {
                maxHealth = card.getHealth();
                cardIndex = i;
            }
        }

        addToRow(myRow, getTable().get(row).get(cardIndex));
        removeFromRow(new Coordinates(row, cardIndex));
    }
}

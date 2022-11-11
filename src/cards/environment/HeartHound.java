package cards.environment;

import cards.minion.Minion;
import fileio.CardInput;

import java.util.ArrayList;

import static table.Table.*;

public class HeartHound extends Environment implements SpecialAbility{
    public HeartHound(CardInput card) {
        super(card);
    }

    @Override
    public boolean ability(int row) {
        int myRow = 3 - row;

        if (getTable().get(myRow).size() == 5) {
            System.out.println("Cannot steal enemy card since the player's row is full.");
            return false;
        }

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
        removeFromRow(row, cardIndex);

        return true;
    }
}

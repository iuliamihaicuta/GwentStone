package cards.environment;

import cards.minion.Minion;
import fileio.CardInput;

import java.util.ArrayList;

import static table.Table.getTable;

public class Firestorm extends Environment implements SpecialAbility{

    public Firestorm(CardInput card) {
        super(card);
    }

    @Override
    public boolean ability(int row) {
        for(Minion card : getTable().get(row)) {
            card.setHealth(card.getHealth() - 1);
        }

        return true;
    }
}

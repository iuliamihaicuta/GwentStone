package cards.environment;

import cards.minion.Minion;
import fileio.CardInput;

import java.util.ArrayList;

import static table.Table.getTable;

public class Winterfell extends Environment implements SpecialAbility{
    public Winterfell(CardInput card) {
        super(card);
    }

    @Override
    public boolean ability(int row) {
        for(Minion card : getTable().get(row)) {
            card.setFrozen(true);
        }

        return true;
    }
}

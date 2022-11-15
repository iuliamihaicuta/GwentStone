package cards.hero;

import cards.Card;
import cards.Minion;
import fileio.CardInput;

import static table.Table.getTable;

public class KingMudface extends Hero{
    public KingMudface(Card card) {
        super(card);
    }

    public KingMudface(CardInput card) {
        super(card);
    }

    public void ability(int row) {
        for (Minion card : getTable().get(row)) {
            card.setHealth(card.getHealth() + 1);
        }

        setHasAttacked(true);
    }
}

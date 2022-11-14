package cards.hero;

import cards.Card;
import cards.minion.Minion;
import fileio.CardInput;

import static table.Table.getTable;

public class KingMudface extends Hero{
    public KingMudface(Card card) {
        super(card);
    }

    public void ability(int indexPlayer, int row) {
        if (!isEnemyRow(indexPlayer, row) && !hasAttacked()) {
            for (Minion card : getTable().get(row)) {
                card.setHealth(card.getHealth() + 1);
            }

            setHasAttacked(true);
        }
    }
}

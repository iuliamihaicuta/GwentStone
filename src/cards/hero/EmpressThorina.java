package cards.hero;

import cards.minion.Minion;
import fileio.CardInput;

import static table.Table.getTable;
import static table.Table.removeFromRow;

public class EmpressThorina extends Hero{
    public EmpressThorina(CardInput cardInput) {
        super(cardInput);
    }

    @Override
    public void ability(int indexPlayer, int row) {
        if(isEnemyRow(indexPlayer, row) && !hasAttacked()) {
            int cardIndex = 0;
            int maxHealth = 0;

            for (int i = 0; i < getTable().get(row).size(); ++i) {
                Minion card = getTable().get(row).get(i);

                if (card.getHealth() > maxHealth) {
                    maxHealth = card.getHealth();
                    cardIndex = i;
                }
            }

            removeFromRow(row, cardIndex);
            setHasAttacked(true);
        }
    }
}

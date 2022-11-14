package cards.minion;

import cards.Card;
import fileio.CardInput;
import fileio.Coordinates;

import static table.Table.getTable;
import static table.Table.removeFromRow;

public class TheCursedOne extends Minion implements SpecialAbility{
    public TheCursedOne(Card card) {
        super(card);
    }

    @Override
    public void ability(int indexPlayer, Coordinates cardAttacker, Coordinates cardAttacked) {
        Minion card = getTable().get(cardAttacked.getX()).get(cardAttacked.getY());
        int temp = card.getHealth();
        card.setHealth(card.getAttackDamage());
        card.setAttackDamage(temp);

        if (card.getHealth() <= 0) {
            removeFromRow(cardAttacked);
        }

        setHasAttacked(true);
    }
}

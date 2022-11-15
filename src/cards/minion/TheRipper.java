package cards.minion;

import cards.Card;
import cards.Minion;
import fileio.Coordinates;

import static table.Table.getTable;
import static table.Table.removeFromRow;

public class TheRipper extends Minion implements SpecialAbility {
    public TheRipper(Card card) {
        super(card);
    }

    @Override
    public void ability(int indexPlayer, Coordinates cardAttacked) {
        Minion card = getTable().get(cardAttacked.getX()).get(cardAttacked.getY());
        card.setAttackDamage(card.getAttackDamage() - 2);

        if (card.getAttackDamage() <= 0)
            card.setAttackDamage(0);

        setHasAttacked(true);
    }
}

package cards.minion;

import cards.Card;
import fileio.CardInput;
import fileio.Coordinates;

import static table.Table.getTable;

public class TheRipper extends Minion implements SpecialAbility{
    public TheRipper(Card card) {
        super(card);
    }

    @Override
    public void ability(int indexPlayer, Coordinates cardAttacker, Coordinates cardAttacked) {
        Minion card = getTable().get(cardAttacked.getX()).get(cardAttacked.getY());
        card.setHealth(card.getHealth() - 2);

        setHasAttacked(true);
    }
}

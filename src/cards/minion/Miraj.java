package cards.minion;

import cards.Card;
import fileio.CardInput;
import fileio.Coordinates;

import static table.Table.getTable;

public class Miraj extends Minion implements SpecialAbility{

    public Miraj(Card card) {
        super(card);
    }

    @Override
    public void ability(int indexPlayer, Coordinates cardAttacker, Coordinates cardAttacked) {
        Minion card = getTable().get(cardAttacked.getX()).get(cardAttacked.getY());
        int temp = this.getHealth();
        this.setHealth(card.getHealth());
        card.setHealth(temp);

        setHasAttacked(true);
    }
}

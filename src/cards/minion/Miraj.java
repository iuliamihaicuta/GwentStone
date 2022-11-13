package cards.minion;

import fileio.CardInput;
import fileio.Coordinates;

import static table.Table.getTable;

public class Miraj extends Minion implements SpecialAbility{

    public Miraj(CardInput card) {
        super(card);
    }

    @Override
    public void ability(int indexPlayer, Coordinates cardCoordinates) {
        if (canUseAbility(indexPlayer, cardCoordinates)) {
            Minion card = getTable().get(cardCoordinates.getX()).get(cardCoordinates.getY());
            int temp = this.getHealth();
            this.setHealth(card.getHealth());
            card.setHealth(temp);

            setHasAttacked(true);
        }
    }
}

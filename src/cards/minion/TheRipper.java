package cards.minion;

import fileio.CardInput;
import fileio.Coordinates;

import static table.Table.getTable;

public class TheRipper extends Minion implements SpecialAbility{
    public TheRipper(CardInput card) {
        super(card);
    }

    @Override
    public void ability(int indexPlayer, Coordinates cardCoordinates) {
        if (canUseAbility(indexPlayer, cardCoordinates)) {
            Minion card = getTable().get(cardCoordinates.getX()).get(cardCoordinates.getY());
            card.setHealth(card.getHealth() - 2);

            setHasAttacked(true);
        }
    }
}

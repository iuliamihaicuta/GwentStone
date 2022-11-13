package cards.minion;

import fileio.CardInput;
import fileio.Coordinates;

import static table.Table.getTable;

public class TheCursedOne extends Minion implements SpecialAbility{
    public TheCursedOne(CardInput card) {
        super(card);
    }

    @Override
    public void ability(int indexPlayer, Coordinates cardCoordinates) {
        if (canUseAbility(indexPlayer, cardCoordinates)) {
            Minion card = getTable().get(cardCoordinates.getX()).get(cardCoordinates.getY());
            int temp = card.getHealth();
            card.setHealth(card.getAttackDamage());
            card.setAttackDamage(temp);

            setHasAttacked(true);
        }
    }
}

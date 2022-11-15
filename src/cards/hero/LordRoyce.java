package cards.hero;

import cards.Card;
import cards.Minion;
import fileio.CardInput;

import static table.Table.getTable;

public class LordRoyce extends Hero{
    public LordRoyce(Card card) {
        super(card);
    }

    public LordRoyce(CardInput card) {
        super(card);
    }

    public void ability(int row) {
        int cardIndex = 0;
        int maxAttack = 0;

        for (int i = 0; i < getTable().get(row).size(); ++i) {
            Minion card = getTable().get(row).get(i);

            if (card.getAttackDamage() > maxAttack) {
                maxAttack = card.getAttackDamage();
                cardIndex = i;
            }
        }

        getTable().get(row).get(cardIndex).setFrozen(true);
        setHasAttacked(true);
    }
}

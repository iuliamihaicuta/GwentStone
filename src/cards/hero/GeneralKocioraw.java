package cards.hero;

import cards.Card;
import cards.Minion;
import fileio.CardInput;

import static table.Table.getTable;

public class GeneralKocioraw extends Hero{
    public GeneralKocioraw(Card card) {
        super(card);
    }

    public GeneralKocioraw(CardInput card) {
        super(card);
    }

    public void ability(int row) {
        for (Minion card : getTable().get(row)) {
            card.setAttackDamage(card.getAttackDamage() + 1);
        }

        setHasAttacked(true);
    }
}

package cards.hero;

import cards.minion.Minion;
import fileio.CardInput;

import static table.Table.getTable;

/**
 * The type Lord royce.
 */
public final class LordRoyce extends Hero {
    /**
     * Instantiates a new Lord royce.
     *
     * @param card the card
     */
    public LordRoyce(final CardInput card) {
        super(card);
    }

    /**
     * set card ability
     */
    @Override
    public void ability(final int row) {
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

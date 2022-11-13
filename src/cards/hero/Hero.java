package cards.hero;

import cards.Card;
import fileio.CardInput;

public abstract class Hero extends Card {
    private boolean hasAttacked = false;
    public Hero(CardInput cardInput) {
        super(cardInput);
        setHealth(30);
    }

    public abstract void ability(int indexPlayer, int row);

    protected boolean isEnemyRow(int indexPlayer, int indexRow) {
        return indexPlayer == 1 && indexRow < 3 || indexPlayer == 2 && indexRow > 2;
    }

    public boolean hasAttacked() {
        return hasAttacked;
    }

    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }
}

package cards.hero;

import cards.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;
import player.Player;

public class Hero extends Card {
    @JsonIgnore
    private boolean hasAttacked = false;
    @JsonIgnore
    private int attackDamage;
    public Hero(CardInput cardInput) {
        super(cardInput);
        setHealth(30);
    }

    public Hero(Card card) {
        super(card);
    }

//    public  void ability(int indexPlayer, int row);

    public String canAttack(Player player) {
        String error = null;

        if (player.getMana() < getMana()) {
            error = "Not enough mana to use hero's ability.";
        } else if (hasAttacked) {
            error = "Hero has already attacked this turn.";
        }

        return error;
    }

    protected boolean isEnemyRow(int indexPlayer, int indexRow) {
        return indexPlayer == 1 && indexRow < 3 || indexPlayer == 2 && indexRow > 2;
    }

    public boolean hasAttacked() {
        return hasAttacked;
    }

    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    public void ability(int row) {};
}

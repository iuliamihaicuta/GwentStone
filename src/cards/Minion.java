package cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fileio.Coordinates;

import java.util.ArrayList;

import static table.Table.enemyHasTankCards;
import static table.Table.getTable;

public class Minion extends Card {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final boolean tank;
    @JsonIgnore
    private boolean frozen = false;
    @JsonIgnore
    private final boolean onTheFrontRow;
    @JsonIgnore
    private boolean hasAttacked = false;

    public Minion(Card card) {
        super(card);
        tank = getName().equals("Goliath") || getName().equals("Warden");
        onTheFrontRow = getName().equals("The Ripper") || getName().equals("Miraj")
                || getName().equals("Goliath") || getName().equals("Warden");
    }

    public Minion(Minion card) {
        super((Card) card);
        this.tank = card.tank;
        this.frozen = card.frozen;
        this.onTheFrontRow = card.onTheFrontRow;
        this.hasAttacked = card.hasAttacked;
    }

    // TODO protected
    public String canUseAbility(int indexPlayer, Coordinates cardAttacked) {
        String error = null;
        int row = cardAttacked.getX();

        if (frozen) {
            error = "Attacker card is frozen.";
        }
        else if (hasAttacked) {
            error = "Attacker card has already attacked this turn.";
        }
        else if ((getName().equals("Disciple") && (indexPlayer == 1 && row < 2 || indexPlayer == 2 && row > 1))) {
            error = "Attacked card does not belong to the current player.";
        }
        else if (!getName().equals("Disciple") && (indexPlayer == 1 && row > 1 || indexPlayer == 2 && row < 2)) {
            error = "Attacked card does not belong to the enemy.";
        }
        else if (!getName().equals("Disciple")) {
            if (enemyHasTankCards(indexPlayer) && !getTable().get(cardAttacked.getX()).get(cardAttacked.getY()).isTank()) {
                ArrayList<ArrayList<Minion>> table = getTable();
                error = "Attacked card is not of type 'Tank'.";
            }
        }

        return error;
    }

    public String canAttack(int indexPlayer, Coordinates cardAttacked) {
        String error  = null;
        int row = cardAttacked.getX();

        if ((indexPlayer == 1 && row > 1 || indexPlayer == 2 && row < 2)) {
            error = "Attacked card does not belong to the enemy.";
        }
        else if (hasAttacked) {
            error = "Attacker card has already attacked this turn.";
        }
        else if (frozen) {
            error = "Attacker card is frozen.";
        }
        else {
            if (enemyHasTankCards(indexPlayer) && !getTable().get(cardAttacked.getX()).get(cardAttacked.getY()).isTank()) {
                error = "Attacked card is not of type 'Tank'.";
            }
        }

        return error;
    }

    public String canAttackHero(int indexPlayer) {
        String error = null;

        if (frozen) {
            error = "Attacker card is frozen.";
        }
        else if (hasAttacked) {
            error = "Attacker card has already attacked this turn.";
        } else {
            if (enemyHasTankCards(indexPlayer)) {
                error = "Attacked card is not of type 'Tank'.";
            }
        }

        return error;
    }

    public boolean isTank() {
        return tank;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public boolean isOnTheFrontRow() {
        return onTheFrontRow;
    }

    public boolean hasAttacked() {
        return hasAttacked;
    }

    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

}

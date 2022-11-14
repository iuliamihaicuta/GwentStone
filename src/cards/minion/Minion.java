package cards.minion;

import cards.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import fileio.Coordinates;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static table.Table.enemyTankCards;

public class Minion extends Card {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final boolean tank;
    @JsonIgnore
    private boolean frozen = false;
    @JsonIgnore
    private final boolean onTheFrontRow;
    @JsonIgnore
    private boolean hasAttacked = false;

    public static final List<String> simpleMinionCardList =
            List.of("Sentinel", "Berserker", "Goliath", "Warden");
    public static final List<String> abilityMinionCardList =
            List.of("The ripper", "Miraj", "The Cursed One", "Disciple");
    public static final List<String> useAbilityOnEnemyCardList =
            List.of("The ripper", "Miraj", "The Cursed One");

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

    private String checkAttack() {
        String error = null;

        if (frozen) {
            error = "Attacker card is frozen.";
        }
        else if (hasAttacked) {
            error = "Attacker card has already attacked this turn.";
        }

        return error;
    }

    // TODO protected
    public String canUseAbility(int indexPlayer, Coordinates cardAttacked) {
        String error = checkAttack();
        int row = cardAttacked.getX();

        if (error == null && (getName().equals("Disciple") && indexPlayer == 1 && row < 3 || indexPlayer == 2 && row > 2)) {
            error = "Attacked card does not belong to the current player.";
        }
        else if (!getName().equals("Disciple") && (indexPlayer == 1 && row > 2 || indexPlayer == 2)) {
            error = "Attacked card does not belong to the enemy.";
        }
        else {
            ArrayList<Coordinates> tankCards= enemyTankCards(indexPlayer);

            if (tankCards.size() != 0 && !tankCards.contains(cardAttacked)) {
                error = "Attacked card is not of type 'Tank'.";
            }
        }

        return error;
    }

    public String canAttack(int indexPlayer, Coordinates cardAttacked) {
        String error  = checkAttack();
        int row = cardAttacked.getX();

        if (error == null && (indexPlayer == 1 && row < 3 || indexPlayer == 2 && row > 2)) {
            error = "Attacked card does not belong to the enemy";
        }
        else {
            ArrayList<Coordinates> tankCards= enemyTankCards(indexPlayer);
            if (tankCards.size() != 0 && !tankCards.contains(cardAttacked)) {
                error = "Attacked card is not of type 'Tank'.";
            }
        }

        return error;
    }

    public String canAttackHero(int indexPlayer) {
        String error = checkAttack();

        if (error == null) {
            ArrayList<Coordinates> tankCards = enemyTankCards(indexPlayer);
            if (tankCards.size() != 0) {
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

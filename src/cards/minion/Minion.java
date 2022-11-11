package cards.minion;

import cards.Card;
import fileio.CardInput;
import fileio.Coordinates;

import java.util.ArrayList;
import java.util.List;

import static table.Table.enemyTankCards;

public class Minion extends Card {

    private final boolean isTank;
    private boolean isFrozen = false;
    private final boolean isOnTheFrontRow;
    private boolean hasAttacked = false;

    private static final List<String> simpleMinionCardList =
            List.of("Sentinel", "Berserker", "Goliath", "Warden");
    private static final List<String> abilityMinionCardList =
            List.of("The ripper", "Miraj", "The Cursed One", "Disciple");
    private static final List<String> useAbilityOnEnemyCardList =
            List.of("The ripper", "Miraj", "The Cursed One");

    public Minion(CardInput card) {
        super(card);
        isTank = getName().equals("Goliath") || getName().equals("Warden");
        isOnTheFrontRow = getName().equals("The Ripper") || getName().equals("Miraj")
                || getName().equals("Goliath") || getName().equals("Warden");
    }

    private boolean checkAttack(int indexPlayer, Coordinates cardCoordinates) {
        boolean check = true;

        if (isFrozen) {
            System.out.println("Attacker card is frozen.");
            check = false;
        }
        else if (hasAttacked) {
            System.out.println("Attacker card has already attacked this turn.");
            check = false;
        }

        return check;
    }

    // TODO protected
    public boolean canUseAbility(int indexPlayer, Coordinates cardCoordinates) {
        boolean canUseAbility = checkAttack(indexPlayer, cardCoordinates);
        int row = cardCoordinates.getX();

        if (canUseAbility && (getName().equals("Disciple") && indexPlayer == 1 && row < 3 || indexPlayer == 2 && row > 2)) {
            System.out.println("Attacked card does not belong to the current player.");
            canUseAbility = false;
        }
        else if (!getName().equals("Disciple") && (indexPlayer == 1 && row > 2 || indexPlayer == 2)) {
            System.out.println("Attacked card does not belong to the enemy.");
            canUseAbility = false;
        }
        else {
            ArrayList<Coordinates> tankCards= enemyTankCards(indexPlayer);
            assert tankCards != null;
            if (!tankCards.contains(cardCoordinates)) {
                System.out.println("Attacked card is not of type 'Tank'.");
                canUseAbility = false;
            }
        }

        return canUseAbility;
    }

    public boolean canAttack(int indexPlayer, Coordinates cardCoordinates) {
        boolean canAttack = checkAttack(indexPlayer, cardCoordinates);
        int row = cardCoordinates.getX();

        if (canAttack && (indexPlayer == 1 && row < 3 || indexPlayer == 2 && row > 2)) {
            System.out.println("Attacked card does not belong to the enemy");
            canAttack = false;
        }
        else {
            ArrayList<Coordinates> tankCards= enemyTankCards(indexPlayer);
            assert tankCards != null;
            if (!tankCards.contains(cardCoordinates)) {
                System.out.println("Attacked card is not of type 'Tank'.");
                canAttack = false;
            }
        }

        return canAttack;
    }

    public boolean isTank() {
        return isTank;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    public boolean isOnTheFrontRow() {
        return isOnTheFrontRow;
    }

    public boolean hasAttacked() {
        return hasAttacked;
    }

    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

}

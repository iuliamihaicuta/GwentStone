package cards.environment;

import cards.minion.Minion;
import fileio.CardInput;
import player.Player;

import java.util.ArrayList;

import static table.Table.getTable;

public class Winterfell extends Environment{
    public Winterfell(CardInput card) {
        super(card);
    }

    @Override
    public boolean ability(Player player, int row) {
        if (!isEnemyRow(player.getId(), row)) {
            System.out.println("Chosen row does not belong to the enemy.");
            return false;
        }

        if(player.getMana() < getMana()) {
            System.out.println("Not enough mana to use environment card.");
        }

        for(Minion card : getTable().get(row)) {
            card.setFrozen(true);
        }

        return true;
    }
}

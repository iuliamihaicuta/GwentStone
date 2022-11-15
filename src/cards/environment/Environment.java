package cards.environment;

import cards.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;
import player.Player;

import java.util.List;

import static table.Table.getTable;

public class Environment extends Card {
    @JsonIgnore
    private int attackDamage;
    @JsonIgnore
    private int health;

    public static final List<String> environmentCardList = List.of("Firestorm", "Winterfell", "Heart Hound");

    public Environment(CardInput card) {
        super(card);
    }

    public Environment(Card card) {super(card);}

    public boolean isEnemyRow(int indexPlayer, int indexRow) {
        return indexPlayer == 1 && indexRow < 2 || indexPlayer == 2 && indexRow > 1;
    }

    public String canUseAbility(Player player, int rowIndex) {
        String error = null;

        if (getMana() > player.getMana())
            error = "Not enough mana to use environment card.";
        else if (getName().equals("Heart Hound") && getTable().get(3 - rowIndex).size() == 5)
            error = "Cannot steal enemy card since the player's row is full.";
        else if (!isEnemyRow(player.getId(), rowIndex))
            error = "Chosen row does not belong to the enemy.";

        return error;
    }

    public void ability(Player player, int row) {};
}

package games;

import cards.Minion;
import cards.hero.Hero;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import player.Player;

import java.util.ArrayList;

public class PlayerActions {
    public static void getPlayerDeck(Player player, ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", "getPlayerDeck");
        node.put("playerIdx", player.getId());
        node.putPOJO("output", player.getUsedDeck());

        output.addPOJO(node);
    }

    public static void getTotalGamesPlayed(ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", "getTotalGamesPlayed");
        node.put("output", Player.getGamesPlayed());

        output.addPOJO(node);
    }

    public static void getPlayerWins(Player player, ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        if (player.getId() == 1)
            node.put("command", "getPlayerOneWins");
        else
            node.put("command", "getPlayerTwoWins");

        node.put("output", player.getTotalGamesWon());

        output.addPOJO(node);
    }

    public static void endPlayerTurn(Player player) {
        for (ArrayList<Minion> row : player.getRows()) {
            for (Minion minion : row) {
                minion.setFrozen(false);
                minion.setHasAttacked(false);
            }
        }

        player.getHero().setHasAttacked(false);
    }

    public static void getPlayerTurn(int turn, ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", "getPlayerTurn");

        turn %= 2;
        if (turn == 0)
            turn = 2;

        node.put("output", turn);

        output.addPOJO(node);
    }

    public static void getPlayerHero(Player player, ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", "getPlayerHero");
        node.put("playerIdx", player.getId());

        node.putPOJO("output", new Hero(player.getHero()));

        output.addPOJO(node);
    }

    public static void getPlayerMana(Player player, ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", "getPlayerMana");
        node.put("playerIdx", player.getId());

        int tmp = player.getMana();
        node.put("output", tmp);

        output.addPOJO(node);
    }
}

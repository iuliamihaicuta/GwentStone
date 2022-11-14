package games;

import cards.minion.Minion;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import player.Player;

import java.util.ArrayList;

import static player.Player.getGamesPlayed;
import static table.Table.getTable;

public class PlayerActions {
    public static void getPlayerDeck(Player player, Action action, ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", action.getCommand());
        node.put("playerIdx", action.getPlayerIdx());
//        System.out.println(player.getUsedDeck());
        node.putPOJO("output", player.getUsedDeck());

        output.addPOJO(node);
    }

    public static void getTotalGamesPlayed() {
//        System.out.println(getGamesPlayed());
    }

    public static void getPlayerWins(Player player) {
//        System.out.println(player.getTotalGamesWon());
    }

    public static void endPlayerTurn(Player player) {
        for (ArrayList<Minion> row : player.getRows()) {
            for (Minion minion : row) {
                if (minion.isFrozen())
                    minion.setFrozen(false);

                if (minion.hasAttacked())
                    minion.setHasAttacked(false);
            }
        }

        if (player.getHero().hasAttacked())
            player.getHero().setHasAttacked(false);
    }

    public static void getPlayerTurn(int turn, Action action, ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", action.getCommand());

        turn %= 2;
        if (turn == 0)
            turn = 2;

        node.put("output", turn);

        output.addPOJO(node);
    }

    public static void getPlayerHero(Player player, Action action, ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", action.getCommand());
        node.put("playerIdx", action.getPlayerIdx());
        node.putPOJO("output", player.getHero());

        output.addPOJO(node);
    }

    public static void getPlayerMana(Player player) {

    }
}

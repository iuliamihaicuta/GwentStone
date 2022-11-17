package games;

import cards.hero.Hero;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import player.Player;

/**
 * The type Player actions.
 */
public final class PlayerActions {
    private PlayerActions() {
    }

    /**
     * display the player's current deck
     *
     * @param player the player
     * @param output the output
     */
    public static void getPlayerDeck(final Player player, final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();

        node.put("command", "getPlayerDeck");
        node.put("playerIdx", player.getId());
        node.putPOJO("output", player.getUsedDeck());

        output.addPOJO(node);
    }

    /**
     * display the number of games played
     *
     * @param output the output
     */
    public static void getTotalGamesPlayed(final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();

        node.put("command", "getTotalGamesPlayed");
        node.put("output", Player.getGamesPlayed());

        output.addPOJO(node);
    }

    /**
     * display the player's wins
     *
     * @param player the player
     * @param output the output
     */
    public static void getPlayerWins(final Player player, final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();

        if (player.getId() == 1) {
            node.put("command", "getPlayerOneWins");
        } else {
            node.put("command", "getPlayerTwoWins");
        }
        node.put("output", player.getTotalGamesWon());

        output.addPOJO(node);
    }

    /**
     * display the current player
     *
     * @param turn   the turn
     * @param output the output
     */
    public static void getPlayerTurn(final int turn, final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();

        node.put("command", "getPlayerTurn");
        int playerTurn = turn % 2;
        if (playerTurn == 0) {
            playerTurn = 2;
        }
        node.put("output", playerTurn);

        output.addPOJO(node);
    }

    /**
     * display the player's hero
     *
     * @param player the player
     * @param output the output
     */
    public static void getPlayerHero(final Player player, final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();

        node.put("command", "getPlayerHero");
        node.put("playerIdx", player.getId());
        node.putPOJO("output", new Hero(player.getHero()));

        output.addPOJO(node);
    }

    /**
     * display the player's mana
     *
     * @param player the player
     * @param output the output
     */
    public static void getPlayerMana(final Player player, final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();

        node.put("command", "getPlayerMana");
        node.put("playerIdx", player.getId());
        int tmp = player.getMana();
        node.put("output", tmp);

        output.addPOJO(node);
    }
}

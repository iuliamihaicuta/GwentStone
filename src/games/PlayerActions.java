package games;

import player.Player;
import static player.Player.getGamesPlayed;

public class PlayerActions {
    public static void getPlayerDeck(Player player) {
        System.out.println(player.getUsedDeck());
    }

    public static void getTotalGamesPlayed() {
        System.out.println(getGamesPlayed());
    }

    public static void getPlayerWins(Player player) {
        System.out.println(player.getTotalGamesWon());
    }

    public static void endPlayerTurn(Player player) {

    }

    public static void getPlayerTurn(int turn) {

    }

    public static void getPlayerHero(Player player) {

    }

    public static void getPlayerMana(Player player) {

    }
}

package games;

import player.Player;
import static player.Player.getGamesPlayed;

public class Actions {
    public static void getPlayerDeck(Player player) {
        System.out.println(player.getUsedDeck());
    }

    public static void getTotalGamesPlayed() {
        System.out.println(getGamesPlayed());
    }

    public static void getPlayerWins(Player player) {
        System.out.println(player.getTotalGamesWon());
    }
}

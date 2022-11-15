package start;

import cards.hero.EmpressThorina;
import cards.hero.GeneralKocioraw;
import cards.hero.LordRoyce;
import cards.hero.KingMudface;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.CardInput;
import fileio.GameInput;
import fileio.Input;
import games.Action;
import games.Game;
import player.Player;
import table.Table;

import java.util.ArrayList;

import static games.CardActions.placeCard;
import static games.CardActions.cardUsesAttack;
import static games.CardActions.cardUsesAbility;
import static games.CardActions.useAttackHero;
import static games.CardActions.useHeroAbility;
import static games.CardActions.useEnvironmentCard;
import static games.CardActions.getCardsInHand;
import static games.CardActions.getCardsOnTable;
import static games.CardActions.getCardAtPosition;
import static games.CardActions.getEnvironmentCardsInHand;
import static games.CardActions.getFrozenCardsOnTable;
import static games.PlayerActions.getPlayerDeck;
import static games.PlayerActions.getPlayerTurn;
import static games.PlayerActions.getPlayerHero;
import static games.PlayerActions.getPlayerMana;
import static games.PlayerActions.getTotalGamesPlayed;
import static games.PlayerActions.getPlayerWins;
import static games.PlayerActions.endPlayerTurn;
import static table.Table.getTable;

import static constants.Constants.PLAYER1_FIRST_ROW;
import static constants.Constants.PLAYER1_SECOND_ROW;
import static constants.Constants.PLAYER2_FIRST_ROW;
import static constants.Constants.PLAYER2_SECOND_ROW;

/**
 * The type Start.
 */
public class Start {
    private final Table table = new Table();
    private Player player1;
    private Player player2;
    private int actionIndex;

    /**
     * entry point to program
     *
     * @param input  the input
     * @param output the output
     */
    public void start(final Input input, final ArrayNode output) {
        Player.setGamesPlayed(0);
        player1 = new Player(input.getPlayerOneDecks(),
                getTable().get(PLAYER1_FIRST_ROW), getTable().get(PLAYER1_SECOND_ROW), 1);
        player2 = new Player(input.getPlayerTwoDecks(),
                getTable().get(PLAYER2_FIRST_ROW), getTable().get(PLAYER2_SECOND_ROW), 2);

        for (GameInput gameInput : input.getGames()) {
            Game game = new Game(gameInput);
            actionIndex = 0;
            int startingPlayer = game.getStartGame().getStartingPlayer();
            int secondPlayer;

            if (startingPlayer == 1) {
                secondPlayer = 2;
            } else {
                secondPlayer = 1;
            }

            startGame(player1, game);
            startGame(player2, game);

            int round = 1;

            while (actionIndex < game.getActions().size()) {
                getPlayer(startingPlayer).startTurn(round);
                getPlayer(secondPlayer).startTurn(round);

                turn(getPlayer(startingPlayer), game.getActions(), output);
                turn(getPlayer(secondPlayer), game.getActions(), output);

                round++;
            }
            Table.clearTable();
        }
    }

    private void startGame(final Player player, final Game game) {
        if (player.getId() == 1) {
            player.shuffleDeck(game.getStartGame().getPlayerOneDeckIdx(),
                    game.getStartGame().getShuffleSeed());
            createHero(player, game.getStartGame().getPlayerOneHero());
        } else {
            player.shuffleDeck(game.getStartGame().getPlayerTwoDeckIdx(),
                    game.getStartGame().getShuffleSeed());
            createHero(player, game.getStartGame().getPlayerTwoHero());
        }

        player.setMana(0);
        player.getHand().getCards().clear();
    }
    private Player getEnemy(final int playerIndex) {
        if (playerIndex == 1) {
            return player2;
        }

        return player1;
    }

    private Player getPlayer(final int playerIndex) {
        if (playerIndex == 1) {
            return player1;
        }

        return player2;
    }

    private void endGame(final Player winner) {
        winner.setTotalGamesWon(winner.getTotalGamesWon() + 1);
        Player.setGamesPlayed(Player.getGamesPlayed() + 1);
    }

    private void createHero(final Player player, final CardInput card) {
        switch (card.getName()) {
            case "Empress Thorina" -> player.setHero(new EmpressThorina(card));
            case "General Kocioraw" -> player.setHero(new GeneralKocioraw(card));
            case "King Mudface" -> player.setHero(new KingMudface(card));
            case "Lord Royce" -> player.setHero(new LordRoyce(card));
            default -> System.err.println("ERROR: Hero card does not exist.");
        }
    }

    private void turn(final Player player, final ArrayList<Action> actions,
                      final ArrayNode output) {
        while (actionIndex < actions.size()) {
            Action action = actions.get(actionIndex);
            actionIndex++;

            switch (action.getCommand()) {
                case "endPlayerTurn" -> {
                    endPlayerTurn(player);
                    return;
                }
                case "placeCard" -> placeCard(player, action.getHandIdx(), output);
                case "cardUsesAttack" ->
                        cardUsesAttack(player, action.getCardAttacker(),
                                action.getCardAttacked(), output);
                case "cardUsesAbility" ->
                        cardUsesAbility(player, action.getCardAttacker(),
                                action.getCardAttacked(), output);
                case "useAttackHero" -> {
                    if (useAttackHero(player, getEnemy(player.getId()),
                            action.getCardAttacker(), output)) {
                        endGame(player);
                    }
                }
                case "useHeroAbility" -> useHeroAbility(player, action.getAffectedRow(), output);
                case "useEnvironmentCard" ->
                        useEnvironmentCard(player, action.getHandIdx(),
                                action.getAffectedRow(), output);
                case "getCardsInHand" -> getCardsInHand(getPlayer(action.getPlayerIdx()), output);
                case "getPlayerDeck" -> getPlayerDeck(getPlayer(action.getPlayerIdx()), output);
                case "getCardsOnTable" -> getCardsOnTable(output);
                case "getPlayerTurn" -> getPlayerTurn(player.getId(), output);
                case "getPlayerHero" -> getPlayerHero(getPlayer(action.getPlayerIdx()), output);
                case "getCardAtPosition" -> getCardAtPosition(action.getX(), action.getY(), output);
                case "getPlayerMana" -> getPlayerMana(getPlayer(action.getPlayerIdx()), output);
                case "getEnvironmentCardsInHand" ->
                        getEnvironmentCardsInHand(getPlayer(action.getPlayerIdx()), output);
                case "getFrozenCardsOnTable" -> getFrozenCardsOnTable(output);
                case "getTotalGamesPlayed" -> getTotalGamesPlayed(output);
                case "getPlayerOneWins" -> getPlayerWins(player1, output);
                case "getPlayerTwoWins" -> getPlayerWins(player2, output);
                default -> System.err.println("ERROR: Command does not exist.");
            }
        }
    }
}

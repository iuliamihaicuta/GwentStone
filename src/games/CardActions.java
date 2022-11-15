package games;

import cards.Card;
import cards.minion.Minion;
import cards.environment.Environment;
import cards.minion.SpecialAbility;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.Coordinates;
import player.Player;

import java.util.ArrayList;

import static cards.environment.Environment.environmentCardList;
import static constants.Constants.MAX_LENGTH_OF_ROW;
import static table.Table.getTable;
import static table.Table.removeFromRow;
import static table.Table.getFrozenCards;

/**
 * The type Card actions.
 */
public final class CardActions {
    private CardActions() {
    }

    /**
     * place card on table
     *
     * @param player    the player
     * @param handIndex the hand index
     * @param output    the output
     */
    public static void placeCard(final Player player, final int handIndex,
                                 final ArrayNode output) {
        String error = null;

        Card card = player.getHand().getCards().get(handIndex);

        if (environmentCardList.contains(card.getName())) {
            error = "Cannot place environment card on table.";
        } else if (card.getMana() > player.getMana()) {
            error = "Not enough mana to place card on table.";
        } else {
            Minion minion = (Minion) card;
            if (minion.isOnTheFrontRow()
                    && player.getRows().get(0).size() == MAX_LENGTH_OF_ROW
                    || !minion.isOnTheFrontRow()
                    && player.getRows().get(1).size() == MAX_LENGTH_OF_ROW) {
                error = "Cannot place card on table since row is full.";
            } else {
                if (minion.isOnTheFrontRow()) {
                    player.getRows().get(0).add(minion);
                } else {
                    player.getRows().get(1).add(minion);
                }

                player.getHand().removeFromHand(handIndex);
                player.setMana(player.getMana() - minion.getMana());
            }
        }

        if (error != null) {
            ObjectNode node = JsonNodeFactory.instance.objectNode();
            node.put("command", "placeCard");
            node.put("error", error);
            node.put("handIdx", handIndex);

            output.addPOJO(node);
        }
    }

    /**
     * use attack of minion card
     *
     * @param player       the player
     * @param cardAttacker the card attacker
     * @param cardAttacked the card attacked
     * @param output       the output
     */
    public static void cardUsesAttack(final Player player, final Coordinates cardAttacker,
                                      final Coordinates cardAttacked, final ArrayNode output) {
        Minion attackerCard = getTable().get(cardAttacker.getX()).get(cardAttacker.getY());
        String error = attackerCard.canAttack(player.getId(), cardAttacked);

        if (error == null) {
            Minion attackedCard = getTable().get(cardAttacked.getX()).get(cardAttacked.getY());
            attackedCard.setHealth(attackedCard.getHealth() - attackerCard.getAttackDamage());

            if (attackedCard.getHealth() <= 0) {
                removeFromRow(cardAttacked);
            }

            getTable().get(cardAttacker.getX()).get(cardAttacker.getY()).setHasAttacked(true);
        } else {
            ObjectNode node = JsonNodeFactory.instance.objectNode();
            node.put("command", "cardUsesAttack");
            node.putPOJO("cardAttacker", cardAttacker);
            node.putPOJO("cardAttacked", cardAttacked);
            node.put("error", error);

            output.addPOJO(node);
        }
    }

    /**
     * use ability of minion card
     *
     * @param player       the player
     * @param cardAttacker the card attacker
     * @param cardAttacked the card attacked
     * @param output       the output
     */
    public static void cardUsesAbility(final Player player, final Coordinates cardAttacker,
                                       final Coordinates cardAttacked, final ArrayNode output) {
        Minion attackerCard = getTable().get(cardAttacker.getX()).get(cardAttacker.getY());
        String error = attackerCard.canUseAbility(player.getId(), cardAttacked);

        if (error == null) {
            ((SpecialAbility) attackerCard).ability(player.getId(), cardAttacked);
            getTable().get(cardAttacker.getX()).get(cardAttacker.getY()).setHasAttacked(true);
        } else {
                ObjectNode node = JsonNodeFactory.instance.objectNode();
                node.put("command", "cardUsesAbility");
                node.putPOJO("cardAttacker", cardAttacker);
                node.putPOJO("cardAttacked", cardAttacked);
                node.put("error", error);

                output.addPOJO(node);
        }
    }

    /**
     * use card attack on hero
     *
     * @param player       the player
     * @param enemy        the enemy
     * @param cardAttacker the card attacker
     * @param output       the output
     * @return the boolean
     */
    public static boolean useAttackHero(final Player player, final Player enemy,
                                        final Coordinates cardAttacker, final ArrayNode output) {
        Minion attackerCard =
                new Minion(getTable().get(cardAttacker.getX()).get(cardAttacker.getY()));
        String error = attackerCard.canAttackHero(player.getId());

        boolean gameEnded = false;

        if (error == null) {
            enemy.getHero().setHealth(enemy.getHero().getHealth() - attackerCard.getAttackDamage());
            getTable().get(cardAttacker.getX()).get(cardAttacker.getY()).setHasAttacked(true);

            if (enemy.getHero().getHealth() <= 0) {
                if (player.getId() == 1) {
                    error = "Player one killed the enemy hero.";
                } else {
                    error = "Player two killed the enemy hero.";
                }

                gameEnded = true;
            }
        } else {
            ObjectNode node = JsonNodeFactory.instance.objectNode();
            node.put("command", "useAttackHero");
            node.putPOJO("cardAttacker", cardAttacker);
            node.put("error", error);

            output.addPOJO(node);
        }

        if (gameEnded) {
            ObjectNode node = JsonNodeFactory.instance.objectNode();
            node.put("gameEnded", error);

            output.addPOJO(node);

            return true;
        }

        return false;
    }

    /**
     * use hero ability
     *
     * @param player      the player
     * @param affectedRow the affected row
     * @param output      the output
     */
    public static void useHeroAbility(final Player player,
                                      final int affectedRow, final ArrayNode output) {
        String error = player.getHero().canAttack(player);

        if (error == null) {
            if (player.getHero().getName().equals("Lord Royce")
                    || player.getHero().getName().equals("Empress Thorina")) {
                if (player.getId() == 1 && affectedRow > 1
                        || player.getId() == 2 && affectedRow < 2) {
                    error = "Selected row does not belong to the enemy.";
                }
            } else if (player.getId() == 1 && affectedRow < 2
                    || player.getId() == 2 && affectedRow > 1) {
                error = "Selected row does not belong to the current player.";
            }
        }

        if (error == null) {
            player.getHero().ability(affectedRow);
            player.setMana(player.getMana() - player.getHero().getMana());

            player.getHero().setHasAttacked(true);
        } else {
            ObjectNode node = JsonNodeFactory.instance.objectNode();

            node.put("command", "useHeroAbility");
            node.put("affectedRow", affectedRow);
            node.put("error", error);

            output.addPOJO(node);
        }
    }

    /**
     * use ability of environment card
     *
     * @param player      the player
     * @param handIndex   the hand index
     * @param affectedRow the affected row
     * @param output      the output
     */
    public static void useEnvironmentCard(final Player player, final int handIndex,
                                          final int affectedRow, final ArrayNode output) {
        String error;

        if (!environmentCardList.contains(player.getHand().getCards().get(handIndex).getName())) {
            error = "Chosen card is not of type environment.";
        } else {
            Environment card = (Environment) player.getHand().getCards().get(handIndex);
            error = card.canUseAbility(player, affectedRow);

            if (error == null) {
                card.ability(player, affectedRow);
                player.setMana(player.getMana() - card.getMana());
                player.getHand().getCards().remove(handIndex);
            }
        }

        if (error != null) {
            ObjectNode node = JsonNodeFactory.instance.objectNode();
            node.put("command", "useEnvironmentCard");
            node.put("handIdx", handIndex);
            node.put("affectedRow", affectedRow);
            node.put("error", error);

            output.addPOJO(node);
        }
    }

    /**
     * display list of frozen cards in hand
     *
     * @param player the player
     * @param output the output
     */
    public static void getCardsInHand(final Player player, final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", "getCardsInHand");
        node.put("playerIdx", player.getId());

        ArrayList<Card> temp = new ArrayList<>();
        for (Card card : player.getHand().getCards()) {
            if (environmentCardList.contains(card.getName())) {
                temp.add(temp.size(), new Environment(card));
            } else {
                temp.add(temp.size(), new Minion(card));
            }
        }

        node.putPOJO("output", temp);

        output.addPOJO(node);
    }

    /**
     * display list of cards on table
     *
     * @param output the output
     */
    public static void getCardsOnTable(final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", "getCardsOnTable");

        ArrayList<ArrayList<Minion>> temp = new ArrayList<>();

        for (int i = 0; i < getTable().size(); ++i) {
            temp.add(new ArrayList<>());
            for (Minion card : getTable().get(i)) {
                temp.get(i).add(new Minion(card));
            }
        }
        node.putPOJO("output", temp);

        output.addPOJO(node);
    }

    /**
     * display card at given position
     *
     * @param x      the x
     * @param y      the y
     * @param output the output
     */
    public static void getCardAtPosition(final int x, final int y, final ArrayNode output) {
        String error = null;
        if (y >= getTable().get(x).size()) {
            error = "No card available at that position.";
        }

        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", "getCardAtPosition");
        node.put("x", x);
        node.put("y", y);

        if (error == null) {
            node.putPOJO("output", new Minion(getTable().get(x).get(y)));
        } else {
            node.put("output", error);
        }

        output.addPOJO(node);
    }

    /**
     * display list of environment cards in hand
     *
     * @param player the player
     * @param output the output
     */
    public static void getEnvironmentCardsInHand(final Player player, final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", "getEnvironmentCardsInHand");
        node.put("playerIdx", player.getId());
        node.putPOJO("output", player.getHand().getEnvironmentCards());

        output.addPOJO(node);
    }

    /**
     * display list of frozen cards on table
     *
     * @param output the output
     */
    public static void getFrozenCardsOnTable(final ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", "getFrozenCardsOnTable");
        node.putPOJO("output", getFrozenCards());

        output.addPOJO(node);
    }
}

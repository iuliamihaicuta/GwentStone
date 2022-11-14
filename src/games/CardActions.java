package games;

import cards.Card;
import cards.environment.Environment;
import cards.environment.Firestorm;
import cards.environment.HeartHound;
import cards.environment.Winterfell;
import cards.hero.EmpressThorina;
import cards.hero.GeneralKocioraw;
import cards.hero.KingMudface;
import cards.hero.LordRoyce;
import cards.minion.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CardInput;
import fileio.Coordinates;
import player.Player;

import java.util.ArrayList;

import static cards.environment.Environment.environmentCardList;
import static table.Table.getTable;
import static table.Table.removeFromRow;

public class CardActions {
    public static void placeCard(Player player, int handIndex, ArrayNode output) {
        String error = null;
        Card card = player.getHand().getHand().get(handIndex);

        if (environmentCardList.contains(card.getName())) {
            error = "Cannot place environment card on table.";
        } else if (card.getMana() > player.getMana()) {
            error = "Not enough mana to place card on table.";
        } else {
            Minion minion = new Minion(card);
            if (minion.isOnTheFrontRow() && player.getRows().get(0).size() == 5 ||
                    !minion.isOnTheFrontRow() && player.getRows().get(1).size() == 5) {
                error = "Cannot place card on table since row is full.";
            } else if (minion.isOnTheFrontRow()){
                player.getRows().get(0).add(minion);
                player.getHand().getHand().remove(minion);
            } else {
                player.getRows().get(1).add(minion);
                player.getHand().getHand().remove(minion);
            }
        }

        if (error != null) {
            ObjectNode node = JsonNodeFactory.instance.objectNode();
            node.put("command", "placeCard");
            node.put("error", error);

            output.addPOJO(node);
        }
    }

    public static void cardUsesAttack(Player player, Coordinates cardAttacker, Coordinates cardAttacked) {
        Minion attackerCard = new Minion(getTable().get(cardAttacker.getX()).get(cardAttacker.getY()));
        String error = attackerCard.canAttack(player.getId(), cardAttacked);

        if (error == null) {
            Minion attackedCard = new Minion(getTable().get(cardAttacked.getX()).get(cardAttacked.getY()));
            attackedCard.setHealth(attackedCard.getHealth() - attackerCard.getAttackDamage());

            if (attackedCard.getHealth() <= 0)
                removeFromRow(cardAttacked);

            attackerCard.setHasAttacked(true);
        }
    }

    public static void cardUsesAbility(Player player, Coordinates cardAttacker, Coordinates cardAttacked) {
        Minion attackerCard = new Minion(getTable().get(cardAttacker.getX()).get(cardAttacker.getY()));
        String error = attackerCard.canUseAbility(player.getId(), cardAttacked);

        if (error == null) {
            switch (attackerCard.getName()) {
                case "The Ripper" -> (new TheRipper(attackerCard)).ability(player.getId(), cardAttacker, cardAttacked);
                case "The Cursed One" ->
                        (new TheCursedOne(attackerCard)).ability(player.getId(), cardAttacker, cardAttacked);
                case "Miraj" -> (new Miraj(attackerCard)).ability(player.getId(), cardAttacker, cardAttacked);
                case "Disciple" -> (new Disciple(attackerCard)).ability(player.getId(), cardAttacker, cardAttacked);
            }
        }
    }

    public static void useAttackHero(Player player, Player enemy, Coordinates cardAttacker) {
        Minion attackerCard = new Minion(getTable().get(cardAttacker.getX()).get(cardAttacker.getY()));
        String error = attackerCard.canAttackHero(player.getId());

        if (error == null) {
            enemy.getHero().setHealth(enemy.getHero().getHealth() - attackerCard.getAttackDamage());
            if (enemy.getHero().getHealth() <= 0) {
                if (player.getId() == 1)
                    error = "Player one killed the enemy hero.";
                else
                    error = "Player two killed the enemy hero.";
            }
        }
    }

    public static void useHeroAbility(Player player, int affectedRow) {
        String error = player.getHero().canAttack(player);

        if (error == null) {
            switch (player.getHero().getName()) {
                case "Lord Royce" -> (new LordRoyce(player.getHero())).ability(player.getId(), affectedRow);
                case "Empress Thorina" -> (new EmpressThorina(player.getHero())).ability(player.getId(), affectedRow);
                case "General Kocioraw" -> (new GeneralKocioraw(player.getHero())).ability(player.getId(), affectedRow);
                case "King Mudface" -> (new KingMudface(player.getHero())).ability(player.getId(), affectedRow);
            }
        }
    }

    public static void useEnvironmentCard(Player player, int handIndex, int affectedRow) {
        String error = null;

        if (!environmentCardList.contains(player.getHand().getHand().get(handIndex).getName())) {
            error = "Chosen card is not of type environment.";
        } else {
            Environment card = new Environment(player.getHand().getHand().get(handIndex));
            error = card.canUseAbility(player, affectedRow);
            if (error == null) {
                switch (card.getName()) {
                    case "Firestorm" -> (new Firestorm(card)).ability(player, affectedRow);
                    case "Winterfell" -> (new Winterfell(card)).ability(player, affectedRow);
                    case "Heart Hound" -> (new HeartHound(card)).ability(player, affectedRow);
                }
            }
        }
    }

    public static void getCardsInHand(Player player, Action action, ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", action.getCommand());
        node.put("playerIdx", action.getPlayerIdx());
        node.putPOJO("output", player.getHand().getHand());

        output.addPOJO(node);
    }

    public static void getCardsOnTable(Action action, ArrayNode output) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", action.getCommand());
        node.putPOJO("output", getTable());

        output.addPOJO(node);
    }

    public static void getCardAtPosition(int x, int y) {
        String error;
        if (getTable().get(x).size() < y)
            error = "No card at that position.";

    }

    public static void getEnvironmentCardsInHand(Player player) {

    }

    public static void getFrozenCardsOnTable() {

    }
}

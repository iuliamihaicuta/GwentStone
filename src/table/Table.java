package table;

import cards.minion.Minion;
import fileio.Coordinates;

import java.util.ArrayList;

import static constants.Constants.*;

/**
 * The type Table.
 */
public final class Table {
    private static ArrayList<ArrayList<Minion>> table;

    private Table() {
    }

    /**
     * prepares the game board for use
     */
    public static void createTable() {
        table = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            table.add(new ArrayList<>());
        }
    }

    /**
     * returns the game board
     *
     * @return the table
     */
    public static ArrayList<ArrayList<Minion>> getTable() {
        return table;
    }

    /**
     * removes given card from row
     *
     * @param coordinates the coordinates
     */
    public static void removeFromRow(final Coordinates coordinates) {
        getTable().get(coordinates.getX()).remove(coordinates.getY());
    }

    /**
     * adds given card to row
     *
     * @param rowIndex the row index
     * @param card     the card
     */
    public static void addToRow(final int rowIndex, final Minion card) {
        getTable().get(rowIndex).add(card);
    }

    /**
     * check for tank cards on enemy rows
     *
     * @param playerIndex the player index
     * @return the boolean
     */
    public static boolean enemyHasTankCards(final int playerIndex) {
        int row;
        if (playerIndex == 2) {
            row = PLAYER1_FIRST_ROW;
        } else {
            row = PLAYER2_FIRST_ROW;
        }

        for (int i = 0; i < getTable().get(row).size(); ++i) {
            if (getTable().get(row).get(i).isTank()) {
                return true;
            }
        }

        return false;
    }

    /**
     * return list of frozen cards on the table
     *
     * @return the frozen cards
     */
    public static ArrayList<Minion> getFrozenCards() {
        ArrayList<Minion> frozenCards = new ArrayList<>();

        for (ArrayList<Minion> cards : getTable()) {
            for (Minion card : cards) {
                if (card.isFrozen()) {
                    frozenCards.add(card);
                }
            }
        }

        return frozenCards;
    }

    /**
     * clear table for a new game
     */
    public static void clearTable() {
        for (ArrayList<Minion> row : Table.getTable()) {
            row.clear();
        }
    }
}

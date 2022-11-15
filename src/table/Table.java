package table;

import cards.Minion;
import fileio.Coordinates;

import java.util.ArrayList;

public class Table {
    private static ArrayList<ArrayList<Minion>> table;

    public Table () {
        table = new ArrayList<ArrayList<Minion>>();
        for (int i = 0; i < 4; i++) {
            table.add(new ArrayList<Minion>());
        }
    }

    public static ArrayList<ArrayList<Minion>> getTable() {
        return table;
    }

    public static void setTable(ArrayList<ArrayList<Minion>> table) {
        Table.table = table;
    }

    public static void removeFromRow(Coordinates coordinates) {
        getTable().get(coordinates.getX()).remove(coordinates.getY());
    }

    public static void addToRow(int rowIndex, Minion card) {
        getTable().get(rowIndex).add(card);
    }

    public static boolean enemyHasTankCards(int playerIndex) {
        if (playerIndex == 2) {
            for (int i = 0; i < getTable().get(2).size(); ++i)
                if (getTable().get(2).get(i).isTank()) {
                    return true;
                }
        }

        if (playerIndex == 1) {
            for (int i = 0; i < getTable().get(1).size(); ++i)
                if (getTable().get(1).get(i).isTank())
                    return true;
        }

        return false;
    }

    public static ArrayList<Minion> getFrozenCards() {
        ArrayList<Minion> frozenCards = new ArrayList<>();

        for (ArrayList<Minion> cards : getTable()) {
            for (Minion card : cards) {
                if (card.isFrozen())
                    frozenCards.add(card);
            }
        }

        return frozenCards;
    }

    public static void clearTable() {
        for (ArrayList<Minion> row : Table.getTable()) {
            row.clear();
        }
    }
}

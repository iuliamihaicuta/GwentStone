package table;

import cards.minion.Minion;
import fileio.Coordinates;

import java.util.ArrayList;

public class Table {
    private static ArrayList<ArrayList<Minion>> table;

    public static ArrayList<ArrayList<Minion>> getTable() {
        return table;
    }

    public static void setTable(ArrayList<ArrayList<Minion>> table) {
        Table.table = table;
    }

    public static void removeFromRow(int rowIndex, int cardIndex) {

    }

    public static void addToRow(int rowIndex, Minion card) {

    }

    // lista cu coordonatele cartilor tank
    public static ArrayList<Coordinates> enemyTankCards(int playerIndex) {
        return null;
    }
}

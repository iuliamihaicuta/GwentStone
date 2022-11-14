package table;

import cards.minion.Minion;
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

    // lista cu coordonatele cartilor tank
    public static ArrayList<Coordinates> enemyTankCards(int playerIndex) {
        ArrayList<Coordinates> enemyTankCards = new ArrayList<Coordinates>();

        if (playerIndex == 2) {
            for (int i = 0; i < getTable().get(2).size(); ++i)
                if (getTable().get(2).get(i).isTank())
                    enemyTankCards.add(new Coordinates(2, i));

            for (int i = 0; i < getTable().get(3).size(); ++i)
                if (getTable().get(3).get(i).isTank())
                    enemyTankCards.add(new Coordinates(3, i));
        }

        if (playerIndex == 1) {
            for (int i = 0; i < getTable().get(0).size(); ++i)
                if (getTable().get(0).get(i).isTank())
                    enemyTankCards.add(new Coordinates(0, i));

            for (int i = 0; i < getTable().get(1).size(); ++i)
                if (getTable().get(1).get(i).isTank())
                    enemyTankCards.add(new Coordinates(1, i));
        }

        return enemyTankCards;
    }
}

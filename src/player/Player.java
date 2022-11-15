package player;

import cards.Card;
import cards.Minion;
import cards.environment.Environment;
import cards.environment.Firestorm;
import cards.environment.HeartHound;
import cards.environment.Winterfell;
import cards.hero.Hero;
import cards.minion.Disciple;
import cards.minion.Miraj;
import cards.minion.TheCursedOne;
import cards.minion.TheRipper;
import deck.Decks;
import fileio.DecksInput;
import table.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Player {
    private final Decks decks;
    private int mana;
    private static int gamesPlayed = 0;
    private int totalGamesWon = 0;
    private final int id;
    private ArrayList<Card> usedDeck;
    private final Hand hand;
    private final ArrayList<ArrayList<Minion>> rows;
    private Hero hero;

    public Player(DecksInput decks, ArrayList<Minion> frontRow, ArrayList<Minion> backRow, int id) {
        this.decks = new Decks(decks);
        hand = new Hand();
        this.id = id;

        rows = new ArrayList<>();
        this.rows.add(0, frontRow);
        this.rows.add(1, backRow);
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public static int getGamesPlayed() {
        return gamesPlayed;
    }

    public static void setGamesPlayed(int gamesPlayed) {
        Player.gamesPlayed = gamesPlayed;
    }

    public int getTotalGamesWon() {
        return totalGamesWon;
    }

    public void setTotalGamesWon(int totalGamesWon) {
        this.totalGamesWon = totalGamesWon;
    }

    public ArrayList<Card> getUsedDeck() {
        return usedDeck;
    }

    public int getId() {
        return id;
    }

    public Hand getHand() {
        return hand;
    }

    public ArrayList<ArrayList<Minion>> getRows() {
        return rows;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void shuffleDeck(int deckIndex, int shuffleSeed) {
        usedDeck = new ArrayList<Card>();

        for (Card card : decks.getDecks().get(deckIndex)) {
            if (Environment.environmentCardList.contains(card.getName())) {
                switch (card.getName()) {
                    case "Firestorm" -> usedDeck.add(new Firestorm(card));
                    case "Heart Hound" -> usedDeck.add(new HeartHound(card));
                    case "Winterfell" -> usedDeck.add(new Winterfell(card));
                    default -> System.err.println("ERROR: Environment card does not exist.");
                }
            }
            else {
                switch (card.getName()) {
                    case "Disciple" -> usedDeck.add(new Disciple(card));
                    case "Miraj" -> usedDeck.add(new Miraj(card));
                    case "The Cursed One" -> usedDeck.add(new TheCursedOne(card));
                    case "The Ripper" -> usedDeck.add(new TheRipper(card));
                    default -> usedDeck.add(new Minion(card));
                }
            }
        }

        Collections.shuffle(usedDeck, new Random(shuffleSeed));
    }

    public void startTurn(int round) {
        mana += Math.min(round, 10);
        if (usedDeck.size() > 0) {
            hand.addToHand(usedDeck.remove(0));
        }
    }
}

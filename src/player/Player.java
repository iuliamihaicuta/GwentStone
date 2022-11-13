package player;

import cards.Card;
import deck.Decks;
import fileio.DecksInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Player {
    private int deckIndex;
    private Decks decks;
    private int mana;
    private static int gamesPlayed = 0;
    private int totalGamesWon = 0;

    private int id;

    private ArrayList<Card> usedDeck;

    public Player(DecksInput decks) {
        this.decks = new Decks(decks);
    }

    public Decks getDecks() {
        return decks;
    }

    public void setDecks(Decks decks) {
        this.decks = decks;
    }

    public int getDeckIndex() {
        return deckIndex;
    }

    public void setDeckIndex(int deckIndex) {
        this.deckIndex = deckIndex;
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

    public void setUsedDeck(ArrayList<Card> usedDeck) {
        this.usedDeck = usedDeck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void shuffleDeck(int deckIndex, int shuffleSeed) {
        usedDeck = new ArrayList<Card>();

        for (Card card : decks.getDecks().get(deckIndex))
            usedDeck.add(new Card(card));

        Collections.shuffle(usedDeck, new Random(shuffleSeed));
    }
}

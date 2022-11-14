package player;

import cards.Card;
import cards.hero.Hero;
import cards.minion.Minion;
import deck.Decks;
import fileio.DecksInput;
import table.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Player {
    private int deckIndex;
    private Decks decks;
    private int mana;
    private static int gamesPlayed = 0;
    private int totalGamesWon = 0;
    private final int id;
    private ArrayList<Card> usedDeck;
    private Hand hand;
    private ArrayList<ArrayList<Minion>> rows;
    private Hero hero;

    public Player(DecksInput decks, ArrayList<Minion> frontRow, ArrayList<Minion> backRow, int id) {
        this.decks = new Decks(decks);
        hand = new Hand();
        rows = new ArrayList<ArrayList<Minion>>();
        this.rows.add(frontRow);
        this.rows.add(backRow);
        this.id = id;
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

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public ArrayList<ArrayList<Minion>> getRows() {
        return rows;
    }

    public void setRows(ArrayList<ArrayList<Minion>> rows) {
        this.rows = rows;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void shuffleDeck(int deckIndex, int shuffleSeed) {
        usedDeck = new ArrayList<Card>();

        for (Card card : decks.getDecks().get(deckIndex))
            usedDeck.add(new Card(card));

        Collections.shuffle(usedDeck, new Random(shuffleSeed));
    }

    public void startTurn(int round) {
        mana += Math.min(round, 10);
//        if (usedDeck.size() > 0)
//            hand.getHand().add(usedDeck.remove(0));

        hand.getHand().add(new Card(usedDeck.get(0)));
        usedDeck.remove(0);
    }

}

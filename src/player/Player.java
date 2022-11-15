package player;

import cards.Card;
import cards.minion.Minion;
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

import static constants.Constants.MAX_MANA;

/**
 * The type Player.
 */
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

    /**
     * Instantiates a new Player.
     *
     * @param decks    the decks
     * @param frontRow the front row
     * @param backRow  the back row
     * @param id       the id
     */
    public Player(final DecksInput decks, final ArrayList<Minion> frontRow,
                  final ArrayList<Minion> backRow, final int id) {
        this.decks = new Decks(decks);
        hand = new Hand();
        this.id = id;

        rows = new ArrayList<>();
        this.rows.add(0, frontRow);
        this.rows.add(1, backRow);
    }

    /**
     * get player mana
     *
     * @return the mana
     */
    public int getMana() {
        return mana;
    }

    /**
     * set player mana
     *
     * @param mana the mana
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    /**
     * get games played
     *
     * @return the games played
     */
    public static int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * set games played
     *
     * @param gamesPlayed the games played
     */
    public static void setGamesPlayed(final int gamesPlayed) {
        Player.gamesPlayed = gamesPlayed;
    }

    /**
     * get player wins
     *
     * @return the total games won
     */
    public int getTotalGamesWon() {
        return totalGamesWon;
    }

    /**
     * set player won games
     *
     * @param totalGamesWon the total games won
     */
    public void setTotalGamesWon(final int totalGamesWon) {
        this.totalGamesWon = totalGamesWon;
    }

    /**
     * get player current deck
     *
     * @return the used deck
     */
    public ArrayList<Card> getUsedDeck() {
        return usedDeck;
    }

    /**
     * get player id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * get player hand
     *
     * @return the hand
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * get player rows
     *
     * @return the rows
     */
    public ArrayList<ArrayList<Minion>> getRows() {
        return rows;
    }

    /**
     * get player hero
     *
     * @return the hero
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * set player hero
     *
     * @param hero the hero
     */
    public void setHero(final Hero hero) {
        this.hero = hero;
    }

    /**
     * set the deck the player uses in the current game
     *
     * @param deckIndex   the deck index
     * @param shuffleSeed the shuffle seed
     */
    public void shuffleDeck(final int deckIndex, final int shuffleSeed) {
        usedDeck = new ArrayList<>();

        for (Card card : decks.getDecks().get(deckIndex)) {
            if (Environment.environmentCardList.contains(card.getName())) {
                switch (card.getName()) {
                    case "Firestorm" -> usedDeck.add(new Firestorm(card));
                    case "Heart Hound" -> usedDeck.add(new HeartHound(card));
                    case "Winterfell" -> usedDeck.add(new Winterfell(card));
                    default -> System.err.println("ERROR: Environment card does not exist.");
                }
            } else {
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

    /**
     * prepare player for the start of the game
     *
     * @param round the round
     */
    public void startTurn(final int round) {
        mana += Math.min(round, MAX_MANA);
        if (usedDeck.size() > 0) {
            hand.addToHand(usedDeck.remove(0));
        }
    }
}

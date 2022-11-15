package cards;

import fileio.CardInput;

import java.util.ArrayList;

/**
 * The type Card.
 */
public class Card {
    private final int mana;
    private int attackDamage;
    private int health;
    private final String description;
    private final ArrayList<String> colors;
    private final String name;

    /**
     * Instantiates a new Card.
     *
     * @param cardInput the card input
     */
    public Card(final CardInput cardInput) {
        this.mana = cardInput.getMana();
        this.attackDamage = cardInput.getAttackDamage();
        this.health = cardInput.getHealth();
        this.description = cardInput.getDescription();
        this.colors = cardInput.getColors();
        this.name = cardInput.getName();
    }

    /**
     * Instantiates a new Card.
     *
     * @param card the card
     */
    public Card(final Card card) {
        this.mana = card.mana;
        this.attackDamage = card.attackDamage;
        this.health = card.health;
        this.description = card.description;
        this.colors = card.colors;
        this.name = card.name;
    }

    /**
     * get card mana
     *
     * @return the mana
     */
    public int getMana() {
        return mana;
    }

    /**
     * get card attack damage
     *
     * @return the attack damage
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     * set card attack damage
     *
     * @param attackDamage the attack damage
     */
    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * get card health
     *
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * set card health
     *
     * @param health the health
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * get card description
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * get card colors
     *
     * @return the colors
     */
    public ArrayList<String> getColors() {
        return colors;
    }

    /**
     * get card name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * override toString() method
     */
    @Override
    public String toString() {
        return "CardInput{"
                +  "mana="
                + mana
                +  ", attackDamage="
                + attackDamage
                + ", health="
                + health
                +  ", description='"
                + description
                + '\''
                + ", colors="
                + colors
                + ", name='"
                +  ""
                + name
                + '\''
                + '}';
    }
}

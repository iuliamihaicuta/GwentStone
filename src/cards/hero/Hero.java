package cards.hero;

import cards.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fileio.CardInput;
import player.Player;

import static constants.Constants.HERO_HEALTH;

/**
 * The type Hero.
 */
public class Hero extends Card {
    // for output
    @JsonIgnore
    private boolean hasAttacked = false;
    @JsonIgnore
    private int attackDamage;

    /**
     * Instantiates a new Hero.
     *
     * @param cardInput the card input
     */
    public Hero(final CardInput cardInput) {
        super(cardInput);
        setHealth(HERO_HEALTH);
    }

    /**
     * Instantiates a new Hero.
     *
     * @param card the card
     */
    public Hero(final Card card) {
        super(card);
    }

    /**
     * check if hero can attack
     *
     * @param player the player
     * @return the string
     */
    public String canAttack(final Player player) {
        if (player.getMana() < getMana()) {
            return "Not enough mana to use hero's ability.";
        } else if (hasAttacked) {
            return "Hero has already attacked this turn.";
        }

        return null;
    }

    /**
     * set card hasAttacked status
     *
     * @param hasAttacked the has attacked
     */
    public void setHasAttacked(final boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    /**
     * set card ability
     *
     * @param row the row
     */
    public void ability(final int row) {
    }
}

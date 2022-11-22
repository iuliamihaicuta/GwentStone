// Copyright Mihaicuta Iulia, 324CA

# GwentStone

---

## Program Flow

After the input files are read, the data is entered into the
program via the start function (class Start). There the table 
(instance of Table) and players (instance of Player) are created,
after which the game starts. The starting player and the
attributes of each competitor (current deck, hero) are set
to be used.

> Cards are added to decks and set as the player's hero as
> their most restrictive type.

Players take turns performing different actions. The end of a
turn is marked by the explicit `endPlayerTurn` command applied to
the current player. After both players have played their turn,
a round ends. The commands that can be entered are the following:

1. `endPlayerTurn` <br />
- the current player's turn ends
- frozen cards and those that attacked can be used again
2. `placeCard` <br />
- check if the desired card in the player's hand (instance of Hand)
is a minion and can be placed
- place the card on the appropriate row and discard it from hand
- the user's mana is reduced
3. `cardUsesAttack`
- check if selected card can attack
- change the health attribute of the attacked card and remove
it from the row if necessary
4. `cardUsesAbility`
- check if selected card can use ability
- apply the changes to the desired card
5. `useAttackHero`
- check if selected card can attack the hero
- if the enemy hero is dead, end the game and display the
appropriate message
6. `useHeroAbility`
- if it can attack, use the special ability specific
to the player's hero
7. `useEnvironmentCard`
- check if the chosen card is an environmental one and
if its ability can be used
- use the card's specific ability on the attacked row
8. `getCardAtPosition`
- display the wanted card
9. `getPlayerDeck` | `getPlayerTurn` | `getPlayerHero`
| `getPlayerMana` | `getCardsInHand`
- display the desired argument corresponding to the player
10. `getEnvironmentCardsInHand` | `getFrozenCardsOnTable`
| `getCardsOnTable`
- display the desired cards
11. `getTotalGamesPlayed` | `getPlayerOneWins` | `getPlayerTwoWins`
- get game statistics

> If the command could not be executed, the error message
> is saved in the output

At the end of each game, the programs return to the desired point,
keeping the same active players. Also, the game table is cleared.

---

## Strucuture of the Program

Based on the card class we create the 3 classes needed to
retain the types used: Minion, Environment and Hero. Each of these
classes is inherited by other classes that represent more
restrictive types:

- Card
  - Environment
    - Firestorm
    - Heart Hound
    - Winterfell
  - Hero
    - Empress Thorina
    - General Kocioraw
    - King Mudface
    - Lord Royce
  - Minion
    - Disciple
    - Miraj
    - The Cursed One
    - The Ripper

> Special minion cards are implemented using the
SpecialAbility interface.

Players are created using the Player class. In an object of this
class we store information such as player decks, mana, current
deck, hero, cards in hand, and corresponding rows on the board.
The total number of games played and won is kept.

There are also designated classes for the game and the actions used,
as well as those for the table and the player's hand.

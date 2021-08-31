/*
 * Copyright (c) 2021 Andrew SASSOYE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.asassoye.esi.atlir4.skyjo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Player.
 */
public class Player implements PlayerInterface {

    private final int id;
    private int totalScore;
    private final String name;
    private final Card[][] cards;

    /**
     * Instantiates a new Player.
     *
     * @param id   the id
     * @param name the name
     */
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.cards = new Card[LINES][COLUMNS];
        this.totalScore = 0;
    }

    /**
     * Instantiates a new Player without name.
     *
     * @param id the id of the player
     */
    public Player(int id) {
        this(id, String.format("Player %d", id));
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return name;
    }

    /**
     * Validate points of actual round.
     *
     * @param doubled if the points have to be doubled when validated.
     */
    public void validatePoints(boolean doubled) {
        if (!isCompletelyReveled()) {
            throw new IllegalStateException("You cannot go to next round if you have not finished the board.");
        }

        totalScore += doubled ? getPoints() * 2 : getPoints();
    }

    /**
     * Next round setting.
     *
     * @param deck the deck to distribute new cards
     */
    public void next(Deck deck) {
        cleanCards();
        placeCards(deck);
    }

    /**
     * Place cards in players hand.
     *
     * @param deck the deck to take cards of
     */
    public void placeCards(Deck deck) {
        for (var i = 0; i < cards.length; ++i) {
            for (var j = 0; j < cards[i].length; ++j) {
                if (cards[i][j] != null) {
                    throw new IllegalStateException("table not empty");
                }
                cards[i][j] = deck.pop();
            }
        }
    }

    /**
     * Place cards in players hand.
     *
     * @param cards fixed card hand
     */
    public void placeCards(Card[][] cards) {
        if (cards.length != this.cards.length) {
            throw new IllegalArgumentException("cards have to be the same size as the player table");
        }

        for (var i = 0; i < this.cards.length; ++i) {
            for (var j = 0; j < this.cards[i].length; ++j) {
                if (this.cards[i][j] != null) {
                    throw new IllegalStateException("table not empty");
                }
                this.cards[i][j] = cards[i][j];
            }
        }
    }

    /**
     * Remove all cards
     */
    public void cleanCards() {
        for (Card[] card : this.cards) {
            Arrays.fill(card, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Card getCard(int x, int y) {
        return cards[y][x];
    }

    /**
     * Sets card.
     *
     * @param newCard the new card
     * @param x       the x position
     * @param y       the y position
     */
    public void setCard(Card newCard, int x, int y) {
        cards[y][x] = newCard;
    }

    /**
     * Show all cards.
     */
    public void showAllCards() {
        for (var y = 0; y < LINES; ++y) {
            for (var x = 0; x < COLUMNS; ++x) {
                Card card = getCard(x, y);
                card.show();
            }
        }
    }

    /**
     * Show a specific card.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public void showCard(int x, int y) {
        Card card = getCard(x, y);

        if (card.isVisible()) {
            throw new IllegalStateException("The card is already visible");
        }

        card.show();
    }

    /**
     * Number of shown cards in hand.
     *
     * @return nb cards
     */
    public int shownCards() {
        int total = 0;
        for (var line : cards) {
            for (var card : line) {
                if (card.isVisible()) {
                    total++;
                }
            }
        }
        return total;
    }

    /**
     * Delete full columns (columns with 3 same numbers).
     */
    public void deleteFullLines() {
        columns:
        for (var j = 0; j < COLUMNS; ++j) {
            List<Card> column = getColumn(j);
            int value = 0;
            for (var i = 0; i < column.size(); ++i) {
                Card card = column.get(i);

                if (!card.isVisible()) {
                    continue columns;
                }

                if (i == 0) {
                    value = card.getValue();
                } else {
                    if (value != card.getValue()) {
                        continue columns;
                    }
                }
            }

            deleteColumn(j);
        }
    }

    /**
     * {@inheritDoc}
     */
    public int getPoints() {
        int total = 0;
        for (Card[] line : cards) {
            for (Card card : line) {
                if (card.isVisible()) {
                    total += card.getValue();
                }
            }
        }

        return total;
    }

    /**
     * Is completely reveled. (all cards are shown)
     *
     * @return true is completely revealed
     */
    public boolean isCompletelyReveled() {
        for (Card[] line : cards) {
            for (Card card : line) {
                if (!card.isVisible()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Exchange card.
     *
     * @param newCard the new card
     * @param x       the x position
     * @param y       the y position
     * @return the card that was placed before newCard
     */
    public Card exchangeCard(Card newCard, int x, int y) {
        Card old = getCard(x, y);
        setCard(newCard, x, y);
        newCard.show();

        return old;
    }

    /**
     * Gets full column.
     *
     * @param column the column number
     * @return the column in form of a list
     */
    public List<Card> getColumn(int column) {
        List<Card> values = new ArrayList<>();

        for (var i = 0; i < LINES; ++i) {
            values.add(cards[i][column]);
        }

        return values;
    }

    /**
     * Delete column.
     *
     * @param column the column
     */
    public void deleteColumn(int column) {
        for (var i = 0; i < LINES; ++i) {
            cards[i][column] = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    public int getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public int getTotalScore() {
        return totalScore;
    }
}

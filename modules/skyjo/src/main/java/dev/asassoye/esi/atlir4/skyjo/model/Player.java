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
import java.util.List;

public class Player {
    public static final int COLUMNS = 4;
    public static final int LINES = 3;
    private final int id;
    private final String name;
    private final Card[][] cards;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.cards = new Card[LINES][COLUMNS];
    }

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

    public String getName() {
        return name;
    }

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

    public Card getCard(int x, int y) {
        return cards[y][x];
    }

    public void setCard(Card newCard, int x, int y) {
        cards[y][x] = newCard;
    }

    public void showCard(int x, int y) {
        Card card = getCard(x, y);

        if (card.isVisible()) {
            throw new IllegalStateException("The card is already visible");
        }

        card.show();
    }

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

    public Card exchangeCard(Card newCard, int x, int y) {
        Card old = getCard(x, y);
        setCard(newCard, x, y);
        newCard.show();

        return old;
    }

    public List<Card> getColumn(int column) {
        List<Card> values = new ArrayList<>();

        for (var i = 0; i < LINES; ++i) {
            values.add(cards[i][column]);
        }

        return values;
    }

    public void deleteColumn(int column) {
        for (var i = 0; i < LINES; ++i) {
            cards[i][column] = null;
        }
    }

    public int getId() {
        return id;
    }
}

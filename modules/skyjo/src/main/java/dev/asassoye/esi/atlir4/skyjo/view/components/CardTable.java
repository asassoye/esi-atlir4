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

package dev.asassoye.esi.atlir4.skyjo.view.components;

import dev.asassoye.esi.atlir4.skyjo.model.CardInterface;
import dev.asassoye.esi.atlir4.skyjo.view.utils.ResourceStylable;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

/**
 * The Card table. (Collection of cards)
 */
public class CardTable extends GridPane implements ResourceStylable {
    /**
     * The constant COLUMNS.
     */
    public static final int COLUMNS = 4;
    /**
     * The constant ROWS.
     */
    public static final int ROWS = 3;
    /**
     * The constant bHeight.
     */
    public static final double bHeight = Card.bHeight * ROWS;
    /**
     * The constant bWidth.
     */
    public static final double bWidth = Card.bHeight * COLUMNS;
    /**
     * The constant gap.
     */
    public static final double gap = 10.0;

    private final List<Card> cards;

    /**
     * Instantiates a new Card table.
     */
    public CardTable() {
        this.cards = new ArrayList<>();
        this.getStyleClass().add("cardTable");

        applyStyles("/styles/components/cardtable.css", this);

        this.prefWidthProperty().bind(this.widthProperty().divide(bWidth / bHeight));
        this.prefWidthProperty().bind(this.heightProperty().multiply(bWidth / bHeight));
    }

    /**
     * Connect choose table card action.
     *
     * @param eventHandler the event handler
     */
    public void connectChooseTableCardAction(EventHandler<MouseEvent> eventHandler) {
        for (var card : cards) {
            card.connectChooseTableCardAction(eventHandler);
        }
    }

    /**
     * Add card.
     *
     * @param card the card
     */
    public void addCard(Card card) {
        this.cards.add(card);
        this.add(card, card.getX(), card.getY());
        card.prefHeightProperty().bind(this.heightProperty().divide(ROWS).subtract(gap * ROWS));
        card.prefWidthProperty().bind(this.widthProperty().divide(COLUMNS).subtract(gap * COLUMNS));
    }

    /**
     * Add card.
     *
     * @param card the card
     * @param x    the x
     * @param y    the y
     */
    public void addCard(CardInterface card, int x, int y) {
        addCard(new Card(x, y, card.getValue(), card.isVisible()));
    }

    /**
     * Add cards.
     *
     * @param cards the cards
     */
    public void addCards(List<Card> cards) {
        for (var card : cards) {
            addCard(card);
        }
    }

    /**
     * Remove card.
     *
     * @param card the card
     */
    public void removeCard(Card card) {
        this.getChildren().remove(card);
        this.cards.remove(card);
    }

    /**
     * Gets card.
     *
     * @param x the x
     * @param y the y
     * @return the card
     */
    public Card getCard(int x, int y) {
        for (var card : cards) {
            if (card != null) {
                if (card.getX() == x && card.getY() == y) {
                    return card;
                }
            }
        }
        return null;
    }

    /**
     * Update card.
     *
     * @param card the card
     * @param x    the x
     * @param y    the y
     */
    public void updateCard(CardInterface card, int x, int y) {
        Card cardNode = getCard(x, y);

        if (cardNode != null) {
            if (card == null) {
                removeCard(cardNode);
            } else {
                cardNode.update(card);
            }
        } else {
            if (card != null) {
                addCard(card, x, y);
            }
        }
    }
}

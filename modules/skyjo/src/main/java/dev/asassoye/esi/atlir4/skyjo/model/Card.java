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

import java.util.Objects;

/**
 * Card class
 */
public class Card {
    /**
     * Value of the Card
     */
    private final int value;

    /**
     * Visibility of the card for the players
     */
    private boolean visible;

    /**
     * Instantiates a new Card.
     *
     * @param value   Value of the Card
     * @param visible Visibility of the card
     */
    public Card(int value, boolean visible) {
        if (value < -2 || value > 12) {
            throw new IllegalArgumentException("Card value has to be between -2 and 12");
        }

        this.value = value;
        this.visible = visible;
    }

    /**
     * Instantiates a new Card.
     *
     * @param value Value of the Card
     */
    public Card(int value) {
        this(value, false);
    }

    /**
     * Gets value.
     *
     * @return the value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * Is visible boolean.
     *
     * @return Card is visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets the visibility of the card
     *
     * @param visible the visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Show the card (change visible to true)
     */
    public void show() {
        this.visible = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return this.value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

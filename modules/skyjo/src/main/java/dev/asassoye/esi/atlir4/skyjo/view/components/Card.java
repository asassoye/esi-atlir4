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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.util.logging.Logger;

/**
 * The Card.
 */
public class Card extends Button implements ResourceStylable {
    private static final Logger LOGGER = Logger.getLogger("card");
    /**
     * The constant bHeight.
     */
    public static final double bHeight = 1195;
    /**
     * The constant bWidth.
     */
    public static final double bWidth = 771;
    private int value;
    private boolean visible;
    private int x;
    private int y;

    /**
     * Instantiates a new Card.
     *
     * @param x       the x position
     * @param y       the y position
     * @param value   the value of the card
     * @param visible the visibility
     */
    public Card(int x, int y, int value, boolean visible) {
        this.x = x;
        this.y = y;
        setValue(value, true);
        setVisibility(visible, true);

        this.getStyleClass().add("card");
        applyStyles("/styles/components/card.css", this);

        this.prefHeightProperty().bind(this.widthProperty().divide(bWidth / bHeight));
        this.prefWidthProperty().bind(this.heightProperty().multiply(bWidth / bHeight));
    }

    /**
     * Instantiates a new Card.
     *
     * @param card the card
     * @param x    the x position
     * @param y    the y position
     */
    public Card(CardInterface card, int x, int y) {
        this(x, y, card.getValue(), card.isVisible());
    }

    /**
     * Class by value string.
     *
     * @param value the value
     * @return the string
     */
    public static String classByValue(int value) {
        switch (value) {
            case -2:
                return "minTwo";
            case -1:
                return "minOne";
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            case 8:
                return "eight";
            case 9:
                return "nine";
            case 10:
                return "ten";
            case 11:
                return "eleven";
            case 12:
                return "twelve";
            case 0:
            default:
                return "zero";
        }
    }

    /**
     * Connect choose table card action.
     *
     * @param eventHandler the event handler
     */
    public void connectChooseTableCardAction(EventHandler<MouseEvent> eventHandler) {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    /**
     * Update.
     *
     * @param value   the value
     * @param visible the visible
     */
    public void update(int value, boolean visible) {
        setValue(value);
        setVisibility(visible);
    }

    /**
     * Update.
     *
     * @param card the card
     */
    public void update(CardInterface card) {
        update(card.getValue(), card.isVisible());
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param newValue the new value
     */
    public void setValue(int newValue) {
        setValue(newValue, false);
    }

    /**
     * Sets value.
     *
     * @param newValue the new value
     * @param init     the init
     */
    public void setValue(int newValue, boolean init) {
        if (newValue != this.value || init) {
            this.getStyleClass().remove(classByValue(this.value));
            this.value = newValue;
            this.getStyleClass().add(classByValue(this.value));
        }
    }

    /**
     * Show the card.
     */
    public void show() {
        show(false);
    }

    /**
     * Show the card.
     *
     * @param init the init
     */
    public void show(boolean init) {
        if (!this.visible || init) {
            this.getStyleClass().remove("hidden");
            this.visible = true;
        }
    }

    /**
     * Sets visibility.
     *
     * @param visible the visible
     * @param init    the init
     */
    public void setVisibility(boolean visible, boolean init) {
        if (visible) {
            show(init);
        } else {
            hide(init);
        }
    }

    /**
     * Sets visibility.
     *
     * @param visible the visible
     */
    public void setVisibility(boolean visible) {
        setVisibility(visible, false);
    }

    /**
     * Hide.
     */
    public void hide() {
        hide(false);
    }

    /**
     * Hide card.
     *
     * @param init the init
     */
    public void hide(boolean init) {
        if (this.visible || init) {
            this.getStyleClass().add("hidden");
            this.visible = false;
        }
    }
}

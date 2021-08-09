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

import dev.asassoye.esi.atlir4.skyjo.view.utils.ResourceStylable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Card extends Button implements ResourceStylable {
    private static final Logger LOGGER = Logger.getLogger("card");
    public static final double bHeight = 1195;
    public static final double bWidth = 771;
    private final int value;
    private boolean visible;
    private int x;
    private int y;

    public Card(int x, int y, int value, boolean visible) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.visible = visible;

        this.getStyleClass().add("card");
        applyStyles("/styles/components/card.css", this);
        if (!this.visible) {
            this.getStyleClass().add("hidden");
        }
        this.getStyleClass().add(classByValue(this.value));

        this.prefHeightProperty().bind(this.widthProperty().divide(bWidth / bHeight));
        this.prefWidthProperty().bind(this.heightProperty().multiply(bWidth / bHeight));

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            LOGGER.log(Level.INFO, "Card clicked: (" + this.x + this.y + ") (" + this.value + ")");
            if (this.visible) {
                this.getStyleClass().add("hidden");
                this.visible = false;
            } else {
                this.getStyleClass().remove("hidden");
                this.visible = true;
            }
        });
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void show() {
        this.visible = true;
    }

    public void hide() {
        this.visible = false;
    }

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
}

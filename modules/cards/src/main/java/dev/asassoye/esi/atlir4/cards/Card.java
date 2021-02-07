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

package dev.asassoye.esi.atlir4.cards;

public class Card {
    private final Color color;

    private final Value value;

    public Card(Color color, Value value) {
        if (color == null) {
            throw new IllegalArgumentException("card color must be defined");
        }

        if (value == null) {
            throw new IllegalArgumentException("card value must be defined");
        }

        this.color = color;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.toString(true);
    }

    public String toString(boolean full) {
        if (full) {
            return this.value.toString() + " de " + this.color.toString();
        } else {
            return this.color.toString(true) + this.value.toString(false);
        }
    }

    public Color getColor() {
        return color;
    }

    public Value getValue() {
        return value;
    }
}

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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import dev.asassoye.esi.atlir4.cards.Exceptions.EmptyDeckException;

public class Deck {
    List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();

        for (Color color : Color.values()) {
            for (Value value : Value.values()) {
                this.cards.add(new Card(color, value));
            }
        }
    }

    public Deck(Collection<Card> cards) {
        this.cards = new ArrayList<>();
        this.cards.addAll(cards);
    }

    public Deck shuffle() {
        Collections.shuffle(cards);
        return this;
    }

    public boolean isEmpty() {
        return this.cards.isEmpty();
    }

    public Card hit() {
        if (this.isEmpty()) {
            throw new EmptyDeckException();
        }
        Card card = this.cards.get(0);
        this.cards.remove(0);

        return card;
    }

    public int size() {
        return cards.size();
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }
}

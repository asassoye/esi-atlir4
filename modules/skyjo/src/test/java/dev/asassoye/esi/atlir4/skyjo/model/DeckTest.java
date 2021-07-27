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

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    @Nested
    class Constructor {
        private final Map<Integer, Integer> basicDeck = Map.ofEntries(
                Map.entry(-2, 5),
                Map.entry(0, 15),
                Map.entry(-1, 10),
                Map.entry(1, 10),
                Map.entry(2, 10),
                Map.entry(3, 10),
                Map.entry(4, 10),
                Map.entry(5, 10),
                Map.entry(6, 10),
                Map.entry(7, 10),
                Map.entry(8, 10),
                Map.entry(9, 10),
                Map.entry(10, 10),
                Map.entry(11, 10),
                Map.entry(12, 10)
        );

        @Test
        void basicDeck() {
            Deck deck = new Deck(basicDeck, false);

            for (var e : basicDeck.entrySet()) {
                assertEquals(deck.count(e.getKey()), e.getValue());
            }

            assertEquals(deck.size(), 150);
        }

        @Test
        void emptyDeck() {
            Deck deck = new Deck();

            assertEquals(deck.size(), 0);
        }
    }

    @Nested
    class Methods {
        @Test
        void addOneCard() {
            Deck deck = new Deck();
            Card card = new Card(0);

            deck.add(card);
            assertTrue(deck.contains(card));
        }

        @Test
        void addMultipleCards() {
            Deck deck = new Deck();
            var cards = new ArrayList<Card>();
            cards.add(new Card(0));
            cards.add(new Card(-2));
            cards.add(new Card(10));
            cards.add(new Card(5));

            deck.add(cards);
            for (var card : cards) {
                assertTrue(deck.contains(card));
            }
        }

        @Test
        void pop() {
            Deck deck = new Deck();
            Card card = new Card(0);
            deck.add(card);

            var popped = deck.pop();

            assertAll(
                    () -> assertEquals(deck.size(), 0),
                    () -> assertEquals(card, popped)
            );
        }

        @Test
        void popEmptyDeck() {
            Deck deck = new Deck();

            assertThrows(
                    RuntimeException.class,
                    deck::pop
            );
        }

        @Test
        void shuffel() {
            Deck deck = new Deck();
            Deck deck2 = new Deck();
            var cards = new ArrayList<Card>();
            cards.add(new Card(0));
            cards.add(new Card(-2));
            cards.add(new Card(10));
            cards.add(new Card(5));

            deck.add(cards);
            deck2.add(cards);

            assertEquals(deck.getCards(), deck2.getCards());

            deck2.shuffle();

            assertNotEquals(deck.getCards(), deck2.getCards());
        }
    }
}
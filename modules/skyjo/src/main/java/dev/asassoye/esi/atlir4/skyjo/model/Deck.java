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

import java.util.*;

/**
 * Deck class
 */
public class Deck {
    public static final Map<Integer, Integer> basicDeck = Map.ofEntries(
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

    private final List<Card> cards;

    /**
     * Instantiates a new Deck.
     *
     * @param initMap A map of cards to add to the deck. Ex: {{-2, 5}, {0, 15}}, creates 5 cards of value -2 and 15 cards
     *                of value 0
     * @param visible visibility of the card (returned or not)
     */
    public Deck(Map<Integer, Integer> initMap, boolean visible) {
        this.cards = new ArrayList<>();

        for (var e : initMap.entrySet()) {
            for (var i = 0; i < e.getValue(); ++i) {
                this.cards.add(new Card(e.getKey(), visible));
            }
        }
    }

    public Deck(Map<Integer, Integer> initMap) {
        this(initMap, false);
    }

    /**
     * Instantiates an empty Deck.
     */
    public Deck() {
        this.cards = new ArrayList<>();
    }

    /**
     * Size of the deck
     *
     * @return size of the deck
     */
    public int size() {
        return this.cards.size();
    }

    /**
     * Gets cards (by copy)
     *
     * @return a list of all cards
     */
    public List<Card> getCards() {
        return new ArrayList<>(this.cards);
    }

    /**
     * Add a single card
     *
     * @param card the card to add
     */
    public void add(Card card) {
        this.cards.add(card);
    }

    /**
     * Add multiple cards
     *
     * @param cards the collection of cards to add
     */
    public void add(Collection<Card> cards) {
        this.cards.addAll(cards);
    }

    /**
     * Check if the deck contains a certain card
     *
     * @param card Value to check
     * @return true if the card is present, false if not
     */
    public boolean contains(Card card) {
        return this.cards.contains(card);
    }

    /**
     * Pop card on the top of the deck
     *
     * @return the card that is removed from the deck
     */
    public Card pop() {
        if (this.size() == 0) {
            throw new RuntimeException("You cannot pop a Card in an empty Deck..");
        }

        Card card = this.cards.get(this.size() - 1);
        this.cards.remove(this.cards.size() - 1);

        return card;
    }

    /**
     * Count how many cards have a certain value
     *
     * @param value the value of the cards to count
     * @return the number of cards with the value
     */
    public int count(int value) {
        int count = 0;

        for (var card : cards)
            if (card.getValue() == value)
                ++count;

        return count;
    }

    /**
     * Shuffles the deck
     */
    public void shuffle() {
        Collections.shuffle(this.cards);
    }
}

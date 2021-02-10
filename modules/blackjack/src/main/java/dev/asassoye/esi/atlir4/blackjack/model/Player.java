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

package dev.asassoye.esi.atlir4.blackjack.model;

import java.util.HashSet;
import java.util.Set;

import dev.asassoye.esi.atlir4.blackjack.model.Exceptions.ScoreOverflow;
import dev.asassoye.esi.atlir4.cards.Card;
import dev.asassoye.esi.atlir4.cards.Deck;
import dev.asassoye.esi.atlir4.cards.Exceptions.EmptyDeckException;

public class Player {
    private Set<Card> cards;

    private int balance;

    public Player(int money) {
        this.cards = new HashSet<>();
        this.balance = money;
    }

    public Player hit(Deck deck) {
        try {
            this.addCard(deck.hit());
        } catch (EmptyDeckException e) {
            System.err.println("Le deck est vide.."); //normalement c'est impossible avec le blackjack
        }

        if (this.score() > Game.MAX_SCORE) {
            throw new ScoreOverflow();
        }
        return this;
    }

    public Player resetHand() {
        this.cards = new HashSet<>();

        return this;
    }

    private Player addCard(Card card) {
        if (card == null) {
            throw new NullPointerException();
        }

        this.cards.add(card);
        return this;
    }

    public Set<Card> getCards() {
        return this.cards;
    }

    public int getBalance() {
        return balance;
    }

    public Player transferMoneyFrom(Player player, int quantity) {
        this.balance += quantity;
        player.balance -= quantity;

        return this;
    }

    public short score() {
        short sum = 0;

        for (var card : this.cards) {
            sum += CardValue.weight(card);
        }

        return sum;
    }


}

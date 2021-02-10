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

import dev.asassoye.esi.atlir4.blackjack.model.Exceptions.ScoreOverflow;
import dev.asassoye.esi.atlir4.cards.Deck;

public class Game implements Model {
    public static final int MAX_SCORE = 21;
    private final Bank bank;
    private final Player user;
    private Deck deck;
    private GameStatus status;

    public Game() {
        this.bank = new Bank(299792458);
        this.user = new Player(100);
        this.status = GameStatus.NOT_STARTED;
    }

    public Game start() {
        this.status = GameStatus.PLAYING;
        this.deck = new Deck().shuffle();

        this.user.resetHand();
        this.bank.resetHand();

        this.hit();
        this.hit();

        return this;
    }

    public Game hit() {
        try {
            this.user.hit(this.deck);
        } catch (ScoreOverflow e) {
            this.status = GameStatus.LOST;
        }

        return this;
    }

    public boolean stand() {
        try {
            this.bank.play(this.deck);
        } catch (ScoreOverflow e) {
            this.status = GameStatus.WON;
        }

        if (this.user.score() >= this.bank.score()) {
            this.status = GameStatus.WON;
        } else {
            this.status = GameStatus.LOST;
        }

        return this.user.score() >= this.bank.score();
    }

    @Override
    public Player getPlayer() {
        return this.user;
    }

    @Override
    public Player getBank() {
        return this.bank;
    }

    @Override
    public GameStatus getStatus() {
        return this.status;
    }
}

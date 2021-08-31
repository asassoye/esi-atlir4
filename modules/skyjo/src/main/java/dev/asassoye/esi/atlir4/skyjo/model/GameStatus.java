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

public enum GameStatus {
    /**
     * The game is not started
     */
    NOT_STARTED,
    /**
     * The player have to choose two initial cards to start the game
     */
    CHOOSING_INIT_CARDS,
    /**
     * The player is choosing a card in the deck or in the discard
     */
    CHOOSING_CARD,
    /**
     * The player choose the deck card
     */
    CHOSEN_FROM_DECK,
    /**
     * The player choose the discard card
     */
    CHOSEN_FROM_DISCARD,
    /**
     * The player put the deck card into the discard
     */
    CHOSEN_TO_DISCARD,
    /**
     * The round is over
     */
    ROUND_OVER,
    /**
     * The game is over
     */
    GAME_OVER
}

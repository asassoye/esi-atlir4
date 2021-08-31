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

import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * The interface Model interface.
 */
public interface ModelInterface {

    /**
     * Add property change listener. (observer)
     *
     * @param listener the listener
     */
    void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * Remove ChangeListener
     *
     * @param listener the listener to remove
     */
    void removePropertyChangeListener(PropertyChangeListener listener);

    /**
     * Add player.
     *
     * @param name the name of the new player
     */
    void addPlayer(String name);

    /**
     * Distribute cards.
     */
    void distributeCards();

    /**
     * Gets players.
     *
     * @return the players in a list
     */
    List<? extends PlayerInterface> getPlayers();

    /**
     * Player choose table card.
     *
     * @param x the x position
     * @param y the y position
     */
    void chooseTableCard(int x, int y);

    /**
     * Player choose discard.
     */
    void chooseDiscard();

    /**
     * Player choose deck.
     */
    void chooseDeck();

    /**
     * Gets playing player.
     *
     * @return the playing player
     */
    PlayerInterface getPlaying();

    /**
     * Gets status.
     *
     * @return the status of the game
     */
    GameStatus getStatus();

    /**
     * Gets info text about situation.
     *
     * @return the info
     */
    String getInfo();

    /**
     * Gets deck card.
     *
     * @return the deck card
     */
    CardInterface getDeckCard();

    /**
     * Gets discard card.
     *
     * @return the discard card
     */
    CardInterface getDiscardCard();
}

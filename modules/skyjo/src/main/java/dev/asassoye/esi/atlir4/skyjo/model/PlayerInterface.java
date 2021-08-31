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

/**
 * The interface Player interface.
 */
public interface PlayerInterface {
    /**
     * The number of columns of a player table
     */
    int COLUMNS = 4;
    /**
     * The number of lines of a player table
     */
    int LINES = 3;

    /**
     * Gets id of the player.
     *
     * @return the id of the player
     */
    int getId();

    /**
     * Gets the player's name.
     *
     * @return the name of the player
     */
    String getName();

    /**
     * Gets points. (total of visible cards)
     *
     * @return the points of the player
     */
    int getPoints();

    /**
     * Gets total score. (validated points after each round)
     *
     * @return the total score
     */
    int getTotalScore();

    /**
     * Gets card at a given position.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the card at the asked position if exists.
     */
    CardInterface getCard(int x, int y);
}

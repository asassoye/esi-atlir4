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

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Nested
    class Constructor {
        @Test
        void ok() {
            Game game = new Game();

            assertEquals(game.getStatus(), GameStatus.NOT_STARTED);
            assertNull(game.getPlaying());
            assertNull(game.getChosenCard());
            assertEquals(game.getDeck().size(), 150);
        }
    }

    @Nested
    class Methods {
        @Nested
        class AddPlayer {
            @Test
            void withoutName() {
                Game newGame = new Game();
                newGame.addPlayer();

                assertEquals(newGame.nbPlayers(), 1);
                assertNotNull(newGame.getPlayer(1));
            }

            @Test
            void withName() {
                Game newGame = new Game();
                String name = "Andrew";
                newGame.addPlayer("Andrew");

                assertEquals(newGame.nbPlayers(), 1);
                assertEquals(newGame.getPlayer(1).getName(), name);
            }
        }

        @Nested
        class nextPlayer {
            @Test
            void lastPlayerPlaying() {
                Game newGame = new Game();
                newGame.addPlayer();
                newGame.addPlayer();
                newGame.distributeCards();
                newGame.chooseTableCard(0, 0);
                newGame.chooseTableCard(0, 1);

                assertEquals(newGame.nextPlayer(), newGame.getPlayer(1));
            }

            @Test
            void firstPlayerPlaying() {
                Game newGame = new Game();
                newGame.addPlayer();
                newGame.addPlayer();
                newGame.distributeCards();
                newGame.chooseTableCard(0, 0);

                assertEquals(newGame.getPlayer(2), newGame.nextPlayer());
            }
        }
    }
}
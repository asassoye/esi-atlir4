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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Nested
    class Constructor {
        @Test
        void playerWithName() {
            String name = "Andrew";
            int id = 96;

            Player player = new Player(id, name);

            assertEquals(player.getName(), name);
            assertEquals(player.getId(), id);
        }

        @Test
        void playerWithoutName() {
            int id = 96;

            var player = new Player(id);

            assertEquals(player.getId(), id);
            assertEquals(player.getName(), String.format("Player %d", id));
        }
    }

    @Nested
    class Methods {
        @Test
        void setCard() {
            Card card = new Card(12);
            Player player = new Player(0);

            player.setCard(card, 1, 1);

            assertEquals(player.getCard(1, 1), card);
        }

        @Test
        void showCard() {
            Card card = new Card(12);
            Player player = new Player(0);

            player.setCard(card, 1, 1);
            player.showCard(1, 1);

            assertTrue(player.getCard(1, 1).isVisible());
        }

        @Nested
        class PlaceCards {
            @Test
            void withDeck() {
                Deck deck = new Deck(Deck.basicDeck);
                int deckInitSize = deck.size();
                int nbPlayerCards = Player.LINES * Player.COLUMNS;

                Player player = new Player(1);

                player.placeCards(deck);

                assertEquals(deck.size(), deckInitSize - nbPlayerCards);
            }

            @Test
            void withFixedArray() {
                Card[][] cards = {
                        {new Card(-2), new Card(8), new Card(7), new Card(6)},
                        {new Card(0), new Card(3), new Card(9), new Card(5)},
                        {new Card(-1), new Card(2), new Card(4), new Card(10)},
                };

                Player player = new Player(0);
                player.placeCards(cards);

                for (var i = 0; i < cards.length; ++i) {
                    for (var j = 0; j < cards[i].length; ++j) {
                        assertEquals(player.getCard(j, i), cards[i][j]);
                    }
                }
            }
        }

        @Nested
        class DeleteFullLines {
            @Test
            void somethingToDelete() {
                Card[][] cards = {
                        {new Card(-2), new Card(3, true), new Card(7), new Card(6)},
                        {new Card(0), new Card(3, true), new Card(9), new Card(5)},
                        {new Card(-1), new Card(3, true), new Card(4), new Card(10)},
                };

                Player player = new Player(0);
                player.placeCards(cards);
                player.deleteFullLines();

                List<Card> guess = Arrays.asList(new Card[]{null, null, null});

                var column = player.getColumn(1);
                assertEquals(column, guess);
            }

            @Test
            void nothingToDelete() {
                Card[][] cards = {
                        {new Card(-2), new Card(3, true), new Card(7), new Card(6)},
                        {new Card(0), new Card(3, true), new Card(9), new Card(5)},
                        {new Card(-1), new Card(2, true), new Card(4), new Card(10)},
                };

                Player player = new Player(0);
                player.placeCards(cards);
                player.deleteFullLines();

                List<Card> guess = Arrays.asList(new Card(3), new Card(3), new Card(2));

                var column = player.getColumn(1);
                assertEquals(column, guess);
            }
        }

        @Nested
        class GetPoints {
            @Test
            void noVisibleCards() {
                int value = 12;
                Card[][] cards = {
                        {new Card(value), new Card(value), new Card(value), new Card(value)},
                        {new Card(value), new Card(value), new Card(value), new Card(value)},
                        {new Card(value), new Card(value), new Card(value), new Card(value)},
                };

                Player player = new Player(0);
                player.placeCards(cards);

                assertEquals(player.getPoints(), 0);
            }

            @Test
            void allVisible() {
                int value = 12;
                Card[][] cards = {
                        {new Card(value, true), new Card(value, true), new Card(value, true), new Card(value, true)},
                        {new Card(value, true), new Card(value, true), new Card(value, true), new Card(value, true)},
                        {new Card(value, true), new Card(value, true), new Card(value, true), new Card(value, true)},
                };

                Player player = new Player(0);
                player.placeCards(cards);

                assertEquals(player.getPoints(), value * Player.COLUMNS * Player.LINES);
            }
        }

        @Nested
        class IsCompletelyReveled {
            @Test
            void yes() {
                int value = 12;
                Card[][] cards = {
                        {new Card(value, true), new Card(value, true), new Card(value, true), new Card(value, true)},
                        {new Card(value, true), new Card(value, true), new Card(value, true), new Card(value, true)},
                        {new Card(value, true), new Card(value, true), new Card(value, true), new Card(value, true)},
                };

                Player player = new Player(0);
                player.placeCards(cards);

                assertTrue(player.isCompletelyReveled());
            }

            @Test
            void no() {
                int value = 12;
                Card[][] cards = {
                        {new Card(value, true), new Card(value, true), new Card(value, true), new Card(value, true)},
                        {new Card(value, true), new Card(value, true), new Card(value, true), new Card(value, true)},
                        {new Card(value, true), new Card(value, true), new Card(value, true), new Card(value, false)},
                };

                Player player = new Player(0);
                player.placeCards(cards);

                assertFalse(player.isCompletelyReveled());
            }
        }

        @Nested
        class ExchangeCard {
            @Test
            void noInitialCard() {
                Card newCard = new Card(12);

                Player player = new Player(0);
                Card response = player.exchangeCard(newCard, 1, 1);

                assertEquals(player.getCard(1, 1), newCard);
                assertNull(response);
            }

            @Test
            void withInitialCard() {
                Card oldCard = new Card(11);
                Card newCard = new Card(12);

                Player player = new Player(0);
                player.setCard(oldCard, 1, 1);
                Card response = player.exchangeCard(newCard, 1, 1);

                assertEquals(player.getCard(1, 1), newCard);
                assertEquals(response, oldCard);
            }
        }
    }
}
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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class Constructor {
        private Stream<Arguments> cardProvider() {
            Stream.Builder<Arguments> argumentsBuilder = Stream.builder();
            for (var i = -2; i <= 12; ++i) {
                argumentsBuilder.add(Arguments.of(i, true));
                argumentsBuilder.add(Arguments.of(i, false));
            }

            return argumentsBuilder.build();
        }

        @ParameterizedTest
        @MethodSource("cardProvider")
        void ok(int value, boolean visible) {
            var card = new Card(value, visible);

            assertAll(
                    () -> assertEquals(card.getValue(), value),
                    () -> assertEquals(card.isVisible(), visible)
            );
        }

        @ParameterizedTest
        @ValueSource(ints = {Integer.MIN_VALUE, -4, -3, 13, 14, Integer.MAX_VALUE})
        void invalidValue(int value) {
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        Card card = new Card(value);
                        card.show();
                    }
            );
        }
    }

    @Nested
    class Methods {
        @ParameterizedTest
        @ValueSource(booleans = {false, true})
        void show(boolean initial) {
            Card card = new Card(0, initial);
            card.show();

            assertTrue(card.isVisible());
        }

        @ParameterizedTest
        @ValueSource(booleans = {false, true})
        void setVisible(boolean target) {
            Card card = new Card(0, !target);
            card.setVisible(target);

            assertEquals(card.isVisible(), target);
        }
    }
}
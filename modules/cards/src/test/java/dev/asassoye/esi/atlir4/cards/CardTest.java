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

package dev.asassoye.esi.atlir4.cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class Constructor {
        @ParameterizedTest
        @EnumSource(Color.class)
        public void noValue(Color color) {
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        Card card = new Card(color, null);
                    }
            );
        }

        @ParameterizedTest
        @EnumSource(Value.class)
        public void noColor(Value value) {
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> {
                        Card card = new Card(null, value);
                    }
            );
        }

        @ParameterizedTest
        @MethodSource("cardProvider")
        public void ok(Color color, Value value) {
            Card card = new Card(color, value);
            assertAll(
                    () -> assertEquals(color, card.getColor()),
                    () -> assertEquals(value, card.getValue())
            );
        }

        public Stream<Arguments> cardProvider() {
            Stream.Builder<Arguments> argumentBuilder = Stream.builder();
            for (Color color : Color.values()) {
                for (Value value : Value.values()) {
                    argumentBuilder.add(Arguments.of(color, value));
                }
            }
            return argumentBuilder.build();
        }

    }
}
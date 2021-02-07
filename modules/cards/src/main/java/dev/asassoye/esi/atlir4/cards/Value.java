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

public enum Value {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING;

    @Override
    public String toString() {
        return this.toString(true);
    }

    public String toString(boolean full) {
       switch (this) {
           case ACE:
               return full ? "as" : "1";
           case TWO:
               return full ? "deux" : "2";
           case THREE:
               return full ? "trois" : "3";
           case FOUR:
               return full ? "quatre" : "4";
           case FIVE:
               return full ? "cinq" : "5";
           case SIX:
               return full ? "six" : "6";
           case SEVEN:
               return full ? "sept" : "7";
           case EIGHT:
               return full ? "huit" : "8";
           case NINE:
               return full ? "neuf" : "9";
           case TEN:
               return full ? "dix" : "10";
           case JACK:
               return full ? "valet" : "V";
           case QUEEN:
               return full ? "dame" : "D";
           case KING:
           default:
               return full ? "roi" : "R";
       }
    }
}

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

package dev.asassoye.esi.atlir4.asciipaint;

/**
 * The type Ascii paint.
 */
public class AsciiPaint {
    private final Drawing drawing;

    /**
     * Instantiates a new Ascii paint.
     */
    public AsciiPaint() {
        this(10, 10);
    }

    /**
     * Instantiates a new Ascii paint.
     *
     * @param width  the width
     * @param height the height
     */
    public AsciiPaint(int width, int height) {
        this.drawing = new Drawing(width, height);
    }

    /**
     * New circle.
     *
     * @param x      the x
     * @param y      the y
     * @param radius the radius
     * @param color  the color
     */
    public void newCircle(int x, int y, double radius, char color) {
        //TODO
    }

    /**
     * New rectangle.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param color  the color
     */
    public void newRectangle(int x, int y, double width, double height, char color) {
        //TODO
    }

    /**
     * New square.
     *
     * @param x     the x
     * @param y     the y
     * @param side  the side
     * @param color the color
     */
    public void newSquare(int x, int y, double side, char color) {
        //TODO
    }

    /**
     * As ascii string.
     *
     * @return the string
     */
    String asAscii() {
        // TODO
        return "";
    }

}

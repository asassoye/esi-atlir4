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
 * The type Rectangle.
 */
public class Rectangle extends ColoredShape {
    private final Point upperLeft;

    private final double width;

    private final double height;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param color     the color
     */
    public Rectangle(Point upperLeft, double width, double height, char color) {
        super(color);

        if (upperLeft == null) {
            throw new IllegalArgumentException();
        }

        if (width <= 0) {
            throw new IllegalArgumentException();
        }

        if (height <= 0) {
            throw new IllegalArgumentException();
        }

        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean isInside(Point p) {
        if (p.getX() < this.upperLeft.getX() || p.getX() > (this.upperLeft.getX() + width)) {
            return false;
        }

        return !(p.getY() < this.upperLeft.getY()) && !(p.getY() > (this.upperLeft.getY() + height));
    }

    @Override
    public void move(double dx, double dy) {
        this.upperLeft.move(dx, dy);
    }
}

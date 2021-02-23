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

import java.util.ArrayList;
import java.util.List;

/**
 * The type Drawing.
 */
public class Drawing {
    private final List<Shape> shapes;

    private final int height;

    private final int width;

    /**
     * Instantiates a new Drawing.
     */
    public Drawing() {
        this(0, 0);
    }

    /**
     * Instantiates a new Drawing.
     *
     * @param width  the width
     * @param height the height
     */
    public Drawing(int width, int height) {
        this.shapes = new ArrayList<>();
        this.height = height;
        this.width = width;
    }

    /**
     * Add shape.
     *
     * @param shape the shape
     */
    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    /**
     * Gets shape.
     *
     * @param point the point
     * @return the shape
     */
    public Shape getShape(Point point) {
        for (var shape : this.shapes) {
            if (shape.isInside(point)) {
                return shape;
            }
        }
        return null;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return this.width;
    }
}

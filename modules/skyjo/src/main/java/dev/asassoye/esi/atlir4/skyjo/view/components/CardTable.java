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

package dev.asassoye.esi.atlir4.skyjo.view.components;

import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.List;

public class CardTable extends GridPane {
    public static final int COLUMNS = 4;
    public static final int ROWS = 3;
    public static final double bHeight = Card.bHeight * ROWS;
    public static final double bWidth = Card.bHeight * COLUMNS;
    public static final double gap = 10.0;


    public CardTable(List<Card> cards) {
        this.getStyleClass().add("cardTable");

        URL url = getClass().getResource("/styles/components/cardtable.css");
        if (url != null) {
            this.getStylesheets().add(url.toExternalForm());
        }

        for (var card : cards) {
            this.add(card, card.getX(), card.getY());
            card.minHeightProperty().bind(this.heightProperty().divide(ROWS).subtract(gap * ROWS));
        }

        this.minWidthProperty().bind(this.widthProperty().divide(bWidth / bHeight));
        this.minWidthProperty().bind(this.heightProperty().multiply(bWidth / bHeight));
    }
}

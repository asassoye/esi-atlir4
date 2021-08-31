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

import dev.asassoye.esi.atlir4.skyjo.model.CardInterface;
import dev.asassoye.esi.atlir4.skyjo.view.utils.ResourceStylable;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class TableCenter extends VBox implements ResourceStylable {
    public final Card discard;
    public final Card deckCard;

    public TableCenter() {
        this.discard = new Card(0, 0, 12, true);
        this.deckCard = new Card(0, 0, -2, false);

        this.discard.prefWidthProperty().bind(this.widthProperty());
        this.deckCard.prefWidthProperty().bind(this.widthProperty());


        this.getChildren().add(this.discard);
        this.getChildren().add(this.deckCard);

        applyStyles("/styles/components/tablecenter.css", this);
        this.getStyleClass().add("tableCenter");
    }

    public void connectChooseDiscardAction(EventHandler<MouseEvent> eventHandler) {
        discard.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    public void connectChooseDeckAction(EventHandler<MouseEvent> eventHandler) {
        deckCard.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    public void setDeckCard(CardInterface card) {
        deckCard.update(card);
    }

    public void setDiscard(CardInterface card) {
        discard.update(card);
    }
}

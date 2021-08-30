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
import dev.asassoye.esi.atlir4.skyjo.model.PlayerInterface;
import dev.asassoye.esi.atlir4.skyjo.view.utils.ResourceStylable;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class Board extends HBox implements ResourceStylable {
    private final Player player1;
    private final TableCenter tableCenter;
    private final Player player2;
    private Player playing;

    public Board(PlayerInterface player1, PlayerInterface player2) {
        this.player1 = new Player(player1);
        this.player2 = new Player(player2);
        this.tableCenter = new TableCenter();

        applyStyles("/styles/components/board.css", this);
        this.getStyleClass().add("board");

        this.getChildren().add(this.player1);
        this.getChildren().add(tableCenter);
        this.getChildren().add(this.player2);

        this.player1.minHeightProperty().bind(this.heightProperty());
        this.tableCenter.minHeightProperty().bind(this.heightProperty());
        this.player2.minHeightProperty().bind(this.heightProperty());
        this.player1.minWidthProperty().bind(this.widthProperty().divide(9).multiply(4));
        this.tableCenter.minWidthProperty().bind(this.widthProperty().divide(9));
        this.player2.minWidthProperty().bind(this.widthProperty().divide(9).multiply(4));

        this.player1.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (this.player1 != playing) {
                e.consume();
            }
        });

        this.player2.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            if (this.player2 != playing) {
                e.consume();
            }
        });
    }

    public void updateDeckCard(CardInterface card) {
        tableCenter.setDeckCard(card);
    }

    public void updateWithdraw(CardInterface card) {
        tableCenter.setWithdraw(card);
    }

    public void connectChooseTableCardAction(EventHandler<MouseEvent> eventHandler) {
        this.player1.connectChooseTableCardAction(eventHandler);
        this.player2.connectChooseTableCardAction(eventHandler);
    }

    public void connectChooseDiscardAction(EventHandler<MouseEvent> eventHandler) {
        tableCenter.connectChooseDiscardAction(eventHandler);
    }

    public void connectChooseDeckAction(EventHandler<MouseEvent> eventHandler) {
        tableCenter.connectChooseDeckAction(eventHandler);
    }

    public void setPlaying(int i) {
        switch (i) {
            case 1:
                playing = player1;
                break;
            case 2:
                playing = player2;
                break;
            default:
                playing = null;
        }
    }

    public void update(PlayerInterface player1, PlayerInterface player2) {
        this.player1.update(player1);
        this.player2.update(player2);
    }
}

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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class Game extends VBox implements ResourceStylable {
    private final Board board;
    private final Label title;
    private final Label info;

    public Game(PlayerInterface player1, PlayerInterface player2) {
        this.board = new Board(player1, player2);
        this.title = new Label("SKYJO");
        this.info = new Label("INIT");

        applyStyles("/styles/components/game.css", this);
        this.getStyleClass().add("game");

        this.getChildren().add(title);
        this.getChildren().add(board);
        this.getChildren().add(info);

        this.board.minHeightProperty().bind(this.heightProperty().divide(12).multiply(9).subtract(20));
        this.board.minWidthProperty().bind(this.widthProperty().subtract(20));

        this.title.minHeightProperty().bind(this.heightProperty().divide(12).multiply(2).subtract(20));
        this.title.minWidthProperty().bind(this.widthProperty().subtract(20));
        this.title.getStyleClass().add("gameTitle");

        this.info.minHeightProperty().bind(this.heightProperty().divide(12).multiply(1).subtract(20));
        this.info.minWidthProperty().bind(this.widthProperty().subtract(20));
        this.info.getStyleClass().add("gameInfo");
    }

    public void setInfo(String info) {
        this.info.setText(info);
    }

    public void setPlaying(int id) {
        board.setPlaying(id);
    }

    public void updateDeckCard(CardInterface card) {
        board.updateDeckCard(card);
    }

    public void updateWithdraw(CardInterface card) {
        board.updateWithdraw(card);
    }

    public void connectChooseTableCardAction(EventHandler<MouseEvent> eventHandler) {
        this.board.connectChooseTableCardAction(eventHandler);
    }

    public void connectChooseDiscardAction(EventHandler<MouseEvent> eventHandler) {
        board.connectChooseDiscardAction(eventHandler);
    }

    public void connectChooseDeckAction(EventHandler<MouseEvent> eventHandler) {
        board.connectChooseDeckAction(eventHandler);
    }

    public void update(PlayerInterface player1, PlayerInterface player2) {
        this.board.update(player1, player2);
    }
}

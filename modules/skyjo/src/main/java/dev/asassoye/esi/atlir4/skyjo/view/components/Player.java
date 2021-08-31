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

import dev.asassoye.esi.atlir4.skyjo.model.PlayerInterface;
import dev.asassoye.esi.atlir4.skyjo.view.utils.ResourceStylable;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * The type Player.
 */
public class Player extends VBox implements ResourceStylable {
    private final Label name;
    private final CardTable cardTable;

    /**
     * Instantiates a new Player.
     *
     * @param player the player
     */
    public Player(PlayerInterface player) {
        applyStyles("/styles/components/player.css", this);
        this.name = new Label(player.getName());
        this.cardTable = new CardTable();

        this.getChildren().add(this.cardTable);
        this.getChildren().add(this.name);

        update(player);

        this.name.prefHeightProperty().bind(this.heightProperty().divide(12));
        this.name.prefWidthProperty().bind(this.widthProperty());
        this.name.getStyleClass().add("playerName");
        this.cardTable.prefHeightProperty().bind(this.heightProperty().divide(12).multiply(11));
        this.cardTable.prefWidthProperty().bind(this.widthProperty());
    }

    /**
     * Connect choose table card action.
     *
     * @param eventHandler the event handler
     */
    public void connectChooseTableCardAction(EventHandler<MouseEvent> eventHandler) {
        cardTable.connectChooseTableCardAction(eventHandler);
    }

    /**
     * Update.
     *
     * @param player the player
     */
    public void update(PlayerInterface player) {
        for (var x = 0; x < PlayerInterface.COLUMNS; ++x) {
            for (var y = 0; y < PlayerInterface.LINES; ++y) {
                cardTable.updateCard(player.getCard(x, y), x, y);
            }
        }

        name.setText(String.format("%s [%d]", player.getName(), player.getPoints()));
    }
}

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

package dev.asassoye.esi.atlir4.skyjo.view;

import dev.asassoye.esi.atlir4.skyjo.model.CardInterface;
import dev.asassoye.esi.atlir4.skyjo.model.GameStatus;
import dev.asassoye.esi.atlir4.skyjo.model.ModelInterface;
import dev.asassoye.esi.atlir4.skyjo.model.PlayerInterface;
import dev.asassoye.esi.atlir4.skyjo.view.components.Game;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * The View.
 */
public class View implements PropertyChangeListener {
    private final Stage primaryStage;
    private Game game;

    /**
     * Instantiates a new View.
     *
     * @param stage the primary stage
     */
    public View(Stage stage) {
        this.primaryStage = stage;
        this.game = null;
        stage.setTitle("Skyjo");
    }

    /**
     * Create board.
     *
     * @param players the players of the game
     * @param deck    the deck card
     * @param discard the discard card
     */
    public void createBoard(List<? extends PlayerInterface> players, CardInterface deck, CardInterface discard) {
        PlayerInterface player1 = players.get(0);
        PlayerInterface player2 = players.get(1);

        this.game = new Game(player1, player2);
        game.updateDeckCard(deck);
        game.updateDiscard(discard);

        Scene scene = new Scene(game, 1200, 700);
        game.minHeightProperty().bind(scene.heightProperty());
        game.minWidthProperty().bind(scene.widthProperty());

        this.primaryStage.setScene(scene);

    }

    public void show() {
        this.primaryStage.show();
    }

    /**
     * Connect choose table card action.
     *
     * @param eventHandler the event handler
     */
    public void connectChooseTableCardAction(EventHandler<MouseEvent> eventHandler) {
        this.game.connectChooseTableCardAction(eventHandler);
    }

    /**
     * Connect choose discard action.
     *
     * @param eventHandler the event handler
     */
    public void connectChooseDiscardAction(EventHandler<MouseEvent> eventHandler) {
        game.connectChooseDiscardAction(eventHandler);
    }

    /**
     * Connect choose deck action.
     *
     * @param eventHandler the event handler
     */
    public void connectChooseDeckAction(EventHandler<MouseEvent> eventHandler) {
        game.connectChooseDeckAction(eventHandler);
    }

    /**
     * Update board.
     *
     * @param players the players
     */
    public void updateBoard(List<? extends PlayerInterface> players) {
        PlayerInterface player1 = players.get(0);
        PlayerInterface player2 = players.get(1);

        this.game.update(player1, player2);
    }

    /**
     * Alert.
     *
     * @param warning the warning
     */
    public void alert(String warning) {
        Alert alert = new Alert(Alert.AlertType.WARNING, warning);
        alert.showAndWait();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ModelInterface model = (ModelInterface) evt.getSource();

        switch (evt.getPropertyName()) {
            case "STATUS":
                if (evt.getNewValue() == GameStatus.CHOOSING_INIT_CARDS) {
                    createBoard(model.getPlayers(), model.getDeckCard(), model.getDiscardCard());
                    this.game.setPlaying(model.getPlaying().getId());
                }
                game.setInfo(model.getInfo());
                break;
            case "BOARD":
                updateBoard(model.getPlayers());
                break;
            case "PLAYING":
                var playing = (PlayerInterface) evt.getNewValue();
                if (this.game != null) {
                    this.game.setPlaying(playing.getId());
                    game.setInfo(model.getInfo());
                }
                break;
            case "DISCARD":
                game.updateDiscard(model.getDiscardCard());
                break;
        }
    }
}

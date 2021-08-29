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
import dev.asassoye.esi.atlir4.skyjo.view.components.Card;
import dev.asassoye.esi.atlir4.skyjo.view.components.Game;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class View implements PropertyChangeListener {
    private final Stage primaryStage;
    private Scene scene;
    private Game cardTable;

    public View(Stage stage) {
        this.primaryStage = stage;
        this.cardTable = null;
        stage.setTitle("Skyjo");
    }

    public static List<Card> generateCardList(PlayerInterface player) {
        List<Card> cards2 = new ArrayList<>();
        for (var x = 0; x < PlayerInterface.COLUMNS; ++x) {
            for (var y = 0; y < PlayerInterface.LINES; ++y) {
                CardInterface card = player.getCard(x, y);
                cards2.add(new Card(x, y, card.getValue(), card.isVisible()));
            }
        }
        return cards2;
    }

    public void showBoard(List<? extends PlayerInterface> players) {
        PlayerInterface player1 = players.get(0);
        List<Card> cards = generateCardList(player1);

        PlayerInterface player2 = players.get(1);
        List<Card> cards2 = generateCardList(player2);


        this.cardTable = new Game(player1.getName(), player2.getName(), cards, cards2);

        this.scene = new Scene(cardTable, 1200, 700);
        cardTable.minHeightProperty().bind(scene.heightProperty());
        cardTable.minWidthProperty().bind(scene.widthProperty());

        this.primaryStage.setScene(this.scene);
        this.primaryStage.show();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ModelInterface model = (ModelInterface) evt.getSource();

        if (evt.getPropertyName().equals("STATUS")) {
            if (evt.getNewValue() == GameStatus.CHOOSING_INIT_CARDS) {
                showBoard(model.getPlayers());
            }
        }
    }
}

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

package dev.asassoye.esi.atlir4.skyjo;

import dev.asassoye.esi.atlir4.skyjo.view.components.Card;
import dev.asassoye.esi.atlir4.skyjo.view.components.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Skyjo");

        List<Card> cards = new ArrayList<>();
        cards.add(new Card(0, 0, -2, false));
        cards.add(new Card(0, 1, 0, false));
        cards.add(new Card(0, 2, 2, false));
        cards.add(new Card(1, 0, 4, false));
        cards.add(new Card(1, 1, 2, false));
        cards.add(new Card(1, 2, 3, false));
        cards.add(new Card(2, 0, 4, false));
        cards.add(new Card(2, 1, 5, false));
        cards.add(new Card(2, 2, 7, false));
        cards.add(new Card(3, 0, 8, false));
        cards.add(new Card(3, 1, 10, false));
        cards.add(new Card(3, 2, 12, false));

        List<Card> cards2 = new ArrayList<>();
        cards2.add(new Card(0, 0, -2, false));
        cards2.add(new Card(0, 1, 0, false));
        cards2.add(new Card(0, 2, 2, false));
        cards2.add(new Card(1, 0, 4, false));
        cards2.add(new Card(1, 1, 2, false));
        cards2.add(new Card(1, 2, 3, false));
        cards2.add(new Card(2, 0, 4, false));
        cards2.add(new Card(2, 1, 5, false));
        cards2.add(new Card(2, 2, 7, false));
        cards2.add(new Card(3, 0, 8, false));
        cards2.add(new Card(3, 1, 10, false));
        cards2.add(new Card(3, 2, 12, false));

        Game cardTable = new Game("Andrew", "Constantin", cards, cards2);

        Scene scene = new Scene(cardTable, 1200, 700);
        stage.setScene(scene);
        stage.show();

        cardTable.minHeightProperty().bind(scene.heightProperty());
        cardTable.minWidthProperty().bind(scene.widthProperty());
    }

}

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
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Skyjo");

        HBox box = new HBox();

        box.getChildren().add(new Card(0, -2, false));
        box.getChildren().add(new Card(0, -2, false));
        box.getChildren().add(new Card(0, -0, false));
        box.getChildren().add(new Card(0, 1, false));
        box.getChildren().add(new Card(0, 2, false));
        box.getChildren().add(new Card(0, 3, false));
        box.getChildren().add(new Card(0, 4, false));
        box.getChildren().add(new Card(0, 5, false));
        box.getChildren().add(new Card(0, 6, false));
        box.getChildren().add(new Card(0, 7, false));
        box.getChildren().add(new Card(0, 8, false));
        box.getChildren().add(new Card(0, 9, false));
        box.getChildren().add(new Card(0, 10, false));
        box.getChildren().add(new Card(0, 11, false));
        box.getChildren().add(new Card(0, 12, false));

        for (var child : box.getChildren()) {
            Card card = (Card) child;
            card.prefHeightProperty().bind(box.heightProperty());
        }

        Scene scene = new Scene(box, 1000, 200);
        stage.setScene(scene);
        stage.show();
    }

}

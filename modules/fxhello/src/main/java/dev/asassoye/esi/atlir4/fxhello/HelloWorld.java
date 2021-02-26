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

package dev.asassoye.esi.atlir4.fxhello;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloWorld extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("My first JavaFX App");

        BorderPane root = new BorderPane();
        Text helloText = new Text("Hello world!");
        helloText.setFont(Font.font("Times New Roman", 20));
        helloText.setFill(Color.RED);
        root.setCenter(helloText);
        Scene scene = new Scene(root, 250, 100);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() {
        Logger logger = Logger.getLogger("log");
        logger.log(Level.INFO, "Initialisation de l'écran");
    }

    @Override
    public void stop() {
        Logger logger = Logger.getLogger("log");
        logger.log(Level.INFO, "Fin de l'écran");
    }
}

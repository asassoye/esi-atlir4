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

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HelloWorldCheckBox extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Title");
        BorderPane root = new BorderPane();

        CheckBox checkBox1 = new CheckBox();
        checkBox1.setText("First");
        checkBox1.setSelected(true);

        CheckBox checkBox2 = new CheckBox("Second");
        checkBox2.setIndeterminate(true);

        CheckBox checkBox3 = new CheckBox("Third");

        checkBox3.setAllowIndeterminate(true);

        //Alignment
        root.setLeft(checkBox1);
        BorderPane.setAlignment(checkBox1, Pos.CENTER);
        root.setCenter(checkBox2);
        root.setRight(checkBox3);
        //BorderPane.setAlignment(checkBox3, Pos.CENTER);

        Scene scene = new Scene(root, 250, 100);
        stage.setScene(scene);
        stage.show();
    }
}

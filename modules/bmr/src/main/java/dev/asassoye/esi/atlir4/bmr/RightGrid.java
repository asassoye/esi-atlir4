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

package dev.asassoye.esi.atlir4.bmr;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class RightGrid extends GridPane {
    private final Label result_label;
    private final Label bmr_label;
    private final Label kcal_label;

    private final TextField bmr_field;
    private final TextField kcal_field;

    public RightGrid() {
        result_label = new Label("Resultats");
        bmr_label = new Label("BMR");
        kcal_label = new Label("Calories");

        this.add(result_label, 0, 0);
        this.add(bmr_label, 0, 1);
        this.add(kcal_label, 0, 2);

        bmr_field = new TextField();
        this.kcal_field = new TextField();

        this.add(bmr_field, 1, 1);
        this.add(kcal_field, 1, 2);
    }

    String getBmrField() {
        return bmr_field.getText();
    }

    void setBmrField(String bmr) {
        bmr_field.setText(bmr);
    }

    String getKcalField() {
        return kcal_field.getText();
    }

    void setKcalField(String kcal) {
        kcal_field.setText(kcal);
    }
}

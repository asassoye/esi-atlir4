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

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class LeftGrid extends GridPane {
    private final Label data_label;
    private final Label height_label;
    private final Label weight_label;
    private final Label age_label;
    private final Label gender_label;
    private final Label lifestyle_label;

    private final TextField height_field;

    private final TextField weight_field;
    private final TextField age_field;
    private final ChoiceBox<String> lifestyle_field;
    private final HBox gender_box;
    private final ToggleGroup gender_group;
    private final RadioButton men_field;
    private final RadioButton women_field;

    public LeftGrid() {
        data_label = new Label("Données");
        height_label = new Label("Taille (cm)");
        weight_label = new Label("Poids (kg)");
        age_label = new Label("Age (années)");
        gender_label = new Label("Sexe");
        lifestyle_label = new Label("Style de vie");

        this.add(data_label, 0, 0);
        this.add(height_label, 0, 1);
        this.add(weight_label, 0, 2);
        this.add(age_label, 0, 3);
        this.add(gender_label, 0, 4);
        this.add(lifestyle_label, 0, 5);

        height_field = new TextField();
        weight_field = new TextField();
        age_field = new TextField();
        lifestyle_field = new ChoiceBox<>();
        lifestyle_field.getItems().addAll("Sédentaire", "Peu actif", "Actif", "Fort actif", "Extremement actif");

        gender_group = new ToggleGroup();
        men_field = new RadioButton("Homme");
        men_field.setToggleGroup(gender_group);
        men_field.setSelected(true);
        women_field = new RadioButton("Femme");
        women_field.setToggleGroup(gender_group);

        gender_box = new HBox();
        gender_box.getChildren().addAll(men_field, women_field);

        this.add(height_field, 1, 1);
        this.add(weight_field, 1, 2);
        this.add(age_field, 1, 3);
        this.add(gender_box, 1, 4);
        this.add(lifestyle_field, 1, 5);
    }

    String getHeightField() {
        return this.height_field.getText();
    }

    String getWeightField() {
        return this.weight_field.getText();
    }

    String getAgeField() {
        return this.age_field.getText();
    }

    String getLifestyleField() {
        return this.lifestyle_field.getValue();
    }

    String getGender() {
        return this.gender_group.getSelectedToggle().toString();
    }
}

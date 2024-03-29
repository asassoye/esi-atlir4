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

package dev.asassoye.esi.atlir4.skyjo.controller;

import dev.asassoye.esi.atlir4.skyjo.model.ModelInterface;
import dev.asassoye.esi.atlir4.skyjo.view.View;
import dev.asassoye.esi.atlir4.skyjo.view.components.Card;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * The Controller.
 */
public class Controller {
    private final ModelInterface model;
    private final View view;

    /**
     * Instantiates a new Controller.
     *
     * @param model the model
     * @param view  the view
     */
    public Controller(ModelInterface model, View view) {
        this.model = model;
        this.view = view;
        model.addPropertyChangeListener(view);

        model.addPlayer("Player 1");
        model.addPlayer("Player 2");
        model.distributeCards();

        EventHandler<MouseEvent> chooseTableCardAction = (mouseEvent) -> {
            Card source = (Card) mouseEvent.getSource();
            int x = source.getX();
            int y = source.getY();
            try {
                model.chooseTableCard(x, y);
            } catch (Exception e) {
                view.alert(e.getMessage());
            }

        };

        EventHandler<MouseEvent> chooseDiscardAction = (mouseEvent) -> {
            try {
                model.chooseDiscard();
            } catch (Exception e) {
                view.alert(e.getMessage());
            }

        };
        EventHandler<MouseEvent> chooseDeckAction = (mouseEvent) -> {
            try {
                model.chooseDeck();
            } catch (Exception e) {
                view.alert(e.getMessage());
            }

        };

        view.connectChooseTableCardAction(chooseTableCardAction);
        view.connectChooseDiscardAction(chooseDiscardAction);
        view.connectChooseDeckAction(chooseDeckAction);
    }

    public void start() {
        view.show();
    }

    public ModelInterface getModel() {
        return model;
    }

    public View getView() {
        return view;
    }
}

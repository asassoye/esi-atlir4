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

package dev.asassoye.esi.atlir4.blackjack.controller;

import dev.asassoye.esi.atlir4.blackjack.model.GameStatus;
import dev.asassoye.esi.atlir4.blackjack.model.Model;
import dev.asassoye.esi.atlir4.blackjack.view.InterfaceView;

public class Controller {
    private final Model game;

    private final InterfaceView view;

    public Controller(Model model, InterfaceView view) {
        this.game = model;
        this.view = view;
        view.clearScreen();
    }

    public void startGame() {
        this.view.displayBalance(this.game.getPlayer());

        System.out.println("Combien souhaitez vous miser? ");
        int mise = view.askBet();

        this.game.start();
        this.view.displayHand(this.game.getPlayer(), "Votre main:");

        while (this.game.getStatus() == GameStatus.PLAYING) {
            System.out.println("Que voulez vous faire?");
            String move = view.askMove();
            switch (move) {
                case "h":
                    game.hit();
                    break;
                case "s":
                    game.stand();
                    break;
            }

            this.view.displayHand(game.getPlayer(), "Votre main:");
        }

        switch (this.game.getStatus()) {
            case WON:
                view.displayMessage("Vous avez gagné!");
                this.view.displayHand(game.getBank(), "Main du banquier:");
                this.game.getPlayer().transferMoneyFrom(this.game.getBank(), mise);
                view.displayBalance(this.game.getPlayer());
                break;
            case LOST:
                view.displayMessage("Vous avez perdu..");
                this.view.displayHand(game.getBank(), "Main du banquier:");
                this.game.getBank().transferMoneyFrom(this.game.getPlayer(), mise);
                view.displayBalance(this.game.getPlayer());
                break;
        }
    }

}

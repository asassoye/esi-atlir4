package dev.asassoye.esi.atlir4.blackjack;

import dev.asassoye.esi.atlir4.blackjack.controller.Controller;
import dev.asassoye.esi.atlir4.blackjack.model.Game;
import dev.asassoye.esi.atlir4.blackjack.model.Model;
import dev.asassoye.esi.atlir4.blackjack.view.InterfaceView;
import dev.asassoye.esi.atlir4.blackjack.view.View;

public class Main {
    public static void main(String[] args) {
        Model model = new Game();
        InterfaceView view = new View();
        Controller controller = new Controller(model, view);

        controller.startGame();
    }
}

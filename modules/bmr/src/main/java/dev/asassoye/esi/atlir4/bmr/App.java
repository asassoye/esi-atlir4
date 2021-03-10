package dev.asassoye.esi.atlir4.bmr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Calcul du BMR");
        VBox root = new Root();

        Scene scene = new Scene(root, 450, 200);
        stage.setScene(scene);
        stage.show();
    }
}

module skyjo {
    requires javafx.controls;
    requires javafx.fxml;

    opens dev.asassoye.esi.atlir4.skyjo to javafx.fxml;
    exports dev.asassoye.esi.atlir4.skyjo;
    exports dev.asassoye.esi.atlir4.skyjo.controller;
}
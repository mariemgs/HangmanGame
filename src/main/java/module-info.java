module tott.pendu {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens tott.pendu to javafx.fxml;
    exports tott.pendu;
}
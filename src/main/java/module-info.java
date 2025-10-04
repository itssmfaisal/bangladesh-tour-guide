module com.example.banglaeshtourguide {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    // Open the model package to javafx.base for reflection
    opens com.example.banglaeshtourguide.model to javafx.base;

    // Existing exports
    exports com.example.banglaeshtourguide;
    opens com.example.banglaeshtourguide.controller to javafx.fxml;

    // Keep existing exports

}
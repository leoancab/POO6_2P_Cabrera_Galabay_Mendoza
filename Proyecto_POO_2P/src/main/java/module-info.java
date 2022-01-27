module com.pooespol.proyecto_poo_2p {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.base;
    requires javafx.graphics;
    requires java.desktop;
    requires java.logging;

    opens com.pooespol.proyecto_poo_2p to javafx.fxml;
    exports com.pooespol.proyecto_poo_2p;
    requires java.mail;
}

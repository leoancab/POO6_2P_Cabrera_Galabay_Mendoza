module com.pooespol.proyecto_poo_2p {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.pooespol.proyecto_poo_2p to javafx.fxml;
    exports com.pooespol.proyecto_poo_2p;
}

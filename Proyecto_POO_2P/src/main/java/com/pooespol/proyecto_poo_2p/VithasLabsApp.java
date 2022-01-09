package com.pooespol.proyecto_poo_2p;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;


public class VithasLabsApp extends Application {

    private static Scene scene;
    public static String pathFile = "src/main/resources/Archivos/";
    public static String pathImg = "src/main/resources/Imagenes/";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VithasLabsApp.class.getResource("inicioSesion.fxml"));
        Parent root = fxmlLoader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VithasLabsApp.class.getResource(fxml + ".fxml"));
        scene.setRoot(fxmlLoader.load());
    }

    public static void main(String[] args) {
        launch();
    }

}
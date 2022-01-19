package com.pooespol.proyecto_poo_2p;

import com.pooespol.proyecto_poo_2p.modelo.usuarios.Paciente;
import static com.pooespol.proyecto_poo_2p.modelo.usuarios.Paciente.generarPacientes;
import com.pooespol.proyecto_poo_2p.modelo.usuarios.Usuario;
import static com.pooespol.proyecto_poo_2p.modelo.usuarios.Usuario.generarUsuarios;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import static javafx.application.Application.launch;

public class VithasLabsApp extends Application {

    private static Scene scene;
    public static String pathFile = "src/main/resources/Archivos/";
    public static String pathImg = "src/main/resources/Imagenes/";
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static ArrayList<Paciente> pacientes = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VithasLabsApp.class.getResource("inicioSesion.fxml"));
        Parent root = fxmlLoader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Vithas Labs");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VithasLabsApp.class.getResource(fxml + ".fxml"));
        scene.setRoot(fxmlLoader.load());
    }

    public static void main(String[] args) {
        generarUsuarios(pathFile + "usuarios.txt");
        generarPacientes(pathFile + "pacientes.txt");
        System.out.println(usuarios);
        System.out.println(pacientes);
        launch();
    }

}

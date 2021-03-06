package com.pooespol.proyecto_poo_2p;

import com.pooespol.proyecto_poo_2p.modelo.usuarios.Paciente;
import static com.pooespol.proyecto_poo_2p.modelo.usuarios.Paciente.generarPacientes;
import com.pooespol.proyecto_poo_2p.modelo.usuarios.Usuario;
import static com.pooespol.proyecto_poo_2p.modelo.usuarios.Usuario.generarUsuarios;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public class VithasLabsApp extends Application {

    public static Scene scene;
    public static String pathFile = "Archivos/";
    public static String pathImg = "Imagenes/";
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static ArrayList<Paciente> pacientes = new ArrayList<>();
    /**
     * Se carga la escena y se realizan los ajustes a la ventana.
     * @param stage
     * @throws IOException 
     */
    @Override
    public void start(Stage stage) throws IOException {
        
        setIcono(stage);
        
        FXMLLoader fxmlLoader = new FXMLLoader(VithasLabsApp.class.getResource("inicioSesion.fxml"));
        Parent root = fxmlLoader.load();
        root.setStyle("-fx-background-color: linear-gradient(#ffffff 20%, #5239d1 )");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Vithas Labs");
        stage.setResizable(false);
        stage.show();
    }
    
    /**
     * Permite cmabiar la escena de la vetana inicial de la App.
     * @param fxml
     * @throws IOException 
     */
    static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VithasLabsApp.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        scene.setRoot(root);
    }

    public static void main(String[] args) {
        generarUsuarios(pathFile + "usuarios.txt");
        generarPacientes(pathFile + "pacientes.txt");
        launch();
    }
    
    /**
     * Agrega una iamgen de fondo al contenedor.
     * @param imagen nombre de la imagen
     * @param formato tipo de formato
     * @param root tipo de contendor
     */
    public static void fondo(String imagen, String formato, Pane root) {
        try (FileInputStream input = new FileInputStream(VithasLabsApp.pathImg + imagen + formato)) {
            
            Image im = new Image(input);
            BackgroundImage backgroundImage = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            Background background = new Background(backgroundImage);
            root.setBackground(background);
        } catch (Exception e) {
            System.out.println("No se encuentra la imagen");
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Agrega el icono a la vetana
     * @param stage ventana a la que se le agregara el icono.
     */
    public static void setIcono(Stage stage) {
        try (FileInputStream fis = new FileInputStream(pathImg + "icono.png")) {
            Image img = new Image(fis);
            stage.getIcons().add(img);
        } catch (IOException e) {
            System.out.println("No se encontro el icono...");
        }
        
    }
}

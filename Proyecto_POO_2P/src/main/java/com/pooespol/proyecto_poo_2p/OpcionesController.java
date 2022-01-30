/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto_poo_2p;

import static com.pooespol.proyecto_poo_2p.VithasLabsApp.setIcono;
import com.pooespol.proyecto_poo_2p.modelo.usuarios.Genero;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cgala
 */
public class OpcionesController implements Initializable {

    @FXML
    private Label l_bienvenida;
    @FXML
    private VBox root;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VithasLabsApp.fondo("fondoPaciente", ".jpg", root);
        String nombres = InicioSesionController.pacienteLogin.getNombres();
        Genero genero = InicioSesionController.pacienteLogin.getGenero();
        if (genero.equals(Genero.Masculino))
            l_bienvenida.setText("Bienvenido " + nombres);
        else if (genero.equals(Genero.Femenino))
            l_bienvenida.setText("Bienvenida " + nombres);
        else
            l_bienvenida.setText("Bienvenid@ " + nombres);
    }
    
    /**
     * Abre una nueva ventana que muestras en un mapa las ubicaciones
     * de las sucursales.
     * @throws IOException 
     */
    @FXML
    private void verSucursales() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VithasLabsApp.class.getResource("ubicaciones.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Vithas Labs - Conoce Nuestras Ubicaciones");
        stage.setResizable(false);
        setIcono(stage);
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Abre una ventana que permite seleccionar las pruebas que desea comprar.
     * @throws IOException 
     */
    @FXML
    private void pruebaDomicilio() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VithasLabsApp.class.getResource("agendarPrueba.fxml"));
        Parent root1 = fxmlLoader.load();
        Scene scene1 = new Scene(root1);
        Stage stage2 = new Stage();
        stage2.setTitle("Vithas Labs - Agendar Prueba");
        stage2.setScene(scene1);
        stage2.setResizable(false);
        setIcono(stage2);
        stage2.show();
    }

}

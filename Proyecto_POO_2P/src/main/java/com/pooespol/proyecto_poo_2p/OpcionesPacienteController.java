/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pooespol.proyecto_poo_2p;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author leonel
 */
public class OpcionesPacienteController implements Initializable {
    
    @FXML
    private Label lbl_bienvenido;
    @FXML
    private Label l;
    @FXML
    private ImageView imagen;
    @FXML
    private Button btn_Sucursales;
    @FXML
    private Button btn_PruebaDomicilio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void verSucursales(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ArchivoRW.class.getResource("sucursales.txt"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    private void pruebasDomicilio(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(ArchivoRW.class.getResource("opcionesPaciente.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
}

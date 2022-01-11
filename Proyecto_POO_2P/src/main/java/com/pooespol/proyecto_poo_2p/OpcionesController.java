/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto_poo_2p;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author cgala
 */
public class OpcionesController implements Initializable {


    @FXML
    private Button btn_Sucursales;
    @FXML
    private Button btn_PruebaDomicilio;
    @FXML
    private Label l_bienvenida;
    @FXML
    private VBox vb_opciones;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String nombres = InicioSesionController.pacienteLogin.getNombres();
        l_bienvenida.setText("Bienvenido " + nombres);
    }    
    
    @FXML
    private void verSucursales(ActionEvent event) {
    }

}

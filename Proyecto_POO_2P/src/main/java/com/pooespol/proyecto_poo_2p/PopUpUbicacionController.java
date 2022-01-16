/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooespol.proyecto_poo_2p;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author leonel
 */
public class PopUpUbicacionController implements Initializable {

    @FXML
    private Label lbNombre;
    @FXML
    private Label lbDireccion;
    @FXML
    private Label lbTiempo;
    @FXML
    private Button btnCerrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void setLbNombre(Label lbNombre) {
        this.lbNombre = lbNombre;
    }

    public void setLbDireccion(Label lbDireccion) {
        this.lbDireccion = lbDireccion;
    }

    public void setLbTiempo(Label lbTiempo) {
        this.lbTiempo = lbTiempo;
    }

    public void setBtnCerrar(Button btnCerrar) {
        this.btnCerrar = btnCerrar;
    }
    
    
    
}

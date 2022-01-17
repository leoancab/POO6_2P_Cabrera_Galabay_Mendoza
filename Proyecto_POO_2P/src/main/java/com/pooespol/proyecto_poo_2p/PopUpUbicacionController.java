/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooespol.proyecto_poo_2p;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author leonel
 */
public class PopUpUbicacionController implements Initializable {

    @FXML
    private static Label lbNombre = new Label();
    @FXML
    private static Label lbDireccion = new Label();
    @FXML
    private static Label lbTiempo = new Label();
    @FXML
    private static Button btnCerrar = new Button("Cerrar");


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //setInfoVentana();

            }
        });

        t.setDaemon(true);
        t.start();
    
    }

}

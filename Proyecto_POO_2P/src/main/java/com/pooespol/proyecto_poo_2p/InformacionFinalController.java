/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pooespol.proyecto_poo_2p;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leonel
 */
public class InformacionFinalController implements Initializable {

    @FXML
    private Label lbThread;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 5; i > 0; i--) {
                    String status = "Se cierra en " + i + " segundo(s)";
                    Platform.runLater(() -> {
                        lbThread.setText(status);
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                Stage s = (Stage)lbThread.getScene().getWindow();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        
                        s.close();
                    }
                });
            }
        });
        t.setDaemon(true);
        t.start();
    }
}

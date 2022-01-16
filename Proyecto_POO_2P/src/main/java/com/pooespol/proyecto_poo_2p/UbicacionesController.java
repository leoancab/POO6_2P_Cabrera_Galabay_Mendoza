/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pooespol.proyecto_poo_2p;

import com.pooespol.proyecto_poo_2p.VithasLabsApp;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author leonel
 */
public class UbicacionesController implements Initializable {
    private int numero = (int) (Math.random() * 11 + 1);
    @FXML
    private Pane root;
    
    @FXML
    private VBox vb = new VBox();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        root.getChildren().add(vb);
        
        //Ubicamos los centros de salud en el mapa
        ubicarLocales();
        
        
    }

    public void ubicarLocales() {
        
        Thread hilo1 = new Thread(new Runnable() {
            @Override
            public void run() {
                
                for (int i = 0; i < 5; i++) {
                
                Platform.runLater(new Runnable() {
            @Override
            public void run() {
                
                
                    System.out.println(numero);
                    ImageView vistaImagen = null;
                    
                    try (FileInputStream fis = new FileInputStream(VithasLabsApp.pathImg + "ubicacion.png")) {
                        Image imagen = new Image(fis, 50, 50, false, false);
                        vistaImagen = new ImageView(imagen);
                        
                    } catch (IOException e) {
                        System.out.println("No se encuentra la imagen");
                    }
                    //VBox vb = new VBox(vistaImagen);
                    //root.getChildren().add(vb);
                    vb.getChildren().add(vistaImagen);
                    
                
            }
        });
                
                try {
                        Thread.sleep(numero * 1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                
                
                
                
                
            }
        });
        hilo1.setDaemon(true);
        hilo1.start();
        
        
    }
}
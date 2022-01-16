/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pooespol.proyecto_poo_2p;

import com.pooespol.proyecto_poo_2p.VithasLabsApp;
import com.pooespol.proyecto_poo_2p.modelo.Local;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leonel
 */
public class UbicacionesController implements Initializable {

    private int numero = (int) (Math.random() * 10 + 1);
    @FXML
    private Pane root;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Ubicamos los centros de salud en el mapa
        ubicarLocales();

    }

    public void ubicarLocales() {
        ArrayList<Local> locales = Local.obtenerLocal();

        Thread hilo1 = new Thread(new Runnable() {
            @Override
            public void run() {

                for (Local l : locales) {

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            System.out.println(numero);
                            ImageView vistaImagen = null;

                            try (FileInputStream fis = new FileInputStream(VithasLabsApp.pathImg + "ubicacion.png")) {
                                Image imagen = new Image(fis, 50, 50, false, false);
                                vistaImagen = new ImageView(imagen);
                                vistaImagen.relocate(l.getCordX()-25, l.getCordY()-25);
                                

                            } catch (IOException e) {
                                System.out.println("No se encuentra la imagen");
                            }

                            root.getChildren().add(vistaImagen);
                            vistaImagen.setOnMouseClicked(new EventHandler <MouseEvent>(){
                                @Override
                                public void handle(MouseEvent t) {
                                    try {
                                        mostrarVentana();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });

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
    
    public void mostrarVentana() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(VithasLabsApp.class.getResource("popUpUbicacion.fxml"));
        Parent rootNuevo = fxmlLoader.load();
        Scene scene = new Scene(rootNuevo);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(true);
        
        
        stage.show();
    }
}

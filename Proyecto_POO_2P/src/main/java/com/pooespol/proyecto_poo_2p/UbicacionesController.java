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
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
        VithasLabsApp.fondo("mapa", ".png", root);
        mostrarLocales();
    }
    
    public void mostrarLocales() {
        Thread hilo1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ubicarLocales();
            }
        });
        
        hilo1.setDaemon(true);
        hilo1.start();
    }
    
    private void ubicarLocales() {
        ArrayList<Local> locales = Local.obtenerLocal();
        for (Local l : locales) {
            System.out.println(l);
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    
                    System.out.println(numero);
                    ImageView vistaImagen = null;
                    
                    try (FileInputStream fis = new FileInputStream(VithasLabsApp.pathImg + "ubicacion.png")) {
                        Image imagen = new Image(fis, 50, 50, false, false);
                        vistaImagen = new ImageView(imagen);
                        vistaImagen.relocate(l.getCordX() - 25, l.getCordY() - 25);
                        
                        vistaImagen.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent t) {
                                mostrarVentana(l.getNombre(), l.getDireccion());
                            }
                        });
                        
                    } catch (IOException e) {
                        System.out.println("No se encuentra la imagen");
                    }
                    
                    root.getChildren().add(vistaImagen);
                    
                }
            });
            
            try {
                Thread.sleep(numero * 1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
    }
    
    public void mostrarVentana(String nombre, String direccion) {
        //Crear labels
        Label lNombre = new Label(nombre);
        Label lDireccion = new Label(direccion);
        Label lContador = new Label("mostrando n segundos...");

        //Creamos el boton para poder cerrar la ventana.
        Button btnCerrar = new Button("Cerrar");

        //Creamos los contenedores que contengan los label y boton. 
        VBox info = new VBox(lNombre, lDireccion);
        HBox control = new HBox(lContador, btnCerrar);

        //El root principal de nuestra ventana.
        VBox popUp = new VBox(info, control);

        //Agregamos proiedades a los contenedores
        popUp.setPrefSize(350, 150);
        info.setPrefHeight(75);
        info.setAlignment(Pos.BOTTOM_LEFT);
        info.setPadding(new Insets(25, 25, 0, 25));
        control.setPrefHeight(75);
        control.setPadding(new Insets(25));
        control.setSpacing(110);
        
        Scene scene = new Scene(popUp);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        //Cerrarmos la ventana cuando detecte la accion en el boton.
        btnCerrar.setOnAction(e -> stage.close());

        //Creamos un hilo.
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                setInfoVentana(stage, lContador);

                //Cerramos la ventana emergente despues de 5 segundos.
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.close();
                    }
                });
            }
        });
        t.setDaemon(true);
        t.start();
    }
    
    private void setInfoVentana(Stage stage, Label contador) {
        for (int i = 5; i > 0; i--) {
            String status = "Mostrando " + i + " segundos...";
            System.out.println(status);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    contador.setText(status);
                }
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

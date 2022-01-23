/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pooespol.proyecto_poo_2p;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author leonel
 */
public class AgendarPruebaP2Controller implements Initializable {

    private int numero = (int) (Math.random() * 9);
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfHora;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private Label lbAdvertencia;
    @FXML
    private Pane pnUbicacion;
    private double x;
    private double y;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ubicarPin();
        String direccion = tfDireccion.getText();
        String fecha = dpFecha.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String hora = tfHora.getText();
    }

    public void ubicarPin() {
        pnUbicacion.setOnMouseClicked((MouseEvent t) -> {
            ImageView imageview = null;
            try (FileInputStream fis = new FileInputStream(VithasLabsApp.pathImg + "PinMapa.png")) {
                Image imagen = new Image(fis, 30, 40, false, false);
                imageview = new ImageView(imagen);
            } catch (IOException e) {
                System.out.println("No se ha encontrado la imagen.");
            }
            x = t.getX() - 15;
            y = t.getY() - 40;
            imageview.setLayoutX(x);
            imageview.setLayoutY(y);
            pnUbicacion.getChildren().add(imageview);
        });
    }

    public String crearIdSolicitud() {
        String id = "";
        for (int i = 0; i < 6; i++) {
            id += String.valueOf(numero);
        }
        return id;
    }

    public void escribir() {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(VithasLabsApp.pathFile + "contratatacionesPruebas.txt", true);
            bw = new BufferedWriter(fw);
            bw.write(crearIdSolicitud() + "," + InicioSesionController.userLogin.getUsuario() + "," + tfDireccion.getText() + dpFecha.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + tfHora.getText() + x + "," + y + "," + /*AgendarPruebaController.totalPagar +*/ "\n");
            bw.close();
            System.out.println("Escribiendo...");
        } catch (IOException e) {
            System.out.println("Error...");
        }
    }
}

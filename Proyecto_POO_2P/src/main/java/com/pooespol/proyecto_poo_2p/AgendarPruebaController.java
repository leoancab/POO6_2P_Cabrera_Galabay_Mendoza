/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pooespol.proyecto_poo_2p;

import com.pooespol.proyecto_poo_2p.modelo.Prueba;
import com.pooespol.proyecto_poo_2p.modelo.TipoPrueba;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author leonel
 */
public class AgendarPruebaController implements Initializable {

    @FXML
    private ComboBox<String> cbTipo;
    @FXML
    private ComboBox<Prueba> cbPrueba;
    @FXML
    private Label lbPrecio;
    @FXML
    private TextField tfCantidad;
    @FXML
    private Label lbSubtotal;
    @FXML
    private Label lbTotal;
    @FXML
    private VBox root;
    @FXML
    private Pane rootAgendar;
    @FXML
    private Pane rootDetalle;
    @FXML
    private Button btnContinuar;
    @FXML
    private Label lbNombre1;
    @FXML
    private Label lbNombre2;
    @FXML
    private Label lbCantidad1;
    @FXML
    private Label lbCantidad2;
    @FXML
    private Label lbPrecio1;
    @FXML
    private Label lbPrecio2;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbTipo.getItems().addAll("Diagnóstico", "Anticuerpos");
        ArrayList<Prueba> pruebas = crearPruebas();
    }

    public ArrayList<Prueba> crearPruebas() {
        ArrayList<Prueba> pruebas = new ArrayList<>();

        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(VithasLabsApp.pathFile + "pruebas.txt", StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                Prueba prueba = new Prueba(linea.split(",")[0], TipoPrueba.valueOf(linea.split(",")[1]), linea.split(",")[2], linea.split(",")[3]);
                pruebas.add(prueba);
            }
        } catch (IOException e) {
            System.out.println("No se encontró el archivo");
        } finally {
            try {
                if (br != null) {
                    System.out.println("Cerrando archivo...");
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Error...");
            }
        }
        return pruebas;
    }
}

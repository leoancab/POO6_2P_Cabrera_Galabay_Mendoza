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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
    private Label lbValorUnitario;
    @FXML
    private TextField tfCantidad;
    @FXML
    private Label lbSubtotal;
    @FXML
    private Label lbTotal;
    @FXML
    private GridPane gpDetalle;
    @FXML
    private Label lbAdvertencia;
    private double subtotal = 0;
    private final ArrayList<String> pruebasCitas = new ArrayList<>();
    private double totalPagar = 0;

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
        //System.out.println(verificarCantidad());
        cbTipo.setOnAction((ActionEvent t) -> {
            if (cbTipo.getSelectionModel().getSelectedItem().equals("Diagnóstico")) {
                cbPrueba.getItems().clear();
                for (int i = 0; i < 2; i++) {
                    cbPrueba.getItems().add(pruebas.get(i));
                }
            }
            if (cbTipo.getSelectionModel().getSelectedItem().equals("Anticuerpos")) {
                cbPrueba.getItems().clear();
                for (int i = 2; i < pruebas.size(); i++) {
                    cbPrueba.getItems().add(pruebas.get(i));
                }
            }
        });
        cbPrueba.setOnAction((ActionEvent t) -> {
            int p = cbPrueba.getSelectionModel().getSelectedIndex();
            if (cbTipo.getSelectionModel().getSelectedItem().equals("Diagnóstico")) {
                lbValorUnitario.setText(pruebas.get(p).getPrecioPrueba());
            } else if (cbTipo.getSelectionModel().getSelectedItem().equals("Anticuerpos")) {
                p += 2;
                lbValorUnitario.setText(pruebas.get(p).getPrecioPrueba());
            }
        });
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

    @FXML
    public ArrayList<String> agregarCita() {
        Label lbNombre = new Label(String.valueOf(cbPrueba.getSelectionModel().getSelectedItem()));
        Label lbCantidad = new Label(tfCantidad.getText());
        Label lbPrecio = new Label(String.valueOf(Double.valueOf(tfCantidad.getText()) * Double.valueOf(cbPrueba.getSelectionModel().getSelectedItem().getPrecioPrueba())) + "0");

        lbNombre.setPrefWidth(100);
        lbNombre.setAlignment(Pos.CENTER);
        lbCantidad.setPrefWidth(100);
        lbCantidad.setAlignment(Pos.CENTER);
        lbPrecio.setPrefWidth(100);
        lbPrecio.setAlignment(Pos.CENTER);

        gpDetalle.addColumn(0, lbNombre);
        gpDetalle.addColumn(1, lbCantidad);
        gpDetalle.addColumn(2, lbPrecio);

        subtotal += Double.valueOf(lbPrecio.getText());
        lbSubtotal.setText(String.valueOf(subtotal) + "0");
        lbTotal.setText(String.valueOf(Double.valueOf(lbSubtotal.getText()) + 5.0) + "0");

        pruebasCitas.add(lbNombre.getText());

        lbValorUnitario.setText("");
        tfCantidad.clear();
        return pruebasCitas;
    }

    public double total() {
        totalPagar = Double.valueOf(lbTotal.getText());
        return totalPagar;
    }

    @FXML
    public void continuar() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VithasLabsApp.class.getResource("agendarPruebaP2.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}

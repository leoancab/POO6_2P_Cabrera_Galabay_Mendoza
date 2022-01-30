/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pooespol.proyecto_poo_2p;

import static com.pooespol.proyecto_poo_2p.VithasLabsApp.setIcono;
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
    public static ArrayList<Prueba> pruebasCita = new ArrayList<>();
    public static double totalPagar;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Ubicar las opciones en el ComboBox de tipo de prueba.
        cbTipo.getItems().addAll("Diagnóstico", "Anticuerpos");

        //Se genera la lista de pruebas.
        ArrayList<Prueba> pruebas = crearPruebas();

        /*
        Este control permite ubicar las opciones correspondientes en el comboBox
        de prueba segun el tipo de prueba que se haya seleccionado
         */
        cbTipo.setOnAction((ActionEvent t) -> {
            String op = cbTipo.getSelectionModel().getSelectedItem();
            setOpcionesPrueba(op, pruebas);
        });
        cbPrueba.setOnAction((ActionEvent t) -> {
            setPrecioLabl();
        });
    }

    private void setOpcionesPrueba(String tipoPrueba, ArrayList<Prueba> listaP) {
        //Limpiamos el contenido del comboBox del contenido de prueba.
        cbPrueba.getItems().clear();
        for (Prueba p : listaP) {
            //Se agregan las pruebas de ese tipo al comoBox de pruebas.
            if (p.getTipoPrueba().equals(TipoPrueba.valueOf(tipoPrueba))) {
                cbPrueba.getItems().add(p);
            }
        }
    }

    private void setPrecioLabl() {
        Prueba p = cbPrueba.getSelectionModel().getSelectedItem();
        if (p != null) {
            lbValorUnitario.setText(p.getPrecioPrueba().toString() + "0");
        }
    }

    /*Lee el archivo de pruebas, genera la lista de pruebas
    y retorna la lista de pruebas.*/
    public ArrayList<Prueba> crearPruebas() {
        ArrayList<Prueba> pruebas = new ArrayList<>();

        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(VithasLabsApp.pathFile + "pruebas.txt", StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                Prueba prueba = new Prueba(linea.split(",")[0], TipoPrueba.valueOf(linea.split(",")[1]), linea.split(",")[2], Double.valueOf(linea.split(",")[3]));
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

    /*Se agregan */
    @FXML
    public ArrayList<Prueba> agregarCita() {
        String cantidad = tfCantidad.getText();
        String tipo = cbTipo.getSelectionModel().getSelectedItem();
        Prueba prueba = cbPrueba.getSelectionModel().getSelectedItem();

        try {
            //Se verifica si el texto ingresado al textfield sea un numero.
            Integer.valueOf(cantidad);
            //Se comprueba que los campos no esten vacios.
            if (cantidad.equals("") || tipo == null || prueba == null) {
                throw new CamposIncompletosException("Error");
                //Si la cantidad ingresada es menos a 1, se avisa.
            } else if (Integer.valueOf(cantidad) < 1) {
                System.out.println("Cantidad no puede ser menor a 1");
                lbAdvertencia.setText("Cantidad no puede ser menor a 1");
                //Si los campos estan llenos, se prosigue.
            } else {
                Label lbNombre = new Label(String.valueOf(cbPrueba.getSelectionModel().getSelectedItem()));
                lbNombre.setStyle("-fx-text-fill: white");
                Label lbCantidad = new Label(tfCantidad.getText());
                lbCantidad.setStyle("-fx-text-fill: white");
                Label lbPrecio = new Label(String.valueOf(Double.valueOf(tfCantidad.getText()) * cbPrueba.getSelectionModel().getSelectedItem().getPrecioPrueba()) + "0");
                lbPrecio.setStyle("-fx-text-fill: white");
                
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
                totalPagar = Double.valueOf(lbTotal.getText());

                pruebasCita.add(cbPrueba.getSelectionModel().getSelectedItem());

                lbValorUnitario.setText("");
                tfCantidad.clear();
                lbAdvertencia.setText("");
            }
            //Se atrapa la excepcion en el caso de que los campos no esten llenos.
        } catch (CamposIncompletosException e) {
            System.out.println("Campos incompletos, no se puede agregar");
            lbAdvertencia.setText("Campos incompletos, no se puede agregar");
            //Se atrapa la excepcion en el caso de que el texto ingresado a cantidad no sea un numero.
        } catch (NumberFormatException e) {
            System.out.println("Texto ingresado no es un numero");
            lbAdvertencia.setText("Texto ingresado no es un numero");
        }
        return pruebasCita;
    }

    @FXML
    public void continuar() throws IOException {
        System.out.println(pruebasCita);
        FXMLLoader fxmlLoader = new FXMLLoader(VithasLabsApp.class.getResource("agendarPruebaP2.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        setIcono(stage);
        stage.setTitle("Vithas Labs - Agende Su Cita");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}

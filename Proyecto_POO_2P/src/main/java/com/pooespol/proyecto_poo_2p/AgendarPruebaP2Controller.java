/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pooespol.proyecto_poo_2p;

import static com.pooespol.proyecto_poo_2p.AgendarPruebaController.pruebasCita;
import com.pooespol.proyecto_poo_2p.modelo.Prueba;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leonel
 */
public class AgendarPruebaP2Controller implements Initializable {

    @FXML
    private TextField tfDireccion;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private Label lbAdvertencia;
    private double x;
    private double y;
    @FXML
    private ComboBox<String> cbHora;
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
        ArrayList<Prueba> pruebasCita = AgendarPruebaController.pruebasCita;
        ubicarPin();
        cbHora.getItems().addAll("07:00", "08:00", "09:00", "10:00", "11:00", "12:00");
    }
    
    public void ubicarPin() {
        root.setOnMouseClicked((MouseEvent t) -> {
            root.getChildren().clear();
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
            root.getChildren().add(imageview);
        });
    }

    public String crearIdSolicitud() {
        String id = "";
        for (int i = 0; i < 6; i++) {
            int numero = (int) (Math.random() * 9);
            id += String.valueOf(numero);
        }
        return id;
    }

    public void escribirContrataciones() {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(VithasLabsApp.pathFile + "contratatacionesPruebas.txt", true);
            bw = new BufferedWriter(fw);
            bw.write(crearIdSolicitud() + "," + InicioSesionController.userLogin.getUsuario() + "," + tfDireccion.getText() + "," + dpFecha.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "," + cbHora.getSelectionModel().getSelectedItem() + "," + x + "," + y + "," + AgendarPruebaController.totalPagar + "\n");
            bw.close();
            System.out.println("Escribiendo...");
        } catch (IOException e) {
            System.out.println("Error...");
        }
    }

    public void escribirDetalles() {
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(VithasLabsApp.pathFile + "contratatacionesPruebas.txt", StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String id = linea.split(",")[0];
                FileWriter fw = null;
                BufferedWriter bw = null;
                try {
                    fw = new FileWriter(VithasLabsApp.pathFile + "detallesSolicitudes.txt", true);
                    bw = new BufferedWriter(fw);
                    bw.write(id);
                    for (Prueba p : pruebasCita) {
                        bw.write("," + p.getCodigoPrueba());
                    }
                    bw.write("\n");
                    bw.close();
                    System.out.println("Escribiendo...");
                } catch (IOException e) {
                    System.out.println("Error...");
                }
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
    }

    public void mostrarInfo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VithasLabsApp.class.getResource("informacionFinal.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void finalizar() throws IOException {
        String direccion = tfDireccion.getText();
        String fecha = dpFecha.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        try {
            if (direccion.equals("") || fecha == null || cbHora == null) {
                throw new CamposIncompletosException("Error");
            } else {
                escribirContrataciones();
                escribirDetalles();
                mostrarInfo();
                lbAdvertencia.setText("");
            }
        } catch (CamposIncompletosException e) {
            System.out.println("Campos incompletos");
            lbAdvertencia.setText("Campos incompletos");
        }

    }

}

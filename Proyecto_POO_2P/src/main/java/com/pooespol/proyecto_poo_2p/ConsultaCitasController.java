/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto_poo_2p;

import static com.pooespol.proyecto_poo_2p.OpcionesLaboratoristaController.citasS;
import static com.pooespol.proyecto_poo_2p.VithasLabsApp.setIcono;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marti
 */
public class ConsultaCitasController implements Initializable {

    @FXML
    private TableView<Cita> tablaDatos;
    @FXML
    private TableColumn<Cita, String> nombres;
    @FXML
    private TableColumn<Cita, String> apellidos;
    @FXML
    private TableColumn<Cita, String> fecha;
    @FXML
    private TableColumn<Cita, String> solicitud;
    
    private ObservableList<Cita> citas = FXCollections.observableArrayList(citasS);
    
    @FXML
    private Button btnCerrar;
    
    private Stage stage;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        nombres.setMinWidth(150);
        nombres.setStyle("-fx-alignment: center");
        apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        apellidos.setMinWidth(150); 
        apellidos.setStyle("-fx-alignment: center");
        fecha.setCellValueFactory(new PropertyValueFactory<>("fechas"));
        fecha.setMaxWidth(79); 
        fecha.setStyle("-fx-alignment: center");
        solicitud.setCellValueFactory(new PropertyValueFactory<>("nroSolicitud"));
        solicitud.setMaxWidth(79); 
        solicitud.setStyle("-fx-alignment: center");
        Collections.sort(citas);
        tablaDatos.setItems(citas);
        
        
    }    

    @FXML
    private void cerrarVentana(ActionEvent event) {
        stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
    
//    public ObservableList<Cita> obtenerCitas() {
//        ObservableList<Cita> citas = FXCollections.observableArrayList();
//        for (Cita c: citasS) {
//            citas.add(c);
//        }
//        System.out.println("Lista Citas: " + citas);
//        return citas;
//    }
//    
}

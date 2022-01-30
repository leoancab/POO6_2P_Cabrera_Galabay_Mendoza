/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto_poo_2p;

import static com.pooespol.proyecto_poo_2p.OpcionesLaboratoristaController.citasS;
import java.net.URL;
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
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombres.setCellValueFactory(new PropertyValueFactory<Cita, String>("nombres"));
        
        apellidos.setCellValueFactory(new PropertyValueFactory<Cita, String>("apellidos"));
        
        fecha.setCellValueFactory(new PropertyValueFactory<Cita, String>("fechas"));
        
        solicitud.setCellValueFactory(new PropertyValueFactory<Cita, String>("nroSolicitud"));
        
        tablaDatos.setItems(citas);
    }    

    @FXML
    private void cerrar(ActionEvent event) {
        
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

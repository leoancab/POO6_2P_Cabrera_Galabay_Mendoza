/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto_poo_2p;

import com.pooespol.proyecto_poo_2p.modelo.Prueba;
import com.pooespol.proyecto_poo_2p.modelo.usuarios.Genero;
import com.pooespol.proyecto_poo_2p.modelo.usuarios.Paciente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.ArrayList;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marti
 */
public class OpcionesLaboratoristaController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private Label l_bienvenida;
    @FXML
    private Label Advertencia;
    @FXML
    private Button btnGCC;
    @FXML
    private Button btnConsultarCitas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VithasLabsApp.fondo("fondoPaciente", ".jpg", root);
        String userLab = InicioSesionController.userLogin.getUsuario();
        l_bienvenida.setText("Bienvenido " + userLab);
    }

    

//    private void MensajeAlerta(ActionEvent event) {
//
//        File arch = new File(VithasLabsApp.pathFile + "PacientesCita.txt");
//        if (arch.length() == 0) {
//            Mensaje.setText("Debe generar el consolidado antes de consultar");
//        }
//    }

    @FXML
    private void consultarCitas(ActionEvent event) throws IOException {
        File archivo = new File(VithasLabsApp.pathFile + "pruebasSolicitadas.xd");
        System.out.println(archivo.exists());
        if (archivo.exists()){
            
        Stage s = new Stage();
        FXMLLoader fx = new FXMLLoader(VithasLabsApp.class.getResource("consultaCitas.fxml"));
        Parent root = fx.load();
        Scene sc = new Scene(root);
        s.setScene(sc);
        s.show();
            
        } else {
             Advertencia.setText("ADVERTENCIA: Debe generar el consolidado antes de consultar");
        }

    }

    @FXML
    private void serializarCitas(ActionEvent event) throws ParseException {

        ArrayList<String> lUsuarios = new ArrayList<String>();
        //ArrayList<Paciente> lPaciente = VithasLabsApp.pacientes;
        ArrayList<Paciente> pacientesConCitas = new ArrayList<Paciente>();
        
        
        //Guardas los nombres que contrataron el servicio en la lista lUsuarios
        try {
            FileReader fr = new FileReader(VithasLabsApp.pathFile + "ContratacionesPruebas.txt");
            BufferedReader bf = new BufferedReader(fr);
            String linea;

            while ((linea = bf.readLine()) != null) {
                lUsuarios.add(linea.split(",")[1]);
            }
        } catch (IOException e) {
            System.out.println("No se ha podido leer el archivo");
        }

        for(Paciente p: VithasLabsApp.pacientes) {
            for (String u: lUsuarios) {
                if (p.getUsuario().equals(u))
                    pacientesConCitas.add(p);
            }
        }
        
        //Serealizar listas pacientesConCitas
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(VithasLabsApp.pathFile + "pruebasSolicitadas.xd"))) {
            salida.writeObject(pacientesConCitas);

        } catch (IOException e) {

        }

    }

}

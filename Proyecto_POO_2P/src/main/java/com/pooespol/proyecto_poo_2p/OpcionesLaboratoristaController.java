/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto_poo_2p;

import static com.pooespol.proyecto_poo_2p.VithasLabsApp.setIcono;
import com.pooespol.proyecto_poo_2p.modelo.usuarios.Paciente;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    //public static ArrayList<Cita> citasS;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VithasLabsApp.fondo("fondoPaciente", ".jpg", root);
        String userLab = InicioSesionController.userLogin.getUsuario();
        l_bienvenida.setText("Bienvenido " + userLab);
    }
    
    /**
     * Abre una nueva ventana para poder ver todas las citas agendadas
     * por los pacientes siempre y cuando se haya consolidado el archivo antes.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void consultarCitas(ActionEvent event) throws IOException {
        File archivo = new File(VithasLabsApp.pathFile + "pruebasSolicitadas.xd");
        System.out.println(archivo.exists());
        if (archivo.exists()) {

            Stage s = new Stage();
            setIcono(s);
            s.setTitle("Vithas Labs - Consultar Citas");
            FXMLLoader fx = new FXMLLoader(VithasLabsApp.class.getResource("consultaCitas.fxml"));
            Parent root1 = fx.load();
            root1.setStyle("-fx-background-color: linear-gradient(#ffffff 20%, #5239d1 )");
            Scene sc = new Scene(root1);
            s.setScene(sc);
            s.show();

        } else {
            Advertencia.setText("ADVERTENCIA: Debe generar el consolidado antes de consultar");
        }

    }
    
    /**
     * Genera un paciente segun el usuario ingresado.
     * @param u String usuario
     * @param listaP Lsita de pacientes
     * @return Retrna al paciente que corresponda ese usuario
     */
    private static Paciente generarPaciente(String u, ArrayList<Paciente> listaP) {
        Paciente paciente = null;
        for (Paciente p : listaP) {
            if (p.getUsuario().equals(u)) {
                paciente = p;
            }
        }
        return paciente;
    }

    /**
     * Serializa Una lista de Citas para pode consultar esos datos después.
     * @param event 
     */
    @FXML
    private void serializarCitas(ActionEvent event) {

        //ArrayList<String> lUsuarios = new ArrayList<String>();
        //ArrayList<Paciente> lPaciente = VithasLabsApp.pacientes;
        ArrayList<Cita> citas = new ArrayList<Cita>();

        //Guardas los nombres que contrataron el servicio en la lista lUsuarios
        try {
            FileReader fr = new FileReader(VithasLabsApp.pathFile + "contratacionesPruebas.txt");
            BufferedReader bf = new BufferedReader(fr);
            String linea;

            while ((linea = bf.readLine()) != null) {
                //lUsuarios.add(linea.split(",")[1]);
                String[] info = linea.split(",");

                Paciente p = generarPaciente(info[1], VithasLabsApp.pacientes);
                //p.getNombres();
                System.out.println(p);
                Cita c = new Cita(p.getNombres(), p.getApellidos(), info[3], info[0]);
                citas.add(c);
            }
            //citasS = citas;
        } catch (IOException e) {
            System.out.println("No se ha podido leer el archivo");
        }

        //Serealizar listas pacientesConCitas
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(VithasLabsApp.pathFile + "pruebasSolicitadas.xd"))) {
            System.out.println(citas);
            salida.writeObject(citas);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        Advertencia.setText("Se ha generado el archivo de Citas");
    }
    
    /**
     * Regesa a la escena anteorir para poder iniciar sesión con otra cuenta.
     * @param event 
     */
    @FXML
    private void regresarAlLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(VithasLabsApp.class.getResource("inicioSesion.fxml"));
            Parent root = fxmlLoader.load();
            root.setStyle("-fx-background-color: linear-gradient(#ffffff 20%, #5239d1 )");
            VithasLabsApp.scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(OpcionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

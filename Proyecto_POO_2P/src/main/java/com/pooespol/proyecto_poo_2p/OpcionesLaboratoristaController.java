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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marti
 */
public class OpcionesLaboratoristaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void EscribirPacientesCita(ArrayList<Paciente> lPaciente){
        
   
        try{
            FileWriter fw = null;
            BufferedWriter bw = null;
            fw = new FileWriter(VithasLabsApp.pathFile+"PacientesCita.txt", true);
            for(Paciente p1: lPaciente){
              bw = new BufferedWriter(fw);
              bw.write(p1+"\n");  
              bw.close();
            }
            
            
            System.out.println("Escribiendo...");
        } catch (IOException e) {
            System.out.println("Error...");
        }
        }
    
 
    public void SerializarCitas() throws ParseException, FileNotFoundException, IOException{
        ArrayList<String> lUsuarios = new ArrayList<String>();
        ArrayList<Paciente> lPaciente = new ArrayList<Paciente>();
        
        try{
            FileReader fr = new FileReader(VithasLabsApp.pathFile+"Contrataciones.txt");
            BufferedReader bf = new BufferedReader(fr);
            String linea;
            
            while((linea = bf.readLine()) != null){
                lUsuarios.add(linea.split(",")[1]);  
            }
        } catch(IOException e){
            System.out.println("No se ha podido leer el archivo");
        }
        
            try{
            FileReader fr = new FileReader(VithasLabsApp.pathFile+"pacientes.txt");
            BufferedReader bf = new BufferedReader(fr);
            String linea;
            
            SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
            while((linea = bf.readLine()) != null){
                Paciente p = new Paciente(linea.split(",")[0],linea.split(",")[1],linea.split(",")[2],linea.split(",")[3],
                formato.parse(linea.split(",")[4]),Genero.valueOf(linea.split(",")[5]),linea.split(",")[6],linea.split(",")[7],linea.split(",")[8]);
                 for(String u: lUsuarios){
                    if (u.equals(linea.split(",")[0])){
                        lPaciente.add(p);
                        
                    }
            }
           
            }
        } catch(IOException e){
            System.out.println("No se ha podido leer el archivo");
        }
            
        try(ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(VithasLabsApp.pathFile + "PruebasSolicitadas.txt"))){  
            salida.writeObject(lPaciente);
        
         }catch(IOException e){
             
         }
    }
        @FXML
        private Label l_bienvenida;
        @FXML
        private Label Mensaje;

        @FXML
        private void MensajeAlerta(ActionEvent event){
        
            File arch = new File(VithasLabsApp.pathFile+"PacientesCita.txt");
            if(arch.length() == 0){
                Mensaje.setText("Debe generar el consolidado antes de consultar");
            }
        }
        

        @FXML
        private void consultarCitas(ActionEvent event) throws IOException {
            Stage s = new Stage();
            FXMLLoader fx = new FXMLLoader(VithasLabsApp.class.getResource("consultaCitas.fxml"));
            Parent root = fx.load();
            Scene sc = new Scene(root);
            s.setScene(sc);
            s.show();
            
        }
    

}
    
            
        
         
         

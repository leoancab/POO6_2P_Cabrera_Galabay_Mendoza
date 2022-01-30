/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto_poo_2p;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Marti
 */
public class ConsultaCitasController implements Initializable {


    
    @FXML
    private GridPane paneInfo;
    @FXML
    private Button cerrar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> lCod = new ArrayList<String>();
        ArrayList<String> lNom = new ArrayList<String>();
        ArrayList<String> lApe = new ArrayList<String>();
        ArrayList<Date> lFecha = new ArrayList<Date>();
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");
        
        try{
            FileReader fr = new FileReader(VithasLabsApp.pathFile+"Contrataciones.txt");
            BufferedReader bf = new BufferedReader(fr);
            String linea;
            
            while((linea = bf.readLine()) != null){
                lCod.add(linea.split(",")[0]);    
                lFecha.add(formato.parse(linea.split(",")[2])); 
            }
        } catch(IOException e){
            System.out.println("No se ha podido leer el archivo");
        } catch (ParseException ex) {
            Logger.getLogger(ConsultaCitasController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se ha podido obtener la Fecha");
        }
        try{
            FileReader fr = new FileReader(VithasLabsApp.pathFile+"pacientes.txt");
            BufferedReader bf = new BufferedReader(fr);
            String linea;
            while((linea = bf.readLine()) != null){
                String nombre = linea.split(",")[2];
                String apellido = linea.split(",")[3];
                lNom.add(nombre);
                lApe.add(apellido);
                
                
            }
        }catch(IOException e){
            System.out.println("No se ha podido leer el archivo");
        }
        for(int i=0; i < lCod.size() - 1; i++){
           Label lNombre = new Label();
           lNombre.setText(lNom.get(i));
           Label lApellidos = new Label();
           lApellidos.setText(lApe.get(i));
           Label lDate = new Label();
           String f1 = formato.format(lFecha.get(i));
           lDate.setText(f1);
           Label lID = new Label();
           lID.setText(lCod.get(i));
           paneInfo.addColumn(0, lNombre);
           paneInfo.addColumn(1, lApellidos);
           paneInfo.addColumn(2, lDate);
           paneInfo.addColumn(3, lID);
           
           
            
        }
        // TODO
    }    

    @FXML
    private void CerrarVentana(ActionEvent event) {
        Stage s1= (Stage) cerrar.getScene().getWindow();
        s1.close();
        
        
        
    }
    

}

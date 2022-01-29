/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto_poo_2p;

import com.pooespol.proyecto_poo_2p.modelo.Prueba;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.ArrayList;

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
    
    public void serializarCitas(){
        try{
            fr = new FileReader(, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String id = linea.split(",")[0];
                FileWriter fw = null;
                BufferedWriter bw = null;
        }catch{
                
                }
    
        
    
}

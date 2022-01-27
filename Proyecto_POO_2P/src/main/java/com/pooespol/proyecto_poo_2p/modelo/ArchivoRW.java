
package com.pooespol.proyecto_poo_2p.modelo;

import com.pooespol.proyecto_poo_2p.VithasLabsApp;
import com.pooespol.proyecto_poo_2p.modelo.usuarios.Genero;
import com.pooespol.proyecto_poo_2p.modelo.usuarios.Paciente;
import com.pooespol.proyecto_poo_2p.modelo.usuarios.TipoUsuario;
import com.pooespol.proyecto_poo_2p.modelo.usuarios.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArchivoRW { 
    public static void leer(String nomArchivo){
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(nomArchivo, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            String linea;
            while((linea = br.readLine()) != null) {
                System.out.println(linea);
            
            }
            
        } catch (IOException e) {
            System.out.println("No se encontró el " + nomArchivo);
            
        } finally {
            try {
                if (br != null) {
                    System.out.println("Cerrando archivo...");
                    br.close(); 
                }
            }catch(IOException e) {
                System.out.println("Error...");
            }
            
        }
    }
    
    public static void escribir(String nomArchivo, String txt){
        FileWriter fw = null;
        BufferedWriter bw = null;
        try{
            fw = new FileWriter(nomArchivo, true);
            bw = new BufferedWriter(fw);
            bw.write(txt + "\n");
            bw.close();
            System.out.println("Escribiendo...");
        } catch (IOException e) {
            System.out.println("Error...");
        }
    }
    
    
    
    
    
}

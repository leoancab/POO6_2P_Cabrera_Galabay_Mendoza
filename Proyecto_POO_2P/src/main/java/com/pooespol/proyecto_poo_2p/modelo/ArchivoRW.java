
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
    
    public static void generarUsuarios(String nomArchivo) {
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(nomArchivo, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            String linea;
            while((linea = br.readLine()) != null) {
                String[] info = linea.split(","); //[usuario,password,tipo]
                String usuario = info[0];
                String pswrd = info[1];
                TipoUsuario tipo = TipoUsuario.valueOf(info[2]);
                //Creamos e instanciamos el objeto usuario.
                Usuario u = new Usuario(usuario, pswrd, tipo);
                
                //Agregamos el objeto creado a la lista de usuarios del main.
                VithasLabsApp.usuarios.add(u);

            
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
    
    public static void generarPacientes(String nomArchivo) {
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(nomArchivo, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            String linea;
            while((linea = br.readLine()) != null) {
                String[] info = linea.split(","); //[usuario,password,tipo]
                String usuario = info[0];
                String cedula = info[1];
                String nombres = info[2];
                String apellidos = info[3];
                
                //Validar el formato de la fecha caso contrario atrapar la excepcion.
                Date fecha = null;
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                try {
                fecha = formato.parse(info[4]);
                } catch (ParseException e) {
                    System.out.println("Error con el formato fecha.");
                }
                
                Genero genero = Genero.valueOf(info[5]);
                String ciudad = info[6];
                String email = info[7];
                String telefono = info[8];
                
                //Creamos e instanciamos el objeto usuario.
                Paciente p = new Paciente(usuario, cedula, nombres, apellidos, fecha, genero, ciudad, email, telefono);
                
                //Agregamos el objeto creado a la lista de usuarios del main.
                VithasLabsApp.pacientes.add(p);

            
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
    
}

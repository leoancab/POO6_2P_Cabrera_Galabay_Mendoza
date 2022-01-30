/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooespol.proyecto_poo_2p.modelo.usuarios;

import com.pooespol.proyecto_poo_2p.VithasLabsApp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author leonel
 */
public class Paciente {
    
    private String usuario;
    private String cedula;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private Genero genero;
    private String ciudad;
    private String email;
    private String telefono;

    public Paciente(String usuario, String cedula, String nombres, String apellidos, Date fechaNacimiento, Genero genero, String ciudad, String email, String telefono) {
        this.usuario = usuario;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.ciudad = ciudad;
        this.email = email;
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Paciente{" + "usuario=" + usuario + ", cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", fechaNacimiento=" + fechaNacimiento + ", genero=" + genero + ", ciudad=" + ciudad + ", email=" + email + ", telefono=" + telefono + '}';
    }
    
    /**
     * Lee el registro de pacientes y genera una lista de pacientes
     * y los guarda en una variable estatica de la clase controller.     
     */
    public static void generarPacientes(String nomArchivo) {
        ArrayList<Paciente> pacientesL = new ArrayList();
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
                pacientesL.add(p);

            
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
        
        VithasLabsApp.pacientes = pacientesL;
        
    }
    
}

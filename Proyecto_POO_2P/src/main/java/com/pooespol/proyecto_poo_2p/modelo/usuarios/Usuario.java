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
import java.util.ArrayList;

/**
 *
 * @author leonel
 */
public class Usuario {

    private String usuario;
    private String contraseña;
    private TipoUsuario tipoUsuario;

    public Usuario(String usuario, String contraseña, TipoUsuario TipoUsuario) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.tipoUsuario = TipoUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario TipoUsuario) {
        this.tipoUsuario = TipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuario=" + usuario + ", contrase\u00f1a=" + contraseña + ", TipoUsuario=" + tipoUsuario + '}';
    }

    /**
     * Lee el registro de usuarios y genera una lista de usuarios y los guarda
     * en una variable estatica de la clase controller.
     *
     * @param nomArchivo
     */
    public static void generarUsuarios(String nomArchivo) {
        ArrayList<Usuario> usuariosL = new ArrayList();
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(nomArchivo, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] info = linea.split(","); //[usuario,password,tipo]
                String usuario = info[0];
                String pswrd = info[1];
                TipoUsuario tipo = TipoUsuario.valueOf(info[2]);
                //Creamos e instanciamos el objeto usuario.
                Usuario u = new Usuario(usuario, pswrd, tipo);

                //Agregamos el objeto creado a la lista de usuarios del main.
                usuariosL.add(u);

            }

        } catch (IOException e) {
            System.out.println("No se encontró el " + nomArchivo);

        } finally {
            try {
                if (br != null) {
                    System.out.println("Cerrando archivo...");
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Error...");
            }

        }

        VithasLabsApp.usuarios = usuariosL;
    }

}

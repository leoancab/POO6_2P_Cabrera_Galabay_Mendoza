/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooespol.proyecto_poo_2p;

/**
 *
 * @author leonel
 */
public class Usuario {
    
    private String usuario;
    private String contraseña;
    private Usuario TipoUsuario;

    public Usuario(String usuario, String contraseña, Usuario TipoUsuario) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.TipoUsuario = TipoUsuario;
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

    public Usuario getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(Usuario TipoUsuario) {
        this.TipoUsuario = TipoUsuario;
    }
    
    
    
}

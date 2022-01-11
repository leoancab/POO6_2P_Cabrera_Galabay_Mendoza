/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooespol.proyecto_poo_2p.modelo.usuarios;

/**
 *
 * @author leonel
 */
public class Usuario {
    
    private String usuario;
    private String contraseña;
    private TipoUsuario TipoUsuario;

    public Usuario(String usuario, String contraseña, TipoUsuario TipoUsuario) {
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

    public TipoUsuario getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario TipoUsuario) {
        this.TipoUsuario = TipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuario=" + usuario + ", contrase\u00f1a=" + contraseña + ", TipoUsuario=" + TipoUsuario + '}';
    }
    
    
    
}

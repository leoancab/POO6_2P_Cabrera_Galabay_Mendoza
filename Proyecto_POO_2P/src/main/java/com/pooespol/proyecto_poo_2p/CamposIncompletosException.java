/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooespol.proyecto_poo_2p;

/**
 *
 * @author leonel
 */
public class CamposIncompletosException extends Exception {
    
    /**
     * Excepcion en caso de que haya campos vacios en los fomularios.
     * @param message 
     */
    public CamposIncompletosException(String message) {
        super(message);
    }
    
}

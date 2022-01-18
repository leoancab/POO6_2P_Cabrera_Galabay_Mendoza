/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooespol.proyecto_poo_2p.modelo;

//import java.util.Date;
/**
 *
 * @author leonel
 */
public class Prueba {

    private String codigoPrueba;
    private TipoPrueba tipoPrueba;
    private String nombrePrueba;
    private String precioPrueba;

    public Prueba(String codigoPrueba, TipoPrueba tipoPrueba, String nombrePrueba, String precioPrueba) {
        this.codigoPrueba = codigoPrueba;
        this.tipoPrueba = tipoPrueba;
        this.nombrePrueba = nombrePrueba;
        this.precioPrueba = precioPrueba;
    }

    public String getCodigoPrueba() {
        return codigoPrueba;
    }

    public void setCodigoPrueba(String codigoPrueba) {
        this.codigoPrueba = codigoPrueba;
    }

    public TipoPrueba getTipoPrueba() {
        return tipoPrueba;
    }

    public void setTipoPrueba(TipoPrueba tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }

    public String getNombrePrueba() {
        return nombrePrueba;
    }

    public void setNombrePrueba(String nombrePrueba) {
        this.nombrePrueba = nombrePrueba;
    }

    public String getPrecioPrueba() {
        return precioPrueba;
    }

    public void setPrecioPrueba(String precioPrueba) {
        this.precioPrueba = precioPrueba;
    }

    @Override
    public String toString() {
        return nombrePrueba;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto_poo_2p;

import java.io.Serializable;

/**
 *
 * @author cgala
 */
public class Cita implements Serializable, Comparable<Cita> {

    private String nombres;
    private String apellidos;
    private String fechas;
    private String nroSolicitud;

    public Cita(String nombres, String apellidos, String fechas, String nroSolicitud) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechas = fechas;
        this.nroSolicitud = nroSolicitud;
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

    public String getFechas() {
        return fechas;
    }

    public void setFechas(String fechas) {
        this.fechas = fechas;
    }

    public String getNroSolicitud() {
        return nroSolicitud;
    }

    public void setNroSolicitud(String nroSolicitud) {
        this.nroSolicitud = nroSolicitud;
    }

    @Override
    public String toString() {
        return "Cita{" + "nombres=" + nombres + ", apellidos=" + apellidos + ", fechas=" + fechas + ", nroSolicitud=" + nroSolicitud + '}';
    }

    @Override
    public int compareTo(Cita o) {
        return nombres.compareTo(o.getNombres());
        
    }
}

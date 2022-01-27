/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooespol.proyecto_poo_2p.modelo;

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
public class Local {

    private String nombre;
    private String direccion;
    private String horario;
    private double cordX;
    private double cordY;

    public Local(String nombre, String direccion, String horario, double cordX, double cordY) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.horario = horario;
        this.cordX = cordX;
        this.cordY = cordY;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public double getCordX() {
        return cordX;
    }

    public void setCordX(double cordX) {
        this.cordX = cordX;
    }

    public double getCordY() {
        return cordY;
    }

    public void setCordY(double cordY) {
        this.cordY = cordY;
    }

    public static ArrayList<Local> obtenerLocal() {
        ArrayList<Local> locales = new ArrayList<>();
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(VithasLabsApp.pathFile + "sucursales.txt", StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                Local local = new Local(linea.split("-")[3], linea.split("-")[2], linea.split("-")[4], Double.parseDouble(linea.split("-")[0]), Double.parseDouble(linea.split("-")[1]));
                locales.add(local);

            }

        } catch (IOException e) {
            System.out.println("No se encontró el archivo");

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

        return locales;
    }

    @Override
    public String toString() {
        return "Local{" + "nombre=" + nombre + ", direccion=" + direccion + ", horario=" + horario + ", cordX=" + cordX + ", cordY=" + cordY + '}';
    }

}

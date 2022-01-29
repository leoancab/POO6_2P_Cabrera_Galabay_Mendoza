/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto_poo_2p;

import com.pooespol.proyecto_poo_2p.modelo.ArchivoRW;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author cgala
 */
public class NuevoPacienteController extends ArchivoRW implements Initializable {

    @FXML
    private Button btn_Registrar;

    @FXML
    private ToggleGroup genero;

    @FXML
    private TextField txt_Cedula;

    @FXML
    private TextField txt_Nombres;

    @FXML
    private TextField txt_Apellidos;

    @FXML
    private DatePicker date;

    @FXML
    private RadioButton femenino;

    @FXML
    private RadioButton masculino;

    @FXML
    private RadioButton otros;

    @FXML
    private TextField txt_Ciudad;

    @FXML
    private TextField txt_Email;

    @FXML
    private TextField txt_Telefono;

    @FXML
    private TextField txt_Usuario;

    @FXML
    private TextField txt_Contrasenia;

    @FXML
    private VBox vb_Registrar;
    @FXML
    private Label l_mensaje;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void registarPaciente(ActionEvent event) {
        //Obtenemos los valores para 
        String[] texto = new String[]{txt_Cedula.getText(), txt_Nombres.getText(),
            txt_Apellidos.getText(), txt_Ciudad.getText(),
            txt_Email.getText(), txt_Telefono.getText(),
            txt_Usuario.getText(), txt_Contrasenia.getText()};
        int i = 0;
        for (String txt : texto) {
            if (txt.equals("")) {
                i++;
            }
        }
        RadioButton elegido = (RadioButton) genero.getSelectedToggle();

        LocalDate datePicker = date.getValue();
        /*
        Damos formato al label por medio de la programación
        Le cambiamos el tipo de letra y el tamaño de letra.
         */
        l_mensaje.setFont(new Font("Arial", 14));

        if (i > 0 || elegido == null || datePicker == null) {

            l_mensaje.setText("Existen campos vacios, complete todos los datos para poder registrarse.");
            l_mensaje.setStyle("-fx-text-fill: white");
        } else {
            //Damos formato al label por medio de la programación.
            l_mensaje.setText("Usuario registrado");
            l_mensaje.setStyle("-fx-text-fill: #5888ee");

            /*
            Obtenemos la informacion de cada uno de los cmapos solicitados
            al usuario por medio del formulario mostrado en la ventana de 
            Registro de Paciente.
             */
            String usuario = txt_Usuario.getText();
            String cedula = txt_Cedula.getText();
            String nombres = txt_Nombres.getText();
            String apellidos = txt_Apellidos.getText();
            String fecha = datePicker.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String gender = elegido.getText();
            String ciudad = txt_Ciudad.getText();
            String email = txt_Email.getText();
            String telefono = txt_Telefono.getText();
            String pw = txt_Contrasenia.getText();

            //Se generan los registros en ambos archivos:
            //Registrar informacion en usurarios.txt
            escribir(VithasLabsApp.pathFile + "usuarios.txt", (usuario + "," + pw + ",P"));
            //Registrar informacion en pacientes.txt
            String info = usuario + "," + cedula + "," + nombres + "," + apellidos
                    + "," + fecha + "," + gender + "," + ciudad + "," + email + ","
                    + telefono;
            escribir(VithasLabsApp.pathFile + "pacientes.txt", info);
        }
    }
}

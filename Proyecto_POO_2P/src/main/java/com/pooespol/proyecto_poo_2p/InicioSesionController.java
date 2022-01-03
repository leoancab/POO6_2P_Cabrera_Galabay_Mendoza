package com.pooespol.proyecto_poo_2p;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InicioSesionController implements Initializable{

    @FXML
    private TextField txt_Usuario;
    @FXML
    private PasswordField txt_Password;
    @FXML
    private Button btn_IniciarSesion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void cambiarEscena(ActionEvent event) throws IOException {
        App.setRoot("nuevoPaciente");
    }



}
package com.pooespol.proyecto_poo_2p;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    private void crearNuevaCuenta(MouseEvent event) throws IOException{ 
        FXMLLoader fxmlLoader = new FXMLLoader(VithasLabsApp.class.getResource("nuevoPaciente.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
    }


}
package com.pooespol.proyecto_poo_2p;

import com.pooespol.proyecto_poo_2p.modelo.usuarios.Paciente;
import com.pooespol.proyecto_poo_2p.modelo.usuarios.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class InicioSesionController implements Initializable{
    public static Usuario userLogin;
    public static Paciente pacienteLogin;
    
    @FXML
    private TextField txt_Usuario;
    @FXML
    private PasswordField txt_Password;
    @FXML
    private Button btn_IniciarSesion;
    @FXML
    private Label l_mensaje;
    
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
    
    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
        String usuario = txt_Usuario.getText();
        String password = txt_Password.getText();
        
        if (validarUsuario(usuario, password)){
        VithasLabsApp.setRoot("opciones");
    } else {
            l_mensaje.setText("Usuario o contraseña incorrectos, por favor intentelo de nuevo.");
            l_mensaje.setStyle("-fx-text-fill: Red");
            l_mensaje.setFont(new Font("Arial", 14));
        }
        
    }
    
    private static boolean validarUsuario(String usuario, String password) {
        ArrayList<Usuario> users = VithasLabsApp.usuarios;
        
        for (Usuario u: users) {
            String user = u.getUsuario();
            String contrasenia = u.getContraseña();
            if (user.equals(usuario) && contrasenia.equals(password)) {
                userLogin = u;
                generarPaciente(u, VithasLabsApp.pacientes);
                return true;
            }
        }
        return false;
    }
    private static void generarPaciente(Usuario u, ArrayList<Paciente> listaP) {
        for (Paciente p: listaP) {
            if (p.getUsuario().equals(u.getUsuario())) {
                pacienteLogin = p;
            }
        }
    }
}
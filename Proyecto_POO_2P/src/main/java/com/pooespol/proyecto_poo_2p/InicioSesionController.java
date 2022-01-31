package com.pooespol.proyecto_poo_2p;

import static com.pooespol.proyecto_poo_2p.VithasLabsApp.setIcono;
import com.pooespol.proyecto_poo_2p.modelo.usuarios.Paciente;
import com.pooespol.proyecto_poo_2p.modelo.usuarios.TipoUsuario;
import com.pooespol.proyecto_poo_2p.modelo.usuarios.Usuario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class InicioSesionController implements Initializable {

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
    @FXML
    private ImageView logo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try(FileInputStream fis = new FileInputStream(VithasLabsApp.pathImg + "nuevoLogo.png")) {
            Image img = new Image(fis);
            logo.setImage(img);
        } catch (IOException ex) {
            Logger.getLogger(InicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Abre una nueva ventana para poder crear una nueva cuenta de paciente
     * @param event
     * @throws IOException 
     */
    @FXML
    private void crearNuevaCuenta(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VithasLabsApp.class.getResource("nuevoPaciente.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        root.setStyle("-fx-background-color: linear-gradient(#53caeb, #5239d1)");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Vithas Labs - Crear Nueva Cuenta");
        setIcono(stage);
        stage.show();

    }
    
    /**
     * Permite iniciar sesion en caso de que el usuario y contraseña ingresados
     * sea correcto.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void iniciarSesion(ActionEvent event) throws IOException {
        String usuario = txt_Usuario.getText();
        String password = txt_Password.getText();

        if (validarUsuario(usuario, password)) {
            if (userLogin.getTipoUsuario().equals(TipoUsuario.P))
            VithasLabsApp.setRoot("opciones");
            else 
                VithasLabsApp.setRoot("opcionesLaboratorista");
            
                
        } else {
            l_mensaje.setText("Usuario o contraseña incorrectos, por favor intentelo de nuevo.");
            l_mensaje.setStyle("-fx-text-fill: white");
            l_mensaje.setFont(new Font("Arial", 14));
        }
    }
    
    /**
     * Permite verificar si el usuario y contraseña ingresados son correctos.
     * @param usuario Un string que corresponde al usuario de la persona
     * @param password Cntraseña que corresponde al mimso usuario ingresado
     * @return Retorna true en caso de ser correctos los datos ingresado,
     * caso contraio retorna false
     */
    private static boolean validarUsuario(String usuario, String password) {
        ArrayList<Usuario> users = VithasLabsApp.usuarios;
        for (Usuario u : users) {
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
    
    /**
     * Genera al paciente que al que le corresponda el Usuario que recibio.
     * @param u Un usuario que se encuentra en los registros
     * @param listaP Lista de pacientes que se encuentran en un registro solo de pacientes
     */
    private static void generarPaciente(Usuario u, ArrayList<Paciente> listaP) {
        for (Paciente p : listaP) {
            if (p.getUsuario().equals(u.getUsuario())) {
                pacienteLogin = p;
            }
        }
    }
}

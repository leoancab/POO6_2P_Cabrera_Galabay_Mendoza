/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.pooespol.proyecto_poo_2p;

import static com.pooespol.proyecto_poo_2p.AgendarPruebaController.pruebasCita;
import static com.pooespol.proyecto_poo_2p.InicioSesionController.pacienteLogin;
import com.pooespol.proyecto_poo_2p.modelo.Prueba;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author leonel
 */
public class AgendarPruebaP2Controller implements Initializable {

    @FXML
    private TextField tfDireccion;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private Label lbAdvertencia;
    private double x = -16;
    private double y = -41;
    private String ID;
    private String horaConsulta;
    private String fechaConsulta;
    private String pruebasSolicitadas = "";
    @FXML
    private ComboBox<String> cbHora;

    @FXML
    private Pane rootMapa;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VithasLabsApp.fondo("mapa", ".png", rootMapa);
        ArrayList<Prueba> pruebasCita = AgendarPruebaController.pruebasCita;
        ubicarPin();
        cbHora.getItems().addAll("07:00", "08:00", "09:00", "10:00", "11:00", "12:00");
    }

    public void ubicarPin() {
        rootMapa.setOnMouseClicked((MouseEvent t) -> {
            rootMapa.getChildren().clear();
            ImageView imageview = null;
            try (FileInputStream fis = new FileInputStream(VithasLabsApp.pathImg + "PinMapa.png")) {
                Image imagen = new Image(fis, 30, 40, false, false);
                imageview = new ImageView(imagen);
            } catch (IOException e) {
                System.out.println("No se ha encontrado la imagen.");
            }
            x = t.getX() - 15;
            y = t.getY() - 40;
            if ((-40 < y) && (y < rootMapa.getHeight() - 40)) {
                imageview.setLayoutX(x);
                imageview.setLayoutY(y);
                rootMapa.getChildren().add(imageview);
            }
        });
    }

    public String crearIdSolicitud() {
        String id = "";
        for (int i = 0; i < 6; i++) {
            int numero = (int) (Math.random() * 9);
            id += String.valueOf(numero);
        }
        return id;
    }

    public void escribirContrataciones() {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(VithasLabsApp.pathFile + "contratatacionesPruebas.txt", true);
            bw = new BufferedWriter(fw);
            ID = crearIdSolicitud();
            horaConsulta = cbHora.getSelectionModel().getSelectedItem();
            fechaConsulta = dpFecha.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            bw.write(ID + "," + InicioSesionController.userLogin.getUsuario() + "," + tfDireccion.getText() + "," + fechaConsulta + "," + horaConsulta + "," + (x + 15) + "," + (y + 40) + "," + AgendarPruebaController.totalPagar + "\n");
            bw.close();
            System.out.println("Escribiendo...");
        } catch (IOException e) {
            System.out.println("Error...");
        }
    }

    public void escribirDetalles() {
        BufferedReader br = null;
        FileReader fr = null;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(VithasLabsApp.pathFile + "detallesSolicitudes.txt", true))) {
            bw.write(ID);
            System.out.println(pruebasCita);
            for (Prueba p : pruebasCita) {
                bw.write("," + p.getCodigoPrueba());
                pruebasSolicitadas += p.getNombrePrueba();
                pruebasSolicitadas += "\n";
            }
            bw.write("\n");
        } catch (IOException e) {
            System.out.println("Error...");
        }
    }

    public void mostrarVentanaInfo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VithasLabsApp.class.getResource("informacionFinal.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void finalizar() throws IOException {
        String direccion = tfDireccion.getText();
        LocalDate date = dpFecha.getValue();
        String select = cbHora.getSelectionModel().getSelectedItem();

        System.out.println("Direccion: " + direccion + ", Fecha: " + date + ", Hora: " + select);

        try {
            if (direccion.equals("") || date == null || select == null || rootMapa.getChildren().isEmpty()) {
                throw new CamposIncompletosException("Error");
                //Se verifica que la fecha escogida no sea pasada ni la fecha actual.
            } else if (dpFecha.getValue().isBefore(LocalDate.now()) || dpFecha.getValue().equals(LocalDate.now())) {
                System.out.println("Fecha no posible, escoga otra");
                lbAdvertencia.setText("Fecha no posible, escoga otra");
            } else {
                lbAdvertencia.setText("");
                escribirContrataciones();
                escribirDetalles();
                mostrarVentanaInfo();
                enviarConGMail(pacienteLogin.getEmail());
                System.out.println(pruebasSolicitadas);
            }
        } catch (CamposIncompletosException e) {
            System.out.println("Campos incompletos, no se puede agregar.");
            lbAdvertencia.setText("Campos incompletos, no se puede agregar.");
        }
    }

    private void enviarConGMail(String correoReceptor) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                Properties props = new Properties();
                props.setProperty("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
                props.setProperty("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
                props.setProperty("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
                props.setProperty("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave

                //props.setProperty("mail.smtp.user", remitente);
                //props.setProperty("mail.smtp.clave", "miClaveDeGMail");    //La clave de la cuenta
                Session sesion = Session.getDefaultInstance(props);

                final String correoEnvia = "vithaslabs7@gmail.com";
                final String contrasenia = "VithasLabsApp12345";
                String dest = correoReceptor; //"ycjimbo@espol.edu.ec"
                String asunto = "Correo de prueba";
                String msg = "Fecha: " + fechaConsulta + "\n"
                        + "Hora: " + horaConsulta + "\n"
                        + "PRUEBAS SOLICITADAS:\n"
                        + pruebasSolicitadas + "\n"
                        + "Codigo: " + ID;

                MimeMessage mail = new MimeMessage(sesion);

                try {
                    mail.setFrom(new InternetAddress(correoEnvia));
                    mail.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));
                    mail.setSubject(asunto);
                    mail.setText(msg);

                    Transport transporte = sesion.getTransport("smtp");
                    transporte.connect(correoEnvia, contrasenia);
                    transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
                    transporte.close();

                } catch (AddressException ex) {
                    ex.printStackTrace();
                } catch (MessagingException ex) {
                    ex.printStackTrace();
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }
    
}

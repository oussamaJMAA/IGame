/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import com.itextpdf.text.Rectangle;
import gestion.utilisateur.entities.Game;
import gestion.utilisateur.entities.Tournois;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Date;
import java.sql.ResultSet;
import javafx.geometry.Bounds;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * FXML Controller class
 *
 * @author rouka
 */
public class Game_ItemController implements Initializable {

    @FXML
    private Label nomT;
    @FXML
    private Label recomT;
    
    @FXML
    private Label dateT;
    @FXML
    private Label id_t;
private Parent root;
private Stage stage;
private Scene scene;
 private Timer timer = new Timer();
    private int counter;

    private int seconds, minutes,hours;
    @FXML
    private Button cancel;
    @FXML
    private ImageView image;
    @FXML
    private Button participate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        cancel.setVisible(false);
      id_t.setVisible(false);
      participate.setVisible(false);
      dateT.setVisible(false);
    }    
    public void setData(Game tournois) throws FileNotFoundException{
   
        nomT.setText("Game Name : "+tournois.getGame_name());
    recomT.setText("  Description : "+tournois.getGame_description());

InputStream stream = new FileInputStream("C:\\Users\\oussa\\PhpstormProjects\\gaming_app\\public\\uploads\\photos\\"+tournois.getGame_img());
      Image image4 = new Image(stream);
      image.setImage(image4);
image.setFitHeight(200);
image.setFitWidth(280);
image.setStyle("-fx-alignment:center");
id_t.setText(Integer.toString(tournois.getId()));

    } 
    
 public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_java", "root", "");
            System.out.println("Connection done ! ");
            return conn;
        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
            return null;
        }
    }
    private void executeQuery(String query) {
        Connection conn = getConnection();
        //To change body of generated methods, choose Tools | Templates.
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void add() throws SQLException{
        
}
    

    public void mail(String msg,String subject,String to) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.transport.protocl", "smtp");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("jemaaoussama64@gmail.com", "sousourourou9899@");
            }
        });
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        message.setContent(msg, "text/html");
        Address addressTo = new InternetAddress(to);
        message.setRecipient(Message.RecipientType.TO, addressTo);
        Transport.send(message);
    }

    @FXML
    private void cancel(ActionEvent event) {
    }

    @FXML
    private void participe(ActionEvent event) {
    }

}

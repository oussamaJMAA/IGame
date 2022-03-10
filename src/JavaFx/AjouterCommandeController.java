/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFx;

import entities.Commande;
import java.net.URL;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;
import service.CommandeCRUD;

/**
 * FXML Controller class
 *
 * @author Magui
 */
public class AjouterCommandeController implements Initializable {

    private TextField modePaiment;
    private TextField livraison;
    private DatePicker date;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_vider;
    private ComboBox<String> produitt;
   
    CommandeCRUD service = new CommandeCRUD();
    @FXML
    private CheckBox ck_esp;
    @FXML
    private CheckBox ck_chèq;
    @FXML
    private CheckBox ck_cartebancaire;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
      combo.getItems().add("rapide");
       combo.getItems().add("express");
        combo.getItems().add("gratuit");
    }    

    @FXML
    private void ajouter(ActionEvent event) throws Exception {
        if(combo.getSelectionModel().isEmpty()&& ck_esp.isSelected()==false){
            Alert a = new Alert(Alert.AlertType.ERROR, "champs vide", ButtonType.CLOSE);
            a.show();
        }
        else {
        Commande c = new Commande();

        
        c.setIdClient(12L);
        c.setIdProduit(3L);
        c.setLivraison(combo.getSelectionModel().getSelectedItem());
        if(ck_cartebancaire.isSelected()){
            
            c.setModePaiment(ck_cartebancaire.getText());
        }
        else if(ck_chèq.isSelected()){
               
            c.setModePaiment(ck_chèq.getText());
        }
        else if(ck_esp.isSelected()){
            c.setModePaiment(ck_esp.getText());
        }
        service.ajouterCommande(c);
       affnotif();
        sendMail("mboughanmi538@gmail.com");
    }
    }
    @FXML
    private void reset(ActionEvent event) {
      ck_cartebancaire.setSelected(false);
      ck_chèq.setSelected(false);
      ck_esp.setSelected(false);
      combo.getSelectionModel().clearSelection();
      nom.setText("");
      prenom.setText("");
      
        
    }
     public void affnotif(){
        Notifications notificationBuilder = Notifications.create()
        .title("Alert").text("Commande ajouté avec succé").graphic(null).hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }
      public static void sendMail(String recepient) throws Exception {
          
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "mariem.boughanmi@esprit.tn";
        //Your gmail password
        String password = "211JFT3981";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Bande commande");
            String htmlCode = " <h2>Bonjour Votre Commande est passé avec succé</h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
       //     Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @FXML
    private void clik(MouseEvent event) {
        if(ck_cartebancaire.isSelected()){
            ck_chèq.setDisable(true);
                        ck_esp.setDisable(true);

           
        }
        else if(ck_chèq.isSelected()){
                ck_cartebancaire.setDisable(true);
                        ck_esp.setDisable(true);
          
        }
        else if(ck_esp.isSelected()){
          ck_cartebancaire.setDisable(true);
                        ck_chèq.setDisable(true);
        }
    }

    @FXML
    private void click2(MouseEvent event) {
    }

    @FXML
    private void click3(MouseEvent event) {
    }
}

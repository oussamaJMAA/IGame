/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFx;

import entities.Commande;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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

    @FXML
    private TextField modePaiment;
    @FXML
    private TextField livraison;
    @FXML
    private DatePicker date;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_vider;
    @FXML
    private ComboBox<String> produitt;
   
    CommandeCRUD service = new CommandeCRUD();
    @FXML
    private Button next;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        produitt.getItems().add("1");
        produitt.getItems().add("2");
        produitt.getItems().add("3");
    }    

    @FXML
    private void ajouter(ActionEvent event) throws Exception {
        if(livraison.getText().equals("")&& modePaiment.equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR, "champs vide", ButtonType.CLOSE);
            a.show();
        }
        else {
        Commande c = new Commande();
       java.sql.Date date_converted = java.sql.Date.valueOf(date.getValue());
        c.setDate(date_converted );
        c.setIdClient(12L);
        c.setIdProduit(3L);
        c.setLivraison(livraison.getText());
        c.setModePaiment(modePaiment.getText());
        service.ajouterCommande(c);
       affnotif();
        sendMail("mboughanmi538@gmail.com");
        sendMail("mboughanmi538@gmail.com");
    }
    }
    @FXML
    private void reset(ActionEvent event) {
        livraison.setText("");
        modePaiment.setText("");
        date.setValue(LocalDate.now());
    }
     public void affnotif(){
        Notifications notificationBuilder = Notifications.create()
        .title("Alert").text("Commande ajouté avec succé").graphic(null).hideAfter(Duration.seconds(10))
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
    private void next(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCommande.fxml"));
                Parent root = loader.load();
              ListCommandeController aa = loader.getController();
                next.getScene().setRoot(root);
    }
}

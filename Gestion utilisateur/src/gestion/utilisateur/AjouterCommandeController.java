/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import gestion.utilisateur.entities.Commande;
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
import gestion.utilisateur.servicem.CommandeCRUD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tools.Connexion;
import java.sql.ResultSet;
/**
 * FXML Controller class
 *
 * @author Magui
 */
public class AjouterCommandeController implements Initializable {
Parent root ;
Scene scene ;
Stage stage ;
    ResultSet rs = null;
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
    private TextField prenom;
    @FXML
    private ImageView admin_image;
    @FXML
    private Label test;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnPackages11;
    @FXML
    private Button btnPackages1;
    @FXML
    private Button btnSignout;
  String email="";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             retrievedata a = retrievedata.getInstance("", "",0);
         
         test.setText(a.getUsername());
         
               int jj=a.getImage().lastIndexOf('\\');
admin_image.setImage(new Image(LoggedinController.class.getResourceAsStream(a.getImage().substring(jj+1))));
        
        nom.setText(a.getUsername());
      combo.getItems().add("rapide");
       combo.getItems().add("express");
        combo.getItems().add("gratuit");
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
      public int get_id_panier() throws SQLException {
          retrievedata a = retrievedata.getInstance("","",0);
        String query = "select p.idP from panier p inner join user u on p.idClient = u.id where u.id ="+a.getId()+" ";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
     int max=0;
      if(rs.next()){
          max=rs.getInt(1);
      }
  
        
return  max;
    }
       public int get_id_produit() throws SQLException {
          retrievedata a = retrievedata.getInstance("","",0);
        String query = "select p.id from produit p inner join panier pa on pa.idProduit = p.id inner join user u on pa.idClient = u.id where u.id ="+a.getId()+" ";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
     int max=0;
      if(rs.next()){
          max=rs.getInt(1);
      }
  
        
return  max;
    }
    @FXML
    private void ajouter(ActionEvent event) throws Exception {
        if(combo.getSelectionModel().isEmpty()&& ck_esp.isSelected()==false){
            Alert a = new Alert(Alert.AlertType.ERROR, "champs vide", ButtonType.CLOSE);
            a.show();
        }
        else {
        Commande c = new Commande();

      retrievedata d = retrievedata.getInstance("","",0);
        c.setIdClient(d.getId());
     
        c.setIdProduit(get_id_produit());
      c.setIdPanier(get_id_panier());
      
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
        retrievedata a = retrievedata.getInstance("", "",0);
       
        Connection cnx = Connexion.getInstance().getCnx();
         String sql = "SELECT  email FROM user WHERE username= '" + a.getUsername() + "'";
                    Statement statement = cnx.createStatement();
 rs = statement.executeQuery(sql);

                    if (rs.next()) {
                      email = rs.getString(1);
                   

       
                    }
                             sendMail(email);
        
        
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

    @FXML
    private void on_click_dashboard_button(ActionEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void on_click_users_button(ActionEvent event) {
    }

    
    
    
            @FXML
    private void on_click_tournaments(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("tournois_client.fxml"));  
             stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("tournois clients");
                stage.show(); 
    }

    @FXML
    private void on_click_teams(ActionEvent event) throws IOException {
             root = FXMLLoader.load(getClass().getResource("affiche_equipe_client.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
    }

    private void on_clicki_products(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("viewclient.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
    }
    private void on_click_paniers(ActionEvent event) throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("ListPanier.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
        
        
    }
@FXML
    private void on_click_commande(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ListCommande.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
    }
    

    @FXML
    private void on_click_sign_out(ActionEvent event) {
    }

    @FXML
    private void on_click_products(ActionEvent event) {
    }

    @FXML
    private void on_click_panier(ActionEvent event) {
    }
}

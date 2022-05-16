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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import java.util.Calendar;
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
    
   
    CommandeCRUD service = new CommandeCRUD();
    @FXML
    private CheckBox ck_esp;
    @FXML
    private CheckBox ck_chèq;
    @FXML
    private CheckBox ck_cartebancaire;
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
    private Button btnMenus;
    @FXML
    private Button btnPackages1;
    @FXML
    private Button btnSignout;
  String email="";
    @FXML
    private Label id_prod;
    @FXML
    private TextField nbProd;
    @FXML
    private TextField prixTot;
    @FXML
    private Button tot;
    @FXML
    private Button btnOverview1;
    @FXML
    private Button btnSettings1;
    @FXML
    private Button btn_blog;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             retrievedata a = retrievedata.getInstance("", "",0);
         prixTot.setVisible(false);
         btn_ajouter.setVisible(false);
         test.setText(a.getUsername());
         
      try{
           InputStream stream = new FileInputStream("C:\\Users\\oussa\\PhpstormProjects\\gaming_app\\public\\uploads\\photos\\"+a.getImage());
      Image image4 = new Image(stream);
      admin_image.setImage(image4);   
              
          }catch(Exception ex){
              System.out.println(ex);
          }
        
        nom.setText(a.getUsername());
        /*
      combo.getItems().add("rapide");
       combo.getItems().add("express");
        combo.getItems().add("gratuit");
        */
    }    

      public void set_id_prod(int id){
      id_prod.setText(""+id);
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
    }/*
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
    }*/
     
   public double calcul_prix_tot_prod(int id,int nb) throws SQLException{
  
        String query = "select prix from produit where id = '"+id+"' ";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
     double prix=0;
     double total=0;
      if(rs.next()){
          prix+=rs.getDouble("prix");
      }
  
        total +=prix*nb;
return  total;
   }
   @FXML
    private void voir_total(ActionEvent event) throws SQLException {
          int nbp = Integer.parseInt(nbProd.getText());
       int id_p = Integer.parseInt(id_prod.getText());
        double total = calcul_prix_tot_prod(id_p,nbp);
        prixTot.setText(""+total);
        prixTot.setVisible(true);
        btn_ajouter.setVisible(true);
    }
    @FXML
    private void ajouter(ActionEvent event) throws Exception {
       Commande c = new Commande();
        int nbp = Integer.parseInt(nbProd.getText());
       int id_p = Integer.parseInt(id_prod.getText());
        double total = calcul_prix_tot_prod(id_p,nbp);
      prixTot.setText(""+total);
        // String methode = combo.getSelectionModel().getSelectedItem().toString();
        String methode="";
         if(ck_cartebancaire.isSelected()){
            
            c.setModePaiment(ck_cartebancaire.getText());
            methode = ck_cartebancaire.getText();
        }
        else if(ck_chèq.isSelected()){
               
            c.setModePaiment(ck_chèq.getText());
            methode = ck_chèq.getText();
        }
        else if(ck_esp.isSelected()){
            c.setModePaiment(ck_esp.getText());
            methode = ck_esp.getText();
        }
         if(methode=="" || nbp==0 || total ==0||nbProd.getText()==""){
             Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez remplir tout les champs !");
        alert.show();
         }else{
          java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
      retrievedata d = retrievedata.getInstance("","",0);
        c.setIdClient(d.getId());
        c.setNbproduit(nbp);
        c.setPrix_tot(total);
        c.setEtat("en attente");
        c.setModePaiment(methode);
        c.setDate(date);
       service.ajouterCommande(c);
       service.Confirmer(id_p);
       root = FXMLLoader.load(getClass().getResource("ListCommande.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
       
         }
          
 }
  
    
    
    @FXML
    private void reset(ActionEvent event) {
      ck_cartebancaire.setSelected(false);
      ck_chèq.setSelected(false);
      ck_esp.setSelected(false);
      //combo.getSelectionModel().clearSelection();
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
        retrievedata a = new retrievedata("","",0);
        try {
           
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("roukaia.khelifi@esprit.tn"));
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
    private void on_click_dashboard_button(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Loggedin.fxml"));  
             stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("tournois clients");
                stage.show(); 
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

    @FXML
    private void on_clicki_products(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("Produit_Client.fxml"));  
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
    private void on_click_games(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Game_Client.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
    }

    @FXML
    private void on_click_blogs(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("Publication_Client.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show();
    }

    
}
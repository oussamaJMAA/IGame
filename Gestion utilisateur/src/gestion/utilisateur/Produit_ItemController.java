/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import gestion.utilisateur.entities.Produit;
import gestion.utilisateur.entities.Tournois;
import java.io.FileInputStream;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tools.Connexion;
/**
 * FXML Controller class
 *
 * @author rouka
 */
public class Produit_ItemController implements Initializable {

    @FXML
    private Label nomT;
    @FXML
    private Label recomT;
    
    @FXML
    private Label dateT;
    @FXML
    private Button participate;
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
     PreparedStatement insert = null;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        cancel.setVisible(false);
      id_t.setVisible(false);
      dateT.setVisible(false);
    }    
    public void setData(Produit tournois){
  // retrievedata a = new retrievedata("","",0);
             /*  int jj=tournois.getImage().lastIndexOf('\\');
image.setImage(new Image(LoggedinController.class.getResourceAsStream(tournois.getImage().substring(jj+1))));*/
             try{
             InputStream stream = new FileInputStream("C:\\Users\\oussa\\PhpstormProjects\\gaming_app\\public\\uploads\\photos\\"+tournois.getImage());
      Image image4 = new Image(stream);
      image.setImage(image4);
             }catch(Exception ex){
                 System.out.println(ex);
             }
        nomT.setText("PRIX : "+tournois.getPrix()+" DT");
    recomT.setText(tournois.getNom());
id_t.setText(Integer.toString(tournois.getId()));
//dateT.setText(""+a.getId());
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

    @FXML
    private void participe(ActionEvent event) throws IOException {
          int idP = Integer.parseInt(id_t.getText());
FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCommande.fxml"));
root = loader.load();
AjouterCommandeController ac = loader.getController();
ac.set_id_prod(idP);
// root = FXMLLoader.load(getClass().getResource("AjouterCommande.fxml")); //khali hedhi pour le moment
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
    }

    @FXML
    private void cancel(ActionEvent event) {
    }
    
  

}

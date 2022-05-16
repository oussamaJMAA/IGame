/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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

/**
 * FXML Controller class
 *
 * @author rouka
 */
public class Equipe_clientController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private Button btn_conf;
    @FXML
    private Label label_user_name;
    @FXML
    private Label label_user_name2;
    @FXML
    private Label label_user_name3;
    @FXML
    private Label label_user_name4;
    @FXML
    private Label label_user_name5;
    @FXML
    private TextField username1;
    @FXML
    private TextField username2;
    @FXML
    private TextField username3;
    @FXML
    private TextField username4;
    @FXML
    private TextField username5;
    @FXML
    private ChoiceBox<Integer> choix;

    @FXML
    private Button cancel;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private Button back;
    @FXML
    private Label warning;
 boolean u2 = false;
   boolean u3 = false;
   boolean u4 = false;
   boolean u5 = false;
    @FXML
    private ImageView admin_image;
    @FXML
    private Label test;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnSettings1;
    @FXML
    private Button btnPackages1;
    @FXML
    private Button btn_blog;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnOverview1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        warning.setVisible(false);
        label_user_name.setVisible(false);
        label_user_name2.setVisible(false);
        label_user_name3.setVisible(false);
        label_user_name4.setVisible(false);
        label_user_name5.setVisible(false);
        username1.setVisible(false);
        username2.setVisible(false);
        username3.setVisible(false);
        username4.setVisible(false);
        username5.setVisible(false);
        Integer[] c = {1, 2, 3, 4, 5};
 retrievedata a= retrievedata.getInstance("testtt","",0);
 username1.setText(a.getUsername());
 username1.setDisable(true);
        choix.getItems().addAll(c);
        choix.setOnAction(this::affiche_nb_choix);
    }

    public void affiche_nb_choix(ActionEvent event) {
        int a = choix.getValue();
        if (a == 1) {
            label_user_name.setVisible(true);
            username1.setVisible(true);
          
            label_user_name2.setVisible(false);
            username2.setVisible(false);
            label_user_name3.setVisible(false);
            username3.setVisible(false);
            label_user_name4.setVisible(false);
            username4.setVisible(false);
            label_user_name5.setVisible(false);
            username5.setVisible(false);
        } else if (a == 2) {
            
            label_user_name.setVisible(true);
            username1.setVisible(true);
            label_user_name2.setVisible(true);
            username2.setVisible(true);
             label_user_name3.setVisible(false);
            username3.setVisible(false);
            label_user_name4.setVisible(false);
            username4.setVisible(false);
            label_user_name5.setVisible(false);
            username5.setVisible(false);
            u2=true;
            u3=true;
            u4=true;
            u5=true;
        } else if (a == 3) {
            label_user_name.setVisible(true);
            username1.setVisible(true);
            label_user_name2.setVisible(true);
            username2.setVisible(true);
            label_user_name3.setVisible(true);
            username3.setVisible(true);
           
            label_user_name4.setVisible(false);
            username4.setVisible(false);
            label_user_name5.setVisible(false);
            username5.setVisible(false);
              u3=true;
            u4=true;
            u5=true;
        } else if (a == 4) {
            label_user_name.setVisible(true);
            username1.setVisible(true);
            label_user_name2.setVisible(true);
            username2.setVisible(true);
            label_user_name3.setVisible(true);
            username3.setVisible(true);
            label_user_name4.setVisible(true);
            username4.setVisible(true);
          
            label_user_name5.setVisible(false);
            username5.setVisible(false);
             
            u4=true;
            u5=true;
        } else if (a == 5) {
            label_user_name.setVisible(true);
            username1.setVisible(true);
            label_user_name2.setVisible(true);
            username2.setVisible(true);
            label_user_name3.setVisible(true);
            username3.setVisible(true);
            label_user_name4.setVisible(true);
            username4.setVisible(true);
            label_user_name5.setVisible(true);
            username5.setVisible(true);
        
            u5=true;
        }
    }

    public void mail(String to, String eq) throws MessagingException {
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
        message.setSubject("YOU ARE ADDED TO A TEAM!->>" + eq);
        message.setContent("<h1>You are now a membre of  "+eq+" For further information, Please visit our WebSite   <a href="+"http://127.0.0.1:8000/home"+">IGAME.com<a></h1>", "text/html");
        Address addressTo = new InternetAddress(to);
        message.setRecipient(Message.RecipientType.TO, addressTo);
        Transport.send(message);
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
/*********************************************************************************************************************************/
    public void insertTeam() throws SQLException, MessagingException {
        String query = "insert into equipe (nom_equipe,membres) values('" + nom.getText() + "'," + choix.getValue() + ")";
        executeQuery(query);

        if (choix.getValue() == 1) {
            if(not_found(username1.getText())){
            
            mail(get_email(username1.getText()), nom.getText());
             add_equipe_toUser1();
                 
            }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText(" A membre not found !");
                alert.showAndWait();
            }
        } else if (choix.getValue() == 2) {
            if(not_found(username1.getText()) ||not_found(username2.getText()) ){
            
            mail(get_email(username1.getText()), nom.getText());
            mail(get_email(username2.getText()), nom.getText());
             add_equipe_toUser1();
                    add_equipe_toUser2();
                  
        }else {
             Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("A membre not found");
                alert.showAndWait();
            }
        }
            else if (choix.getValue() == 3) {
                
                if(not_found(username1.getText()) || not_found(username2.getText()) ||not_found(username3.getText())){
            mail(get_email(username1.getText()), nom.getText());
            mail(get_email(username2.getText()), nom.getText());
            mail(get_email(username3.getText()), nom.getText());
             add_equipe_toUser1();
                    add_equipe_toUser2();
                    add_equipe_toUser3();
                  
                }else{
              
             Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("A membre not found");
                alert.showAndWait();
 }
        } else if (choix.getValue() == 4) {
            
            if(not_found(username1.getText())|| not_found(username2.getText()) ||not_found(username3.getText())||not_found(username4.getText())){
            mail(get_email(username1.getText()), nom.getText());
            mail(get_email(username2.getText()), nom.getText());
            mail(get_email(username3.getText()), nom.getText());
            mail(get_email(username4.getText()), nom.getText());
             add_equipe_toUser1();
                    add_equipe_toUser2();
                    add_equipe_toUser3();
                    add_equipe_toUser4();
                   
        }else{
              
             Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("A membre not found");
                alert.showAndWait();
            }
        }else if (choix.getValue() == 5) {
            if(not_found(username1.getText()) ||not_found(username2.getText())||!not_found(username3.getText())||not_found(username4.getText())||not_found(username5.getText())){
            mail(get_email(username1.getText()), nom.getText());
            mail(get_email(username2.getText()), nom.getText());
            mail(get_email(username3.getText()), nom.getText());
            mail(get_email(username4.getText()), nom.getText());
            mail(get_email(username5.getText()), nom.getText());
             add_equipe_toUser1();
                    add_equipe_toUser2();
                    add_equipe_toUser3();
                    add_equipe_toUser4();
                    add_equipe_toUser5();
            }else {
              
             Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText("A membre not found");
                alert.showAndWait();
            }
        }

    }

    public void add_equipe_toUser1() {
        String query = "update user set equipe = (select id from equipe where nom_equipe = '" + nom.getText() + "') where username = '" + username1.getText() + "'";
        executeQuery(query);
    }

    public void add_equipe_toUser2() {
        String query = "update user set equipe = (select id from equipe where nom_equipe = '" + nom.getText() + "') where username = '" + username2.getText() + "'";
        executeQuery(query);
    }

    public void add_equipe_toUser3() {
        String query = "update user set equipe = (select id from equipe where nom_equipe = '" + nom.getText() + "') where username = '" + username3.getText() + "'";
        executeQuery(query);
    }

    public void add_equipe_toUser4() {
        String query = "update user set equipe = (select id from equipe where nom_equipe = '" + nom.getText() + "') where username = '" + username4.getText() + "'";
        executeQuery(query);
    }

    public void add_equipe_toUser5() {
        String query = "update user set equipe = (select id from equipe where nom_equipe = '" + nom.getText() + "') where username = '" + username5.getText() + "'";
        executeQuery(query);
    }

    public String get_email(String username) throws SQLException {
        String query = "select email from user where username='" + username + "'";
        Connection conn = getConnection();
      Statement st;
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        String max = "";
        if (rs.next()) {
            max = rs.getString(1);
        }

        return max;

    }

    public boolean verif(String t) {

        if (t.isEmpty()) {

            return false;
        }
        return true;
    }
    
     public int already_in_team(String u ) throws SQLException {
       retrievedata a= retrievedata.getInstance("testtt","",0);
      // int idT = Integer.parseInt(id_t.getText());
        String query = "select equipe from user where username = '"+u+"'";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
    int max = 0;
      if(rs.next()){
          max=rs.getInt(1);
      }
  
        
return  max;
    }
     
     public boolean not_found(String u ) throws SQLException {
       retrievedata a= retrievedata.getInstance("testtt","",0);
      // int idT = Integer.parseInt(id_t.getText());
        String query = "select email from user where username = '"+u+"'";
Connection conn = getConnection();
boolean test;
      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
    String max ="";
      if(rs.next()){
          max=rs.getString(1);
          test = true;
          
      }
  
        test = false;
return  test;
    }
   
     
    public String verif_finale() throws SQLException{
      String name = "";
   if(already_in_team(username2.getText())!=0){
   name = username2.getText();
   }else if(already_in_team(username3.getText())!=0){
   name = username3.getText();
   }else if(already_in_team(username4.getText())!=0){
   name = username4.getText();
   }
   else if(already_in_team(username5.getText())!=0){
  name = username5.getText();
   }
   
    return name;
    }
    
  

@FXML
    private void confirmer(ActionEvent event) throws IOException, SQLException, MessagingException {
        if (event.getSource() == btn_conf) {
            if (verif(nom.getText()) == false) {
                warning.setText("Vous Devez Verifier !");
                warning.setVisible(true);
            } 
            else if(verif_finale()!=""){
            
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText(verif_finale()+"   is already in a team !");
                alert.showAndWait();
            }
            else {
                warning.setVisible(false);
                insertTeam();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("You have just created a team! , a confirmation email will be sent you ");
                alert.showAndWait();
 int a = choix.getValue();
                if (a == 1) {
                    add_equipe_toUser1();
                } else if (a == 2) {
                    add_equipe_toUser1();
                    add_equipe_toUser2();
                } else if (a == 3) {
                    add_equipe_toUser1();
                    add_equipe_toUser2();
                    add_equipe_toUser3();
                } else if (a == 4) {
                    add_equipe_toUser1();
                    add_equipe_toUser2();
                    add_equipe_toUser3();
                    add_equipe_toUser4();
                } else if (a == 5) {
                    add_equipe_toUser1();
                    add_equipe_toUser2();
                    add_equipe_toUser3();
                    add_equipe_toUser4();
                    add_equipe_toUser5();
                }
                System.out.println("Ajout effectué");
            
            }
        if (event.getSource() == cancel) {
            label_user_name.setVisible(false);
            label_user_name2.setVisible(false);
            label_user_name3.setVisible(false);
            label_user_name4.setVisible(false);
            label_user_name5.setVisible(false);
            username1.setVisible(false);
            username2.setVisible(false);
            username3.setVisible(false);
            username4.setVisible(false);
            username5.setVisible(false);
            nom.setText("");

        }
        if (event.getSource() == back) {
            root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    }

    @FXML
    private void on_click_dashboard_button(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("Loggedin.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show();
    }

    @FXML
    private void on_click_tournaments(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("tournois_client.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show();
    }

    @FXML
    private void on_click_teams(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("equipe_Client.fxml"));  
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
    private void on_click_blogs(ActionEvent event) throws IOException {
          root = FXMLLoader.load(getClass().getResource("Publication_Client.fxml"));  
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
}

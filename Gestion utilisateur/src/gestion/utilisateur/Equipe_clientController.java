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
    @FXML
    private Button back;
    @FXML
    private Label warning;

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
        } else if (a == 2) {
            label_user_name.setVisible(true);
            username1.setVisible(true);
            label_user_name2.setVisible(true);
            username2.setVisible(true);
        } else if (a == 3) {
            label_user_name.setVisible(true);
            username1.setVisible(true);
            label_user_name2.setVisible(true);
            username2.setVisible(true);
            label_user_name3.setVisible(true);
            username3.setVisible(true);
        } else if (a == 4) {
            label_user_name.setVisible(true);
            username1.setVisible(true);
            label_user_name2.setVisible(true);
            username2.setVisible(true);
            label_user_name3.setVisible(true);
            username3.setVisible(true);
            label_user_name4.setVisible(true);
            username4.setVisible(true);
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
        message.setContent("<h1>You are now a membre of </h1>"+eq+"<h1>For further, Please visit our WebSite..</h1>", "text/html");
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

    public void insertTeam() throws SQLException, MessagingException {
        String query = "insert into equipe (nom_equipe,membres) values('" + nom.getText() + "'," + choix.getValue() + ")";
        executeQuery(query);

        if (choix.getValue() == 1) {
            mail(get_email(username1.getText()), nom.getText());
        } else if (choix.getValue() == 2) {
            mail(get_email(username1.getText()), nom.getText());
            mail(get_email(username2.getText()), nom.getText());
        } else if (choix.getValue() == 3) {
            mail(get_email(username1.getText()), nom.getText());
            mail(get_email(username2.getText()), nom.getText());
            mail(get_email(username3.getText()), nom.getText());

        } else if (choix.getValue() == 4) {
            mail(get_email(username1.getText()), nom.getText());
            mail(get_email(username2.getText()), nom.getText());
            mail(get_email(username3.getText()), nom.getText());
            mail(get_email(username4.getText()), nom.getText());
        } else if (choix.getValue() == 5) {
            mail(get_email(username1.getText()), nom.getText());
            mail(get_email(username2.getText()), nom.getText());
            mail(get_email(username3.getText()), nom.getText());
            mail(get_email(username4.getText()), nom.getText());
            mail(get_email(username5.getText()), nom.getText());

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
    
    
public String member_alrady_exists(int id,String mem) throws SQLException{
 String query = "select username from user where user.equipe = "+id+" and user.username = '"+mem+"'";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
     String exist="";
      if(rs.next()){
          exist=rs.getString(1);
      }
  return  exist;
}
  

@FXML
    private void confirmer(ActionEvent event) throws IOException, SQLException, MessagingException {
        if (event.getSource() == btn_conf) {
            if (verif(nom.getText()) == false) {
                warning.setText("Vous Devez Verifier !");
                warning.setVisible(true);
            } else {
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

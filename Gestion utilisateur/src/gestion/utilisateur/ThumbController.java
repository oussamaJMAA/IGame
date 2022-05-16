/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import gestion.utilisateur.entities.Tournois;
import java.io.IOException;
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
import tools.Connexion;
/**
 * FXML Controller class
 *
 * @author rouka
 */
public class ThumbController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        cancel.setVisible(false);
      id_t.setVisible(false);
    }    
    public void setData(Tournois tournois){
   
        nomT.setText(tournois.getNom_tournois());
    recomT.setText("  Prize : "+tournois.getRecompense());
id_t.setText(Integer.toString(tournois.getId()));

   int diff_m = tournois.getDate().getMonth()-(java.time.LocalDate.now().getMonthValue()-1);
   int diff_d =tournois.getDate().getDate()-(java.time.LocalDate.now().getDayOfMonth());
int diff_h = tournois.getTime().getHours() - java.time.LocalTime.now().getHour();
if(diff_m==0 && diff_d ==0 &&diff_h==0){
  
  timer = new Timer();
 
     hours = java.time.LocalTime.now().getHour();
     int minute = java.time.LocalTime.now().getMinute();

  counter=60*(60-minute);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                   
                    counter--;
                       seconds = counter % 60;
                    minutes = counter /60;
                 if (minutes < 10) {
                        dateT.setText("0" + minutes + ":" + seconds);
                    
                    }else if(seconds < 10){
                            dateT.setText(minutes + ":0" + seconds);
                            
                    }                
                 else {
                        dateT.setText(minutes + ":" + seconds);
                    }

                });
            }
        },0,1000);
    
}else if(diff_m==0 && diff_d ==0 &&diff_h!=0){
dateT.setText("IN "+diff_h+" hours");
}else{
dateT.setText(tournois.getDate().toString());
}
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
    private void participe(ActionEvent event) throws SQLException {
        
        
        if(verifParticipation()!=0){
         Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("You are Participated!!!");
            alert.showAndWait();
        }
        else if(get_id_equipe()==0&&get_nb_participants()==4){
          Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("You are not in a team to participate PLUS the Tournament is full :) ");
            alert.showAndWait();
        }
        else if(get_id_equipe()==0&&get_nb_participants()<4){
    Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("You are not in a team to participate!!!");
            alert.showAndWait();
  }
  
  
  
  else if(get_nb_participants()==4&&get_id_equipe()!=0){
    Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Tournament Full !!!");
            alert.showAndWait();
  
  }

  else{
        
       add();
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("You have Participated in a Tournament an verification email will be sent to you soon !");
            alert.showAndWait();
        participate.setVisible(false);
        cancel.setVisible(true);
        try {
            mail("YOU HAVE PARTICIPATED IN A TOURNAMENT","PARTICIPATION CONFIRMED",get_email_by_id());
        } catch (MessagingException ex) {
            Logger.getLogger(ThumbController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}
     public int get_nb_participants() throws SQLException {
       retrievedata a= retrievedata.getInstance("testtt","",0);
       int idT = Integer.parseInt(id_t.getText());
        String query = "select count(*) from participation where id_tournois = '"+idT+"'";
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
     public int get_id_equipe() throws SQLException {
       retrievedata a= retrievedata.getInstance("testtt","",0);
        String query = "select e.id from equipe e inner join user u on u.equipe = e.id where u.username='"+a.getUsername()+"'";
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
  public int verifParticipation() throws SQLException {
       retrievedata a= retrievedata.getInstance("testtt","",0);
       int idT = Integer.parseInt(id_t.getText());
        String query = "select id from participation where id_tournois = '"+idT+"' and id_equipe = '"+get_id_equipe()+"'";
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
    public String get_name_equipe(int id) throws SQLException {
      // retrievedata a= retrievedata.getInstance("testtt","",0);
        String query = "select nom_equipe from equipe where id = "+id+"";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
    String max = "";
      if(rs.next()){
          max=rs.getString("nom_equipe");
      }
  
        
return  max;
    }
    
    public String get_nom_tournois() throws SQLException {
       retrievedata a= retrievedata.getInstance("testtt","",0);
       int id = Integer.parseInt(id_t.getText());
        String query = "select nom_tournois from tournois where id = '"+id+"'";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
   String max = "";
      if(rs.next()){
          max=rs.getString("nom_tournois");
      }
  
        
return  max;
    }

    
    public String get_email_by_id() throws SQLException {
       retrievedata a= retrievedata.getInstance("testtt","",0);
       int id = Integer.parseInt(id_t.getText());
        String query = "select email from user where id ='"+a.getId()+"'";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
   String max = "";
      if(rs.next()){
          max=rs.getString("email");
      }
  
        
return  max;
    }

    
    

    public void add() throws SQLException{
          String nom_equipe = get_name_equipe(get_id_equipe());
           String nom_tournois = get_nom_tournois();
  
   Connection cnx = null;
    cnx = Connexion.getInstance().getCnx();
   insert = cnx.prepareStatement("insert into participation(id_tournois,id_equipe,tournois_nom,equipe_nom)values(?,?,?,?)");
   insert.setInt(2, get_id_equipe());
   insert.setInt(1, Integer.parseInt(id_t.getText()));
   insert.setString(4, nom_equipe);
   insert.setString(3,nom_tournois);
      insert.executeUpdate();
       System.out.println(get_id_equipe());
    }
    
public void remove() throws SQLException{
String query = "delete from participation where id_equipe = '"+get_id_equipe()+"' and id_tournois = "+id_t.getText()+"";
        executeQuery(query);
}
    
    @FXML
    private void cancel(ActionEvent event) throws SQLException {
   
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("You're about to cancel your participation are you sure you want to cancel ?");
            if(alert.showAndWait().get() == ButtonType.OK){
            
            remove();
            
        participate.setVisible(true);
        cancel.setVisible(false);
          try {
              retrievedata a = retrievedata.getInstance("","",0);
              
              mail("YOU HAVE CANCELED YOUR PARTICIPATION","CANCELED PARTICIPATION",get_email_by_id());
          } catch (MessagingException ex) {
              Logger.getLogger(ThumbController.class.getName()).log(Level.SEVERE, null, ex);
          }
            }
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
}

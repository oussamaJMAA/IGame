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
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rouka
 */
public class Edit_memberController implements Initializable {

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
    private Button add;
    @FXML
    private TextField equipe;
   private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Button cancel;
       boolean u2 = false;
   boolean u3 = false;
   boolean u4 = false;
   boolean u5 = false;
    @FXML
    private Label not_in;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      not_in.setVisible(false);
        Affiche_equipe_clientController test = new Affiche_equipe_clientController();
      equipe.setText(test.getEquipesList().get(0).getNom_equipe());
   equipe.setVisible(false);

        try {
            if(test().size()==1){
                username1.setVisible(true);
                   username1.setDisable(true);
               
                username1.setText(test().get(0));
                username1.setDisable(true);
                u2 = true;
                u3 = true;
                u4 = true;
                u5 = true;
            }else if(test().size()==2){
                username1.setVisible(true);
                username2.setVisible(true);
                
            username1.setText(test().get(0));
            username2.setText(test().get(1));
            
             username1.setDisable(true);
            username2.setDisable(true);
            
           u3 = true;
                u4 = true;
                u5 = true;
            }else if(test().size()==3){
              username1.setVisible(true);
                username2.setVisible(true);
                username3.setVisible(true);
             username1.setDisable(true);
            username2.setDisable(true);
            username3.setDisable(true);
            username1.setText(test().get(0));
            username2.setText(test().get(1));
            username3.setText(test().get(2));
           
                u4 = true;
                u5 = true;
            }else if(test().size()==4){
             username1.setVisible(true);
                username2.setVisible(true);
                username3.setVisible(true);
                username4.setVisible(true);
                   username1.setDisable(true);
            username2.setDisable(true);
            username3.setDisable(true);
            username4.setDisable(true);
            username1.setText(test().get(0));
            username2.setText(test().get(1));
            username3.setText(test().get(2));
             username4.setText(test().get(3));
             u5 = true;
            }else{
             username1.setVisible(true);
                username2.setVisible(true);
                username3.setVisible(true);
                username4.setVisible(true);
                username5.setVisible(true);
                username1.setDisable(true);
            username2.setDisable(true);
            username3.setDisable(true);
            username4.setDisable(true);
             username5.setDisable(true);
            username1.setText(test().get(0));
            username2.setText(test().get(1));
            username3.setText(test().get(2));
             username4.setText(test().get(3));
             username5.setText(test().get(4));
            }
            
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
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
 public List<String> test() throws SQLException {
     retrievedata b= retrievedata.getInstance("testtt","",0);
        String query = "select u.username from user u inner join equipe e on e.id = u.equipe where e.id=(select equipe from user where username = '"+b.getUsername()+"')";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
      String min ="";
      List a = new ArrayList();
      while(rs.next()){
          a.add(rs.getString(1));
      }
   
        
return a;
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
    
    
  public void add_equipe_toUser(String n) {
        String query = "update user set equipe = (select id from equipe where nom_equipe = '" + equipe.getText() + "') where username = '" + n + "'";
        executeQuery(query);
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
   
     
    public String verif_finale() throws SQLException{
      String name = "";
      
      if(!u2){
   if(already_in_team(username2.getText())!=0){
   name = username2.getText();
   }
    if(already_in_team(username3.getText())!=0){
   name = username3.getText();
   }
     if(already_in_team(username4.getText())!=0){
   name = username4.getText();
   }
      if(already_in_team(username5.getText())!=0){
   name = username5.getText();
   }
      }
      else if(!u3){
           if(already_in_team(username3.getText())!=0){
   name = username3.getText();
   } if(already_in_team(username4.getText())!=0){
   name = username4.getText();
   }
      if(already_in_team(username5.getText())!=0){
   name = username5.getText();
   }
           
      }
      else if(!u4){
      
     if(already_in_team(username4.getText())!=0){
   name = username4.getText();
   }
      if(already_in_team(username5.getText())!=0){
   name = username5.getText();
   }
      }
      
      else if(!u5){
    if(already_in_team(username5.getText())!=0){
  name = username5.getText();
   }
      }
   
    return name;
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
  
  
    @FXML
    private void ajout(ActionEvent event) throws SQLException {
      if(verif_finale()!=""){
       
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText(null);
                alert.setContentText(verif_finale()+"   is already in a team !");
                alert.showAndWait();
       }else
        if(!username1.getText().isEmpty()&&not_found(username1.getText())&&username2.getText().isEmpty()&&username3.getText().isEmpty()&&username4.getText().isEmpty()&&username5.getText().isEmpty()){
       add_equipe_toUser(username1.getText());
       }else{
       not_in.setText("A MEMBRE NOT FOUND");
                 Stage stage = (Stage) cancel.getScene().getWindow();
    // do what you have to do
   
    stage.close();
        }
  
        if(!username2.getText().isEmpty()&&not_found(username2.getText())&&username3.getText().isEmpty()&&username4.getText().isEmpty()&&username5.getText().isEmpty()){
       add_equipe_toUser(username2.getText());
       }else {
        not_in.setText("A MEMBRE NOT FOUND");
                 Stage stage = (Stage) cancel.getScene().getWindow();
    // do what you have to do
   
    stage.close();
        }
        
        if(!username3.getText().isEmpty()&&not_found(username3.getText())&&username4.getText().isEmpty()&&username5.getText().isEmpty()){
       add_equipe_toUser(username3.getText());
       }else{
        not_in.setText("A MEMBRE NOT FOUND");
                 Stage stage = (Stage) cancel.getScene().getWindow();
    // do what you have to do
   
    stage.close();
        }
        
        if(!username4.getText().isEmpty()&&not_found(username4.getText())&&username5.getText().isEmpty()){
       add_equipe_toUser(username4.getText());
       }else{
       not_in.setText("A MEMBRE NOT FOUND");
                 Stage stage = (Stage) cancel.getScene().getWindow();
    // do what you have to do
   
    stage.close();
        }
        
        if(!username5.getText().isEmpty()&&not_found(username5.getText())){
       add_equipe_toUser(username5.getText());
       }else{
      not_in.setText("A MEMBRE NOT FOUND");
                 Stage stage = (Stage) cancel.getScene().getWindow();
    // do what you have to do
   
    stage.close();
        }
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
       Stage stage = (Stage) cancel.getScene().getWindow();
    // do what you have to do
   
    stage.close();
    }
    
    
    
}

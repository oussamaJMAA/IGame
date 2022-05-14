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
import javafx.scene.control.Button;
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
        Affiche_equipe_clientController test = new Affiche_equipe_clientController();
      equipe.setText(test.getEquipesList().get(0).getNom_equipe());
   equipe.setVisible(false);
        try {
            if(test().size()==1){
                username1.setVisible(true);
                   username1.setDisable(true);
               
                username1.setText(test().get(0));
                username1.setDisable(true);
            }else if(test().size()==2){
                username1.setVisible(true);
                username2.setVisible(true);
                
            username1.setText(test().get(0));
            username2.setText(test().get(1));
             username1.setDisable(true);
            username2.setDisable(true);
            
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
    @FXML
    private void ajout(ActionEvent event) {
       if(!username1.getText().isEmpty()){
       add_equipe_toUser(username1.getText());
        try {
                root = FXMLLoader.load(getClass().getResource("edit_team_client.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Affiche_equipe_clientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
       
       }if(!username2.getText().isEmpty()){
       add_equipe_toUser(username2.getText());
       }if(!username3.getText().isEmpty()){
       add_equipe_toUser(username3.getText());
       }if(!username4.getText().isEmpty()){
       add_equipe_toUser(username4.getText());
       }if(!username5.getText().isEmpty()){
       add_equipe_toUser(username5.getText());
       }
    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
      root = FXMLLoader.load(getClass().getResource("affiche_equipe_client.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
    
    
}

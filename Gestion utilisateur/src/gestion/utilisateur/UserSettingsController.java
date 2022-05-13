/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import com.mysql.cj.protocol.Resultset;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tools.Connexion;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class UserSettingsController implements Initializable {
private Stage stage;
    private Scene scene;
    private Parent root;
     ResultSet rs = null;
    PreparedStatement p;
    @FXML
    private PasswordField passwordtf;
    @FXML
    private PasswordField confirmpasswordtf;
    @FXML
    private TextField usernametf;
    @FXML
    private TextField emailtf;
Connection cnx=null;
    @FXML
    private Label userlabel;
    @FXML
    private TextField lastnametf;
    @FXML
    private TextField firstnametf;
    @FXML
    private TextField adresstf;
    @FXML
    private TextField phonetf;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    @FXML
    private void GoToHomePage(MouseEvent event) throws IOException {
       root = FXMLLoader.load(getClass().getResource("Loggedin.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }
     public static boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    public static boolean isMobileValid(String s) {
        Pattern p = Pattern.compile("^\\d{8}$");
        Matcher m = p.matcher(s);
        return (m.matches());
    }
    public static boolean isusernameValid(String s){
 // accepts 5 to 15 characters with any lower case character, digit or special symbol “_-” only.
              Pattern p = Pattern.compile("^[A-Za-z]\\w{5,29}$");
                Matcher m = p.matcher(s);
        return (m.matches());
    }
      public static boolean isStringOnlyAlphabet(String str)
    {
 
        return ((str != null) && (!str.equals(""))
                && (str.matches("^[a-zA-Z]*$"))&&str.length()<=12);
    }
          public static boolean isadressValid(String str)
    {
 
        return ((str != null) && (!str.equals(""))
                && (str.matches("^\\s*[\\da-zA-Z][\\da-zA-Z\\s]*$"))&&str.length()<=12);
    }
                 public static boolean ispasswordValid(String str)
    {
 
        return ((str != null) && (!str.equals(""))
                && (str.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{5,20}$")));
    }

    
    public void change_fn() throws SQLException{
     retrievedata b = retrievedata.getInstance("", "",0);
        String a =  b.getUsername();
     cnx = Connexion.getInstance().getCnx();
     if(!firstnametf.getText().isEmpty()&& isStringOnlyAlphabet(firstnametf.getText())){
       p = cnx.prepareStatement("update user set firstname = ? where username = ? or email=? ");
            p.setString(1,firstnametf.getText());
           p.setString(2,a);
           p.setString(3,a);
            p.execute();  
     }
       else {
             firstnametf.setStyle("-fx-background-color:red");
     }
}
      public void change_ln() throws SQLException{
     retrievedata b = retrievedata.getInstance("", "",0);
        String a =  b.getUsername();
     cnx = Connexion.getInstance().getCnx();
     if(!lastnametf.getText().isEmpty()&& isStringOnlyAlphabet(lastnametf.getText())){
       p = cnx.prepareStatement("update user set lastname = ? where username = ? or email=? ");
            p.setString(1,lastnametf.getText());
           p.setString(2,a);
           p.setString(3,a);
            p.execute();  
     }
     else {
             lastnametf.setStyle("-fx-background-color:red");
     }
}
     public void change_ad() throws SQLException{
     retrievedata b = retrievedata.getInstance("", "",0);
     String a =  b.getUsername();
     cnx = Connexion.getInstance().getCnx();
     if(!adresstf.getText().isEmpty()&&isadressValid(adresstf.getText())){
       p = cnx.prepareStatement("update user set address = ? where username = ? or email=? ");
            p.setString(1,adresstf.getText());
           p.setString(2,a);
           p.setString(3,a);
            p.execute();  
     }
     else {
             adresstf.setStyle("-fx-background-color:red");
     }
}
          public void change_ph() throws SQLException{
     retrievedata b = retrievedata.getInstance("", "",0);
        String a =  b.getUsername();
     cnx = Connexion.getInstance().getCnx();
     if(!phonetf.getText().isEmpty()&&isMobileValid(phonetf.getText())){
       p = cnx.prepareStatement("update user set phone = ? where username = ? or email=? ");
            p.setInt(1, Integer.parseInt(phonetf.getText()));
           p.setString(2,a);
           p.setString(3,a);
            p.execute();  
     }
         else {
             phonetf.setStyle("-fx-background-color:red");
     }
   
}
public void change_pwd() throws SQLException{
    
         retrievedata b = retrievedata.getInstance("", "",0);
        String a =  b.getUsername();
     cnx = Connexion.getInstance().getCnx();
       String password = passwordtf.getText();
       String confirm_password=confirmpasswordtf.getText();
                 String salt = BCrypt.gensalt(12);
   String hashed_password = BCrypt.hashpw(password, salt);
   if(!password.isEmpty()&&confirm_password.equals(password)&&ispasswordValid(password)){
            p = cnx.prepareStatement("update user set password = ? where username = ? or email=? ");
            p.setString(1, hashed_password);
           p.setString(2,a);
           p.setString(3,a);
            p.execute();
            }
         else {
       passwordtf.setStyle("-fx-background-color:red");
             confirmpasswordtf.setStyle("-fx-background-color:red");
     }
   
}
public void change_email() throws SQLException{
        retrievedata b = retrievedata.getInstance("", "",0);
        String a =  b.getUsername();
     cnx = Connexion.getInstance().getCnx();

    
    
   if(!emailtf.getText().isEmpty()&&isEmailValid(emailtf.getText())){
       
         String query=("select email from user where email = '" + emailtf.getText() + "'");
  Statement statement = cnx.createStatement();
    rs= statement.executeQuery(query) ;
    if (rs.isBeforeFirst()){
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Email already exists");
            alert.showAndWait();}else{
            p = cnx.prepareStatement("update user set email = ? where username = ? or email=? ");
            p.setString(1, emailtf.getText());
           p.setString(2,a);
           p.setString(3,a);
            p.execute();
    }
            }
        else {
      
             usernametf.setStyle("-fx-background-color:red");
     }
}
public void change_username() throws SQLException{
        retrievedata b = retrievedata.getInstance("", "",0);
        String a =  b.getUsername();
     cnx = Connexion.getInstance().getCnx();

    
   if(!usernametf.getText().isEmpty()&&isusernameValid(usernametf.getText())){
          String query=("select username from user where username = '" + usernametf.getText() + "'");
  Statement statement = cnx.createStatement();
    rs= statement.executeQuery(query) ;
    if (rs.isBeforeFirst()){
         Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username already exists");
            alert.showAndWait();
    }else {
            p = cnx.prepareStatement("update user set username = ? where username = ? or email=? ");
            p.setString(1, usernametf.getText());
           p.setString(2,a);
           p.setString(3,a);
            p.execute();
            }}
   
         else {
      
             usernametf.setStyle("-fx-background-color:red");
     }
}
    @FXML
    private void ModifyUser(ActionEvent event) throws SQLException, IOException {
       try{
           change_fn();
           change_ln();
           change_ad();
           change_ph();
        change_pwd();
        change_username();
        change_email();
        
      
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close(); 
     
       }catch (SQLException e) {
            System.out.println(e.getMessage());
       }
    }
    
}

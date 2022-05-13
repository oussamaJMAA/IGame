/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tools.Connexion;

/**
 *
 * @author oussa
 */
public class LoginController implements Initializable {
    
    
    
    
    
    
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }
    

    


    @FXML
    private Label loginmessage;
    @FXML
    private TextField usernametf;
    @FXML
    private TextField passwordtf;
    @FXML
    private AnchorPane anchor1;
    private Stage stage;
    private Scene scene;
    private Parent root;
    PreparedStatement insert = null;
    

    @FXML
    public void LoginButton(ActionEvent event) throws IOException, SQLException {
        Connection cnx;
        String admin_image = "";
        int id_client =5;
        String client_image="";
        String usr ="";
        String usr2="";
        if (usernametf.getText().isEmpty() == false && passwordtf.getText().isEmpty() == false) {
           
            
            
            
            if (validateLogin() == "Admin") {

                try {
                    cnx = Connexion.getInstance().getCnx();
                    String sql = "SELECT image,username FROM user WHERE username= '" + usernametf.getText() + "'or email= '" + usernametf.getText() + "'";
                    Statement statement = cnx.createStatement();
                    ResultSet rs = statement.executeQuery(sql);

                    if (rs.next()) {
                        admin_image = rs.getString("image");
                        usr=rs.getString("username");
                    }
                } catch (SQLException e) {
                    e.getMessage();
                }
                retrievedata.getInstance(usr, admin_image,id_client);
          
                 root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root, 1400, 800);
                stage.setScene(scene);
                stage.show();

            }
            if (validateLogin() == "Client") {

                try {
                    cnx = Connexion.getInstance().getCnx();
                    String sql = "SELECT image,username,id FROM user WHERE username= '" + usernametf.getText() + "'or email= '" + usernametf.getText() + "'";
                    Statement statement = cnx.createStatement();
                    ResultSet rs = statement.executeQuery(sql);

                    if (rs.next()) {
                        client_image = rs.getString("image");
                       usr2=rs.getString("username");
                       id_client=rs.getInt("id");
                    }
                } catch (SQLException e) {
                    e.getMessage();
                }
                
                
                 retrievedata.getInstance(usr2, client_image,id_client);
               
               root = FXMLLoader.load(getClass().getResource("Loggedin.fxml"));

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root, 1400, 800);
                stage.setScene(scene);
                stage.show();

            }

        } else {
            loginmessage.setText("Please enter username and password");
        }

    }

    @FXML
    public void RegisterLink(ActionEvent event) throws IOException {

       root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

  

    public String validateLogin() throws SQLException {
        String role_identifier = "";
        Connection cnx;
        cnx = Connexion.getInstance().getCnx();
        //123->>azaaeazaz
      
        String sql = "SELECT * FROM user WHERE username= '" + usernametf.getText() + "'or email= '" + usernametf.getText() + "'";
        String sql2= "SELECT password , java_password  FROM user  WHERE username= '" + usernametf.getText() + "'or email= '" + usernametf.getText() + "'";

        try {
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(sql);
          
Statement pp= cnx.createStatement();
 ResultSet rs2 = pp.executeQuery(sql2);

if(rs2.next()){
    String hash =rs2.getString(1);
    String javapwd= rs2.getString(2);
     
       
           System.out.println(javapwd);   
    

  // boolean password_verified = BCrypt.checkpw(passwordtf.getText(), hash);

if(javapwd.equals(passwordtf.getText())){
            if (rs.next()) {

                if ("admin".equals(rs.getString("role"))) {
                   
                    role_identifier = "Admin";

                } else if ("client".equals(rs.getString("role"))) {
               
                    role_identifier = "Client";
                }

            } else {
                loginmessage.setText("Invalid Username or Password");
            }
        }
else {
                loginmessage.setText("Invalid Username or Password");
            }
}
        } catch (SQLException e) {
            e.getMessage();
        }
        return role_identifier;
    }

    @FXML
    private void exit(MouseEvent event) {
        anchor1.setStyle("-fx-background-color:white ;");
    }

    @FXML
    private void black_theme(MouseEvent event) {
        anchor1.setStyle("-fx-background-color:black ;");
    }

    @FXML
    private void on_click_forgot_password(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Forgotpassword.fxml"));

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root, 1400, 800);
                stage.setScene(scene);
                stage.show();
        
        
    }
}


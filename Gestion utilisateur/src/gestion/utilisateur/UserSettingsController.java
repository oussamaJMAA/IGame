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
import java.util.ResourceBundle;
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
        else { 
       try{
           cnx = Connexion.getInstance().getCnx();
          
     
       }catch (SQLException e) {
            System.out.println(e.getMessage());
       }
    }
    }
}

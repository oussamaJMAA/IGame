/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import tools.Connexion;

/**
 *
 * @author oussa
 */
public class LoginController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /* 
 private Scene RegisterScene;


    public void setRegisterScene(Scene scene) {
        RegisterScene = scene;
    }

 
    
     */
    @FXML
    private Label loginmessage;
    @FXML
    private TextField usernametf;
    @FXML
    private TextField passwordtf;

    @FXML
                loginmessage.setText("Invalid Username or Password");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return role_identifier;
    }

}

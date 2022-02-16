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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tools.Connexion;

/**
 *
 * @author oussa
 */
public class RegisterController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    // private Scene LoginScene;
/*
    public void setLoginScene(Scene scene) {
        LoginScene = scene;
    } */
    public void LoginLink(ActionEvent event) throws IOException {

        /*
         Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
         primaryStage.setTitle("iGame.exe");
        primaryStage.setScene(LoginScene);
         */
        Parent root =);
        stage.show();
    }
    @FXML
    private TextField usernametf;
    @FXML
    private TextField passwordtf;
    @FXML
    private TextField firstnametf;
    @FXML
    private TextField lastnametf;
    @FXML
    private TextField addresstf;
    @FXML
    private TextField emailtf;
    @FXML
    private Label messagelabel;
    @FXML
    private RadioButton maler;
    @FXML
    private RadioButton femaler;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    }

}

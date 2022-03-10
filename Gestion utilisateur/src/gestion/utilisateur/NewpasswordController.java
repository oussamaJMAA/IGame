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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import tools.Connexion;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class NewpasswordController implements Initializable {

    @FXML
    private PasswordField passwordtf;
    @FXML
    private PasswordField confirm_passwordtf;
    Connection cnx = null;
    PreparedStatement p = null;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label emaillabel;

    public void setemail(String email) {
        emaillabel.setText(email);
    }
    String email = "";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void on_click_confirm(ActionEvent event) throws IOException {
        email = emaillabel.getText();
           if(passwordtf.getText().isEmpty()&&confirm_passwordtf.getText().isEmpty()){
           
           
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Please fill all data");
                alert.showAndWait();
           
           
           }
           else if(!confirm_passwordtf.getText().equals(passwordtf.getText())){
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Passwords don't match");
                alert.showAndWait();
           }
           else {
        try {
        
            String salt = BCrypt.gensalt(12);
            String hashed_password = BCrypt.hashpw(passwordtf.getText(), salt);
            cnx = Connexion.getInstance().getCnx();
            String sql = "UPDATE `user` SET "
                    + "`password`= ? WHERE email = '" + email + "'";
            p = cnx.prepareStatement(sql);
            p.setString(1, hashed_password);
            p.executeUpdate();
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 1400, 800);
            stage.setScene(scene);
            stage.show();

        } catch (SQLException e) {
            e.getMessage();
        }
    }
          
    }
}

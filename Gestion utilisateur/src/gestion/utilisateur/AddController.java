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
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import tools.Connexion;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class AddController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField firstnametf;
    @FXML
    private TextField lastnametf;
    @FXML
    private TextField usernametf;
    @FXML
    private RadioButton femaler;
    @FXML
    private RadioButton maler;
    @FXML
    private PasswordField passwordtf;
    @FXML
    private RadioButton coachr;
    @FXML
    private RadioButton adminr;
    @FXML
    private TextField emailtf;
    String query = null;
    Connection cnx = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement;
    private boolean update;
    int userId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

            preparedStatement.setString(7, role);
            preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void BACK(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminSpace.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 868, 600);
        stage.setScene(scene);
        stage.show();
    }

    void setTextField(int id, String firstname, String lastname, String password, String username, String email) {

        userId = id;
        firstnametf.setText(firstname);
        lastnametf.setText(lastname);
        passwordtf.setText(password);
        emailtf.setText(email);
        usernametf.setText(username);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

}

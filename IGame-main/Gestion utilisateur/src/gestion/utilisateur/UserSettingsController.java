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
    private TextField emailtf;
    Connection cnx = null;
    private TextField lastnametf;
    private TextField firstnametf;
    private TextField adresstf;
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

    public static boolean isusernameValid(String s) {
        // accepts 5 to 15 characters with any lower case character, digit or special symbol “_-” only.
        Pattern p = Pattern.compile("^[A-Za-z]\\w{5,29}$");
        Matcher m = p.matcher(s);
        return (m.matches());
    }

    public static boolean isStringOnlyAlphabet(String str) {

        return ((str != null) && (!str.equals(""))
                && (str.matches("^[a-zA-Z]*$")) && str.length() <= 12);
    }

    public static boolean isadressValid(String str) {

        return ((str != null) && (!str.equals(""))
                && (str.matches("^\\s*[\\da-zA-Z][\\da-zA-Z\\s]*$")) && str.length() <= 12);
    }

    public static boolean ispasswordValid(String str) {

        return ((str != null) && (!str.equals(""))
                && (str.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{5,20}$")));
    }

    public void change_username() throws SQLException {
        retrievedata b = retrievedata.getInstance("", "", 0);
        int a = b.getId();
        cnx = Connexion.getInstance().getCnx();
        String query = ("select username from user where username = '" + usernametf.getText() + "'");
        Statement statement = cnx.createStatement();
        rs = statement.executeQuery(query);
        if (rs.isBeforeFirst()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Username already exists");
            alert.showAndWait();
        } else {
            p = cnx.prepareStatement("update user set username = ? where id = ?");
            p.setString(1, usernametf.getText());
            p.setInt(2, a);
            p.execute();
        }

    }

    public void change_pwd() throws SQLException {

        retrievedata b = retrievedata.getInstance("", "", 0);
        int a = b.getId();
        cnx = Connexion.getInstance().getCnx();
        String password = passwordtf.getText();
        String confirm_password = confirmpasswordtf.getText();
        String salt = BCrypt.gensalt(12);
        String hashed_password = BCrypt.hashpw(password, salt);

        p = cnx.prepareStatement("update user set password=?,java_password = ? where id = ? ");
        p.setString(1, hashed_password);
        p.setString(2, passwordtf.getText());
        p.setInt(3, a);
        p.execute();

    }

    @FXML
    private void ModifyUser(ActionEvent event) throws SQLException, IOException {
        try {
            if (!passwordtf.getText().isEmpty() && confirmpasswordtf.getText().equals(passwordtf.getText()) && ispasswordValid(passwordtf.getText()) && usernametf.getText().isEmpty()) {
                change_pwd();
            } else if (!usernametf.getText().isEmpty() && isusernameValid(usernametf.getText())&&passwordtf.getText().isEmpty()) {
                change_username();
            } else if (!passwordtf.getText().isEmpty() && confirmpasswordtf.getText().equals(passwordtf.getText()) && ispasswordValid(passwordtf.getText()) && !usernametf.getText().isEmpty() && isusernameValid(usernametf.getText())) {
                   change_username();
                             change_pwd();
            }
            else {
                System.out.println("error");
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

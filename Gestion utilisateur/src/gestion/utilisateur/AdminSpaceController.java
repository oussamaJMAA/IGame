/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tools.Connexion;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class AdminSpaceController implements Initializable {

    private ImageView photo;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Label hiLabel;
    @FXML
    private TableView<User> usersTable;
    // @FXML
    //private TableColumn<User, String> idcol;
    @FXML
    private TableColumn<User, String> firstnamecol;
    @FXML
    private TableColumn<User, String> lastnamecol;
    @FXML
    private TableColumn<User, String> emailcol;
    @FXML
    private TableColumn<User, String> usernamecol;
    @FXML
    private TableColumn<User, String> gendercol;
    @FXML
    private TableColumn<User, String> rolecol;
    @FXML
    pri
                if (user_gender.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (user_email.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (user_address.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (user_nationality.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (user_date.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (k.indexOf(searchkeyword) > -1) {
                    return true;
                }

                return false;

            });
        });
        SortedList<User> s = new SortedList<>(filteredData);
        s.comparatorProperty().bind(usersTable.comparatorProperty());
        usersTable.setItems(s);
    }

    @FXML
    private void on_click_dashboard_button(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void on_click_users_button(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminSpace.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void on_click_sign_out(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Are you sure you want to logout ?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            retrievedata b = retrievedata.getInstance("", "");
            b.cleanUserSession();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

}

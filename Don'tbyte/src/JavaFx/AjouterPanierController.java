/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFx;

import entities.Panier;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.PanierCRUD;

/**
 * FXML Controller class
 *
 * @author Magui
 */
public class AjouterPanierController implements Initializable {

    @FXML
    private TextField nomProduit;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<String> produitt;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_vider;

    PanierCRUD service = new PanierCRUD();
    @FXML
    private Button next;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         produitt.getItems().add("1");
        produitt.getItems().add("2");
        produitt.getItems().add("3");
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        if(nomProduit.getText().equals("")&& prix.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR, "champs vide", ButtonType.CLOSE);
            a.show();
        }
        else
        {
        Panier p= new Panier();
        p.setIdProduit(11L);
        p.setNomProduit(nomProduit.getText());
        p.setPrix(Integer.valueOf(prix.getText()));
        p.setImage("image");
        service.ajouterPanier(p);
      affnotif();
    }
    }
    @FXML
    private void reset(ActionEvent event) {
        nomProduit.setText("");
        prix.setText("");
    }
     public void affnotif(){
        Notifications notificationBuilder = Notifications.create()
        .title("Alert").text("Panier ajouté avec succé").graphic(null).hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    @FXML
    private void next(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListPanier.fxml"));
                Parent root = loader.load();
               ListPanierController aa = loader.getController();
                next.getScene().setRoot(root);
    }
}

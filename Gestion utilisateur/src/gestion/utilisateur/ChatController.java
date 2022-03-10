/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class ChatController implements Initializable {

    @FXML
    private Button serveurr;
    @FXML
    private Button clientt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LoadChat1(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("server.fxml"));
            Parent root = loader.load();
            serverController sc1 = loader.getController();
            serveurr.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());


        }
    }

    @FXML
    private void LoadChat2(ActionEvent event) {
            Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("client.fxml"));
             root = loader.load();
            ClientController cc = loader.getController();
            clientt.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());




        }

    }
    
}

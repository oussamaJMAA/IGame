/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFx;

import entities.Commande;
import entities.Panier;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.CommandeCRUD;

/**
 * FXML Controller class
 *
 * @author Magui
 */
public class ListCommandeController implements Initializable {

    @FXML
    private TextField mdp_jour;
    @FXML
    private TextField livraison_a_jour;
    @FXML
    private DatePicker date_a_jour;
    @FXML
    private TableView<Commande> table;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_supprimer;
    @FXML
    private TableColumn<Commande, String> prod;
    @FXML
    private TableColumn<Commande, ?> client;
    @FXML
    private TableColumn<Commande, ?> date_a_j;
    @FXML
    private TableColumn<Commande, ?> mode;
    @FXML
    private TableColumn<Commande, ?> liv;

    CommandeCRUD service= new CommandeCRUD();
     private ObservableList<Commande> CommandeData = FXCollections.observableArrayList();
    @FXML
    private Button rt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
              List<Commande> list= new ArrayList<Commande>();
       list= service.displayCommmande();
       CommandeData.addAll(list);
       table.setItems(CommandeData);
        prod.setCellValueFactory(
            new PropertyValueFactory<>("idProduit")
        );
        client.setCellValueFactory(
            new PropertyValueFactory<>("idClient")
        );
        date_a_j.setCellValueFactory(
            new PropertyValueFactory<>("date")
        );
        mode.setCellValueFactory(
            new PropertyValueFactory<>("modePaiment")
        );
         liv.setCellValueFactory(
            new PropertyValueFactory<>("livraison")
        );
    }    

    @FXML
    private void ajouter(ActionEvent event) {
          try {
                           Parent root = FXMLLoader.load(getClass().getResource("AjouterCommande.fxml"));
                            Stage myWindow = (Stage) table.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AjouterCommandeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }

    @FXML
    private void update(ActionEvent event) {
        if(mdp_jour.getText().equals("") && livraison_a_jour.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR, "champs vide", ButtonType.CLOSE);
            a.show();
        }
        else{
             Commande c = table.getSelectionModel().getSelectedItem();
             c.setLivraison(livraison_a_jour.getText());
            c.setModePaiment(mdp_jour.getText());
           service.updateCommande(c);
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Commande modifié", ButtonType.OK);
        alert.show();
        resetTableData();
        }
    }

    @FXML
    private void supp(ActionEvent event) {
         Commande c = table.getSelectionModel().getSelectedItem();
            service.suppCommande(c);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Commande supprimé", ButtonType.OK);
        alert.show();
            resetTableData();

    }
      public void resetTableData() {
        List<Commande> list = new ArrayList<>();

        list = service.displayCommmande();
        ObservableList<Commande> data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }

    @FXML
    private void test(MouseEvent event) {
        Commande c = table.getSelectionModel().getSelectedItem();
        mdp_jour.setText(c.getModePaiment());
        livraison_a_jour.setText(c.getLivraison());
       
    }

    @FXML
    private void rt(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCommande.fxml"));
                Parent root = loader.load();
                AjouterCommandeController aa = loader.getController();
                rt.getScene().setRoot(root);
    }
}

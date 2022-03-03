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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.PanierCRUD;

/**
 * FXML Controller class
 *
 * @author Magui
 */
public class ListPanierController implements Initializable {

    @FXML
    private TableView<Panier> table;
    @FXML
    private TextField p_a_jour;
    @FXML
    private TextField prix_a_j;
    @FXML
    private TextField image_a_ajour;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_supprimer;
    @FXML
    private TableColumn<Panier, String> nomP;
    @FXML
    private TableColumn<Panier, Integer> prix;
    @FXML
    private TableColumn<Panier, String> img;
    @FXML
    private TableColumn<Panier, String> produit;
    private ObservableList<Panier> PanierData = FXCollections.observableArrayList();

    PanierCRUD service=new PanierCRUD();
    @FXML
    private Button rt;
    @FXML
    private Button vd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           List<Panier> list= new ArrayList<Panier>();
       list= service.displayPanier();
       PanierData.addAll(list);
       table.setItems(PanierData);
        nomP.setCellValueFactory(
            new PropertyValueFactory<>("nomProduit")
        );
        prix.setCellValueFactory(
            new PropertyValueFactory<>("prix")
        );
        img.setCellValueFactory(
            new PropertyValueFactory<>("image")
        );
        produit.setCellValueFactory(
            new PropertyValueFactory<>("idProduit")
        );
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        try {
                           Parent root = FXMLLoader.load(getClass().getResource("AjouterPanier.fxml"));
                            Stage myWindow = (Stage) table.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AjouterPanierController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }

    @FXML
    private void update(ActionEvent event) {
          if(p_a_jour.getText().equals("") && prix_a_j.getText().equals("")&& image_a_ajour.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR, "champs vide", ButtonType.CLOSE);
            a.show();
        }
        else{
             Panier p = table.getSelectionModel().getSelectedItem();
             p.setNomProduit(p_a_jour.getText());
            p.setPrix(Integer.parseInt(prix_a_j.getText()));
            p.setImage(image_a_ajour.getText());
           service.updatePanier(p);
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Panier modifié", ButtonType.OK);
        alert.show();
        resetTableData();
        }
    }

    @FXML
    private void supp(ActionEvent event) {
         Panier p = table.getSelectionModel().getSelectedItem();
            service.suppPanier(p);
            resetTableData();
    }
     public void resetTableData() {
        List<Panier> list = new ArrayList<>();

        list = service.displayPanier();
        ObservableList<Panier> data = FXCollections.observableArrayList(list);
        table.setItems(data);
    }

    @FXML
    private void test(MouseEvent event) {
        
         Panier p = table.getSelectionModel().getSelectedItem();
        prix_a_j.setText(Integer.toString(p.getPrix()));
        p_a_jour.setText(p.getNomProduit());
        image_a_ajour.setText(p.getImage());
    }

    @FXML
    private void rt(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterPanier.fxml"));
                Parent root = loader.load();
                AjouterPanierController aa = loader.getController();
                rt.getScene().setRoot(root);
    }

    @FXML
    private void vd(ActionEvent event)throws IOException
    { FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCommande.fxml"));
                Parent root = loader.load();
                AjouterCommandeController aa = loader.getController();
                vd.getScene().setRoot(root);
    }
    
}

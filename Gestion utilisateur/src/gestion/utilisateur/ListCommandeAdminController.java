/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import com.itextpdf.text.DocumentException;
import gestion.utilisateur.entities.Commande;
import gestion.utilisateur.entities.Pdf;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import gestion.utilisateur.servicem.CommandeCRUD;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class ListCommandeAdminController implements Initializable {
Parent root ;
Scene scene ;
Stage stage ;

    @FXML
    private ListView<Commande> listview;
    @FXML
    private ComboBox<String> combo;
 CommandeCRUD service= new CommandeCRUD();
     private ObservableList<Commande> CommandeData = FXCollections.observableArrayList();
    @FXML
    private Button btn_etat;
    @FXML
    private ImageView admin_image;
    @FXML
    private Label test;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnPackages1;
    @FXML
    private Button btnSignout;
    @FXML
    private Button on_click_pdf;
    @FXML
    private Button btnPackages11;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
          List<Commande> list= new ArrayList<Commande>();
       list= service.displayCommmande();
       CommandeData.addAll(list);
       listview.setItems(CommandeData);
       
        combo.getItems().add("en cours");
        combo.getItems().add("annulé");
        combo.getItems().add("livré");
        combo.getItems().add("en attend");
     
    }    

    @FXML
    private void charger(MouseEvent event) {
        
         Commande c = listview.getSelectionModel().getSelectedItem();
        
    }
      public void resetTableData() {
        List<Commande> list = new ArrayList<>();

        list = service.displayCommmande();
        ObservableList<Commande> data = FXCollections.observableArrayList(list);
        listview.setItems(data);
    }

    @FXML
    private void setEtat(ActionEvent event) throws SQLException {
                 Commande c = listview.getSelectionModel().getSelectedItem();
                 service.setEtat(c, combo.getSelectionModel().getSelectedItem());
                 resetTableData();
    }

    @FXML
    private void on_click_dashboard_button(ActionEvent event) {
    }


    @FXML
    private void on_click_users_button(ActionEvent event) throws IOException {
          root = FXMLLoader.load(getClass().getResource("AdminSpace.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void on_click_tournaments(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); 
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
    }

    @FXML
    private void on_click_teams(ActionEvent event) throws IOException {
          root = FXMLLoader.load(getClass().getResource("equipe.fxml")); 
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void on_click_sign_out(ActionEvent event) {
    }
    
    
    @FXML
    private void pdf(ActionEvent event) throws DocumentException, IOException, FileNotFoundException, InterruptedException, SQLException {

        
        Pdf pd=new Pdf();
        try{
        pd.GeneratePdf("List of commands");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(CommandeCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void on_click_products(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Produit.fxml")); 
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void on_click_blog(ActionEvent event) throws IOException {
           root = FXMLLoader.load(getClass().getResource("PubCom.fxml")); 
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void on_click_commande(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ListCommandeAdmin.fxml")); 
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
    }


    

}

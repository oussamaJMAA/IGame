/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFx;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import entities.Commande;
import entities.Panier;
import entities.Pdf;
import entities.Produit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.PanierCRUD;
import service.ProduitService;

/**
 * FXML Controller class
 *
 * @author Magui
 */
public class ListPanierController implements Initializable {

    private TableView<Panier> table;
    private TextField p_a_jour;
    private TextField prix_a_j;
    private TextField image_a_ajour;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_supprimer;
    private TableColumn<Panier, String> nomP;
    private TableColumn<Panier, Integer> prix;
    private TableColumn<Panier, String> img;
    private TableColumn<Panier, String> produit;
    private ObservableList<Panier> PanierData = FXCollections.observableArrayList();

    PanierCRUD service=new PanierCRUD();
    @FXML
    private ListView<Panier> listview;
    @FXML
    private ComboBox<Produit> comboProduit;
     ProduitService ps=new ProduitService();
         private ObservableList<Produit> ProduitData = FXCollections.observableArrayList();
    @FXML
    private Pane pan;
    @FXML
    private Pane pan2;
    @FXML
    private TextField totaleP;
    @FXML
    private Button totalePanier;
    public int idclient=22;
    @FXML
    private TextField reduction;
    @FXML
    private Button pdf;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           List<Panier> list= new ArrayList<Panier>();
       list= service.displayPanier();
       PanierData.addAll(list);
       listview.setItems(PanierData);
        List<Produit> list2= new ArrayList<Produit>();
       list2= ps.getAllProduit();
       ProduitData.addAll(list2);
       comboProduit.setItems(ProduitData);
       pan.setVisible(false); pan2.setVisible(false);
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        try {
                           Parent root = FXMLLoader.load(getClass().getResource("AjouterPanier.fxml"));
                            Stage myWindow = (Stage) listview.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(AjouterPanierController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }

    @FXML
    private void update(ActionEvent event) {
       /*   if(p_a_jour.getText().equals("") && prix_a_j.getText().equals("")&& image_a_ajour.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR, "champs vide", ButtonType.CLOSE);
            a.show();
        }
        else{
             p.setNomProduit(p_a_jour.getText());
            p.setPrix(Integer.parseInt(prix_a_j.getText()));
            p.setImage(image_a_ajour.getText());
           service.updatePanier(p);
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Panier modifié", ButtonType.OK);
        alert.show();*/
                Produit prod = comboProduit.getSelectionModel().getSelectedItem();
             Panier p = listview.getSelectionModel().getSelectedItem();
             p.setIdProduit(Long.valueOf(prod.getId()));
             p.setNomProduit(prod.getNom());
             p.setPrix(prod.getPrix());
           service.updatePanier(p);

        resetTableData();
       // }
       
    }

    @FXML
    private void supp(ActionEvent event) {
         Panier p = listview.getSelectionModel().getSelectedItem();
            service.suppPanier(p);
            resetTableData();
    }
     public void resetTableData() {
        List<Panier> list = new ArrayList<>();

        list = service.displayPanier();
        ObservableList<Panier> data = FXCollections.observableArrayList(list);
        listview.setItems(data);
    }

    private void test(MouseEvent event) {
       
    }

    @FXML
    private void charger(MouseEvent event) {
         
         Panier p = listview.getSelectionModel().getSelectedItem();
       
        pan.setVisible(true);
    }

    @FXML
    private void totale(ActionEvent event) throws SQLException {
        pan2.setVisible(true);
        
                totaleP.setText(service.totalePanier(idclient).toString());
                float x=service.réductionParSaison(service.totalePanier(idclient));
                reduction.setText(Float.toString(x));
    }

    @FXML
    private void pdf(ActionEvent event)  throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
        Pdf pd=new Pdf();
        try{
        pd.GeneratePdfp("list Panier");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(PanierCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import gestion.utilisateur.entities.Commande;
import gestion.utilisateur.entities.Panier;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import gestion.utilisateur.servicem.CommandeCRUD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Magui
 */
public class ListCommandeController implements Initializable {
Parent root ;
Stage stage ;
Scene scene ;
    private TextField mdp_jour;
    private TextField livraison_a_jour;
    private TableView<Commande> table;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_supprimer;
    private TableColumn<Commande, String> prod;
    private TableColumn<Commande, String> client;
    private TableColumn<Commande, ?> date_a_j;
    private TableColumn<Commande, ?> mode;
    private TableColumn<Commande, ?> liv;

    CommandeCRUD service= new CommandeCRUD();
     private ObservableList<Commande> CommandeData = FXCollections.observableArrayList();
    @FXML
    private ListView<Commande> listview;
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
    private Button btnPackages11;
   @FXML
    private TableColumn<Commande, Integer> col_nb_prod;
    @FXML
    private TableColumn<Commande, java.sql.Date> col_date;
    @FXML
    private TableColumn<Commande,String> col_meth;
    @FXML
    private TableColumn<Commande,String> col_etat;
    @FXML
    private TableColumn<Commande, Double> col_prix;
    @FXML
    private TableView<Commande> tab;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        retrievedata a = retrievedata.getInstance("","",0);
              List<Commande> list= new ArrayList<Commande>();
       list= service.getCommandeByClient(a.getId());
       CommandeData.addAll(list);
       listview.setItems(CommandeData);
             
         
         test.setText(a.getUsername());
         
               int jj=a.getImage().lastIndexOf('\\');
admin_image.setImage(new Image(LoggedinController.class.getResourceAsStream(a.getImage().substring(jj+1))));
       showTournois();
    }    

 public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_java", "root", "");
            System.out.println("Connection done ! ");
            return conn;
        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
            return null;
        }
    }

    public ObservableList<Commande> getTournoisList() {
        ObservableList<Commande> TournoisList = FXCollections.observableArrayList();
        Connection conn = getConnection();
         retrievedata a = retrievedata.getInstance("","",0);
         int id = a.getId();
        String query = "SELECT * FROM commande where user_id= "+id+" ";
        Statement st;
       
        ResultSet rs;
        try {
            st = conn.createStatement();
           
            rs = st.executeQuery(query);
           Commande commande;
            while (rs.next()) {
                //int id_cmd, Date date, String modePaiment, int idClient, String etat, int nbproduit, float prix_tot
               commande = new Commande(rs.getInt("id"), rs.getDate("date"), rs.getString("methodedepaiement"), rs.getInt("user_id"), rs.getString("etat"), rs.getInt("nbproduit"), rs.getDouble("prixtotale"));
                TournoisList.add(commande);

            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());

        }
        return TournoisList;
    }


    public void showTournois() {
   CommandeCRUD test = new CommandeCRUD();
        List aa = test.displayCommmande();
        ObservableList<Commande> list = getTournoisList();
   Commande commande;
   /*col_nb_prod;
    @FXML
    private TableColumn<Commande, Date> col_date;
    @FXML
    private TableColumn<Commande,String> col_meth;
    @FXML
    private TableColumn<Commande,String> col_etat;
    @FXML
    private TableColumn<Commande, Float> col_prix;*/
        col_nb_prod.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("nbproduit"));
        col_date.setCellValueFactory(new PropertyValueFactory<Commande, java.sql.Date>("date"));
        col_meth.setCellValueFactory(new PropertyValueFactory<Commande, String>("modePaiment"));
       col_etat.setCellValueFactory(new PropertyValueFactory<Commande,String>("etat"));
      col_prix.setCellValueFactory(new PropertyValueFactory<Commande, Double>("prix_tot"));

    tab.setItems(list);

    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("AjouterCommande.fxml"));  
             stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("tournois clients");
                stage.show(); 
    }

    private void update(ActionEvent event) {
        if(mdp_jour.getText().equals("") && livraison_a_jour.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR, "champs vide", ButtonType.CLOSE);
            a.show();
        }
        else{
             Commande c = listview.getSelectionModel().getSelectedItem();
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
         Commande c = listview.getSelectionModel().getSelectedItem();
            service.suppCommande(c);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Commande supprimé", ButtonType.OK);
        alert.show();
            resetTableData();

    }
      public void resetTableData() {
        List<Commande> list = new ArrayList<>();

        list = service.displayCommmande();
        ObservableList<Commande> data = FXCollections.observableArrayList(list);
        listview.setItems(data);
    }

    @FXML
    private void test(MouseEvent event) {
        Commande c = listview.getSelectionModel().getSelectedItem();
        mdp_jour.setText(c.getModePaiment());
        livraison_a_jour.setText(c.getLivraison());
       
    }

     @FXML
    private void on_click_dashboard_button(ActionEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void on_click_users_button(ActionEvent event) {
    }

    @FXML
    private void on_click_tournaments(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("tournois_client.fxml"));  
             stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("tournois clients");
                stage.show(); 
    }

    @FXML
    private void on_click_teams(ActionEvent event) throws IOException {
             root = FXMLLoader.load(getClass().getResource("affiche_equipe_client.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
    }

    @FXML
    private void on_click_sign_out(ActionEvent event) {
    }

 

    

    @FXML
    private void on_click_commande(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ListCommande.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
    }

    @FXML
    private void charger(MouseEvent event) {
    }

    @FXML
    private void on_click_products(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("viewclient.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
    }

    @FXML
    private void on_click_panier(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ListPanier.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
    }

    @FXML
    private void getID(MouseEvent event) {
    }
}
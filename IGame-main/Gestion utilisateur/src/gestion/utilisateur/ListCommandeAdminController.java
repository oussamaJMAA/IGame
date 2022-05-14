/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import com.itextpdf.text.DocumentException;
import gestion.utilisateur.entities.Commande;
import gestion.utilisateur.entities.Pdf;
import gestion.utilisateur.entities.Tournois;
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
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        ArrayList list = new ArrayList<>();
         CommandeCRUD test = new CommandeCRUD();
        List aa = test.displayCommmande();
      System.out.println(aa);
         
    
        combo.getItems().add("en cours");
        combo.getItems().add("annulé");
        combo.getItems().add("livré");
           System.out.println(list.toString());
        combo.getItems().add("en attend");
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
        String query = "SELECT * FROM commande";
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

    private void charger(MouseEvent event) {
        
         Commande c = tab.getSelectionModel().getSelectedItem();
        
    }
      public void resetTableData() {
      Commande c = new Commande();
ObservableList<Commande> list1 = getTournoisList();
    list1.add(c);
        ObservableList<Commande> data = FXCollections.observableArrayList(list1);
       tab.setItems(data);
    }

    @FXML
    private void setEtat(ActionEvent event) throws SQLException {
                 Commande c = tab.getSelectionModel().getSelectedItem();
                 service.setEtat(c, combo.getSelectionModel().getSelectedItem());
                 showTournois();
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gestion.utilisateur;

import gestion.utilisateur.entities.Categorie;
import gestion.utilisateur.entities.Tournois;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale.Category;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rouka
 */
public class CategorieController implements Initializable {

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
    private TextField tfnom;
    @FXML
    private TextField tfdesc;
    @FXML
    private TableColumn<Categorie, String> nom;
    @FXML
    private TableColumn<Categorie, String> desc;
    @FXML
    private TableView<Categorie> tab;
    @FXML
    private AnchorPane keywordtextfield;
    @FXML
    private TextField keywordtextfield1;
    @FXML
    private Button add;
    @FXML
    private Button modif;
    @FXML
    private Button del;
    @FXML
    private TextField tfid;
    @FXML
    private Button anuller;
  private Parent root;
    private Stage stage;
    private Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfid.setVisible(false);
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

    private void executeQuery(String query) {
        Connection conn = getConnection();
        //To change body of generated methods, choose Tools | Templates.
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);

            showTournois();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public ObservableList<Categorie> getTournoisList() {
        ObservableList<Categorie> TournoisList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select * from category";
        Statement st;
       
        ResultSet rs;
        try {
            st = conn.createStatement();
           
            rs = st.executeQuery(query);
            Categorie cat;
            while (rs.next()) {
               cat = new Categorie(rs.getString("category_name"),rs.getInt("category_id"),rs.getString("discription"));
                TournoisList.add(cat);

            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());

        }
        return TournoisList;
    }

    public void showTournois() {

        ObservableList<Categorie> list = getTournoisList();
        Categorie cat;
        nom.setCellValueFactory(new PropertyValueFactory<Categorie, String>("nom"));
        desc.setCellValueFactory(new PropertyValueFactory<Categorie, String>("description"));
       
        tab.setItems(list);
         
    }
      @FXML
    private void clicked_tab(MouseEvent event) {
         Categorie t = tab.getSelectionModel().getSelectedItem();
         tfnom.setText(t.getNom());
         tfdesc.setText(t.getDescription());
         tfid.setText(""+t.getId());
    }

   
    
    public void insertTournois() throws ParseException {
        String nom = tfnom.getText();
        String desc = tfdesc.getText();
     String query = "insert into category(category_name,discription) values('"+nom+"','"+desc+"')";
        executeQuery(query);
        showTournois();
    }

    public void updateTournois() throws ParseException {
         String nom = tfnom.getText();
        String desc = tfdesc.getText();
        int id = Integer.parseInt(tfid.getText());
     String query = "update category set category_name = '"+nom+"',discription = '"+desc+"' where category_id="+id+"";
 executeQuery(query);
        showTournois();
       
    }

    public void deleteTournois() {
        int id = Integer.parseInt(tfid.getText());
        String query = "delete from category where category_id="+id+"";

        executeQuery(query);
        showTournois();
    }
    
    
    
    
    
    
     @FXML
    private void handleButtonAction(ActionEvent event) throws ParseException {
        
       if(event.getSource()==anuller){
       tfnom.setText("");
       tfdesc.setText("");
       tfid.setText("");
       
       }else if(event.getSource()==add){
                tfid.setText("");
tfnom.setText("");
tfdesc.setText("");
       insertTournois();
       
       }else if(event.getSource()==del){
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Vous etes entrain de Supprimer, Confirmer la Suppression");
            if (alert.showAndWait().get() == ButtonType.OK) {

                deleteTournois();
                tfid.setText("");
tfnom.setText("");
tfdesc.setText("");

            }
       }else if(event.getSource()==modif){
             Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setHeaderText(null);
            alert1.setContentText("Vous etes entrain de Modifier, Confirmer la Modification");
            if (alert1.showAndWait().get() == ButtonType.OK) {

       updateTournois();
            tfid.setText("");
tfnom.setText("");
tfdesc.setText("");
       }
       
       
    
    }
    }

       @FXML
    private void on_click_dashboard_button(ActionEvent event)throws IOException {
      root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException{
    }

    @FXML
    private void on_click_users_button(ActionEvent event)throws IOException {
        
          root = FXMLLoader.load(getClass().getResource("AdminSpace.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void on_click_tournaments(ActionEvent event) throws IOException{
            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); //khali hedhi pour le moment
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
    }

    @FXML
    private void on_click_teams(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("equipe.fxml")); //khali hedhi pour le moment
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void on_click_sign_out(ActionEvent event)throws IOException {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Are you sure you want to logout ?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            retrievedata b = retrievedata.getInstance("", "",0);
            b.cleanUserSession();
          root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

}

    @FXML
    private void on_click_live_tournaments(ActionEvent event) throws IOException {
           root = FXMLLoader.load(getClass().getResource("Live_tournois.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void on_click_participation(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("participation.fxml"));
           stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Participation");
                stage.show();
    }
  
    
}

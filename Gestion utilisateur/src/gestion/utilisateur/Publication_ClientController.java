/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gestion.utilisateur;

import gestion.utilisateur.entities.Publication;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rouka
 */

public class Publication_ClientController implements Initializable {

    /**
     * Initializes the controller class.
     */
     private Stage stage ;
    private Scene scene ;
    private Parent root ;
    private List<Publication> pub;
    @FXML
    private GridPane GridPane;
    @FXML
    private ImageView admin_image;
    @FXML
    private Label test;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOverview1;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnSettings1;
    @FXML
    private Button btnPackages1;
    @FXML
    private Button btn_blog;
    @FXML
    private Button btnSignout;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int columns = 0;
        int row =1;
        pub = new ArrayList<>(getTournoisList());
        try{
        for(int i=0;i<pub.size();i++){
        FXMLLoader l = new FXMLLoader();
        l.setLocation(getClass().getResource("Publication_Item.fxml"));
          
          VBox box = l.load();
          Publication_ItemController pc = l.getController();
          pc.setData(pub.get(i));
        if(columns==1){
        columns = 0;
        ++row;
        }
        GridPane.add(box, columns++, row);
        GridPane.setMargin(box,new Insets(10));
        }  }catch(Exception ex){
          System.out.println(ex);
          }
        
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


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public List<Publication> getTournoisList() {
        List<Publication> TournoisList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select * from publication";
        Statement st;
       
        ResultSet rs;
  
        try {
            st = conn.createStatement();
           
            rs = st.executeQuery(query);
            Publication g;
            while (rs.next()) {
                //(int id, String titre, String contenu, String image, Date created_at)
             g = new Publication(rs.getInt("id"),rs.getString("titre"),rs.getString("contenu"),rs.getString("image"),rs.getDate("created_at"));
                TournoisList.add(g);

            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());

        }
        return TournoisList;
    }

    @FXML
    private void on_click_dashboard_button(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Loggedin.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
    }

   @FXML
    private void on_click_games(ActionEvent event) throws IOException {
          root = FXMLLoader.load(getClass().getResource("Game_Client.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
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
    private void on_clicki_products(ActionEvent event) throws IOException {
             root = FXMLLoader.load(getClass().getResource("Produit_Client.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
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
    private void on_click_blogs(ActionEvent event) throws IOException {
           root = FXMLLoader.load(getClass().getResource("Publication_Client.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
    }

    @FXML
    private void on_click_sign_out(ActionEvent event) {
    }
    
    
    
    
}

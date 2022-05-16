/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import gestion.utilisateur.entities.Tournois;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rouka
 */
public class Tournois_clientController implements Initializable {

    @FXML
    private GridPane grid;
private List<Tournois> t;
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private ImageView admin_image;
    @FXML
    private Label test;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnSettings1;
    @FXML
    private Button btnPackages1;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btn_blog;
    @FXML
    private Button btnOverview1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                retrievedata a = retrievedata.getInstance("", "",0);
         
      test.setText(a.getUsername());
         
      try{
           InputStream stream = new FileInputStream("C:\\Users\\oussa\\PhpstormProjects\\gaming_app\\public\\uploads\\photos\\"+a.getImage());
      Image image4 = new Image(stream);
      admin_image.setImage(image4);   
              
          }catch(Exception ex){
              System.out.println(ex);
          }
        int c = 0;
        int row =1;
        t= new ArrayList<>(tt());
       for(int i=0;i<t.size();i++){
       FXMLLoader fxml = new FXMLLoader();
       fxml.setLocation(getClass().getResource("thumb.fxml"));
            try {
                VBox box = fxml.load();
            
            ThumbController tc = fxml.getController();
      tc.setData(t.get(i));
      if(c==3){
      c=0;
      ++row;
      
      }
      grid.add(box, c++, row);
      GridPane.setMargin(box,new Insets(10));
            } catch (IOException ex) {
                Logger.getLogger(Tournois_clientController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
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
    public List<Tournois> tt() {
        ObservableList<Tournois> TournoisList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select * from tournois";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Tournois tournois;
            while (rs.next()) {
                tournois = new Tournois(rs.getInt("id"), rs.getString("nom_tournois"), rs.getDate("date_tournois"), rs.getInt("capacite"), rs.getString("platforme"), rs.getString("recompense"),rs.getTime("time_t"));
                TournoisList.add(tournois);

            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());

        }
        return TournoisList;
    }
    
    private void swtichTomenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void on_click_dashboard_button(ActionEvent event) throws IOException {
          root = FXMLLoader.load(getClass().getResource("Loggedin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
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
    private void on_click_paniers(ActionEvent event) throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("ListPanier.fxml"));  
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
    private void on_click_sign_out(ActionEvent event) {
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
    private void on_click_games(ActionEvent event) throws IOException {
           root = FXMLLoader.load(getClass().getResource("Game_Client.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
    }
}

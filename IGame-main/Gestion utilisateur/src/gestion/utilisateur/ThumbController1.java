/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import gestion.utilisateur.entities.Panier;
import gestion.utilisateur.entities.Produit;
import gestion.utilisateur.servicem.PanierCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import toolsm.MyConnection;

/**
 * FXML Controller class
 *
 * @author Midou
 */
public class ThumbController1 implements Initializable {
private  Parent root ;
private Stage stage ;
private Scene scene;
    @FXML
    private Label nomp;
    @FXML
    private Label prixp;
    @FXML
    private Label qtep;
    @FXML
    private Label refp;
    @FXML
    private ImageView image;
    @FXML
    private Label id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 public void setData(Produit p){
   
        nomp.setText(p.getNom());
    prixp.setText(Integer.toString(p.getPrix()));

qtep.setText(Integer.toString(p.getQte()));
 refp.setText(p.getReference());

id.setVisible(false);
id.setText(Integer.toString(p.getId()));

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

    @FXML
    private void on_click_add_to_cart(ActionEvent event) throws IOException {
       
      //  System.out.println(a.getId());
  try {
      PanierCRUD service = new PanierCRUD();
       retrievedata a = retrievedata.getInstance("", "",0);
            String requete= "INSERT INTO panier(nomProduit,prix,idClient,idProduit)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, nomp.getText());
           
           
            pst.setInt(2,Integer.valueOf(prixp.getText()));
       
           pst.setInt(3, a.getId());
           pst.setInt(4,Integer.valueOf(id.getText()));
            pst.executeUpdate();
            System.out.println("Panier inserée");
            
             service.updateQuantité(Integer.valueOf(id.getText()));
             
             root = FXMLLoader.load(getClass().getResource("ListPanier.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    

    }

    
}

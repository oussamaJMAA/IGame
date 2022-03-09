/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Produit;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Midou
 */
public class ThumbController1 implements Initializable {

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

    } 
    
 public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit3a26", "root", "");
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

    
}

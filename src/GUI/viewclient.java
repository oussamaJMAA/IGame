/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Produit;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Midou
 */
public class viewclient implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private Button back;
private List<Produit> p;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int c = 0;
        int row =1;
        p= new ArrayList<>(tt());
       for(int i=0;i<p.size();i++){
       FXMLLoader fxml = new FXMLLoader();
       fxml.setLocation(getClass().getResource("thumbp.fxml"));
            try {
                VBox box = fxml.load();
            
            ThumbController1 tc = fxml.getController();
        
      tc.setData(p.get(i));
      if(c==3){
      c=0;
      ++row;
      
      }
      grid.add(box, c++, row);
      GridPane.setMargin(box,new Insets(10));
            } catch (IOException ex) {
                Logger.getLogger(viewclient .class.getName()).log(Level.SEVERE, null, ex);
            }
       
       }
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
    public List<Produit> tt() {
        ObservableList<Produit> produitList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select * from produit";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Produit p;
            while (rs.next()) {
                //int id, String nom, int prix,int qte, String reference,String image
                p = new Produit(rs.getInt("id"), rs.getString("nom"), rs.getInt("prix"), rs.getInt("qte"), rs.getString("reference"));
                produitList.add(p);

            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());

        }
        return produitList;
    }
    

    @FXML
    private void swtichTomenu(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Produit.fxml"));
           grid.getChildren().setAll(pane);
    }
    
}

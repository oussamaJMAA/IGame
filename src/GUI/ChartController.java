/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author Midou
 */
public class ChartController implements Initializable {

    @FXML
    private PieChart piechart;
    
    /* CONNEXION AU BD */
    
     static Connection cnx;
  
    private PreparedStatement pst = null ;
        static ResultSet rs;

        
     ObservableList<PieChart.Data> data = FXCollections.observableArrayList();       
    @FXML
    private Button b2;
    @FXML
    private AnchorPane recpane;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cnx = MaConnexion.getInstance().getCnx();
        stat();
    }

    private void stat() {

        try {

                       String query = "SELECT COUNT(*) ,produit.nom\n"
                    + "             	from produit join commande\n"
                    + "                on produit.id=commande.id_produit\n"
                    + "                GROUP BY produit.nom;";
                       
                       
            PreparedStatement PreparedStatement = cnx.prepareStatement(query);
            rs = PreparedStatement.executeQuery();

            while (rs.next()) {
                data.add(new PieChart.Data(rs.getString("produit.nom"), rs.getInt("COUNT(*)")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChartController.class.getName()).log(Level.SEVERE, null, ex);
        }

      piechart.setTitle("**Statistiques des commandes**");
        piechart.setLegendSide(Side.LEFT);
        piechart.setData(data);

    }

    @FXML
    private void back1(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/menu.fxml"));
           recpane.getChildren().setAll(pane); 
        
    }

    
}

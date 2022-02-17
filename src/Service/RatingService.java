/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Rating;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import Tools.MaConnexion;

public class RatingService {
    
     Connection cnx;
/**************************************************************/
/*********ajouter evaluation***************/     
    public RatingService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    public void AddRating(Rating r){
        String sql="INSERT INTO Rating(Rating_id,Rating_value) VALUES ( '"+r.getRating_id()+"','"+r.getRating_value()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("evaluation Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    /******************************************************/
    /************* afficher liste evaluationx **********************/
     public List<Rating> afficherRating(){
        List<Rating> Ratings = new ArrayList<>();
        String query="select * from Rating";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Rating r = new Rating();
                r.setRating_id(rs.getInt("Rating_id"));
                r.setRating_value(rs.getInt("Rating_value"));  
                 Ratings.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Ratings;
        
    }
    /***********************************************************/
     /*********** supprimer evaluation****************/
    /* public void DeletRating(int Rating_id) {
        try {
            String req = "DELETE FROM Rating where Rating_id=" + Rating_id ;
            Statement st =cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("evaluation supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     /********************************************/
     /********* modefier d'un evaluation*************/
     
    public void modifierRating(Rating r) {
        try {

             String req = "UPDATE Rating SET Rating_value ='" + r.getRating_value() + "' WHERE Rating_id=" + r.getRating_id();
                      
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Le evaluation est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }
    
}

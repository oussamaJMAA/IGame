/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Like;
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

public class LikeService {
    
     Connection cnx;
/**************************************************************/
/*********ajouter evaluation***************/     
    public LikeService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
 /*  public void AddRating(like r){
        String sql="INSERT INTO Rating(like_id,id_user,game_id) VALUES ()";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("j'aime Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }*/
   
    public void AddLike(Like r){
        String sql="INSERT INTO Raiting(like_id,id_user,game_id) VALUES(?,?,?)";
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);
             ste.setInt(1, r.getLike_id());
             ste.setInt(2, r.getId_user());
             ste.setInt(3, r.getGame_id());
            ste.executeUpdate();
              System.out.println("like Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /******************************************************/
    /************* afficher liste evaluation**********************/
    public List<Like> ReadL(){
        List<Like> Ratings = new ArrayList<>();
        String query="select * from Rating";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Like r = new Like();
                //r.setuser_id(rs.getInt());  
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
     
 /*  public void modifiyR(Rating r) {
        try {

             String req = "UPDATE rating SET rating_id ='"+r.getRating_id()+"',id_user='"+r.getId_user()+"',game_id='"+r.getGame_id()+"',rating_value='"+r.getRating_value()+"',rating_date='"+r.getRating_date()+"'WHERE Rating_id=" + r.getRating_id();

    
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Le evaluation est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }*/
    
}

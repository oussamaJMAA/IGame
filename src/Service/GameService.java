/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entities.Game;
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


public class GameService {
     Connection cnx;
/**************************************************************/
/*********ajouter jeu***************/     
    public GameService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    public void AddGame(Game g){
        String sql="INSERT INTO Game(game_id,game_name,game_description,game_link,game_vid,game_addDate) VALUES ( '"+g.getGame_id()+"','"+g.getGame_name()+"','"+g.getGame_description()+"','"+g.getGame_link()+"','"+g.getGame_vid()+"','"+g.getGame_addDate()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("jeu Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
  /*  public void ajouterGame2(Game g){
        String sql="insert into Game(game_id,game_name,game_decription,game_link,game_vid,game_addDate) values(?,?,?,?,?,?)";
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);
            ste.setString(1, g.getGame_name());
            ste.setString(2, g.getGame_vid());
            ste.setString(3, g.getGame_link());
            ste.setString(4, g.getGame_description());
            ste.setString(5, g.getGame_addDate());
            ste.executeUpdate();
            System.out.println("jeu Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
    /******************************************************/
    /************* afficher liste jeux **********************/
     public List<Game> afficherJeu(){
        List<Game> Games = new ArrayList<>();
        String query="select * from Game";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Game g = new Game();
                g.setGame_id(rs.getInt("game_id"));
                g.setGame_name(rs.getString("game_name"));
                g.setGame_description(rs.getString("game_description"));
                g.setGame_link(rs.getString("game_link"));
                g.setGame_vid(rs.getString("game_vid"));
               
           
                g.setGame_addDate(rs.getString("game_addDate"));
                Games.add(g);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Games;
        
    }
    /***********************************************************/
     /*********** supprimer jeu****************/
     public void DeletGame(int game_id) {
        try {
            String req = "DELETE FROM game where game_id=" + game_id ;
            Statement st =cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("jeu supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     /********************************************/
     /********* modefier d'un jeu*************/
     
    public void modifierJeu(Game g) {
        try {
            
        
           // String req = "UPDATE game SET game_name ='" + g.getGame_name()+"', game_description='"+g.getGame_description()+"', game_link='"+g.getGame_link()+"', game_vid='"+g.getGame_vid()+"',game_addDate='"g.getGame_addDate()+"' WHERE game_id=" + g.getGame_id();
             String req = "UPDATE game SET game_name ='" + g.getGame_name() +  "', game_description ='"+ g.getGame_description() +  "',game_link ='" + g.getGame_link() +  "',game_vid ='" + g.getGame_vid() +  "', game_addDate ='" + g.getGame_addDate() +  "' WHERE game_id=" + g.getGame_id();
                      //  String req = "UPDATE panier SET nomProduit ='" + p.getNomProduit() +  ,"',Prix='" + p.getPrix() +"',  Image='" + p.getImage() + "' WHERE idProduit=" + p.getIdProduit();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Le jeu est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }
}

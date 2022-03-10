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
import Tools.MaConnexion;

import java.sql.Date;

public class GameService {
     Connection cnx;
/**************************************************************/
/*********ajouter jeu***************/     
    public GameService() {
        cnx=MaConnexion.getInstance().getCnx();
    }

    public void AddGame(Game g){
        String sql="insert into Game(game_id,game_name,category_name,game_description,game_link,game_img) values(?,?,?,?,?)";
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);
            ste.setInt(1, g.getGame_id());
            ste.setString(2, g.getGame_name());
            ste.setString(3, g.getCategory_name());
             ste.setString(4, g.getgame_description());
            ste.setString(5, g.getGame_img());
            ste.setString(6, g.getGame_link());    
            ste.executeUpdate();
            System.out.println("jeu Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    /******************************************************/
    /************* read liste game **********************/
    public List<Game> ReadG(){
        List<Game> Games = new ArrayList<>();
        String query="select * from Game";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Game g = new Game();
                g.setGame_id(rs.getInt("game_id"));
                g.setGame_name(rs.getString("game_name"));
                g.setCategory_name(rs.getString("category_name"));
                g.setgame_description(rs.getString("game_description"));
                g.setGame_img(rs.getString("game_img"));
                g.setGame_link(rs.getString("game_link"));
               
           
              
                Games.add(g);
                System.out.print(g);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Games;
        
    }
    /***********************************************************/
     /*********** detele game****************/
     public void DeletG(int game_id) {
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
     /********* Modefiy Game*************/
     
    public void ModefiyG(Game g) {
        try {
            
        
             String req = "UPDATE game SET game_name ='" + g.getGame_name() +  "', game_description ='"+ g.getgame_description() +  "',game_link ='" + g.getGame_link() +  "',game_img ='" + g.getGame_img() +    "' WHERE game_id=" + g.getGame_id();
                     Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Le jeu est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }
    
    
    /************************************************************/
    /************recherche d'un jeu ***************************/
  /* public List<Game> search(String Rgame){
     //List<String> RGames = new ArrayList<>();
    ArrayList<Game> GameList = new ArrayList<>();

    try {
         String req="SELECT* FROM Game WHERE game_name LIKE'"+Rgame +"'";
         Statement st=cnx.createStatement();
         ResultSet rs=st.executeQuery(req);

            while(rs.next()){
             //RGames.add(rs.getInt("game_id"),rs.getString("game_name"));
            
               Game Rg = new Game(rs.getInt("game_id"),rs.getString("game_name"),rs.getString("category_name")
                       ,rs.getString("game_description"),rs.getString("game_vid"),rs.getString("game_link"));
            GameList.add(Rg);
                
            
            }
           
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}return GameList ;
   }*/
   /****************/
   /**************** tri selon date d'ajout **********************/
   
  /* public List<Game> triDate(){
     //List<String> RGames = new ArrayList<>();
    ArrayList<Game> GameList = new ArrayList<>();

    try {
         String req="SELECT * FROM Game ORDER BY game_addDate ASC;";
         Statement st=cnx.createStatement();
         ResultSet rs=st.executeQuery(req);

            while(rs.next()){
            
               Game TDg = new Game(rs.getInt("game_id"),rs.getString("game_name"),rs.getString("category_name")
                       ,rs.getString("game_description"),rs.getString("game_img"),rs.getString("game_link"),rs.getDate("game_addDate"));
            GameList.add(TDg);
            }
           
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}return GameList ;
   }*/
 /**************** tri selon evaluation ***********************/
   
   public List<Game> triRating(){
     
       ArrayList<Game> GameList = new ArrayList<>();

    try {
         String req="SELECT *, AVG(rating_value) as x FROM game g JOIN rating rate ON g.game_id=rate.game_id GROUP BY g.game_id";
         Statement st=cnx.createStatement();
         ResultSet rs=st.executeQuery(req);

            while(rs.next()){
            
    //  Game TDg = new Game(rs.getInt("game_id"),rs.getString("game_name"),rs.getInt("category_id"),rs.getString("game_description"),rs.getString("game_vid"),rs.getString("game_link"),rs.getDate("game_addDate"),rs.getInt("x"));
         //   GameList.add(TDg);
            }
           
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}return GameList ;
   }
}


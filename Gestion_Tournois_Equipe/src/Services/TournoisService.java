/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Tournois;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import tools.MaConnexion;

/**
 *
 * @author rouka
 */
public class TournoisService {
        Connection cnx;
    public TournoisService(){
    cnx = MaConnexion.getInstance().getCnx();
    }
    
        public void ajouterTournois(Tournois t){
    String sql = "insert into `tournois` (`nom_tournois`,`date_tournois`,`capacite`,`platforme`,`recompense`) values (?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1,t.getNom_tournois());
        ste.setDate(2,t.getDate());
        ste.setInt(3,t.getCapacite());
        ste.setString(4,t.getPlatforme());
      
        ste.setString(5,t.getRecompense());
        

        ste.executeUpdate();
        System.out.println("ajout effectuée !");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("ajout non effectué");
        }
    
    }
         public List<Tournois>afficherTournois(){
List<Tournois>tournois= new ArrayList<>();
String sql="select * from tournois";
try{
    PreparedStatement ste= cnx.prepareStatement(sql);
    ResultSet result=ste.executeQuery(sql);
 while(result.next()){
     Tournois t = new Tournois();
  
     t.setId(result.getInt("id"));
     //`equipe`,`recompense`
     t.setNom_tournois(result.getString("nom_tournois"));
     t.setPlatforme(result.getString("platforme"));
      t.setDate(result.getDate("date_tournois"));
       t.setCapacite(result.getInt("capacite"));
  t.setRecompense(result.getString("recompense"));
         tournois.add(t);
}}
catch( SQLException ex){
            System.out.println(ex.getMessage());
 }
 return tournois;

      }
         
          public void ModifierTournois(int id,String nom_tournois,int capacite,String platforme,String recompense,java.sql.Date date){
             
    String sql = "update `tournois` set nom_tournois=?, capacite=?, platforme=?,recompense=?, date_tournois=? where id=?  ";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1,nom_tournois);
        ste.setInt(2,capacite);
        ste.setString(3,platforme);
       
        ste.setString(4,recompense);
        ste.setDate(5, date);
        ste.setInt(6, id);

        ste.executeUpdate();
        System.out.println("modification effectuée !");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("modification non effectué");
        }
    
    }
        public void SupprimerTournois(int id){
             
    String sql = "delete from `tournois` where id=?  ";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
   
        ste.setInt(1, id);

        ste.executeUpdate();
        System.out.println("Suppression effectuée !");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Suppression non effectué");
        }
    
    }    
          
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Equipes;
import Entities.Tournois;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;

/**
 *
 * @author rouka
 */
public class EquipeService {
       Connection cnx;
    public EquipeService(){
    cnx = MaConnexion.getInstance().getCnx();
    }
    
        public void ajouterEquipe(Equipes e){
    String sql = "insert into `equipe` (`nom_equipe`,`membres`,`pts_exp`,`tournois_gagne`) values (?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1,e.getNom_equipe());
        ste.setInt(2,e.getMembres());
        ste.setInt(3,e.getPts_xp());
        ste.setInt(4,e.getTournois_gagne());
 
         
        
      ste.executeUpdate();
        System.out.println("ajout effectuée !");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("ajout non effectué");
        }
    
    }
         public List<Equipes>afficherEquipe(){
List<Equipes>users= new ArrayList<>();
String sql="select * from equipe";
try{
    PreparedStatement ste= cnx.prepareStatement(sql);
    ResultSet result=ste.executeQuery(sql);
    while(result.next()){
     Equipes u = new Equipes();
     u.setId(result.getInt("id"));
     //`equipe`,`recompense`
     u.setNom_equipe(result.getString("nom_equipe"));
   u.setMembres(result.getInt("membres"));
   u.setPts_xp(result.getInt("pts_exp"));
   u.setTournois_gagne(result.getInt("tournois_gagne"));
 users.add(u);
    }
    
}
catch( SQLException ex){
            System.out.println(ex.getMessage());
            
        }
 return users;

      }
         
          public void ModifierEquipe(int id,String nom_equipe,int membres,int pts_xp,int tournois_gagne){
             
    String sql = "update `equipe` set nom_equipe=?, membres=?, pts_exp=?, tournois_gagne=? where id=?  ";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1,nom_equipe);
        ste.setInt(2,membres);
        ste.setInt(3,pts_xp);
        ste.setInt(4,tournois_gagne);
        ste.setInt(5, id);

        ste.executeUpdate();
        System.out.println("modification effectuée !");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("modification non effectué");
        }
    
    }
        public void SupprimerEquipe(int id){
             
    String sql = "delete from `equipe` where id=?  ";
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
     
          public void participation(int id_e,int id_t){
             
    String sql1 = "insert into participation (id_equipe, id_tournois) values(?,?) ";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql1);
      
        ste.setInt(1, id_e);
ste.setInt(2, id_t);
        ste.executeUpdate();
        System.out.println("Participation effectué !");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Participation non effectué !!!!");
            
        }
  }
   
          
           public void supp_part(int id_e){
             
    String sql1 = "delete from participation where id_equipe=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql1);
      
        ste.setInt(1, id_e);

        ste.executeUpdate();
        System.out.println(" Annulation Participation effectué !");
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println(" Annulation Participation non effectué !!!!");
            
        }
  }
          
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur.service;

import gestion.utilisateur.entities.Produit;
import gestion.utilisateur.entities.Promotion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import toolsp.MaConnexion;

/**
 *
 * @author Fayechi
 */
public class PromotionServices {
    Connection cnx;

    public PromotionServices() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    public void ajouterPromotion(Promotion r){
        String sql="INSERT INTO `Promotion`( `nom`, `date`,`prix_pro`,`datef`) VALUES ('"+r.getNom()+"','"+r.getDate()+"','"+r.getPrixPro()+"','"+r.getDatef()+"')";
        try {
            Statement ste= cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("promotion Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }
    public int supprimerproduit(int id)throws SQLException {
 
            
     int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "DELETE FROM `promotion` WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return i;
    }
    public void ajouterPromotion2(Promotion r){
        String sql="insert into promotion(nom,date,prix_pro,datef) values(?,?,?,?)";
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);
            ste.setString(1, r.getNom());
            ste.setString(2, r.getDate());
            ste.setInt(3, r.getPrixPro());
             ste.setString(4, r.getDatef());
            ste.executeUpdate();
            System.out.println("promotion Ajout??e");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
    public List<Promotion> afficherPromotion(){
        List<Promotion> promotions = new ArrayList<>();
        String query="select * from promotion";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Promotion r = new Promotion();
                r.setId(rs.getInt("id"));
                r.setNom(rs.getString("nom"));
                r.setDate(rs.getString("date"));
                r.setPrixPro(rs.getInt("prixPro"));
                 r.setDatef(rs.getString("datef"));
                promotions.add(r);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return promotions;
        
    }
    public void supprimerpromotion(int id) {
 try {
            String sql = "DELETE FROM promotion WHERE id="+id+"";
            PreparedStatement ste  = cnx.prepareStatement(sql);
           
            ste.executeUpdate();
            System.out.println("promotion Supprim??e ");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }
public void modifierPromotion(Promotion r) {
      try {
            String sql = "UPDATE promotion SET nom=?, date=? ,prix_pro=?,datef=?  WHERE `id`=?";
            PreparedStatement pre = cnx.prepareStatement(sql);
            pre.setString(1, r.getNom());
            pre.setString(2, r.getDate());
            pre.setInt(3, r.getPrixPro());
          pre.setString(5, r.getDatef());
            pre.setInt(4, r.getId());
             
            pre.executeUpdate();
            System.out.println("promotion Modfi?? !");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }   
}
}

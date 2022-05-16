/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion.utilisateur.service;

import gestion.utilisateur.entities.Produit;
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

public class ProduitServices {
     
     Connection cnx;
    private Object ste;
   
    /**
     *
     */
    public ProduitServices(){
        cnx=MaConnexion.getInstance().getCnx();
    }
    public void ajouterProduit(Produit p){
        String sql="INSERT INTO `Produit`(`id`, `nom`, `prix`,`quantite`,`reduction`,`image`) VALUES ('"+p.getId()+"','"+p.getNom()+"','"+p.getPrix()+"','"+p.getQte()+"','"+p.getReduction()+"','"+p.getImage()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Produit Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    /**
     *
     * @return
     */
}
    public List<Produit> afficherProduit() {
        List<Produit> produits = new ArrayList<>();
        String query="select * from produit";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Produit p = new Produit();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrix(rs.getInt("prix"));
                p.setQte(rs.getInt("quantite"));
                // p.setReference(rs.getString("reference"));
                 p.setReduction(rs.getInt("reduction"));
                 p.setImage(rs.getString("image"));
                produits.add(p);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return produits;
        
    }

    

public int supprimerproduit(int id)throws SQLException {
 
            
     int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "DELETE FROM `produit` WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProduitServices.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return i;
    }

 public void modifier(Produit p) {
      try {
            String sql = "UPDATE produit SET nom=?, prix=? ,qte=?,reduction=? ,image=? WHERE `id`=?";
            PreparedStatement pre = cnx.prepareStatement(sql);
            pre.setString(1, p.getNom());
            pre.setInt(2, p.getPrix());
            pre.setInt(3, p.getQte());
         pre.setInt(5, p.getReduction());
          pre.setString(6, p.getImage());
            pre.setInt(4, p.getId());
             
            pre.executeUpdate();
            System.out.println("produit Modfié !");
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }   
}
}
   
   
 
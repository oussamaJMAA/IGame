/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Commande;
import entities.Panier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tools.MyConnection;

/**
 *
 * @author Magui
 */
public class PanierCRUD {
        public void ajouterPanier(Panier p){
        try {
            String requete= "INSERT INTO panier(nomProduit,prix,image,idProduit)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, p.getNomProduit());
            pst.setLong(4,p.getIdProduit());
           
            pst.setInt(2,p.getPrix());
           pst.setString(3,p.getImage());
            pst.executeUpdate();
            System.out.println("Panier inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void suppPanier(Panier p){
         try {
            String requete = "DELETE FROM panier where idP=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setLong(1, p.getIdP());
            pst.executeUpdate();
            System.out.println("Panier supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void updatePanier(Panier p){
         try {
            String requete = "UPDATE panier SET idProduit=?,nomProduit=?,prix=?,image=?"
                    + " WHERE idP=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            
              pst.setString(2, p.getNomProduit());
            pst.setLong(1,p.getIdProduit());
           
            pst.setInt(3,p.getPrix());
           pst.setString(4,p.getImage());
           pst.setLong(5,p.getIdP());
            pst.executeUpdate();
            System.out.println("Panier modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public List<Panier> displayPanier() {
         List<Panier> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM panier";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Panier c = new Panier();
                c.setIdP(rs.getLong("idP"));
                c.setIdProduit(rs.getLong("idProduit"));
                c.setNomProduit(rs.getString("nomProduit"));
                c.setPrix(rs.getInt("prix"));
                c.setImage(rs.getString("image"));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
}
   

}

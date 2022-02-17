
package services;

import entities.Commande;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

 public class ServiceCommande implements ICsevice<Commande>{
private Connection cnx ;
 
public ServiceCommande() {
    cnx = MyDB.getInstance().getCnx();
}

  /* 
public void supprimer(int id_cmd) {
        try {
            String req = "DELETE FROM commande where id_cmd=" + id_cmd ;
            Statement st =cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("commande supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
} 
*/

/* @Override
    public void ajouter(Commande c ) {
        try {
             String querry="INSERT INTO commande ( idProduit, idClient,  id_cmd,  date,  modePaiment, livraison ) VALUES ('"+c.getIdProduit()+"','"+c.getIdClient()+"','"+c.getId_cmd()+"','"+c.getDate()+"','"+c.getModePaiment()+"','"+c.getLivraison()+"')"; 
             
            Statement stm =cnx.createStatement();
        
        stm.executeUpdate(querry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
*/
/*  
@Override
    public List<Commande> afficherCmd() {
 List<Commande> commandes = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM panier";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Commande cmd = new Commande();
            cmd.setId_cmd(rs.getInt(1));
            cmd.setIdProduit(rs.getInt(2));
            cmd.setIdClient(rs.getInt(3));
            cmd.setDate(rs.getDate(4));
            cmd.setModePaiment(rs.getString(5));
            cmd.setLivraison(rs.getString(6));
            
            
            
            commandes.add(cmd);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
        return panies;
   
    } 

  */

    @Override
    public List<Commande> afficherCmd() {

   List<Commande> commandes = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM commande";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Commande cmd = new Commande();
            cmd.setId_cmd(rs.getInt(1));
            cmd.setIdProduit(rs.getInt(2));
            cmd.setIdClient(rs.getInt(3));
            cmd.setDate(rs.getDate(4));
            cmd.setModePaiment(rs.getString(5));
            cmd.setLivraison(rs.getString(6));
            
            
            
            commandes.add(cmd);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
        return commandes;
   
    } 

    @Override
    public void supprimerCmd(Commande c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void supprimerCmdd(int id_cmd ) {
      try {
            String req = "DELETE FROM commande where id_cmd=" + id_cmd ;
            Statement st =cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("commande supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

   

} 
 

/**
 *
 * @author pc hp core i3
 */


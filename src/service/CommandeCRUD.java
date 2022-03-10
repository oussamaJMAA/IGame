/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Commande;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MyConnection;

/**
 *
 * @author Magui
 */
public class CommandeCRUD {
    
    
    private static final String seasons[] = {
  "Winter", "Winter", "Spring", "Spring", "Summer", "Summer", 
  "Summer", "Summer", "Fall", "Fall", "Winter", "Winter"
};
public String getSeason( Date date ) {
   return seasons[ date.getMonth() ];
}
     
    public void ajouterCommande(Commande c){
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
         Random rand = new Random();
        int n = rand.nextInt(50);
        n += 1;
        try {
            String requete= "INSERT INTO commande(idProduit,idClient,date,modePaiment,livraison,etat,id_cmd,idPanier)"
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setLong(1, c.getIdProduit());
            pst.setLong(2,c.getIdClient());
            pst.setDate(3,date);
            pst.setString(4,c.getModePaiment());
            pst.setString(5,c.getLivraison());
            pst.setString(6, "en attend");
            pst.setInt(7, n);
            pst.setLong(8, c.getIdPanier());
           
            pst.executeUpdate();
            System.out.println("Commande inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void suppCommande(Commande c){
         try {
            String requete = "DELETE FROM commande where id_cmd=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setLong(1, c.getId_cmd());
            pst.executeUpdate();
            System.out.println("Commande supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void updateCommande(Commande c){
         try {
            String requete = "UPDATE commande SET idProduit=?,idClient=?,date=?,modePaiment=?,livraison=?"
                    + " WHERE id_cmd=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setDate(3, c.getDate());
            pst.setLong(2, c.getIdClient());
            pst.setLong(1, c.getIdProduit());
            pst.setString(4, c.getModePaiment());
            pst.setString(5, c.getLivraison());
            pst.setLong(6, c.getId_cmd());

            pst.executeUpdate();
            System.out.println("Commande modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Commande> displayCommmande() {
         List<Commande> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM commande";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Commande c = new Commande();
                c.setId_cmd(rs.getLong("id_cmd"));
                c.setIdClient(rs.getLong("idClient"));
                c.setIdProduit(rs.getLong("idProduit"));
                c.setDate(rs.getDate("date"));
                c.setLivraison(rs.getString("livraison"));
                c.setModePaiment(rs.getString("modePaiment"));
                 c.setEtat(rs.getString("etat"));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
}
    public List<Commande> getCommandeByClient(int idClient) {
         List<Commande> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM commande where idClient="+idClient;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Commande c = new Commande();
                c.setId_cmd(rs.getLong("id_cmd"));
                c.setIdClient(rs.getLong("idClient"));
                c.setIdProduit(rs.getLong("idProduit"));
                c.setDate(rs.getDate("date"));
                c.setLivraison(rs.getString("livraison"));
                c.setModePaiment(rs.getString("modePaiment"));
                c.setEtat(rs.getString("etat"));
                
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
}
    
    
 /*   public List<Actualite> getActualiteParDate(Date date)throws SQLException{
                List<Actualite> list= new ArrayList<>();

       
       Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
        String query="select * from actualite where date_ajout="+"'"+date+"'";
        ResultSet rs=st.executeQuery(query);
        while(rs.next())
        {
            Actualite a=new Actualite(rs.getInt("id"),rs.getDate("date_ajout"));
            list.add(a);
        }
        return list;
    }
   */
    
   
                
             
        
    
       public void setEtat(Commande c,String etat) throws SQLException {
       
            String requete = "UPDATE commande SET etat=?"
                    + " WHERE id_cmd=?";
            
       
                PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, etat);
            pst.setLong(2, c.getId_cmd());

            pst.executeUpdate();
          //  System.out.println("Commande modifiée");
     
        
            
       }
                 
             
       }       

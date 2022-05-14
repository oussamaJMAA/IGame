/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur.servicem;

import gestion.utilisateur.entities.Commande;
import gestion.utilisateur.retrievedata;
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
import toolsm.MyConnection;

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
        
         retrievedata a = retrievedata.getInstance("", "",0);
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
         Random rand = new Random();
        int n = rand.nextInt(50);
        n += 1;
        try {
            String requete= "INSERT INTO commande(user_id,date,methodedepaiement,etat,id)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
         
            pst.setLong(1,a.getId());
            pst.setDate(2,date);
            pst.setString(3,c.getModePaiment());
     
            pst.setString(4, "en attend");
            pst.setInt(5, n);
        
            pst.executeUpdate();
            System.out.println("Commande inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void suppCommande(Commande c){
         try {
            String requete = "DELETE FROM commande where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setLong(1, c.getId_cmd());
            pst.executeUpdate();
            System.out.println("Commande supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void updateCommande2(String m,int id){
        
         try {
            String requete = "UPDATE commande SET methodedepaiement=?"
                    + " WHERE user_id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
      
            pst.setLong(2, id);
         
            pst.setString(1,m);
          
        

            pst.executeUpdate();
            System.out.println("Commande modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void updateCommande(Commande c){
         retrievedata a = retrievedata.getInstance("", "",0);
         try {
            String requete = "UPDATE commande SET methodedepaiement=?"
                    + " WHERE user_id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
      
            pst.setLong(2, a.getId());
         
            pst.setString(1, c.getModePaiment());
          
        

            pst.executeUpdate();
            System.out.println("Commande modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public void confirmer(int c,int p){
         retrievedata a = retrievedata.getInstance("", "",0);
         try {
            String requete = "insert into commande_panier(commande_id,produit_id) values(?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
         
            pst.setInt(1, c);
         pst.setInt(2,p);

            pst.executeUpdate();
            System.out.println("Commande Confirmer");
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
                //(idProduit,user_id,date,modePaiment,etat,id)
                Commande c = new Commande();
                c.setId_cmd(rs.getInt("id"));
                c.setIdClient(rs.getInt("user_id"));
              //  c.setIdProduit(rs.getInt("idProduit"));
                c.setDate(rs.getDate("date"));
              c.setNbproduit(rs.getInt("nbproduit"));
                c.setModePaiment(rs.getString("methodedepaiement"));
                 c.setEtat(rs.getString("etat"));
                 c.setPrix_tot(rs.getDouble("prixtotale"));
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
            String requete = "SELECT * FROM commande where user_id="+idClient;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Commande c = new Commande();
                c.setId_cmd(rs.getInt("id"));
                c.setIdClient(rs.getInt("user_id"));
            
                c.setDate(rs.getDate("date"));
               c.setNbproduit(rs.getInt("nbproduit"));
                c.setModePaiment(rs.getString("methodedepaiement"));
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
                    + " WHERE id=?";
            
       
                PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, etat);
            pst.setInt(2, c.getId_cmd());

            pst.executeUpdate();
          //  System.out.println("Commande modifiée");
     
        
            
       }
                 
             
       }       

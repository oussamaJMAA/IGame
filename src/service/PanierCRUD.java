/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Commande;
import entities.Panier;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    
      private static final String seasons[] = {
  "Winter", "Winter", "Spring", "Spring", "Summer", "Summer", 
  "Summer", "Summer", "Fall", "Fall", "Winter", "Winter"
};
public String getSeason( java.util.Date date ) {
   return seasons[ date.getMonth() ];
}
    
    
    

        public void ajouterPanier(Panier p){
        try {
            String requete= "INSERT INTO panier(nomProduit,prix,image,idProduit)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, p.getNomProduit());
           
           
            pst.setInt(2,p.getPrix());
           pst.setString(3,"");
            pst.setLong(4,p.getIdProduit());
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
    
    public Integer totalePanier(int idClient) throws SQLException{
         List<Panier> list = new ArrayList<>();
         int prixtotale=0;
       
            String requete = "SELECT * FROM panier where idClient="+idClient;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
              while(rs.next()){
                  prixtotale +=rs.getInt("prix");
        
           }
             return prixtotale; 
      
    }
 

     public float réductionParSaison(int  totalPrix){
          SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
          java.util.Date d=new java.util.Date();
    
       float f[];
       float prix = totalPrix;
		// MEMORY ALLOCATION FOR JAVA FLOAT ARRAY
		f = new float[4];
		// ASSIGNING ELEMENTS TO JAVA FLOAT ARRAY
		f[0] = 0.2f;
		f[1] = 0.15f;
		f[2] = 0.1f;
		f[3] = 0.05f;
                
                if(getSeason(d).equals("summer")){
                    prix=prix*f[0];
                    
                }
                else if(getSeason(d).equals("Spring")){
                    prix=prix*f[1];
                    
                }else if(getSeason(d).equals("Winter")){
                    prix=prix*f[2];
                    
                }else if(getSeason(d).equals("Fall")){
                    prix=prix*f[3];
                    
                }
                return prix;
     }
   
     public Integer countProduitenPanier(int idProduit) throws SQLException{
         List<Panier> list = new ArrayList<>();
         int sum=0;
          String requete = "SELECT * FROM panier where idProduit="+idProduit;
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
            sum=list.size();
     
     return sum;    
     }
     
    public Integer getQuantiteParProduit(int idProduit) throws SQLException{
        int capacite=0;
    //    SELECT COUNT(*) from panier where idProduit=11
     String requete = "SELECT * FROM produit where id="+idProduit;
     Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
              while(rs.next()){
               capacite= rs.getInt("qte");
        
           }
          return capacite;      
    }
    
    
    
    public void updateQuantité(int idProduit) throws SQLException{
        int qte=getQuantiteParProduit(idProduit);
        int count = countProduitenPanier(idProduit);
        int newqte=qte - count ;
        
        try {
            String requete = "UPDATE produit SET qte=?"
                    + " WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            
             
           
            pst.setInt(1,newqte);
         
           pst.setLong(2,idProduit);
            pst.executeUpdate();
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

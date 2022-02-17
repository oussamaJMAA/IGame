package services;
import entities.Panier;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

 public class ServicePanier implements IService<Panier>{
private Connection cnx ;
 
public ServicePanier() {
    cnx = MyDB.getInstance().getCnx();
}

   
public void supprimer(int idProduit) {
        try {
            String req = "DELETE FROM panier where idProduit=" + idProduit ;
            Statement st =cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("produit supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        /*
        public void modifier(Panier p) {
        try {
            String req = "UPDATE panier SET nomProduit panier='" + p.getIdProduit() +"', Nom produit='" + p.getNomProduit() + "', Prix='" + p.getPrix() + "', Image='" + p.getImage() + "' WHERE idProduit=" + p.getIdProduit();
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("La panier  est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
*/
 
        
          @Override
    public void ajouter(Panier p) {
        try {
             String querry="INSERT INTO panier( nomProduit,image,prix ) VALUES ('"+p.getNomProduit()+"','"+p.getImage()+"','"+p.getPrix()+"')"; 
             
            Statement stm =cnx.createStatement();
        
        stm.executeUpdate(querry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
        
    
    } 

    @Override
    public List<Panier> afficher() {
 List<Panier> panies = new ArrayList();
    
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM panier";
     
        ResultSet rs= stm.executeQuery(querry);
        
        while(rs.next()){
            Panier p = new Panier();
            p.setIdProduit(rs.getInt(1));
            p.setNomProduit(rs.getString(2));
            p.setImage(rs.getString(3));
            p.setPrix(rs.getString(4));
            
            
            panies.add(p);
        }
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
    
    }
   
        return panies;
   
    } 

    @Override
    public boolean supprimer(Panier p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Panier p) {
        try {
            String req = "UPDATE panier SET nomProduit ='" + p.getNomProduit() +  "',Prix='" + p.getPrix() +"',  Image='" + p.getImage() + "' WHERE idProduit=" + p.getIdProduit();
            
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("La panier  est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
} 
    

   


        
        
    

 
   
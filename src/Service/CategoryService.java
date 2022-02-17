/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Tools.MaConnexion;

public class CategoryService {
    
      Connection cnx;
/**************************************************************/
/*********ajouter categorie***************/     
    public CategoryService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    public void AddCategory(Category c){
        String sql="INSERT INTO category(category_id,category_name,category_imgUrl,category_addDate) VALUES ( '"+c.getCategory_id()+"','"+c.getCategory_name()+"','"+c.getCategory_imgUrl()+"','"+c.getCategory_addDate()+"')";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("categorie Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }

    /******************************************************/
    /************* afficher liste categoriex **********************/
    public List<Category> afficher(){
        List<Category> Categorys = new ArrayList<>();
        String query="select * from Category";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Category c = new Category();
                c.setCategory_id(rs.getInt("category_id"));
                c.setCategory_name(rs.getString("category_name"));
                c.setCategory_imgUrl(rs.getString("category_imgUrl"));
                c.setCategory_addDate(rs.getString("category_addDate"));
                Categorys.add(c);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Categorys;
        
    }
    /***********************************************************/
     /*********** supprimer categorie****************/
     public void DeletCategory(int Category_id) {
        try {
            String req = "DELETE FROM Category where Category_id=" + Category_id ;
            Statement st =cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("categorie supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     /********************************************/
     /********* modefier d'un categorie*************/
     
   public void modifiercategorie(Category c) {
        try {
            
        
             String req = "UPDATE Category SET Category_name ='" + c.getCategory_name() +  "',category_imgUrl ='" + c.getCategory_imgUrl() + "', category_addDate ='" + c.getCategory_addDate() +  "' WHERE category_id=" + c.getCategory_id();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Le categorie est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

    
}

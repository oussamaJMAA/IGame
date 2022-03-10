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

import java.sql.Date;

public class CategoryService {
    
      Connection cnx;
/**************************************************************/
/*********************ajouter categorie***********************/     
    public CategoryService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
  /* public void AddCategory(Category c){
      String sql="INSERT INTO category(category_name,category_addDate) VALUES ('"+c.getCategory_name()+"','"+c.getCategory_addDate()+"')";
           
      try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("categorie Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }*/
    public void AddCategory(Category c){
        String sql="insert into category(category_name , discription ) values('"+c.getCategory_name()+"','"+c.getDiscription()+"')";
        try {
             Statement stm =cnx.createStatement();
        
        stm.executeUpdate(sql);
              System.out.println("categorie Ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
         
        
        
    }
  
 /****************************************************/
    /************* afficher liste categoriex **********************/
   public List<Category> ReadC(){
        List<Category> Categorys = new ArrayList<>();
        String query="select * from Category";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Category c = new Category();
                c.setCategory_name(rs.getString("category_name"));
              //  c.setCategory_addDate(rs.getDate("category_addDate"));
                Categorys.add(c);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Categorys;
        
    }
    /***********************************************************/
     /*********** delete category****************/
     public void DeletC(int Category_name) {
        try {
            String req = "DELETE FROM Category where Category_name=" + Category_name ;
            Statement st =cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("categorie supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     /********************************************/
     /********* modify category*************/
     
  public void ModifyC(Category c) {
        try {
            
        
             String req = "UPDATE Category SET Category_name ='" + c.getCategory_name() +   "' WHERE category_name=" + c.getCategory_name();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Le categorie est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }
    
}

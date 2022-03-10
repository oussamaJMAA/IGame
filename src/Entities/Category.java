/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
/**
 *
 * @author Malak
 */
public class Category {
    private String category_name;
        private String discription;


    public Category(){
    }

  
    public Category(String category_name,String discription) {
        this.category_name = category_name;
         this.discription = discription;

    }

   
   

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
    @Override
    public String toString() {
        return "Category{" + "category_name=" + category_name + ", discription=" + discription + '}';
    }

    

    



   
    
    
    
}

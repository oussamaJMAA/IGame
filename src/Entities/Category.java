/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Malak
 */
public class Category {
    private int category_id;
    private String category_name,category_addDate,category_imgUrl;
    
    public Category(){
    }

    public Category(int category_id, String category_name, String category_addDate, String category_imgUrl) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_imgUrl = category_imgUrl;
        this.category_addDate = category_addDate;
        
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_addDate() {
        return category_addDate;
    }

    public void setCategory_addDate(String category_addDate) {
        this.category_addDate = category_addDate;
    }

    public String getCategory_imgUrl() {
        return category_imgUrl;
    }

    public void setCategory_imgUrl(String category_imgUrl) {
        this.category_imgUrl = category_imgUrl;
    }

    @Override
    public String toString() {
        return "Category{" + "category_id=" + category_id + ", category_name=" + category_name + ", category_imgUrl=" + category_imgUrl + ", category_addDate=" + category_addDate + '}';
    }
    
    
}

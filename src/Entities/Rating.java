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
public class Rating {
    
     private int rating_id,rating_value;

     public Rating(){}
    public Rating(int rating_id, int rating_value) {
        this.rating_id = rating_id;
        this.rating_value = rating_value;
    }

    public int getRating_id() {
        return rating_id;
    }

    public void setRating_id(int rating_id) {
        this.rating_id = rating_id;
    }

    public int getRating_value() {
        return rating_value;
    }

    public void setRating_value(int rating_value) {
        this.rating_value = rating_value;
    }

    @Override
    public String toString() {
        return "Rating{" + "rating_id=" + rating_id + ", rating_value=" + rating_value + '}';
    }
     
     
}

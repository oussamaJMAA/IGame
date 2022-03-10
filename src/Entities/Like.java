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
public class Like {
    
     private int like_id,id_user, game_id;
     
    
     public Like(){}

    public Like(int like_id, int id_user, int game_id ) {
        this.like_id = like_id;
        this.id_user = id_user;
        this.game_id = game_id;
    }

    public int getLike_id() {
        return like_id;
    }

    public void setRating_id(int like_id) {
        this.like_id = like_id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }


    

     
    

 
     
     
}

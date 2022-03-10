/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
//import java.time.LocalDateTime; 
//import java.time.format.DateTimeFormatter;
import java.sql.Date;
//import java.sql.Time;
/**
 *
 */
public class Game {
    private int game_id;
    private String game_name , game_img , game_link ,game_description,category_name;
    
    
    public Game(){
    }
    
  public Game(int game_id,String game_name,String category_name,String game_description,String game_img ,String game_link ){
    this.game_id = game_id ;
    this.game_name = game_name ;
    this.category_name = category_name ;
    this.game_description = game_description ;
    this.game_link = game_link ;
    this.game_img = game_img ;
   }
    

    
    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

     
    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_img() {
        return game_img;
    }

    public void setGame_img(String game_img) {
        this.game_img = game_img;
    }

    public String getGame_link() {
        return game_link;
    }

    public void setGame_link(String game_link) {
        this.game_link = game_link;
    }

    public String getgame_description() {
        return game_description;
    }

    public void setgame_description(String game_discreption) {
        this.game_description = game_discreption;
    }

    

    @Override
    public String toString() {
        return "Game{" + "game_name=" + game_name + "category_name=" + category_name+ ", game_img=" + game_img + ", game_link=" + game_link + ", game_discreption=" + game_description +  '}';
    }

    public void setCategory_addDate(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     

  
   
    
}

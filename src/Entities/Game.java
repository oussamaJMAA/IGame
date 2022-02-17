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
public class Game {
    private int game_id;
    private String game_name , game_vid , game_link , game_discreption ,game_addDate  ;
    
    public Game(){
    }
    
    public Game(int game_id,String game_name,String game_discreption,String game_vid ,String game_link , String game_addDate){
    this.game_id = game_id ;
    this.game_name = game_name ;
    this.game_discreption = game_discreption ;
    this.game_link = game_link ;
    this.game_vid = game_vid ;
    this.game_addDate = game_addDate ;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getGame_name() {
        return game_name;
    }

    public Game(String game_name, String game_vid, String game_link, String game_discreption, String game_addDate) {
        this.game_name = game_name;
        this.game_vid = game_vid;
        this.game_link = game_link;
        this.game_discreption = game_discreption;
        this.game_addDate = game_addDate;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_vid() {
        return game_vid;
    }

    public void setGame_vid(String game_vid) {
        this.game_vid = game_vid;
    }

    public String getGame_link() {
        return game_link;
    }

    public void setGame_link(String game_link) {
        this.game_link = game_link;
    }

    public String getGame_description() {
        return game_discreption;
    }

    public void setGame_description(String game_discreption) {
        this.game_discreption = game_discreption;
    }

    public String getGame_addDate() {
        return game_addDate;
    }

    public void setGame_addDate(String game_addDate) {
        this.game_addDate = game_addDate;
    }
     @Override
     public String toString() {
        return "Game{" + "game id=" + game_id +", game name = "+game_name +",game vidlink = "+game_vid  +",game link = "+game_link +",game description="+game_discreption +",game addDate ="+game_addDate +'}';
        
    }
    
    
}

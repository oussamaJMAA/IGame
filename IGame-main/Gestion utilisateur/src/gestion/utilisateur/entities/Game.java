/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion.utilisateur.entities;

/**
 *
 * @author rouka
 */
public class Game {
    int id,id_cat,nbr_vu,rating;
    String game_name,game_description,game_link,game_img;

    public Game(int id, int id_cat, int nbr_vu, int rating, String game_name, String game_description, String game_link, String game_img) {
        this.id = id;
        this.id_cat = id_cat;
        this.nbr_vu = nbr_vu;
        this.rating = rating;
        this.game_name = game_name;
        this.game_description = game_description;
        this.game_link = game_link;
        this.game_img = game_img;
    }

    public Game(int id, int id_cat, String game_name, String game_description, String game_link, String game_img) {
        this.id = id;
        this.id_cat = id_cat;
        this.game_name = game_name;
        this.game_description = game_description;
        this.game_link = game_link;
        this.game_img = game_img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public int getNbr_vu() {
        return nbr_vu;
    }

    public void setNbr_vu(int nbr_vu) {
        this.nbr_vu = nbr_vu;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public String getGame_description() {
        return game_description;
    }

    public void setGame_description(String game_description) {
        this.game_description = game_description;
    }

    public String getGame_link() {
        return game_link;
    }

    public void setGame_link(String game_link) {
        this.game_link = game_link;
    }

    public String getGame_img() {
        return game_img;
    }

    public void setGame_img(String game_img) {
        this.game_img = game_img;
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", id_cat=" + id_cat + ", nbr_vu=" + nbr_vu + ", rating=" + rating + ", game_name=" + game_name + ", game_description=" + game_description + ", game_link=" + game_link + ", game_img=" + game_img + '}';
    }
    
}

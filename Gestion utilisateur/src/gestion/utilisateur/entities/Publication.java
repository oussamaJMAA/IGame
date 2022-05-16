/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion.utilisateur.entities;

import java.sql.Date;

/**
 *
 * @author rouka
 */
public class Publication {
    int id;
    String titre,contenu,image;
    java.sql.Date created_at;

    public Publication(int id, String titre, String contenu, String image, Date created_at) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.image = image;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", titre=" + titre + ", contenu=" + contenu + ", image=" + image + ", created_at=" + created_at + '}';
    }
    
    
}

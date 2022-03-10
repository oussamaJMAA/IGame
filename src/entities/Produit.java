/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author pc hp core i3
 */
public class Produit {
    private int id;
    private int prix;
    private int qte;
    private String nom;

    public Produit(int id, int prix, int qte, String nom) {
        this.id = id;
        this.prix = prix;
        this.qte = qte;
        this.nom = nom;
    }

    public Produit(int prix, int qte, String nom) {
        this.prix = prix;
        this.qte = qte;
        this.nom = nom;
    }

    public Produit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return  nom ;
    }
    
}

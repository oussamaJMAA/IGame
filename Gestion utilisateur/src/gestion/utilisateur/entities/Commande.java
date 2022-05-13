/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur.entities;

import java.sql.Date;

/**
 *
 * @author Magui
 */
public class Commande {

    private int id_cmd;
    private int idProduit;
    private java.sql.Date date;
    private String modePaiment;
    private String livraison;
    private int idPanier;
    private int idClient;
    private String etat;
private int nbproduit;
private double prix_tot;

    public double getPrix_tot() {
        return prix_tot;
    }

    public void setPrix_tot(double prix_tot) {
        this.prix_tot = prix_tot;
    }

    public Commande(int idProduit, Date date, String modePaiment, int idPanier, int idClient, String etat, int nbproduit, double prix_tot) {
        this.idProduit = idProduit;
        this.date = date;
        this.modePaiment = modePaiment;
        this.idPanier = idPanier;
        this.idClient = idClient;
        this.etat = etat;
        this.nbproduit = nbproduit;
        this.prix_tot = prix_tot;
    }


    public int getNbproduit() {
        return nbproduit;
    }

    public void setNbproduit(int nbproduit) {
        this.nbproduit = nbproduit;
    }

    public Commande(int idProduit, Date date, String modePaiment, int idPanier, int idClient, String etat, int nbproduit) {
        this.idProduit = idProduit;
        this.date = date;
        this.modePaiment = modePaiment;
        this.idPanier = idPanier;
        this.idClient = idClient;
        this.etat = etat;
        this.nbproduit = nbproduit;
    }


    public Commande() {
    }

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public Commande(int idProduit, Date date, String modePaiment, String livraison, int idClient) {
        this.idProduit = idProduit;
        this.date = date;
        this.modePaiment = modePaiment;
        this.livraison = livraison;
        this.idClient = idClient;
    }
   public Commande(int idProduit, Date date, String modePaiment, String livraison, int idClient,int idPanier) {
        this.idProduit = idProduit;
        this.date = date;
        this.modePaiment = modePaiment;
        this.livraison = livraison;
        this.idClient = idClient;
        this.idPanier= idPanier;
    }
    public Commande(int id_cmd, int idProduit, Date date, String modePaiment, String livraison, int idClient) {
        this.id_cmd = id_cmd;
        this.idProduit = idProduit;
        this.date = date;
        this.modePaiment = modePaiment;
        this.livraison = livraison;
        this.idClient = idClient;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId_cmd() {
        return id_cmd;
    }

    public void setId_cmd(int id_cmd) {
        this.id_cmd = id_cmd;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public String getModePaiment() {
        return modePaiment;
    }

    public void setModePaiment(String modePaiment) {
        this.modePaiment = modePaiment;
    }

    public String getLivraison() {
        return livraison;
    }

    public void setLivraison(String livraison) {
        this.livraison = livraison;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Commande{" + "idProduit=" + idProduit + ", date=" + date + ", modePaiment=" + modePaiment + ", idPanier=" + idPanier + ", idClient=" + idClient + ", etat=" + etat + ", nbproduit=" + nbproduit + ", prix_tot=" + prix_tot + '}';
    }

    public Commande(int id_cmd, java.sql.Date date, String modePaiment, int idClient, String etat, int nbproduit, double prix_tot) {
        this.id_cmd = id_cmd;
        this.date = date;
        this.modePaiment = modePaiment;
        this.idClient = idClient;
        this.etat = etat;
        this.nbproduit = nbproduit;
        this.prix_tot = prix_tot;
    }

   
    
   

}

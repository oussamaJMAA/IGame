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

    private Long id_cmd;
    private int idProduit;
    private Date date;
    private String modePaiment;
    private String livraison;
    private int idPanier;
    private int idClient;
    private String etat;

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
    public Commande(Long id_cmd, int idProduit, Date date, String modePaiment, String livraison, int idClient) {
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

    public Long getId_cmd() {
        return id_cmd;
    }

    public void setId_cmd(Long id_cmd) {
        this.id_cmd = id_cmd;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
        return "Commande{" + "idProduit=" + idProduit + ", date=" + date + ", modePaiment=" + modePaiment + ", livraison=" + livraison + ", idClient=" + idClient + ", etat=" + etat + '}';
    }

    
   

}

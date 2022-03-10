/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Magui
 */
public class Commande {

    private Long id_cmd;
    private Long idProduit;
    private Long idPanier;
    private Date date;
    private String modePaiment;
    private String livraison;
    private Long idClient;
    private String etat;

    public Commande() {
    }

    public Commande(Long idProduit, Date date, String modePaiment, String livraison, Long idClient, Long idPanier)
    {   this.idPanier = idPanier;
        this.idProduit = idProduit;
        this.date = date;
        this.modePaiment = modePaiment;
        this.livraison = livraison;
        this.idClient = idClient;
    }

    public Commande (Long id_cmd, Long idProduit, Date date, String modePaiment, String livraison, Long idClient,Long idPanier) {
        this.idPanier = idPanier;
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

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
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

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }
     public Long getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(Long idPanier) {
        this.idPanier = idPanier;
    }


    @Override
    public String toString() {
        return "Commande{"  + ", \n date=" + date +  "\n, modePaiment=" + modePaiment + ", \n livraison=" + livraison + ",\n client=" + idClient + ",\n etat=" + etat + '}';
    }

   
    
   

}

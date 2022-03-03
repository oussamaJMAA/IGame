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
    private Date date;
    private String modePaiment;
    private String livraison;
    private Long idClient;

    public Commande() {
    }

    public Commande(Long idProduit, Date date, String modePaiment, String livraison, Long idClient) {
        this.idProduit = idProduit;
        this.date = date;
        this.modePaiment = modePaiment;
        this.livraison = livraison;
        this.idClient = idClient;
    }

    public Commande(Long id_cmd, Long idProduit, Date date, String modePaiment, String livraison, Long idClient) {
        this.id_cmd = id_cmd;
        this.idProduit = idProduit;
        this.date = date;
        this.modePaiment = modePaiment;
        this.livraison = livraison;
        this.idClient = idClient;
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

    @Override
    public String toString() {
        return "Commande{" + "id_cmd=" + id_cmd + ", idProduit=" + idProduit + ", date=" + date + ", modePaiment=" + modePaiment + ", livraison=" + livraison + ", idClient=" + idClient + '}';
    }
    
    

}

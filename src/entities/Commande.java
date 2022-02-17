/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author pc hp core i3
 */
public class Commande { 
   
    int idProduit;
    int idClient;
    int id_cmd;
    Date date;
    String modePaiment ;
    String livraison ;

    public Commande() {
    }

    public Commande(int idProduit, int idClient, int id_cmd, Date date, String modePaiment, String livraison) {
        this.idProduit = idProduit;
        this.idClient = idClient;
        this.id_cmd = id_cmd;
        this.date = date;
        this.modePaiment = modePaiment;
        this.livraison = livraison;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getId_cmd() {
        return id_cmd;
    }

    public void setId_cmd(int id_cmd) {
        this.id_cmd = id_cmd;
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

    @Override
    public String toString() {
        return "Commande{" + "idProduit=" + idProduit + ", idClient=" + idClient + ", id_cmd=" + id_cmd + ", date=" + date + ", modePaiment=" + modePaiment + ", livraison=" + livraison + '}';
    }
    


    
    

   
    }
    
    


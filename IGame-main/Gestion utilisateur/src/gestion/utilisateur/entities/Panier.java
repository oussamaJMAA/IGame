/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur.entities;

/**
 *
 * @author Magui
 */
public class Panier {
    private int idP;
    private String nomProduit;
    private int prix;
    private String image;
    private Long idProduit;
private int idClient;
    public Panier() {
    }

    public Panier(int idP, String nomProduit, int prix, String image, Long idProduit) {
        this.idP = idP;
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.image = image;
        this.idProduit = idProduit;
    }

    public Panier(String nomProduit, int prix, String image, Long idProduit) {
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.image = image;
        this.idProduit = idProduit;
    }

    public int  getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    

    @Override
    public String toString() {
        return "Panier{ nomProduit=" + nomProduit + ", prix=" + prix +  '}';
    }
    
    
    
}

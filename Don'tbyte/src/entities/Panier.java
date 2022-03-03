/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Magui
 */
public class Panier {
    private Long idP;
    private String nomProduit;
    private int prix;
    private String image;
    private Long idProduit;

    public Panier() {
    }

    public Panier(Long idP, String nomProduit, int prix, String image, Long idProduit) {
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

    public Long getIdP() {
        return idP;
    }

    public void setIdP(Long idP) {
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

    @Override
    public String toString() {
        return "Panier{" + "idP=" + idP + ", nomProduit=" + nomProduit + ", prix=" + prix + ", image=" + image + ", idProduit=" + idProduit + '}';
    }
    
    
    
}

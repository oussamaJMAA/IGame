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
public class Panier {
    int idProduit;
    String nomProduit,image,prix;

    public Panier() {
    }

    public Panier(int idProduit, String nomProduit, String image, String prix) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.image = image;
        this.prix = prix;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Panier{" + "idProduit=" + idProduit + ", nomProduit=" + nomProduit + ", image=" + image + ", prix=" + prix + '}';
    }
    
    
    
}

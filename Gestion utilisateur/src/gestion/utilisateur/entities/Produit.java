/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestion.utilisateur.entities;


public class Produit {
        private int id,qte,prix,reduction;
    private String nom,reference,image;
public Produit() {
    }

    public int getReduction() {
        return reduction;
    }

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }
 public Produit(int qte, int prix, int reduction, String nom, String image) {
        this.id = id;
        this.qte = qte;
        this.prix = prix;
        this.reduction = reduction;
        this.nom = nom;
        this.image = image;
    }
   
    public Produit(int id, int qte, int prix, int reduction, String nom, String image) {
        this.id = id;
        this.qte = qte;
        this.prix = prix;
        this.reduction = reduction;
        this.nom = nom;
        this.image = image;
    }
   

    public Produit(int id, String nom, int prix,int qte, String reference,String image) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.qte=qte;
         this.reference = reference;
         this.image = image;
         
    }

    
    public Produit(int id, String nom, int prix,int qte, String reference) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.qte=qte;
         this.reference = reference;
       
         
    }

    public Produit( String nom, int prix, int qte,String reference, String image) {
       
        this.nom = nom;
        this.prix = prix;
        this.qte=qte;
           this.reference = reference;
            this.image = image;
    }

    

  
   
  

    

    
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
     public String getImage() {
        return image;
    }

    public int getPrix() {
        return prix;
    }
 public int getQte() {
        return qte;
    }
 public String getReference() {
        return reference;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
     public void setImage(String image) {
        this.image = image;
    }
public void setPrix(int prix) {
        this.prix = prix;
    }
    
    public void setQte(int qte) {
        this.qte = qte;
    }
     public void setReference(String reference) {
        this.reference = reference;
    }
    @Override
    public String toString() {
return nom;       
// return "Produit{" + "id=" + id + ", nom=" + nom + ",prix="+ prix +",qte="+ qte +",reference="+ reference +", image=" + image+ '}';
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;


public class Produit {
        private int id,qte,prix;
    private String nom;
public Produit() {
    }
   

    public Produit(int id, String nom, int prix,int qte) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.qte=qte;
    }

    
    public Produit( String nom, int prix, int qte) {
       
        this.nom = nom;
        this.prix = prix;
        this.qte=qte;
    }

    

  
   
  

    

    
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getPrix() {
        return prix;
    }
 public int getQte() {
        return qte;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
public void setPrix(int prix) {
        this.prix = prix;
    }
    
    public void setQte(int qte) {
        this.qte = qte;
    }
    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ",prix="+ prix +", qte=" + qte + '}';
    }
    
    
}

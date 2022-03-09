package entities;

/**
 *
 * @author Fayechi
 */
public class Promotion {
    private int id,prixPro;
    private String nom;
    private String date;
     private String datef;
//java.sql.Date test;
    public Promotion() {
    }

    public Promotion(int id, String nom, String date, int prixPro,String datef) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.prixPro = prixPro;
         this.datef = datef;

    }

    public Promotion( String nom, String date, int prixPro,String datef) {
       
        this.nom = nom;
        this.date = date;
        this.prixPro=prixPro;
         this.datef = datef;
    }

  

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDate() {
        return date;
    }
     public String getDatef() {
        return datef;
    }
public int getPrixPro() {
        return prixPro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate(String date) {
        this.date = date;
    }
     public void setDatef(String datef) {
        this.datef = datef;
    }
     public void setPrixPro(int prixPro) {
        this.prixPro = prixPro;
    }
    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", nom=" + nom + ",date=" + date + ",prixPro=" + prixPro + ", datef=" + datef + '}';
    }
    
    
}

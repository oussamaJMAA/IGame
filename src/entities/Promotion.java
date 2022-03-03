package entities;

/**
 *
 * @author Fayechi
 */
public class Promotion {
    private int id,prixPro;
    private String nom;
    private String date;

    public Promotion() {
    }

    public Promotion(int id, String nom, String date, int prixPro) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.prixPro = prixPro;

    }

    public Promotion( String nom, String date, int prixPro) {
       
        this.nom = nom;
        this.date = date;
        this.prixPro=prixPro;
    }

    public Promotion(String string, String ui, String aa) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Promotion(String text, int r, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
     public void setPrixPro(int prixPro) {
        this.prixPro = prixPro;
    }
    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", nom=" + nom + ",date=" + date + ", prixPro=" + prixPro + '}';
    }
    
    
}

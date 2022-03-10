package gestion.utilisateur;


public class Commentaires {
    private final int id_com ;
    private final String description_com;


    public Commentaires(int id_com, String description_com) {
        this.id_com = id_com;
        this. description_com= description_com;


    }



    public int getId_com() {
        return id_com;
    }

    public String getDescription_com() {
        return description_com;
    }



}

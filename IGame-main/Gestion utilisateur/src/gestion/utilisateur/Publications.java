package gestion.utilisateur;


public class Publications {
    private final int id_pub;
    private final String titre_pub;
    private final String description_pub;

    public Publications(int id_pub, String titre_pub, String description_pub) {
        this.id_pub = id_pub;
        this. titre_pub= titre_pub;
        this.description_pub= description_pub;


    }



    public int getId_pub() {
        return id_pub;
    }

    public String getTitre_pub() {return titre_pub;}

    public String getDesrciption_pub() {
        return description_pub;
    }


}

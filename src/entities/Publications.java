package entities;

import services.publications_services;


public class Publications {
    private int id_pub;
    private String titre_pub;
    private String desciption_pub;

    public Publications() {
        this.id_pub = id_pub;
        this. titre_pub= titre_pub;
        this.desciption_pub= desciption_pub;


    }



    public int getId_pub() {
        return this.id_pub;
    }

    public String getTitre_pub() {
        return this.titre_pub;
    }

    public String getDesciption_pub() {
        return this.desciption_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public void setTitre_pub(String titre_pub) {
        this.titre_pub = titre_pub;
    }

    public void setDesciption_pub(String desciption_pub) {
        this.desciption_pub = desciption_pub;
    }

    public String toString() {
        return "Publication{id=" + this.id_pub + ", titre=" + this.titre_pub + ", Description=" + this.desciption_pub + '}';
    }
}

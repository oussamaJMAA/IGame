package library;


public class Publications {
    private int id_pub;
    private String titre_pub;
    private String description_pub;

    public Publications(int id_pub, String titre_pub, String description_pub) {
        this.id_pub = this.id_pub;
        this. titre_pub= this.titre_pub;
        this.description_pub= this.description_pub;


    }



    public int getId_pub() {
        return this.id_pub;
    }

    public String getTitre_pub() {
        return this.titre_pub;
    }

    public String getDesrciption_pub() {
        return this.description_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public void setTitre_pub(String titre_pub) {
        this.titre_pub = titre_pub;
    }

    public void setDesciption_pub(String desciption_pub) {
        this.description_pub = desciption_pub;
    }

    public String toString() {
        return "Publication{ titre=" + this.titre_pub + ", Description=" + this.description_pub + '}';
    }
}

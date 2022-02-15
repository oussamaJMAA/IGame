package entities;




public class Commentaires {
    private int id_com ;
    private String description_com;


    public Commentaires() {
        this.id_com = id_com;
        this. description_com= description_com;


    }



    public int getId_com() {
        return this.id_com;
    }

    public String getDescription_com() {
        return this.description_com;
    }


    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public void setDescription_com(String description_com) {
        this.description_com = description_com;
    }


    public String toString() {
        return "Commentaire{id=" + this.id_com + ",Description=" + this.description_com + '}';
    }
}

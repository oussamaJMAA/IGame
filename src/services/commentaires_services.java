package services;

import entities.Commentaires;
import entities.Publications;

import services.commentaires_services;

import tools.Maconnexion;
import com.company.Main;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class commentaires_services {
    Connection cnx = Maconnexion.getInstance().getCnx();

    public commentaires_services () {
    }

   // public void ajoutercommentaire(Publications c ) {
        public void ajoutercommentaire(String description_com) {
      //  String sql = "INSERT INTO `commentaires`(`id_com`, `description_com`) VALUES ('" + c.getId_com() + "','" + c.getDescription_com() + "')";
        String sql = "INSERT INTO `commentaires`(`description_com`) VALUES ('" +description_com+"')";

        try {
            Statement ste = this.cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Commentaire Ajoutee");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }



    public List<Commentaires> afficherCommentaire() {
        List<Commentaires> Commentaires = new ArrayList();
        String query = "select * from commentaires";

        try {
            PreparedStatement ste = this.cnx.prepareStatement(query);
            ResultSet rs = ste.executeQuery();

            while(rs.next()) {
                Commentaires c = new Commentaires();
                c.setId_com(rs.getInt("id_com"));
                c.setDescription_com(rs.getString("description_com"));
                Commentaires.add(c);
            }
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }

        return Commentaires;
    }

    public void supprimercommentaire(int id_com){


        String sql = "DELETE FROM commentaires WHERE id_com ='"+id_com+"';";

        try {
            PreparedStatement ste = this.cnx.prepareStatement(sql);

          //  ste.setInt(1, c.getId_com());
            ste.executeUpdate();
            System.out.println("Commentaire supprime");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }


    public void modifiercommentaire(int id_com,String description_com){

        String sql = "UPDATE commentaires SET description_com = '"+description_com+"' WHERE id_com = '"+id_com+"';";

        try {
            PreparedStatement ste = this.cnx.prepareStatement(sql);

            //  ste.setInt(1, c.getId_com());
            ste.executeUpdate();
            System.out.println("Commentaire modifie");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

}

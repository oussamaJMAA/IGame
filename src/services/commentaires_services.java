package services;

import entities.Commentaires;
import entities.Publications;

import services.commentaires_services;

import tools.Maconnexion;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class commentaires_services {
    Connection cnx = Maconnexion.getInstance().getCnx();

    public commentaires_services () {
    }

    public void ajoutercommentaire(Commentaires c) {
        String sql = "INSERT INTO `commentaires`(`id`, `nom`, `prenom`) VALUES ('" + c.getId_com() + "','" + c.getDescription_com() + "')";

        try {
            Statement ste = this.cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Commentaire Ajoutee");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public void ajouterCommentaire2(Commentaires c) {
        String sql = "insert into esprit3a26(nom,prenom) values('hi','ha')";

        try {
            PreparedStatement ste = this.cnx.prepareStatement(sql);

            ste.setString(2, c.getDescription_com());
            ste.executeUpdate();
            System.out.println("Commentaire Ajoutée");
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
}

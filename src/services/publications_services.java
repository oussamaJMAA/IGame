package services;

import entities.Commentaires;
import entities.Publications;
import tools.Maconnexion;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class publications_services {
    Connection cnx = Maconnexion.getInstance().getCnx();



    public void ajouterpublication(String titre_pub, String desciption_pub) {
        String sql = "INSERT INTO `publications`(`titre_pub`, `desciption_pub`) VALUES ('" + titre_pub + "','" + desciption_pub + "')";
        try {
            Statement ste = this.cnx.createStatement();
            ste.executeUpdate(sql);
            System.out.println("Publication Ajoutee");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public void ajouterPublication(Publications p) {
        String sql = "insert into publications(titre_pub,desciption_pub) values('hi','ha')";

        try {
            PreparedStatement ste = this.cnx.prepareStatement(sql);
            ste.setString(1, (p.getTitre_pub()));
            ste.setString(2, p.getDesciption_pub());
            ste.executeUpdate();
            System.out.println("Publication Ajoutée");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public List<Publications> afficherPublication() {
        List<Publications> Publications = new ArrayList();
        String query = "select * from publications";

        try {
            PreparedStatement ste = this.cnx.prepareStatement(query);
            ResultSet rs = ste.executeQuery();

            while(rs.next()) {
                Publications p = new Publications();
                p.setId_pub(rs.getInt("id_pub"));
                p.setTitre_pub(rs.getString("titre_pub"));
                p.setDesciption_pub(rs.getString("desciption_pub"));
                Publications.add(p);
            }
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }

        return Publications;
    }


    public void modifierpublication(int id_pub,String description_pub){

        String sql = "UPDATE publications SET desciption_pub = '"+description_pub+"' WHERE id_pub = '"+id_pub+"';";

        try {
            PreparedStatement ste = this.cnx.prepareStatement(sql);

            //  ste.setInt(1, c.getId_com());
            ste.executeUpdate();
            System.out.println("Contenu modifie");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }


    public void supprimerpublication(int id_pub){


        String sql = "DELETE FROM publications WHERE id_pub ='"+id_pub+"';";

        try {
            PreparedStatement ste = this.cnx.prepareStatement(sql);

            //ste.setInt(1, c.getId_com());
            ste.executeUpdate();
            System.out.println("Publication supprimé");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public List<Publications> afficherpublications() {
        List<Publications> Publications = new ArrayList();
        String query = "select * from publications";

        try {
            PreparedStatement ste = this.cnx.prepareStatement(query);
            ResultSet rs = ste.executeQuery();

            while(rs.next()) {
                Publications p = new Publications();
              //  p.setId_pub(rs.getInt("titre_pub"));
                p.setTitre_pub(rs.getString("titre_pub"));
                p.setDesciption_pub(rs.getString("desciption_pub"));
                Publications.add(p);
            }
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }

        return Publications;
    }

}

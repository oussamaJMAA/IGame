package services;

import entities.Publications;
import tools.Maconnexion;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class publications_services {
    Connection cnx = Maconnexion.getInstance().getCnx();



    public void ajouterpublication(Publications p) {
        String sql = "INSERT INTO `Personne`(`id_pub `, `titre_pub`, `desciption_pub`) VALUES ('" + p.getId_pub() + "','" + p.getTitre_pub() + "','" + p.getDesciption_pub() + "')";
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
}

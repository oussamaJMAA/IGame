/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author rouka
 */

public class Equipes {
 int id ;
    String nom_equipe;
    int membres; 
    int pts_xp;
    int tournois_gagne;

    public Equipes() {
    }


    public Equipes(int id,String nom_equipe, int membres, int pts_xp, int tournois_gagne) {
    this.id=id;
        this.nom_equipe = nom_equipe;
        this.membres = membres;
        this.pts_xp = pts_xp;
        this.tournois_gagne = tournois_gagne;

    }

    public String getNom_equipe() {
        return nom_equipe;
    }

    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }

    public int getMembres() {
        return membres;
    }

    public void setMembres(int membres) {
        this.membres = membres;
    }

    public int getPts_xp() {
        return pts_xp;
    }

    public void setPts_xp(int pts_xp) {
        this.pts_xp = pts_xp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTournois_gagne() {
        return tournois_gagne;
    }

    public void setTournois_gagne(int tournois_gagne) {
        this.tournois_gagne = tournois_gagne;
    }

    @Override
    public int hashCode() {
        int hash = 5;
       
        hash = 29 * hash + Objects.hashCode(this.nom_equipe);
        hash = 29 * hash + this.membres;
        hash = 29 * hash + this.pts_xp;
        hash = 29 * hash + this.tournois_gagne;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipes other = (Equipes) obj;
     
        if (this.membres != other.membres) {
            return false;
        }
        if (this.pts_xp != other.pts_xp) {
            return false;
        }
        if (this.tournois_gagne != other.tournois_gagne) {
            return false;
        }
        if (!Objects.equals(this.nom_equipe, other.nom_equipe)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Equipes{" + "id=" + id + ", nom_equipe=" + nom_equipe + ", membres=" + membres + ", pts_xp=" + pts_xp + ", tournois_gagne=" + tournois_gagne + ", equipe_tournois="+'}';
    }


    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur.entities;

/**
 *
 * @author rouka
 */
public class Participation {
    int id;
    int id_equipe;
    int id_tournois;
String equipe, tournois;

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getTournois() {
        return tournois;
    }

    public void setTournois(String tournois) {
        this.tournois = tournois;
    }


    public Participation(int id, int id_equipe, int id_tournois) {
        this.id = id;
        this.id_equipe = id_equipe;
        this.id_tournois = id_tournois;
    }
  public Participation(int id_equipe, int id_tournois) {
    
        this.id_equipe = id_equipe;
        this.id_tournois = id_tournois;
    }
  public Participation(String equipe, String tournois) {
    
        this.equipe = equipe;
        this.tournois = tournois;
    }
    public Participation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_equipe() {
        return id_equipe;
    }

    public void setId_equipe(int id_equipe) {
        this.id_equipe = id_equipe;
    }

    public int getId_tournois() {
        return id_tournois;
    }

    public void setId_tournois(int id_tournois) {
        this.id_tournois = id_tournois;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        hash = 79 * hash + this.id_equipe;
        hash = 79 * hash + this.id_tournois;
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
        final Participation other = (Participation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.id_equipe != other.id_equipe) {
            return false;
        }
        if (this.id_tournois != other.id_tournois) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Participation{" + "id=" + id + ", id_equipe=" + id_equipe + ", id_tournois=" + id_tournois + '}';
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.util.Objects;
import java.sql.Date;
/**
 *
 * @author rouka
 */
public class Tournois {
 int id;
 String nom_tournois;
java.sql.Date date;
int capacite;
String platforme;

String recompense;

    @Override
    public String toString() {
        return "Tournois{" + "id=" + id + ", nom_tournois=" + nom_tournois + ", date=" + date + ", capacite=" + capacite + ", platforme=" + platforme + ", recompense=" + recompense + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.nom_tournois);
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + this.capacite;
        hash = 97 * hash + Objects.hashCode(this.platforme);
        
        hash = 97 * hash + Objects.hashCode(this.recompense);
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
        final Tournois other = (Tournois) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.capacite != other.capacite) {
            return false;
        }
    
        if (!Objects.equals(this.nom_tournois, other.nom_tournois)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.platforme, other.platforme)) {
            return false;
        }
        if (!Objects.equals(this.recompense, other.recompense)) {
            return false;
        }
        return true;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom_tournois(String nom_tournois) {
        this.nom_tournois = nom_tournois;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }


    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setPlatforme(String platforme) {
        this.platforme = platforme;
    }

    public void setRecompense(String recompense) {
        this.recompense = recompense;
    }

    public int getId() {
        return id;
    }

    public String getNom_tournois() {
        return nom_tournois;
    }

  
    public int getCapacite() {
        return capacite;
    }

    public String getPlatforme() {
        return platforme;
    }

    public String getRecompense() {
        return recompense;
    }

    public Tournois() {
    }

    public Tournois(int id, String nom_tournois, java.sql.Date date, int capacite, String platforme, String recompense) {
        this.id = id;
        this.nom_tournois = nom_tournois;
        this.date = date;
        this.capacite = capacite;
        this.platforme = platforme;

        this.recompense = recompense;
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur.entities;
import java.util.Objects;
import java.sql.Date;
import java.sql.Time;
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
java.sql.Time time;
String recompense;
int pts_xp;
    @Override
    public String toString() {
        return "Tournois{" + "id=" + id + ", nom_tournois=" + nom_tournois + ", date=" + date + ", capacite=" + capacite + ", platforme=" + platforme + ", recompense=" + recompense + '}';
    }

    public int getPts_xp() {
        return pts_xp;
    }

    public void setPts_xp(int pts_xp) {
        this.pts_xp = pts_xp;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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

    public Tournois(int id, String nom_tournois, java.sql.Date date, int capacite, String platforme, String recompense,int pts_xp) {
        this.id = id;
        this.nom_tournois = nom_tournois;
        this.date = date;
        this.capacite = capacite;
        this.platforme = platforme;
this.pts_xp=pts_xp;
        this.recompense = recompense;
    }

    public Tournois(int id, String nom_tournois, Date date, int capacite, String platforme, Time time, String recompense, int pts_xp) {
        this.id = id;
        this.nom_tournois = nom_tournois;
        this.date = date;
        this.capacite = capacite;
        this.platforme = platforme;
        this.time = time;
        this.recompense = recompense;
        this.pts_xp = pts_xp;
    }
    
    public Tournois(int id, String nom_tournois, java.sql.Date date, int capacite, String platforme, String recompense,java.sql.Time time) {
        this.id = id;
        this.nom_tournois = nom_tournois;
        this.date = date;
        this.capacite = capacite;
        this.platforme = platforme;

        this.recompense = recompense;
        this.time=time;
    }


}

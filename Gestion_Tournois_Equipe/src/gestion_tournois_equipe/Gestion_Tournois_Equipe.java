/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_tournois_equipe;

import Entities.Equipes;
import Entities.Tournois;
import Services.EquipeService;
import Services.TournoisService;
import tools.MaConnexion;

/**
 *
 * @author rouka
 */
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Gestion_Tournois_Equipe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);  
          MaConnexion mc = MaConnexion.getInstance();
          TournoisService ts = new TournoisService();
            EquipeService Es = new EquipeService();
      

int choix;
Scanner sc = new Scanner(System.in);

do{
System.out.println("1.Ajouter Tournois");
System.out.println("2.Afficher Tournois");
System.out.println("3.Modifier Tournois");
System.out.println("4.Supprimer Tournois");

System.out.println("5.Ajouter Equipe");
System.out.println("6.Afficher Equipe");
System.out.println("7.Modifier Equipe");
System.out.println("8.Supprimer Equipe");

System.out.println("9.Participation Au Tournois");
System.out.println("10. Suppression Participation");

System.out.print("Donner Votre Choix : ");
 choix = sc.nextInt();
 
 switch(choix){
     case 1 :
         //`nom_tournois`,`date`,`capacite`,`platforme`,`recompense`
         System.out.println("Donner Le Nom de Tounois");
         String nom_tournois = sc.next();
         System.out.println("Donner Le Capacite de Tournois");
         int Capacite=sc.nextInt();
         System.out.println("Donner Le Platforme de Tournois");
         String plat = sc.next();
         System.out.println("Donner La Recompense de Tournois");
         String recompense = sc.next();
         System.out.println("Ajout En cours..");
         //int id, String nom_tournois, java.sql.Date date, int capacite, String platforme, String recompense
          Tournois t = new Tournois(nom_tournois,date,Capacite,plat,recompense);
         ts.ajouterTournois(t);
         
         break;
     case 2:
        System.out.println(ts.afficherTournois());
         break;
     case 3:
         System.out.println("Modification Tournois ....");
         System.out.println("Donner L'id de Tounois a modifier");
         int id_tournois = sc.nextInt();
          System.out.println("Donner Le Nom de Tounois a modifier");
         String nom_tournois_m = sc.next();
         System.out.println("Donner Le Capacite de Tournois a modifier");
         int Capacite_m=sc.nextInt();
         System.out.println("Donner Le Platforme de Tournois a modifier");
         String plat_m = sc.next();
         System.out.println("Donner La Recompense de Tournois a modifier");
         String recompense_m= sc.next();
         System.out.println("Modification En cours..");
     ts.ModifierTournois(id_tournois, nom_tournois_m, Capacite_m, plat_m, recompense_m, date);
         break;
     case 4:
         System.out.println("Donner l'id de Tournois a supprimer !");
         int id = sc.nextInt();
         ts.SupprimerTournois(id);
         break;
     case 5 : 
         //`nom_equipe`,`membres`,`pts_exp`,`tournois_gagne`
         System.out.println("Donner le Nom d'equipe");
         String nom_equipe = sc.next();
         System.out.println("Donner le Nombre de membres");
         int membres = sc.nextInt();
         System.out.println("Donner les points xp de l'equipe");
         int pts_xp = sc.nextInt();
         System.out.println("Combien de Tournois Gagne");
         int tg = sc.nextInt();
         
        Equipes e = new Equipes(nom_equipe,membres,pts_xp,tg);
         Es.ajouterEquipe(e);
         
         break; 
     case 6:
         System.out.println(Es.afficherEquipe());
         break;
     case 7: 
          //`nom_equipe`,`membres`,`pts_exp`,`tournois_gagne`
         System.out.println("Donner l'id equipe a modifier");
         int id_e = sc.nextInt();
         System.out.println("Donner le Nom d'equipe a modifier");
         String nom_equipe_m = sc.next();
         System.out.println("Donner le Nombre de membres a modifier");
         int membres_m = sc.nextInt();
         System.out.println("Donner les points xp de l'equipe a modifier");
         int pts_xp_m = sc.nextInt();
         System.out.println("Combien de Tournois Gagne a modifier");
         int tg_m = sc.nextInt();
         
        Equipes e_m= new Equipes(nom_equipe_m,membres_m,pts_xp_m,tg_m);
         Es.ModifierEquipe(id_e, nom_equipe_m, membres_m, pts_xp_m, tg_m);
         
         break;
     case 8 : 
         System.out.println("Donner l'id de l'equipe a supprimer");
         int id_e_s = sc.nextInt();
         Es.SupprimerEquipe(id_e_s);
         break;
     case 9:
         System.out.println("Donner l'id de equipe a participer au tournois");
         int id_ee = sc.nextInt();
         System.out.println("Donner l'id de tournois");
         int id_tt = sc.nextInt();
         Es.participation(id_ee, id_tt);
         break;
     case 10:
         System.out.println("Donner l'id dequipe");
         int id_s_e = sc.nextInt();
         Es.supp_part(id_s_e);
         
         break;
 }
}while(choix!=0);


    }
    
}

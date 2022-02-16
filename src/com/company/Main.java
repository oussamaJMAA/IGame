package com.company;

import entities.Commentaires;
import entities.Publications;

import services.commentaires_services;
import services.publications_services;
import tools.Maconnexion;


import javax.swing.plaf.synth.SynthStyle;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public Main(){

    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int ch;

        Maconnexion mc = Maconnexion.getInstance();
        commentaires_services cs = new commentaires_services();
        publications_services pb = new publications_services();

        System.out.println(cs.afficherCommentaire());


        do {



            System.out.println("1.Ajouter commentaire");
            System.out.println("2.Modifier commentaire");
            System.out.println("3.Supprimer commentaire");
            System.out.println("4.Afficher commentaire");
            System.out.println("5.Ajouter Publication");
            System.out.println("6.Modifier Publication");
            System.out.println("7.Supprimer Publication");
            System.out.println("8.Afficher Publication");
            System.out.println("0.EXIT");
            System.out.println("Make your choice : ");
            ch = s.nextInt();

        switch (ch){

            case 1:
            System.out.println("Entrez votre commentaire ");
                Scanner ajout = new Scanner(System.in);
                String description_com = ajout.nextLine();
                cs.ajoutercommentaire(description_com);

            break;

            case 2:
                Scanner modid = new Scanner(System.in);
                Scanner moddes = new Scanner(System.in);

                System.out.println("ID du commentaire a modifier : ");
                int id_com = modid.nextInt();
                System.out.println("Nouveau commentaire : ");
                description_com = moddes.nextLine();


                cs.modifiercommentaire(id_com,description_com);
                break;


            case 3:
                System.out.println("ID du commentaire a supprimer : ");
                Scanner supp = new Scanner(System.in);
                int Id_com = supp.nextInt();
                cs.supprimercommentaire(Id_com);

                break;


            case 4:

                System.out.println(cs.afficherCommentaire());
                break;

            case 5:
                System.out.println("Titre de la publication : ");
                Scanner ajout2 = new Scanner(System.in);
                String titre_pub = ajout2.nextLine();
                System.out.println("Contenu : ");
                Scanner ajout3 =  new Scanner(System.in);
                String desciption_pub = ajout3.nextLine();
                pb.ajouterpublication(titre_pub,desciption_pub);

                break;


            case 6:

                Scanner modid2 = new Scanner(System.in);
                Scanner moddes2 = new Scanner(System.in);

                System.out.println("ID de la publication a modifier : ");
                int id_pub = modid2.nextInt();
                System.out.println("Nouvelle description : ");
                String description_pub = moddes2.nextLine();


                pb.modifierpublication(id_pub,description_pub);

                break;

            case 7:

                System.out.println("ID de la publication a supprimer : ");
                Scanner supp2 = new Scanner(System.in);
                id_pub = supp2.nextInt();
                pb.supprimerpublication(id_pub);


                break;

            case 8:
                System.out.println(pb.afficherpublications());
                break;

        }




    }while(ch != 0);




    }}



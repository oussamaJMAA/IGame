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
            System.out.println("1.INSERT");
            System.out.println("2.UPDATE");
            System.out.println("3.DELETE");
            System.out.println("4.DISPLAY");
            System.out.println("5.SEARCH");
            System.out.println("0.EXIT");
            System.out.println("Make your choice : ");
            ch = s.nextInt();

        switch (ch){

            case 1:

                cs.ajoutercommentaire();

            break;

            case 2:

                cs.modifiercommentaire();
                break;


            case 3:

                cs.supprimercommentaire();
                break;


            case 4:

                System.out.println(cs.afficherCommentaire());
                break;

            case 5:
                break;


        }




    }while(ch != 0);




    }}



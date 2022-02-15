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

        do {
            System.out.println("1.INSERT");
            System.out.println("2.UPDATE");
            System.out.println("3.DELETE");
            System.out.println("4.DISPLAY");
            System.out.println("5.SEARCH");
            System.out.println("0.EXIT");
            System.out.println("Make your choice : ");
            ch = s.nextInt();
        }while(ch != 0);

        switch (ch){

            case 1:;


        }



        Maconnexion mc = Maconnexion.getInstance();
        commentaires_services ps = new commentaires_services();
        System.out.println(ps.afficherCommentaire());


    }



}


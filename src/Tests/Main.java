/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Game;
import Service.GameService;
import Entities.Category;
import Service.CategoryService;
import Entities.Rating;
import Service.RatingService;
import Tools.MaConnexion;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MaConnexion mc = MaConnexion.getInstance();
        /************** jeu **********************/
       GameService ps = new GameService();
                /**** ajout jeu ****/
     // ps.AddGame (new Game (2,"Pubg","game description 2"," link2","vid2","12/02/2022")); 
     // ps.AddGame (new Game (3,"freefire","game description 3","link3","vid3","13/02/2022")); 
     // ps.AddGame (new Game (4,"Call of Duty","game description 4","link4","vid4","14/02/2022")); 

      // Games g1 = new game('PUBG','gamepubgdescreption','gamepubglink','gamepubgvid','gamepubgaddDate') ;
      // ps.ajouterGame2(g1);
               /**affiche jeux**/     
               System.out.println(ps.afficherJeu().toString()); 
             /***delete****/ 
     //  ps.DeletGame(123);
     /*****modefier jeu *****/
      // ps.modifierJeu(new Game(1,"pubj","sdfs","sdf","lkjh","jhh"));
      
      
      
      /*******************************/
      /************** categorie**********************/
       //CategoryService cs = new CategoryService();
                /****  ajouter category  ****/
     // cs.AddCategory(new Category (1,"gamepubglink","gamepubgvid","gamepubgaddDate")); 

      // Games c1 = new game('','','','','') ;
     
               /**affiche jeux**/     
      //System.out.println(cs.afficher().toString()); // 
             /***delete****/ 
      //cs.DeletCategory(1);
     /*****modefier jeu *****/
     // cs.modifiercategorie(new Category(1,"sdf","lkjh","jhh"));
   
        /*******************************/
      /************** evaluation **********************/
       RatingService rs = new RatingService();
                /****  ajouter evaluation  ****/
      //rs.AddRating(new Rating (1,70)); 

      // Ratings r1 = new Rating('','','','','') ;
     
               /**affiche jeux**/     
      System.out.println(rs.afficherRating().toString()); // 
            
     /*****modefier jeu *****/
      //rs.modifierRating(new Rating(1,80));
      
    
    
    }
    
}

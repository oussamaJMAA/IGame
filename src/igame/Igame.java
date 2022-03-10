/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package igame;

import Entities.Game;
import Service.GameService;
import Entities.Category;
import Service.CategoryService;
import Entities.Like;
import Service.LikeService;
import Tools.MaConnexion;
import java.sql.Date;
import java.sql.Time;

import java.util.concurrent.atomic.AtomicInteger;

public class Igame {

    public static void main(String[] args) { 
        MaConnexion mc = MaConnexion.getInstance();
    
          /************** categorie**********************/
  //   CategoryService cs = new CategoryService();
                /****  add category  ****/
 
     //Category g2 = new Category(2,"cat 2",new Date(System.currentTimeMillis()));
  // cs.AddCategory(g2);
   
               /**affiche category**/     
      //System.out.println(cs.ReadC().toString()); // 
             /***delete****/ 
      //cs.DeletC(1);
     /*****Modify category *****/
      //cs.ModifyC(new Category(2,"sdf",new Date(System.currentTimeMillis())));
   
        /*******************************/
        
      /**************   game **********************/
    GameService gs = new GameService();
                 
                /**** Add game ****/
  // Game g1 = new Game (3,"free fire",2,"game description "," link2","vid2",new Date(System.currentTimeMillis())); 
      //  Game g2 = new Game (2,"game2",2,"game description "," link2","vid2",new Date(System.currentTimeMillis())); 
    //  gs.AddGame(g1);
               /**affiche jeux**/     
      //System.out.println(gs.ReadG().toString()); 
             /***delete****/ 
    ////  gs.DeletG(2);
     /*****modefiy game *****/
   //  gs.ModefiyG(new Game(2,"game2",2,"game description "," link2","vid2",new Date(System.currentTimeMillis())));
         /********recherch********************/
        
        // System.out.println(gs.search("Pubg1").get(0));
        /**********tri date************/
        // System.out.println(gs.triDate());
         /************** tri evaluation *************/
     // System.out.println(gs.triRating());
      
      
      /***********************************************************/
    
      /************** evaluation **********************/
   LikeService rs = new LikeService();
                /****  add rating  ****/
        //    Rating r3 = new Rating (3,17,3,10,new Date(System.currentTimeMillis())); 

     // rs.AddRating(r3);
        // rs.AddRating(r1);
      

     
               /**affiche jeux**/     
    // System.out.println(rs.ReadR().toString()); // 
            
     /*****modefier jeu *****/
       //rs.modifiyR(3,17,3,30,new Date(System.currentTimeMillis())); 

      
    
    
    
    }
    
}

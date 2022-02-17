/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wedontbyte;

import entities.Panier;
import services.ServicePanier;
import entities.Commande;
import services.ServiceCommande;




/**
 *
 * @author pc hp core i3
 */
public class Wedontbyte {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ServicePanier sp = new ServicePanier() ;
       // ServiceCommande sc = new ServiceCommande() ;
        
       /* sp.ajouter(new Panier (1,"ppp","145","154")); */
       
         /*  sp.supprimer (1);*/  
         
        sp.modifier(new Panier (2 ,"jeux","145","154"));
        
               System.out.println(sp.afficher().toString()); 
           // sc.supprimerCmdd(2);
            //   System.out.println(sc.afficherCmd().toString()); 
                
    }
    
}

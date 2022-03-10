/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevmariem;

import entities.Panier;
import java.sql.SQLException;
import service.PanierCRUD;

/**
 *
 * @author Magui
 */
public class PidevMariem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        PanierCRUD p = new  PanierCRUD();
       System.out.print(p.totalePanier(22));
    }
    
}

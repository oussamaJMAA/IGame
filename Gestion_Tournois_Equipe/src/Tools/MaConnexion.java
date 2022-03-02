/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rouka
 */
public class MaConnexion {
    public String url  ="jdbc:mysql://localhost:3306/projet_java";
    public String user  ="root";
    public String password ="";
    private Connection cnx;
    public static MaConnexion ct;
    private MaConnexion(){
        try {
            cnx = DriverManager.getConnection(url,user,password);
            System.out.println("cnx done ");
        } catch (SQLException ex) {
            Logger.getLogger(MaConnexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    
    public static MaConnexion getInstance(){
    if(ct == null )
    ct = new MaConnexion();
    return ct;
    
    }

    public Connection getCnx() {
        return cnx;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author oussa
 */
public class Connexion {
    public String url = "jdbc:mysql://localhost:3306/projet_java";
      public String username = "root";
        public String password = "";
        private Connection cnx;
        public static Connexion c;
        private Connexion(){
            try{
                cnx=DriverManager.getConnection(url, username, password);
                System.out.println("connexion etablie");
            }
            catch (SQLException e) {
            System.out.println(e);

        }
        }
            public static Connexion getInstance(){
                if(c==null)
                    c=new Connexion();
                return c;
            }
            public Connection getCnx(){
                return cnx;
            }
            
            
        }
        


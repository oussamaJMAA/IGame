package tools;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
public class Maconnexion {
    public String url = "jdbc:mysql://localhost:3306/blog";
    public String user = "root";
    public String pwd = "";
    private Connection cnx;
    public static Maconnexion ct;

    private Maconnexion() {
        try {
            this.cnx = DriverManager.getConnection(this.url, this.user, this.pwd);
            System.out.println("Connexion etablie");
        } catch (SQLException var2) {
            System.out.println(var2.getMessage());
        }

    }

    public static Maconnexion getInstance() {
        if (ct == null) {
            ct = new Maconnexion();
        }

        return ct;
    }

    public Connection getCnx() {
        return this.cnx;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import gestion.utilisateur.entities.Equipes;
import gestion.utilisateur.entities.Tournois;
import com.mysql.cj.protocol.Resultset;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author rouka
 */
public class MenuController implements Initializable {

    @FXML
    private Button ta;
    @FXML
    private Button ea;
    @FXML
    private Button tc;
    @FXML
    private Button ec;
  
private Parent root;
private Stage stage;
private Scene scene;
    @FXML
    private Button Live_tour;
    @FXML
    private PieChart pieChart;
    @FXML
    private AnchorPane test;
 private Timer timer = new Timer();
    private int counter;

    private int seconds, minutes,hours;
    @FXML
    private Label abc;
    @FXML
    private Label m;
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
            try {
            String t = equipe_plus_gagne();
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data(t,equipe_moins_gagnante2() ),
            new PieChart.Data(equipe_moins_gagnante(),equipe_moins_gagnante1())
 );
       // pieChart.getStyle().charAt(0);
            pieChart.setData( pieChartData);
            pieChart.setTitle("Résultat des equipes : ");
            } catch (SQLException ex) {
            
            }

    }
    
    

      public String equipe_plus_gagne() throws SQLException {
        String query = "select nom_equipe from equipe where tournois_gagne = (select max(tournois_gagne) from equipe)";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
     String max="";
      if(rs.next()){
          max=rs.getString(1);
      }
  
        
return  max;
    }
      
        public String equipe_moins_gagnante() throws SQLException {
        String query = "select nom_equipe from equipe where tournois_gagne = (select min(tournois_gagne) from equipe)";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
      String min ="";
      if(rs.next()){
          min=rs.getString(1);
      }
   
        
return  min;
    }
           
        public List<String> test() throws SQLException {
        String query = "select nom_equipe from equipe where tournois_gagne = (select min(tournois_gagne) from equipe)";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
      String min ="";
      List a = new ArrayList();
      while(rs.next()){
          a.add(rs.getString("nom_equipe"));
      }
   
        
return a;
    }
        public int equipe_moins_gagnante1() throws SQLException {
        String query = "select min(tournois_gagne) from equipe";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
      int min =0;
      if(rs.next()){
          min=rs.getInt(1);
      }
   
        
return  min;
    }
      public int equipe_moins_gagnante2() throws SQLException {
        String query = "select max(tournois_gagne) from equipe";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
      int min =0;
      if(rs.next()){
          min=rs.getInt(1);
      }
   
        
return  min;
    }       
public ObservableList<Equipes> getEquipesList() {
        ObservableList<Equipes> EquipesList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select * from equipe";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Equipes equipes;
            while (rs.next()) {
                //int id,String nom_equipe, int membres, int pts_xp, int tournois_gagne
                equipes = new Equipes(rs.getInt("id"), rs.getString("nom_equipe"), rs.getInt("membres"), rs.getInt("pts_exp"), rs.getInt("tournois_gagne"));
                EquipesList.add(equipes);

            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());

        }
        return EquipesList;
    }
public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_java", "root", "");
            System.out.println("Connection done ! ");
            return conn;
        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
            return null;
        }
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        //To change body of generated methods, choose Tools | Templates.
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
          
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == ta) {
            try {
                root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } else if (event.getSource() == tc) {
     try {
                root = FXMLLoader.load(getClass().getResource("tournois_client.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } else if (event.getSource() ==ea) {
            try {
                root = FXMLLoader.load(getClass().getResource("equipe.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } else if (event.getSource() == ec) {
            try {
                root = FXMLLoader.load(getClass().getResource("equipe_client.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }else if(event.getSource()==Live_tour){
          try {
                root = FXMLLoader.load(getClass().getResource("Live_tournois.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }

    }

    
    
}

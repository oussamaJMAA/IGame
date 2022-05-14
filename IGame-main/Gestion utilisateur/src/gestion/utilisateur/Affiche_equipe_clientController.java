/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import gestion.utilisateur.entities.Equipes;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author rouka
 */
public class Affiche_equipe_clientController implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private TableColumn<Equipes, Integer> colid;
    @FXML
    private TableColumn<Equipes, String> colnom;
    @FXML
    private TableColumn<Equipes, Integer> colmembres;
    @FXML
    private TableColumn<Equipes, Integer> colptsxp;
    @FXML
    private TableColumn<Equipes, Integer> coltg;
  
    @FXML
    private TableView<Equipes> tvequipe;
  
    private Button back;
    @FXML
    private Button out;
    @FXML
    private TextField nom;

    @FXML
    private TextField pts;
    @FXML
    private TextField tg;
    @FXML
    private TextField id;
    private ChoiceBox<String> nom_mem;
    @FXML
    private Label l;
    @FXML
    private Button edit;
    @FXML
    private ImageView admin_image;
    @FXML
    private Label test;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnSettings1;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnPackages1;
    @FXML
    private Button btnSignout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // showEquipes();
               retrievedata a = retrievedata.getInstance("", "",0);
         
         test.setText(a.getUsername());
         
               int jj=a.getImage().lastIndexOf('\\');
admin_image.setImage(new Image(LoggedinController.class.getResourceAsStream(a.getImage().substring(jj+1))));
        tvequipe.setVisible(false);
      nom.setText(getEquipesList().get(0).getNom_equipe());
    
      pts.setText(Integer.toString(getEquipesList().get(0).getPts_xp()));
    tg.setText(Integer.toString(getEquipesList().get(0).getTournois_gagne()));
    id.setText(Integer.toString(getEquipesList().get(0).getId()));
id.setVisible(false);

        try {
          String mm = "";
            for(int i =0;i<test().size();i++){
              //  nom_mem.getItems().add(test().get(i));
           mm+="-> "+test().get(i)+"\n";
            }
            l.setText(mm);
        } catch (SQLException ex) {
          System.out.println(ex.getMessage());
        }
            
      // nom_mem.setOnAction(this::tt);
 
    }
   public void tt(ActionEvent event) {
       
        String a = nom_mem.getValue();
       
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
 public List<String> test() throws SQLException {
     retrievedata b = retrievedata.getInstance("","",0);
        String query = "select u.username from user u inner join equipe e on e.id = u.equipe where e.id=(select equipe from user where username = '"+b.getUsername()+"') ";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
      String min ="";
      List a = new ArrayList();
      while(rs.next()){
          a.add(rs.getString(1));
      }
   
        
return a;
    }
    public ObservableList<Equipes> getEquipesList() {
        ObservableList<Equipes> EquipesList = FXCollections.observableArrayList();
        Connection conn = getConnection();
               retrievedata a= retrievedata.getInstance("testtt","",0);
        String query = "select * from equipe where id=(select equipe from user where username = '"+a.getUsername()+"')";
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

    public void showEquipes() {
        ObservableList<Equipes> list = getEquipesList();
        colid.setCellValueFactory(new PropertyValueFactory<Equipes, Integer>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<Equipes, String>("nom_equipe"));
        colmembres.setCellValueFactory(new PropertyValueFactory<Equipes, Integer>("membres"));
        colptsxp.setCellValueFactory(new PropertyValueFactory<Equipes, Integer>("pts_xp"));
        coltg.setCellValueFactory(new PropertyValueFactory<Equipes, Integer>("tournois_gagne"));

        tvequipe.setItems(list);

    }

private void out_of_team_user(){
     ObservableList<Equipes> list = getEquipesList();
 String query = "update user set equipe = NULL where equipe = "+ list.get(0).getId()+"";

        executeQuery(query);
        }

private void out_of_team_equipe(){
    ObservableList<Equipes> list = getEquipesList();
 String query = "update equipe set membres = membres-1 where id = "+list.get(0).getId()+"";

        executeQuery(query);
     }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        //To change body of generated methods, choose Tools | Templates.
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
            showEquipes();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
 @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == back) {
            try {
                root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Affiche_equipe_clientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        }if (event.getSource() == out) {
      out_of_team_equipe();
      out_of_team_user();
 try {
                root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Affiche_equipe_clientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

if(event.getSource()==edit){
 /*root = FXMLLoader.load(getClass().getResource("edit_team_client.fxml"));  
            stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Edit Team");
                stage.show(); */
 try {
                root = FXMLLoader.load(getClass().getResource("edit_team_client.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Affiche_equipe_clientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
 
}
}

    @FXML
    private void on_click_createTeam(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("equipe_client.fxml")); 
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
    }

    @FXML
    private void on_click_dashboard_button(ActionEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void on_click_users_button(ActionEvent event) {
    }

      @FXML
    private void on_click_tournaments(ActionEvent event) throws IOException {
            root = FXMLLoader.load(getClass().getResource("tournois_client.fxml"));  
             stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("tournois clients");
                stage.show(); 
    }

    @FXML
    private void on_click_teams(ActionEvent event) throws IOException {
             root = FXMLLoader.load(getClass().getResource("affiche_equipe_client.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
    }

@FXML
    private void on_clicki_products(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("viewclient.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
    }
@FXML
    private void on_click_paniers(ActionEvent event) throws IOException {
        
        root = FXMLLoader.load(getClass().getResource("ListPanier.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
        
        
    }
@FXML
    private void on_click_commande(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ListCommande.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
    }

    @FXML
    private void on_click_sign_out(ActionEvent event) {
    }
}

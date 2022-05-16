/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import gestion.utilisateur.entities.Equipes;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
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
    private Button btnMenus;
    @FXML
    private Button btnSettings1;
    @FXML
    private Button btnPackages1;
    @FXML
    private Button btnSignout;
    @FXML
    private Label not_in;
    @FXML
    private Button create;
    @FXML
    private Button btnOverview1;
    @FXML
    private Button btn_blog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          out.setVisible(false);
        try {
            // TODO
            // showEquipes();
            retrievedata a = retrievedata.getInstance("", "",0);
          try{
           InputStream stream = new FileInputStream("C:\\Users\\oussa\\PhpstormProjects\\gaming_app\\public\\uploads\\photos\\"+a.getImage());
      Image image4 = new Image(stream);
      admin_image.setImage(image4);   
              
          }catch(Exception ex){
              System.out.println(ex);
          }
                tvequipe.setVisible(false);
                not_in.setVisible(false);
            if(verif()!=0){
           create.setVisible(false);
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
                
            }else{
            nom.setVisible(false);
            pts.setVisible(false);
            tg.setVisible(false);
            id.setVisible(false);
            not_in.setVisible(true);
            not_in.setText("YOU ARE NOT IN A TEAM");
            }
              
                } catch (SQLException ex) {
            Logger.getLogger(Affiche_equipe_clientController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    
     public int verif() throws SQLException {
       retrievedata a= retrievedata.getInstance("testtt","",0);
      // int idT = Integer.parseInt(id_t.getText());
        String query = "select equipe from user inner join equipe on equipe.id = user.equipe where user.id = "+a.getId()+"";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
    int max = 0;
      if(rs.next()){
          max=rs.getInt(1);
      }
  
        
return  max;
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
    
      /***************************************************/
     public int taille_team() throws SQLException {
         //list.get(0).getId()
       retrievedata a= retrievedata.getInstance("testtt","",0);
      // int idT = Integer.parseInt(id_t.getText());
       ObservableList<Equipes> list = getEquipesList();
        String query = "select id from equipe where id = "+list.get(0).getId()+" and membres = 0";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
    int max = 0;
      if(rs.next()){
          max=rs.getInt(1);
      }
  
        
return  max;
    }
     public void delete_team() throws SQLException{
     ObservableList<Equipes> list = getEquipesList();
 String query = "delete from equipe where id = "+ taille_team()+"";

        executeQuery(query);
     }
     
 @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException {
        if (event.getSource() == back) {
            try {
                root = FXMLLoader.load(getClass().getResource("Loggedin.fxml"));
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
      if(taille_team()!=0){
      delete_team();
      }
 try {
                root = FXMLLoader.load(getClass().getResource("Loggedin.fxml"));
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
         root = FXMLLoader.load(getClass().getResource("Produit_Client.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
    }
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

    @FXML
    private void on_click_games(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("Game_Client.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
    }

    @FXML
    private void on_click_blogs(ActionEvent event) throws IOException {
          root = FXMLLoader.load(getClass().getResource("Publication_Client.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rouka
 */
public class Edit_team_clientController implements Initializable {

    private Button back;
    @FXML
    private TextField nom;
    @FXML
    private Label l;
    @FXML
    private Button edit;
    @FXML
    private Button add;

    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField id;
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
    private Button btnOverview1;
    @FXML
    private Button btn_blog;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                retrievedata a = retrievedata.getInstance("", "",0);
         
        // test.setText(a.getUsername());
   try{
           InputStream stream = new FileInputStream("C:\\Users\\oussa\\PhpstormProjects\\gaming_app\\public\\uploads\\photos\\"+a.getImage());
      Image image4 = new Image(stream);
      admin_image.setImage(image4);   
              
          }catch(Exception ex){
              System.out.println(ex);
          }
       Affiche_equipe_clientController test = new Affiche_equipe_clientController();
       nom.setText(test.getEquipesList().get(0).getNom_equipe());
     id.setText(Integer.toString(test.getEquipesList().get(0).getId()));
     id.setVisible(false);
          
         try {
          String mm = "";
            for(int i =0;i<test.test().size();i++){
              //  nom_mem.getItems().add(test().get(i));
           mm+="-> "+test.test().get(i)+"\n";
            }
            l.setText(mm);
        } catch (SQLException ex) {
          System.out.println(ex.getMessage());
        }

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

       public void updateEquipe() throws ParseException {

        String query = "update equipe set nom_equipe ='"+nom.getText()+"' where id = "+id.getText()+"";
 executeQuery(query);
 
 }

 

    @FXML
    private void handleButtonAction(ActionEvent event) throws ParseException {
        if(event.getSource()==add){
          try {
                root = FXMLLoader.load(getClass().getResource("edit_member.fxml"));
                  stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Edit member");
                stage.show(); 
            } catch (IOException ex) {
                Logger.getLogger(Affiche_equipe_clientController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
          

        }
        if(event.getSource()==back){
        try {
                root = FXMLLoader.load(getClass().getResource("affiche_equipe_client.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Affiche_equipe_clientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        }
        if(event.getSource()==edit){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Vous etes entrain de modifier, Confirmer la modification");
            if (alert.showAndWait().get() == ButtonType.OK) {

                 updateEquipe();
            
          
         try {
                root = FXMLLoader.load(getClass().getResource("affiche_equipe_client.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(Affiche_equipe_clientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
        }
    }

    @FXML
    private void on_click_dashboard_button(ActionEvent event) throws IOException {
             root = FXMLLoader.load(getClass().getResource("Loggedin.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show();
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

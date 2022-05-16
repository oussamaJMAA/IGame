/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author oussa
 */
public class LoggedinController implements Initializable {
      private Stage stage ;
    private Scene scene ;
    private Parent root ;
  
    
    
  @FXML
    private Label welcomelabel;
    @FXML
    private ImageView user_image;
    @FXML
    private ImageView admin_image;
    @FXML
    private Label test;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages1;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnOverview1;
    @FXML
    private Button btnSettings1;
    @FXML
    private Button btn_blog;
  

  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
          try {
              set();
          } catch (FileNotFoundException ex) {
              System.out.println(ex.getMessage());
          }
    }
    public void set() throws FileNotFoundException{
        retrievedata a = retrievedata.getInstance("", "",0);
         
         test.setText(a.getUsername());
         
InputStream stream = new FileInputStream("C:\\Users\\oussa\\PhpstormProjects\\gaming_app\\public\\uploads\\photos\\"+a.getImage());
      Image image4 = new Image(stream);
      admin_image.setImage(image4);
    }
    /**
     *
     * @param event
     * @throws IOException
     */

    @FXML
    private void getLogout(MouseEvent event) throws IOException, Throwable {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Are you sure you want to logout ?");
        
        if (alert.showAndWait().get() == ButtonType.OK) {
             

              
            
              
              
              Runtime.getRuntime().exec(
                      "cmd /c logout.bat", null, new File("C:\\Users\\oussa\\Desktop\\"));
              System.exit(0);
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));  
           stage= (Stage)((Node)event.getSource()).getScene().getWindow();
           scene= new Scene(root);
           stage.setScene(scene);
           stage.show();

        }}
    @FXML
    private void getSettings(MouseEvent event) throws IOException {
           root = FXMLLoader.load(getClass().getResource("userSettings.fxml"));  
            stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("user Settings");
                stage.show(); 
    }

    @FXML
    private void on_click_message_button(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("ChatBot.fxml"));
        
              stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("ChatBot");
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
    private void on_click_sign_out(ActionEvent event) {
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


    private void on_click_blog(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Publication_Client.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
        
    }

    @FXML
    private void on_click_dashboard_button(ActionEvent event) {
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

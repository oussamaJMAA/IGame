/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnPackages1;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnPackages11;
    @FXML
    private Button btnPackages111;
  

  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     set();
    }
    public void set(){
        retrievedata a = retrievedata.getInstance("", "",0);
         
         test.setText(a.getUsername());
         
               int jj=a.getImage().lastIndexOf('\\');
admin_image.setImage(new Image(LoggedinController.class.getResourceAsStream(a.getImage().substring(jj+1))));
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
    private void on_click_sign_out(ActionEvent event) {
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
    private void on_click_accueil(ActionEvent event) {
    }

    @FXML
    private void on_click_blog(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PubCom.fxml"));  
              stage= (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("iGame");
                stage.show(); 
        
    }
 
                                                        
    
    
}

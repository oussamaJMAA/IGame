/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import javafx.stage.StageStyle;

/**
 *
 * @author oussa
 */
public class GestionUtilisateur extends Application {
 
    
   
    @Override
    public void start(Stage primaryStage) throws Exception {
    
  /*
        FXMLLoader firstPaneLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent firstPane = firstPaneLoader.load();
         Scene LoginScene = new Scene(firstPane, 868, 600);
     // getting loader and a pane for the second scene
        FXMLLoader secondPageLoader = new FXMLLoader(getClass().getResource("Register.fxml"));
        Parent secondPane = secondPageLoader.load();
        Scene RegisterScene = new Scene(secondPane, 868, 600);

        // the loggedin pane
        FXMLLoader LoggedinPaneLoader = new FXMLLoader(getClass().getResource("Loggedin.fxml"));
        Parent LoggedinPane = LoggedinPaneLoader.load();
        Scene LoggedinScene = new Scene(LoggedinPane, 868, 600);
        
        // injecting second scene into the controller of the first scene
        LoginController firstPaneController = (LoginController) firstPaneLoader.getController();
       firstPaneController.setRegisterScene(RegisterScene);
      
          
        // injecting first scene into the controller of the second scene
        RegisterController secondPaneController = (RegisterController) secondPageLoader.getController();
        secondPaneController.setLoginScene(LoginScene);
      
     */
       
  
  try{
      
  
       Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       Scene scene= new Scene(root);
   
       primaryStage.setScene(scene) ;
        primaryStage.setTitle("iGame.exe");
             
        primaryStage.show();
          } catch(Exception e){
              e.printStackTrace();
          }
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

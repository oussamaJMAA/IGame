/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import javafx.scene.image.Image;
import entities.Produit;
import entities.Promotion;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.ProduitServices;
import service.PromotionServices;
import tools.MaConnexion;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import static javafx.application.Application.launch;
/**
 *
 * @author Fayechi
 */
 

public class main extends Application {
    
    
    public void start(Stage primaryStage) {
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/menu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
    }



    


     
    public static void main(String[] args) {
        launch(args);
    }
    
}
 

    
    


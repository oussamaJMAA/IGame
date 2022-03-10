/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Malak
 */
public class NewFXMain extends Application {
     public static final String CURRENCY = "$";

    @Override
   public void start(Stage primaryStage) {
        
         try {
            URL fxURL = getClass().getResource("gestionJeu.fxml"); 
            Parent root = FXMLLoader.load(fxURL);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("gestion jeu");
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        
    }
   }

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        launch(args);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class ChatBotController implements Initializable {

    @FXML
    private TextField tf;
    @FXML
    private TextArea ta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void on_click_send(ActionEvent event) {
        ta.appendText("YOU: "+ tf.getText().toLowerCase()+"\n");
        if (tf.getText().equals("hi")){
                int rand = (int) (Math.random()*3 +1);
           
      if(rand==1){
          bot("Hello there !");
          
      }else if (rand==2){
              bot("Hola!");
      }
      else if (rand==3){
             bot("Hi !"); 
      }
   
        }
         else  if (tf.getText().contains("good morning")){
            bot("Good morning!");
           
        }
      else  if (tf.getText().contains("what's your name")){
            bot("My name is oussama");
           
        } else  if (tf.getText().contains("application")){
            bot("This a GUI Application made by We Don't Byte");
           
        } else  if (tf.getText().contains("bye")){
            bot("Bye! See you soon");
           
        } else  if (tf.getText().contains("good night")){
            bot("Good night !");
           
        }
          
        else { 
            int rand = (int) (Math.random()*3 +1);
           
      if(rand==1){
          bot("Sorry ,i don't understand you !");
          
      }else if (rand==2){
              bot("Sorry ,i don't understand you bro  !");
      }
      else if (rand==3){
             bot("No response for that !"); 
      }
           
        }
    }
    private void bot( String string ){
        ta.appendText("BOT : "+string+"\n");
   
       
    }
    
}

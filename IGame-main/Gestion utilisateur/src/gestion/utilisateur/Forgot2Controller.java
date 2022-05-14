/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class Forgot2Controller implements Initializable {

    @FXML
    private TextField codetf;
    @FXML
    private Label code_label;
    @FXML
    private Label warning_label;
    @FXML
    private Label usernamelabel;
public void setcodelabel(String code){
    code_label.setText(code);
}
public void setusername(String username){
    usernamelabel.setText(username);
}
private String verify_code ;
private String username="";
Parent root;
Stage stage;
Scene scene;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
    }    

    @FXML
    private void on_click_confirm(ActionEvent event) throws IOException {
        username= usernamelabel.getText();
          verify_code =code_label.getText();
          if(codetf.getText().equals(verify_code)){
               FXMLLoader loader = new FXMLLoader(getClass().getResource("Newpassword.fxml"));
                    root = loader.load();
        NewpasswordController newpasswordController=loader.getController();
                  newpasswordController.setemail(username);
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root, 1400, 800);
                    stage.setScene(scene);
                    stage.show();
              
          }
          else {
               warning_label.setText("Wrong code");
          }
    }
        
}

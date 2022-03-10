/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import Entities.Game;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Malak
 */
public class ItemController implements Initializable {

    private Label nameGame;
    

    private Game game;
   // private MyListener myListener;
    @FXML
    private ImageView Game_img;

    public void setData(Game game) {
        this.game = game;
     //   this.myListener = myListener;
        nameGame.setText(game.getGame_name());
      //  game.setText(ItemController.CURRENCY + game.getgame_description());
        Image image = new Image(getClass().getResourceAsStream(game.getGame_img()));
       // img.setImage(image);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
}

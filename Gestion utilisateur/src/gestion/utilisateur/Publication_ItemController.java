/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gestion.utilisateur;

import gestion.utilisateur.entities.Publication;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author rouka
 */
public class Publication_ItemController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label titre;
    @FXML
    private Label contenu;
    @FXML
    private Label commentaire;
    @FXML
    private TextField tfCommentaire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(Publication p){
        try{
           InputStream stream = new FileInputStream("C:\\Users\\oussa\\PhpstormProjects\\gaming_app\\public\\uploads\\photos\\"+p.getImage());
      Image image4 = new Image(stream);
      image.setImage(image4);  
        }catch(Exception e){
            System.out.println(e);
        }
        titre.setText(p.getTitre());
    contenu.setText(p.getContenu());
    
    }
}

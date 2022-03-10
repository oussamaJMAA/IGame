/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFx;

import entities.Commande;
import entities.Panier;
import entities.Produit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import service.PanierCRUD;
import service.ProduitService;

/**
 * FXML Controller class
 *
 * @author Magui
 */
public class AjouterPanierController  implements Initializable {

    @FXML
    private TextField nomProduit;
    private TextField prix;
    private ComboBox<String> produitt;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_vider;

    PanierCRUD service = new PanierCRUD();
    @FXML
    private ImageView img;
    @FXML
    private Button btn_img;
    
    
    Panier p= new Panier();
    @FXML
    private TextField qte;
    @FXML
    private ComboBox<Produit> comboProduit;
    ProduitService ps=new ProduitService();
         private ObservableList<Produit> ProduitData = FXCollections.observableArrayList();
  public int idClient=22;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
            List<Produit> list= new ArrayList<Produit>();
       list= ps.getAllProduit();
       ProduitData.addAll(list);
       comboProduit.setItems(ProduitData);
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        if(nomProduit.getText().equals("")&& prix.getText().equals("")){
            Alert a = new Alert(Alert.AlertType.ERROR, "champs vide", ButtonType.CLOSE);
            a.show();
        }
        else
        {
                    Produit p2 = comboProduit.getSelectionModel().getSelectedItem();

      
        p.setIdProduit(Long.valueOf(p2.getId()));
        p.setNomProduit(p2.getNom());
        p.setPrix(Integer.valueOf(nomProduit.getText()));
        p.setIdClient(idClient);
       
        service.ajouterPanier(p);
        service.updateQuantité(p2.getId());
      affnotif();
    }
    }
    @FXML
    private void reset(ActionEvent event) {
        nomProduit.setText("");
        prix.setText("");
    }
     public void affnotif(){
         Notifications notificationBuilder = Notifications.create()
        .title("Alert").text("Panier ajouté avec succé").graphic(null).hideAfter(Duration.seconds(10))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

  

    @FXML
    
    private void charger_image(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
             
            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = 
                    new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
            FileChooser.ExtensionFilter extFilterjpg = 
                    new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
            FileChooser.ExtensionFilter extFilterPNG = 
                    new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
            FileChooser.ExtensionFilter extFilterpng = 
                    new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
            fileChooser.getExtensionFilters()
                    .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

            
            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
          
             p.setImage(file.getAbsolutePath());
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                img.setImage(image);
                
            } catch (IOException ex) {
                Logger.getLogger(AjouterCommandeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    @FXML
    private void charger_image(MouseEvent event) {
    }

    @FXML
    private void charge(MouseEvent event) {
        Produit p = comboProduit.getSelectionModel().getSelectedItem();
        nomProduit.setText(String.valueOf(p.getPrix()));
    }


    
}

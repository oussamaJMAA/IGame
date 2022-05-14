/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class ProduitController implements Initializable {

    @FXML
    private AnchorPane recpane;
    @FXML
    private TextField id_nom;
    @FXML
    private Label nom;
    @FXML
    private Label prix;
    @FXML
    private TextField id_prix;
    @FXML
    private Label qte;
    @FXML
    private TextField id_qte;
    @FXML
    private Button ajouter;
    @FXML
    private TableView<?> viewProduit;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> d;
    @FXML
    private TableColumn<?, ?> pr;
    @FXML
    private TableColumn<?, ?> qt;
    @FXML
    private TableColumn<?, ?> re;
    @FXML
    private TableColumn<?, ?> eimage;
    @FXML
    private Button supprimer;
    @FXML
    private Button update;
    @FXML
    private Label idd;
    @FXML
    private TextField id1;
    @FXML
    private Button b1;
    @FXML
    private TextField filterField;
    @FXML
    private Label reference;
    @FXML
    private TextField id_ref;
    @FXML
    private Button insert_image;
    @FXML
    private Button ftMail;
    @FXML
    private ImageView image_view;
    @FXML
    private Label file_path;
    @FXML
    private Button st;
    @FXML
    private Button v1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutproduit(ActionEvent event) {
    }

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }

    @FXML
    private void Edit(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) {
    }

    @FXML
    private void chercher(KeyEvent event) {
    }

    @FXML
    private void insertImage(ActionEvent event) {
    }

    @FXML
    private void mailing(ActionEvent event) {
    }

    @FXML
    private void stat(ActionEvent event) {
    }

    @FXML
    private void viewlist(ActionEvent event) {
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFx;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import entities.Commande;
import entities.Pdf;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import service.CommandeCRUD;

/**
 * FXML Controller class
 *
 * @author Firas
 */
public class ListCommandeAdminController implements Initializable {

    @FXML
    private ListView<Commande> listview;
    @FXML
    private ComboBox<String> combo;
 CommandeCRUD service= new CommandeCRUD();
     private ObservableList<Commande> CommandeData = FXCollections.observableArrayList();
    @FXML
    private Button btn_etat;
    @FXML
    private Button pdf;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
          List<Commande> list= new ArrayList<Commande>();
       list= service.displayCommmande();
       CommandeData.addAll(list);
       listview.setItems(CommandeData);
       
        combo.getItems().add("en cours");
        combo.getItems().add("annulé");
        combo.getItems().add("livré");
        combo.getItems().add("en attend");
     
    }    

    @FXML
    private void charger(MouseEvent event) {
        
         Commande c = listview.getSelectionModel().getSelectedItem();
        
    }
      public void resetTableData() {
        List<Commande> list = new ArrayList<>();

        list = service.displayCommmande();
        ObservableList<Commande> data = FXCollections.observableArrayList(list);
        listview.setItems(data);
    }

    @FXML
    private void setEtat(ActionEvent event) throws SQLException {
                 Commande c = listview.getSelectionModel().getSelectedItem();
                 service.setEtat(c, combo.getSelectionModel().getSelectedItem());
                 resetTableData();
    }

    @FXML
    private void pdf(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {

        
        Pdf pd=new Pdf();
        try{
        pd.GeneratePdf("list of users");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(CommandeCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    }
    


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;



import static GUI.ProduitController.cnx;
import entities.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import entities.Promotion;
import java.awt.HeadlessException;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import service.ProduitServices;
import service.PromotionServices;
import tools.MaConnexion;


public class PromotionController implements Initializable {

    ObservableList<Promotion> list = FXCollections.observableArrayList();
@FXML
    private ComboBox<String> id_nom;
@FXML
    private TextField id_promo;
@FXML
    private DatePicker id_date;
    @FXML
    private TableView<Promotion> viewPromotion;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> n;
    @FXML
    private TableColumn<?, ?> pro ;
    @FXML
    private TableColumn<?, ?> dt;
@FXML
private Button ajouter ;
    @FXML
    private Label nom;
    @FXML
    private Label promo;
    @FXML
    private Label date;
    @FXML
    private Button supprimer;
      static Connection cnx;
  
    private PreparedStatement pst = null ;
  static ResultSet rs;
    @FXML
    private TextField iddd;
    @FXML
    private AnchorPane recpane;
  
    @FXML
    private Button ss;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try { 
            initialiserlist();
        } catch (SQLException ex)
 {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
           Afficher();
    }
@FXML
public void ajoutpromotion(ActionEvent event) throws SQLException{ 
PromotionServices rs = new PromotionServices();
String date = id_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
int r = Integer.parseInt(id_promo.getText());

rs.ajouterPromotion(new Promotion(id_nom.getValue(),date,r));
list.clear();

id_nom.getItems().clear();

initialiserlist();
Afficher();
}  
public void Afficher(){
 id.setCellValueFactory(new PropertyValueFactory<>("id"));
          n.setCellValueFactory(new PropertyValueFactory<>("nom"));
          pro.setCellValueFactory(new PropertyValueFactory<>("prixPro"));
          dt.setCellValueFactory(new PropertyValueFactory<>("date"));
        viewPromotion.setItems(list);
}
 public void initialiserlist() throws SQLException{
             try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM promotion");
            while(rs.next()){
            list.add(new Promotion(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
         }
            } catch (SQLException ex) {
            Logger.getLogger(PromotionController.class.getName()).log(Level.SEVERE, null, ex);
        }

              Connection cnx = MaConnexion.getInstance().getCnx();
        ResultSet rs = cnx.createStatement().executeQuery("SELECT nom FROM produit");
           while(rs.next())

            
                id_nom.getItems().addAll(rs.getString("nom"));
           
        } 
   public Promotion gettempProduit(TableColumn.CellEditEvent edittedCell) {
        Promotion test = viewPromotion.getSelectionModel().getSelectedItem();
        return test;
    }
   
     @FXML
    public void Edit () throws SQLException{   
        try {
            cnx = MaConnexion.getInstance().getCnx();
            String value0 = iddd.getText();
            String value2 = id_nom.getValue();
            
            String value3 = id_promo.getText();
            
            String value4 = id_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            
            String sql = "update promotion set nom= '"+value2+"',date= '"+value4+"',prixPro= '"+
                    value3+"' where id='"+value0+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
              
            JOptionPane.showMessageDialog(null, "Update");
            iddd.setText("");
             
           
    iddd.setText("");

    id_nom.setValue("");
    id_promo.setText("");
      
    
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }list.clear();

id_nom.getItems().clear();

initialiserlist();
Afficher();
    }
  
    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException {
        
        int index = viewPromotion.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
            Connection cnx = MaConnexion.getInstance().getCnx();
     //ResultSet rsd =null ;
    iddd.setText(id.getCellData(index).toString());
    id_nom.setValue(n.getCellData(index).toString());
    id_promo.setText(pro.getCellData(index).toString()); 
    
   
   
                
     
    
          
    

    
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
          TableColumn.CellEditEvent edittedcell = null;
        Promotion p = gettempProduit(edittedcell);

        if (p != null) {

            int i = p.getId();
            PromotionServices cat = new PromotionServices();

            int s = cat.supprimerproduit(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("promotion supprimé");
                alert.showAndWait();
              
                
               iddd.setText("");

    id_nom.setValue("");
    id_promo.setText("");
    
   
            }list.clear();

id_nom.getItems().clear();

initialiserlist();
Afficher();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }
    }

    @FXML
    private void b(ActionEvent event)throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/menu.fxml"));
           recpane.getChildren().setAll(pane); 
        
    
       }
}
    


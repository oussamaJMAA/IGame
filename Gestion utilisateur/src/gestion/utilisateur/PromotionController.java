/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gestion.utilisateur;



import static gestion.utilisateur.ProduitController.cnx;
import gestion.utilisateur.entities.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import gestion.utilisateur.entities.Promotion;
import java.awt.HeadlessException;
import java.io.IOException;
import javafx.scene.image.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.util.Collections.list;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import gestion.utilisateur.service.ProduitServices;
import gestion.utilisateur.service.PromotionServices;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import toolsp.MaConnexion;


public class PromotionController implements Initializable {
 private Stage stage;
    private Scene scene;
    private Parent root;
    ObservableList<Promotion> list = FXCollections.observableArrayList();
@FXML
    private ComboBox<String> id_nom;
@FXML
    private TextField id_promo;
@FXML
    private DatePicker id_date;
@FXML
    private DatePicker id_datef;

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
    private TableColumn<?, ?> df;
@FXML
private Button ajouter ;
    @FXML
    private Label nom;
    @FXML
    private Label promo;
    @FXML
    private Label date;
    @FXML
    private Label datef;
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
    private ImageView admin_image;
    @FXML
    private Label test;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnPackages1;
    @FXML
    private Button btnSignout;
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
public void ajoutpromotion(ActionEvent event) throws SQLException, ParseException{ 
int p = JOptionPane.showConfirmDialog(null,"Do you really want to add","add",JOptionPane.YES_NO_OPTION);
 if(p==0){
    PromotionServices rs = new PromotionServices();
    
        
            
            
    if(controleDeSaisi()){
        SimpleDateFormat sdformat = new
                SimpleDateFormat("yyyy-MM-dd");
            Date date2 = sdformat.parse(id_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            Date date1 = sdformat.parse(id_datef.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            System.out.println("Date-1: " + 
                               sdformat.format(date1));
            System.out.println("Date-2: " + 
                               sdformat.format(date2));
            if (date1.after(date2)) {
                  String date = id_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
String datef = id_datef.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
int r = Integer.parseInt(id_promo.getText());
                rs.ajouterPromotion(new Promotion(id_nom.getValue(),date,r,datef));
            }else{
                  JOptionPane.showMessageDialog(null, "les dates ne sont pas en ordre ");
            }
    }

list.clear();

id_nom.getItems().clear();

initialiserlist();
Afficher();
}}
private boolean controleDeSaisi() {  

        if (id_promo.getText().isEmpty() || id_nom.getValue().isEmpty()
                ) {
          JOptionPane.showMessageDialog(null, "verifier les champs");
            return false;
        } else {

           

           if (!Pattern.matches("[A-z]*", id_nom.getValue())) {
                  JOptionPane.showMessageDialog(null, "verifier le nom");
                id_nom.requestFocus();
                
                return false;
            }
            if (!Pattern.matches("[0-9]*", id_promo.getText())) {
               
                  JOptionPane.showMessageDialog(null, "verifier les promotions");
                  id_promo.requestFocus();
                id_promo.selectEnd();
                return false;
            }
        
           
        }
        return true;
    }
public void Afficher(){
 id.setCellValueFactory(new PropertyValueFactory<>("id"));
          n.setCellValueFactory(new PropertyValueFactory<>("nom"));
          pro.setCellValueFactory(new PropertyValueFactory<>("prixPro"));
          dt.setCellValueFactory(new PropertyValueFactory<>("date"));
          df.setCellValueFactory(new PropertyValueFactory<>("datef"));
        viewPromotion.setItems(list);
}
 public void initialiserlist() throws SQLException{
             try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM promotion");
            while(rs.next()){
            list.add(new Promotion(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5)));
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
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");

alert.setContentText("do you really want to modify?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){ try {
            cnx = MaConnexion.getInstance().getCnx();
            String value0 = iddd.getText();
            String value2 = id_nom.getValue();
            
            String value3 = id_promo.getText();
            
            String value4 = id_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String value5 = id_datef.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            
            String sql = "update promotion set nom= '"+value2+"',date= '"+value4+"',datef= '"+value5+"',prixPro= '"+
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
        }
}




list.clear();

id_nom.getItems().clear();

initialiserlist();
Afficher();
    }
  
    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException, ParseException {
        
        int index = viewPromotion.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
            Connection cnx = MaConnexion.getInstance().getCnx();
     //ResultSet rsd =null ;
     Promotion p =  viewPromotion.getSelectionModel().getSelectedItem();
    iddd.setText(id.getCellData(index).toString());
    id_nom.setValue(n.getCellData(index).toString());
    id_promo.setText(pro.getCellData(index).toString()); 
    // id_date.setDate(dt.getCellData(index).toString()); 
    
  // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
 //Date date = new SimpleDateFormat("dd-MM-yyyy").parse(p.getDate());
 // LocalDate test = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
 // id_date.setValue(test);
   
                
     
    
          
    

    
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
          TableColumn.CellEditEvent edittedcell = null;
        Promotion p = gettempProduit(edittedcell);

        if (p != null) {

            int i = p.getId();
            PromotionServices cat = new PromotionServices();
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");

alert.setContentText("do you really want to delete ?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
            int s = cat.supprimerproduit(i);
            if (s == 1) {
               
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
        } }else {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }
        
    }
    private void b(ActionEvent event)throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/menu.fxml"));
           recpane.getChildren().setAll(pane); 
        
    
       }

   @FXML
    private void on_click_dashboard_button(ActionEvent event) throws IOException {
      root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void on_click_users_button(ActionEvent event) throws IOException {
       root = FXMLLoader.load(getClass().getResource("AdminSpace.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void on_click_sign_out(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Are you sure you want to logout ?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            retrievedata b = retrievedata.getInstance("", "",0);
            b.cleanUserSession();
          root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

    @FXML
    private void on_click_tournaments(ActionEvent event) throws IOException {
                 root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); //khali hedhi pour le moment
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
    }

    @FXML
    private void on_click_teams(ActionEvent event) throws IOException {
             root = FXMLLoader.load(getClass().getResource("equipe.fxml")); //khali hedhi pour le moment
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
    }

       
    }
    


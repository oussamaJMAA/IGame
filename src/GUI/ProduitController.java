/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entities.Produit;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ProduitServices;
import tools.MaConnexion;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ProduitController implements Initializable {
ObservableList<Produit> list = FXCollections.observableArrayList();
//private Stage stage;
//private Scene scene;
//private Parent root;
@FXML
private TextField id1;
@FXML 
private Label idd;
@FXML
    private TextField id_nom;
@FXML
    private TextField id_prix;
@FXML
    private TextField id_qte;
    @FXML
    private TableView<Produit> viewProduit;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> d;
    @FXML
    private TableColumn<?, ?> pr ;
    @FXML
    private TableColumn<?, ?> qt;
    
@FXML
private Button ajouter ;
  static Connection cnx;
  
    private PreparedStatement pst = null ;
  static ResultSet rs;

 
    @FXML
    private Label nom;
    @FXML
    private Label prix;
    @FXML
    private Label qte;
    @FXML
    private Button supprimer;
    @FXML
    private Button update;
    @FXML
    private Button b1;
@FXML
    private AnchorPane recpane;
   
    @FXML
    private TextField filterField;
  
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try { 
            initialiserlist();
            

        } catch (SQLException ex)
 {
     
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }Afficher();
        
        

    }    
@FXML
public void ajoutproduit(ActionEvent event) throws SQLException{

ProduitServices ps = new ProduitServices();
int r = Integer.parseInt(id_prix.getText());
int f = Integer.parseInt(id_qte.getText());
 
ps.ajouterProduit(new Produit(id_nom.getText(),r,f));

list.clear();
initialiserlist();

Afficher();
viewProduit.refresh();


}
public void Afficher(){
 id.setCellValueFactory(new PropertyValueFactory<>("id"));
          d.setCellValueFactory(new PropertyValueFactory<>("nom"));
          pr.setCellValueFactory(new PropertyValueFactory<>("prix"));
          qt.setCellValueFactory(new PropertyValueFactory<>("qte"));
        viewProduit.setItems(list);
}

  
       public void initialiserlist() throws SQLException{
             try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet ps = cnx.createStatement().executeQuery("SELECT * FROM produit");
            while(ps.next()){
            list.add(new Produit(ps.getInt(1),ps.getString(2),ps.getInt(3),ps.getInt(4)));
        }
            } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }
   
    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException {
        
        int index = viewProduit.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
            Connection cnx = MaConnexion.getInstance().getCnx();
     
    id1.setText(id.getCellData(index).toString());
    id_nom.setText(d.getCellData(index).toString());
    id_prix.setText(pr.getCellData(index).toString());
    id_qte.setText(qt.getCellData(index).toString());
    
   
   
                
     
    
          
    

    
    }
    public Produit gettempProduit(TableColumn.CellEditEvent edittedCell) {
        Produit test = viewProduit.getSelectionModel().getSelectedItem();
        return test;
    }

     @FXML
    public void Edit () throws SQLException{   
        try {
            cnx = MaConnexion.getInstance().getCnx();
            String value0 = id1.getText();
            String value2 = id_nom.getText();
            
            String value3 = id_prix.getText();
            
            String value4 = id_qte.getText();
            
            String sql = "update produit set nom= '"+value2+"',qte= '"+value4+"',prix= '"+
                    value3+"' where id='"+value0+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
              
            JOptionPane.showMessageDialog(null, "Update");
            id1.setText("");
            
    id1.setText("");

    id_nom.setText("");
    id_prix.setText("");
    
    id_qte.setText("");
      
    
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
         list.clear();
                initialiserlist(); 
                Afficher();
                viewProduit.refresh();
    }
    @FXML
 public void supprimer(ActionEvent event) throws SQLException {
  
 
   TableColumn.CellEditEvent edittedcell = null;
        Produit p = gettempProduit(edittedcell);

        if (p != null) {

            int i = p.getId();
            ProduitServices cat = new ProduitServices();

            int s = cat.supprimerproduit(i);
            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("produit supprimé");
                alert.showAndWait();
              
                list.clear();
                initialiserlist(); 
                Afficher();
                viewProduit.refresh();
                  id1.setText("");
    id_nom.setText("");
    id_prix.setText("");
    id_qte.setText("");
   
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }


    }
   @FXML
     public void chercher(){
    ProduitServices re= new ProduitServices() ;
    List<Produit>results = new ArrayList<>();
    results = re.afficherProduit();
     FilteredList<Produit> filteredData = new FilteredList<>(list , b -> true);
		Produit r = new Produit();
		
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(produit -> {
				
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (produit.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(r.getPrix()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(r.getQte()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Produit> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(viewProduit.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		viewProduit.setItems(sortedData);
               
        
    }
    @FXML
    private void back(ActionEvent event)  throws IOException 
        {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/menu.fxml"));
           recpane.getChildren().setAll(pane);
    }

   
    }
    
   
    
    
    

            

   



     

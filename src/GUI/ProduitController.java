/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javafx.scene.image.Image;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.spi.DirStateFactory.Result;
import entities.Produit;
import entities.Promotion;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import java.util.Optional;
import java.util.Properties;
import java.util.regex.Pattern;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ProduitController implements Initializable {
ObservableList<Produit> list = FXCollections.observableArrayList();

@FXML
private TextField id1;
@FXML 
private Label idd;
@FXML
    private TextField id_nom;
@FXML
    private TextField id_ref;
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
    private TableColumn<?, ?> re;
    private TableColumn<?, ?> im;

    
@FXML
private Button ajouter ;
  static Connection cnx;
  
    private PreparedStatement pst = null ;
  static ResultSet rs;

  @FXML
    private Label reference;
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

    @FXML
    private Button ftMail;

    @FXML
    private ImageView image_view;
    @FXML
    private Label file_path;
    @FXML
    private Button insert_image;
    @FXML
    private TableColumn<?, ?> eimage;
    @FXML
    private Button st;
    @FXML
    private Button v1;
  
   

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
int p = JOptionPane.showConfirmDialog(null,"Do you really want to add","add",JOptionPane.YES_NO_OPTION);
 if(p==0){
     if(controleDeSaisi())
{ 
ProduitServices ps = new ProduitServices();
int r = Integer.parseInt(id_prix.getText());
int f = Integer.parseInt(id_qte.getText());
 
ps.ajouterProduit(new Produit(id_nom.getText(),r,f,id_ref.getText(),file_path.getText()));

list.clear();
initialiserlist();

Afficher();
viewProduit.refresh();

} 
}}
private boolean controleDeSaisi() {  

        if (id_nom.getText().isEmpty() || id_prix.getText().isEmpty()
                || id_qte.getText().isEmpty()|| id_ref.getText().isEmpty()||file_path.getText().isEmpty()) {
          JOptionPane.showMessageDialog(null, "verifier les champs");
            return false;
        } else {

           

           if (!Pattern.matches("[A-z]*", id_nom.getText())) {
                  JOptionPane.showMessageDialog(null, "verifier le nom");
                id_nom.requestFocus();
                id_nom.selectEnd();
                return false;
            }
            if (!Pattern.matches("[0-9]*", id_prix.getText())) {
               
                  JOptionPane.showMessageDialog(null, "verifier les prix");
                  id_prix.requestFocus();
                id_prix.selectEnd();
                return false;
            }
            if (!Pattern.matches("[0-9]*", id_qte.getText())) {
               
                  JOptionPane.showMessageDialog(null, "verifier  quantite ");
                  id_qte.requestFocus();
                id_qte.selectEnd();
                return false;
            }
           
        }
        return true;
    }
public void Afficher(){
 id.setCellValueFactory(new PropertyValueFactory<>("id"));
          d.setCellValueFactory(new PropertyValueFactory<>("nom"));
          pr.setCellValueFactory(new PropertyValueFactory<>("prix"));
          qt.setCellValueFactory(new PropertyValueFactory<>("qte"));
           re.setCellValueFactory(new PropertyValueFactory<>("reference"));
           eimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        viewProduit.setItems(list);
}

  
       public void initialiserlist() throws SQLException{
             try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet ps = cnx.createStatement().executeQuery("SELECT * FROM produit");
            while(ps.next()){
            list.add(new Produit(ps.getInt(1),ps.getString(3),ps.getInt(4),ps.getInt(5),ps.getString(2),ps.getString(6)));
        }
            } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }
   
    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException {
        
         Produit e = viewProduit.getSelectionModel().getSelectedItem();
        
        int index = viewProduit.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
            Connection cnx = MaConnexion.getInstance().getCnx();
     
    id1.setText(id.getCellData(index).toString());
    id_nom.setText(d.getCellData(index).toString());
    id_prix.setText(pr.getCellData(index).toString());
    id_qte.setText(qt.getCellData(index).toString());
     id_ref.setText(re.getCellData(index).toString());
//    tfphoto.setText(im.getCellData(index).toString());
    //eimage.setText(eimage.getCellData(index).toString());
    
   
   
          String picture = "file:" + e.getImage();

        Image image = new Image(picture, 110, 110, false, true);

        image_view.setImage(image);

        String path = e.getImage();

        file_path.setText(path);
        file_path.setOpacity(0);       
     
    
          
    

    
    }
    public Produit gettempProduit(TableColumn.CellEditEvent edittedCell) {
        Produit test = viewProduit.getSelectionModel().getSelectedItem();
        return test;
    }

     @FXML
    public void Edit () throws SQLException{   
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Look, a Confirmation Dialog");
alert.setContentText("Are you ok with this?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){try {
            cnx = MaConnexion.getInstance().getCnx();
            String value0 = id1.getText();
            String value2 = id_nom.getText();
            
            String value3 = id_prix.getText();
            
            String value4 = id_qte.getText();
             String value5 = id_ref.getText();
            String value6 = file_path.getText();
            String sql = "update produit set nom= '"+value2+"',qte= '"+value4+"',reference= '"+value5+"',image= '"+value6+"',prix= '"+
                    value3+"' where id='"+value0+"' ";
            pst= cnx.prepareStatement(sql);
            pst.execute();
              
            JOptionPane.showMessageDialog(null, "Update");
          

    id_nom.setText("");
    id_prix.setText("");
    
    id_qte.setText("");
        id_ref.setText("");
      //   tfphoto.setText("");
    
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }}
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


Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Look, a Confirmation Dialog");
alert.setContentText("Are you ok with this?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
            int s = cat.supprimerproduit(i);
            if (s == 1) {
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
     id_ref.setText("");
//      tfphoto.setText("");
            }

        } } else {
    
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
    
    
  
    
    

    public void senemail(String email) throws SQLException{
      
  String to = email;
 String from = "mohamedamine.hamzaoui@esprit.tn"; 
 final String username="mohamedamine.hamzaoui@esprit.tn";
 final String password="191JMT3281";
   
 String host = "smtp.google.com";
 
       Properties props = new Properties();
         props.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        props.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        props.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        props.put("mail.smtp.port", "587");
  Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
  try {
      MimeMessage m = new MimeMessage(session);
      m.setFrom(new InternetAddress(from));
      m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
      m.setSubject("Produit");
      m.setText("nouveau produit est ajouté , venez le consulter");      
      
      
      Transport.send(m); 
      System.out.println("message envoyé");
      // Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //        alert.setTitle("Information");
        //        alert.setHeaderText(null);
        //        alert.setContentText("message envoyé");
      
  } catch (MessagingException e){
  e.printStackTrace();
  }
  }
    @FXML
    private void mailing(ActionEvent event) throws SQLException {
        
        cnx=MaConnexion.getInstance().getCnx();
       String query="select email from user ";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                rs.getString("email"); 
                senemail(rs.getString("email"));
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("amin");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


      
         
    }

    @FXML
    private void insertImage(ActionEvent event) {
        
        FileChooser open = new FileChooser();

        Stage stage = (Stage) recpane.getScene().getWindow();

        File file = open.showOpenDialog(stage);

        if (file != null) {

            String path = file.getAbsolutePath();

            path = path.replace("\\", "\\\\");

            file_path.setText(path);

            Image image = new Image(file.toURI().toString(), 110, 110, false, true);

            image_view.setImage(image);

        } else {

            System.out.println("NO DATA EXIST!");

        }
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/chart.fxml"));
           recpane.getChildren().setAll(pane);
    }

    @FXML
    private void viewlist(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/viewclient.fxml"));
           recpane.getChildren().setAll(pane);
    }

   /* @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException {
    
       
                  Produit p =new Produit();
       
              
                Document doc = new Document();

                PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Midou\\Desktop\\amin\\produit.pdf"));
                doc.open();
                            Paragraph pr =new Paragraph();
                pr.add("liste des produits"
                        + "");
             doc.add(pr);
                 PdfPTable table = new PdfPTable(4);
            PdfPCell c = new PdfPCell(new Phrase("nom"));
            table.addCell(c);
             c = new PdfPCell(new Phrase("prix"));
            table.addCell(c);
            c = new PdfPCell(new Phrase("qte"));
              
            table.addCell(c);
              c = new PdfPCell(new Phrase("reference"));
            table.addCell(c);
              
             
            doc.add(table);
             
    
                 Connection cnx = MaConnexion.getInstance().getCnx();
            String query = "SELECT * FROM produit" ;
           PreparedStatement smt = cnx.prepareStatement(query);
                ResultSet rs= smt.executeQuery();
                PdfPTable t = new PdfPTable(4);
               while(rs.next()){ 
          
             PdfPCell  c1 = new PdfPCell(new Phrase(rs.getString("nom")));
            t.addCell(c1);
             PdfPCell  c2 = new PdfPCell(new Phrase(rs.getString("prix")));
            t.addCell(c2);
             PdfPCell  c3 = new PdfPCell(new Phrase(rs.getString("qte")));
                          t.addCell(c3);
 PdfPCell  c4 = new PdfPCell(new Phrase(rs.getString("reference")));
            t.addCell(c4);
             
                     
               }
               doc.add(t);
                doc.close();
               
       
           
                 
          
             
               
    }*/
             
    }


    

            

   



     

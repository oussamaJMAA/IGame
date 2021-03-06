/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gestion.utilisateur;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import gestion.utilisateur.entities.Produit;
import gestion.utilisateur.entities.Promotion;
import java.awt.HeadlessException;
import java.io.File;
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
import gestion.utilisateur.service.ProduitServices;
import java.sql.DriverManager;
import toolsp.MaConnexion;
import java.sql.Statement;
import java.text.ParseException;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ProduitController implements Initializable {
ObservableList<Produit> list = FXCollections.observableArrayList();
private  Parent root ;
private Stage stage ;
private Scene scene;
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
    private TableColumn<?, ?> re;
    private TableColumn<?, ?> im;

    
@FXML
private Button ajouter ;
  static Connection cnx;
  
    private PreparedStatement pst = null ;
  static ResultSet rs;
String imageName="";
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
    private ImageView admin_image;
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
    private Button btnSignout;
    @FXML
    private TableColumn<?, ?> reduction;
  PreparedStatement insert = null;
    @FXML
    private Label idp;
    @FXML
    private ComboBox<String> promotion;
    @FXML
    private Label label_promo;
    @FXML
    private Button annuler;
    FileChooser fc = new FileChooser();
String image_path="";
    @FXML
    private Label nshalah;
    @FXML
    private Button btnCustomers1;
    @FXML
    private Button btnCustomers11;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnOrders1;
    @FXML
    private Button btn_promotion;
    @FXML
    private Button btnMenus1;
    @FXML
    private Button btnMenus11;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idp.setVisible(false);
        Afficher();
        promotion.setVisible(false);
        label_promo.setVisible(false);
       try {
           
           Connection cnx = MaConnexion.getInstance().getCnx();
           ResultSet rs = cnx.createStatement().executeQuery("SELECT prix_pro FROM promotion");
           while(rs.next())
               
               
               promotion.getItems().addAll(rs.getString("prix_pro"));
           
           
          
               initialiserlist();
               
 } catch (SQLException ex)
 {
     
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }   
 
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_java", "root", "");
            System.out.println("Connection done ! ");
            return conn;
        } catch (SQLException ex) {
            System.out.println("Error : " + ex.getMessage());
            return null;
        }
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        //To change body of generated methods, choose Tools | Templates.
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);

           Afficher();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   public int get_id_by_prix(int p) throws SQLException{
  
        String query = "select id from promotion where prix_pro = '"+p+"' ";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
     int max=0;
      if(rs.next()){
          max+=rs.getInt("id");
      }
  
        
return  max;
   } 
      public void insertTournois() throws ParseException, SQLException {
        Connection conn = getConnection();
     
     insert = conn.prepareStatement("insert into produit(nom,prix,quantite,image) values(?,?,?,?)");
        insert.setString(1, id_nom.getText());
        int prix = Integer.parseInt(id_prix.getText());
        int qte = Integer.parseInt(id_qte.getText());
        
        insert.setInt(2,prix);
        insert.setInt(3,qte);
       
      insert.setString(4,imageName);
     
     insert.executeUpdate();
 
      } 
      
       public void updateTournois() throws ParseException, SQLException {
     
       /* String c = promotion.getSelectionModel().getSelectedItem().toString();
        int vv = Integer.parseInt(c);
        int category_id = get_id_by_prix(vv);*/
      int  new_p = Integer.parseInt(id_prix.getText());
        String nom = id_nom.getText();
            String desc =file_path.getText();
           
           int prix= Integer.parseInt(id_prix.getText());
           int qte = Integer.parseInt(id_qte.getText());
           
            int id = Integer.parseInt(idp.getText());
         
            String query = "update produit set nom = '"+nom+"',image= '"+imageName+"', prix = "+new_p+", quantite= "+qte+"  where id="+id+"";
            executeQuery(query);
           Afficher();
        
      
    }

    public void deleteTournois() { 
        int id = Integer.parseInt(idp.getText());
        String query = "delete from produit where id="+id+"";

        executeQuery(query);
        Afficher();
    }
    
    
    
@FXML
public void ajoutproduit(ActionEvent event) throws SQLException, ParseException{
//int p = JOptionPane.showConfirmDialog(null,"Do you really want to add","add",JOptionPane.YES_NO_OPTION);
 //if(p==0){
     if(controleDeSaisi())
{ 
ProduitServices ps = new ProduitServices();
/*
int r = Integer.parseInt(id_prix.getText());
int f = Integer.parseInt(id_qte.getText());
*/
 //int qte, int prix, int reduction, String nom, String image
//ps.ajouterProduit(new Produit(f,r,red,id_nom.getText(),file_path.getText()));
insertTournois();
list.clear();
initialiserlist();

Afficher();
viewProduit.refresh();

//}
}}
private boolean controleDeSaisi() {  

        if (id_nom.getText().isEmpty() || id_prix.getText().isEmpty()
                || id_qte.getText().isEmpty()) {
          JOptionPane.showMessageDialog(null, "verifier les champs");
            return false;
        } else {

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
           reduction.setCellValueFactory(new PropertyValueFactory<>("reduction"));
           eimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        viewProduit.setItems(list);
}

  
       public void initialiserlist() throws SQLException{
             try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet ps = cnx.createStatement().executeQuery("SELECT * FROM produit");
            while(ps.next()){
                //(int qte, int prix, int reduction, String nom, String image)
            list.add(new Produit(ps.getInt("id"),ps.getInt("quantite"),ps.getInt("prix"),ps.getInt("reduction"),ps.getString("nom"),ps.getString("image")));
        }
            } catch (SQLException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }
      
   public int get_id_by_name(String name) throws SQLException{
  
        String query = "select id from produit where nom = '"+name+"' ";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
     int max=0;
      if(rs.next()){
          max+=rs.getInt("id");
      }
  
        
return  max;
   } 
       
   
    @FXML
    private void getSelected(MouseEvent event) throws SQLException {
        promotion.setVisible(true);
        label_promo.setVisible(true);
        ajouter.setVisible(false);
         Produit t = viewProduit.getSelectionModel().getSelectedItem();
       
        idp.setText(""+t.getId());
      
         Produit e = viewProduit.getSelectionModel().getSelectedItem();
       
        int index = viewProduit.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
            Connection cnx = MaConnexion.getInstance().getCnx();
     // idp.setText(id.getCellData(index).toString());
    id1.setText(id.getCellData(index).toString());
    id_nom.setText(d.getCellData(index).toString());
    id_prix.setText(pr.getCellData(index).toString());
    id_qte.setText(qt.getCellData(index).toString());
    
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
    public void Edit () throws SQLException, ParseException{   
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Look, a Confirmation Dialog");
alert.setContentText("Are you ok with this?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
         /*   cnx = MaConnexion.getInstance().getCnx();
            String value0 = id1.getText();
            String value2 = id_nom.getText();
            
            String value3 = id_prix.getText();
            
            String value4 = id_qte.getText();
            
            String value6 = file_path.getText();
            String sql = "update produit set nom= '"+value2+"',quantite= '"+value4+"',reduction= '"+value5+"',image= '"+value6+"',prix= '"+
                    value3+"' where id="+value0+"";
            pst= cnx.prepareStatement(sql);
            pst.execute();
              */
         updateTournois();
            JOptionPane.showMessageDialog(null, "Update");
          

    id_nom.setText("");
    id_prix.setText("");
    
    id_qte.setText("");
      promotion.setVisible(false);
      label_promo.setVisible(false);
      //   tfphoto.setText("");
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


Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Look, a Confirmation Dialog");
alert.setContentText("Are you ok with this?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
          //  int s = cat.supprimerproduit(i);
          deleteTournois();
         //   if (s == 1) {
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("produit supprim??");
                alert.showAndWait();
              
                list.clear();
                initialiserlist(); 
                Afficher();
                viewProduit.refresh();
                  id1.setText("");
    id_nom.setText("");
    id_prix.setText("");
    id_qte.setText("");
  
//      tfphoto.setText("");
          //  }

        } /*} else {
    
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }*/
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
                                else if (String.valueOf(r.getReduction()).indexOf(lowerCaseFilter) != -1) {
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
      m.setText("nouveau produit est ajout?? , venez le consulter");      
      
      
      Transport.send(m); 
      System.out.println("message envoy??");
      // Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //        alert.setTitle("Information");
        //        alert.setHeaderText(null);
        //        alert.setContentText("message envoy??");
      
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
        /*
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
*/
           fc.setTitle("Choose an image");
       fc.setInitialDirectory(new File("C:\\Users\\oussa\\PhpstormProjects\\gaming_app\\public\\uploads\\photos"));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            //image_path= file.getName();
            imageName=file.getName();
            image_path = file.getAbsolutePath();
            image_view.setImage(new Image(file.toURI().toString()));

        } else {

            System.out.println("Invalid file");
        }
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
    private void on_click_users_button(ActionEvent event) throws IOException {
       root = FXMLLoader.load(getClass().getResource("AdminSpace.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void on_click_sign_out(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
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
    
    
   public int get_id_pro(int p) throws SQLException{
  
        String query = "select id from promotion where prix_pro = "+p+"";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
     int max=0;
      if(rs.next()){
          max+=rs.getInt("id");
      }
  
        
return  max;
   } 

    @FXML
    private void on_click_promotion(ActionEvent event) throws IOException, SQLException {
          /*    String c = promotion.getSelectionModel().getSelectedItem().toString();
        int vv = Integer.parseInt(c);
               int prom_id = get_id_pro(vv);
            int  new_pp = Integer.parseInt(id_prix.getText()) * vv/100;
          
            int id = Integer.parseInt(idp.getText());
         
            String query = "update produit set promotion_id="+prom_id+",prix ="+new_pp+" where id="+id+"";
            executeQuery(query);
           root = FXMLLoader.load(getClass().getResource("Produit.fxml")); //khali hedhi pour le moment
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); */
        
    }

    @FXML
    private void annuler(ActionEvent event) {
        promotion.setVisible(false);
        label_promo.setVisible(false);
        id_nom.setText("");
        id_prix.setText("");
        id_qte.setText("");
    }

 @FXML
    private void on_click_messages(ActionEvent event) throws IOException {
        /*
           root = FXMLLoader.load(getClass().getResource("Chat.fxml")); 
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); */
        root = FXMLLoader.load(getClass().getResource("Chat.fxml"));

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Chat");
        stage.show();
    }

   @FXML
    private void on_click_blog(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Publication.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void on_click_products(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void on_click_commande(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("ListCommandeAdmin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

 

    @FXML
    private void on_click_games(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Game_Admin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void on_click_categories(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("Categorie.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    private void on_click_participation(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("participation.fxml"));  
            stage = new Stage();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Participations");
                stage.show(); 
    }

    @FXML
    private void on_click_p(ActionEvent event) throws SQLException, IOException {
        String c = promotion.getSelectionModel().getSelectedItem().toString();
        int vv = Integer.parseInt(c);
               int prom_id = get_id_pro(vv);
            int  new_pp = Integer.parseInt(id_prix.getText()) * vv/100;
          
            int id = Integer.parseInt(idp.getText());
         
            String query = "update produit set promotion_id="+prom_id+",prix ="+new_pp+" where id="+id+"";
            executeQuery(query);
            
           root = FXMLLoader.load(getClass().getResource("Produit.fxml")); //khali hedhi pour le moment
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

}
    

            

   



     

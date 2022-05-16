    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gestion.utilisateur;

import gestion.utilisateur.entities.Categorie;
import gestion.utilisateur.entities.Game;
import gestion.utilisateur.entities.Tournois;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rouka
 */
public class Game_AdminController implements Initializable {

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
    private TextField tfnom;
    @FXML
    private TextField tfdesc;
    @FXML
    private TableView<Game> tab;
    @FXML
    private TableColumn<Game, String> nom;
    @FXML
    private TableColumn<Game, String> desc;
    @FXML
    private Button add;
    @FXML
    private Button modif;
    @FXML
    private Button del;
    @FXML
    private TextField tfid;
    @FXML
    private Button anuller;
  private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private TableColumn<Game, String> link;
    @FXML
    private Button img;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField tflink;
    FileChooser fc = new FileChooser();
      String image_path = "";
    @FXML
     ComboBox<String> category;
     PreparedStatement insert = null;
    @FXML
    private TextField filterfield;
        String imageName="";
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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showTournois();
    tfid.setVisible(false);
      try{
          Connection conn = getConnection();
          ResultSet rs = conn.createStatement().executeQuery("select category_name from category");
      ObservableList data = FXCollections.observableArrayList();
      while(rs.next()){
      data.add(new String(rs.getString("category_name")));
      
      }
      category.setItems(data);
      }catch(Exception ex){System.out.println(ex);}
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

            showTournois();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public ObservableList<Game> getTournoisList() {
        ObservableList<Game> TournoisList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select * from game";
        Statement st;
       
        ResultSet rs;
  
        try {
            st = conn.createStatement();
           
            rs = st.executeQuery(query);
            Game g;
            while (rs.next()) {
             g = new Game(rs.getInt("game_id"),rs.getInt("idcategory"),rs.getString("game_name"),rs.getString("game_description"),rs.getString("game_link"),rs.getString("game_img"));
                TournoisList.add(g);

            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());

        }
        return TournoisList;
    }

    public void showTournois() {

        ObservableList<Game> list = getTournoisList();
        Game cat;
        nom.setCellValueFactory(new PropertyValueFactory<Game, String>("game_name"));
        desc.setCellValueFactory(new PropertyValueFactory<Game, String>("game_description"));
       link.setCellValueFactory(new PropertyValueFactory<Game, String>("game_link"));
       
        tab.setItems(list);
        FilteredList<Game> filteredData = new FilteredList<>(list,b->true);
          filterfield.textProperty().addListener((observable, oldvalue, newvalue) -> {
            filteredData.setPredicate(t -> {
                if (newvalue.isEmpty() || newvalue == null) {
                    return true;
                }
                String searchkeyword = newvalue.toLowerCase();

                String tournoisNameLower = t.getGame_name().toLowerCase();
                String tournoisDate = t.getGame_description();

                String tournoisplatforme = t.getGame_link();

                if (tournoisNameLower.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (tournoisDate.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (tournoisplatforme.indexOf(searchkeyword) > -1) {
                    return true;
                }
             
                return false;

            });
        });

        SortedList<Game> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tab.comparatorProperty());
       tab.setItems(sortedData);
    }
      @FXML
    private void clicked_tab(MouseEvent event) throws SQLException {
       Game t = tab.getSelectionModel().getSelectedItem();
         tfnom.setText(t.getGame_name());
         tfdesc.setText(t.getGame_description());
         tflink.setText(t.getGame_link());
         
         tfid.setText(""+t.getId());
       int idc = t.getId_cat();
       String nom_c = get_name_cat_from_id(idc);
      category.getSelectionModel().select(nom_c);
      
    }

   public int get_id_cat_from_name(String name) throws SQLException{
  
        String query = "select category_id from category where category_name = '"+name+"' ";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
     int max=0;
      if(rs.next()){
          max+=rs.getInt("category_id");
      }
  
        
return  max;
   }
    
   
   public String get_name_cat_from_id(int id) throws SQLException{
  
        String query = "select category_name from category where category_id = "+id+" ";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
     String max="";
      if(rs.next()){
          max+=rs.getString("category_name");
      }
  
        
return  max;
   }
    public String get_name_cat_for_each_game(int id) throws SQLException{
  
        String query = "select c.category_name from category c inner join game g on c.category_id = g.idcategory where game_id ="+id+" ";
Connection conn = getConnection();

      Statement st;
        st = conn.createStatement();
   ResultSet rs= st.executeQuery(query);
     String max="";
      if(rs.next()){
          max+=rs.getString(1);
      }
  
        
return  max;
   }
    public void insertTournois() throws ParseException, SQLException {
        Connection conn = getConnection();
       
        String c = category.getSelectionModel().getSelectedItem().toString();
        int category_id = get_id_cat_from_name(c);
     insert = conn.prepareStatement("insert into game(game_name,game_description,game_link,game_img,idcategory) values(?,?,?,?,?)");
        insert.setString(1, tfnom.getText());
        insert.setString(2,tfdesc.getText());
        insert.setString(3,tflink.getText());
        insert.setString(4, imageName);
       insert.setInt(5, category_id);
     insert.executeUpdate();
        System.out.println(category_id);
        showTournois();
    }

    public void updateTournois() throws ParseException {
        
        try {
            String nom = tfnom.getText();
            String desc = tfdesc.getText();
            String link = tflink.getText();
            int id = Integer.parseInt(tfid.getText());
            String c = category.getSelectionModel().getSelectedItem().toString();
            
            int category_id = get_id_cat_from_name(c);
            
            
            
            String query = "update game set game_name = '"+nom+"',game_description = '"+desc+"', game_link = '"+link+"', game_img = '"+imageName+"',idcategory = "+category_id+" where game_id="+id+"";
            executeQuery(query);
            showTournois();
        } catch (SQLException ex) {
          //  Logger.getLogger(Game_AdminController.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println(ex);
        }
       
    }

    public void deleteTournois() { 
        int id = Integer.parseInt(tfid.getText());
        String query = "delete from game where game_id="+id+"";

        executeQuery(query);
        showTournois();
    }
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws ParseException, SQLException, IOException {
        
       if(event.getSource()==anuller){
       tfnom.setText("");
       tfdesc.setText("");
       tfid.setText("");
       tflink.setText("");
       imageView = null;
      category.getSelectionModel().select("");
       }else if(event.getSource()==add){
             
       insertTournois();
          tfid.setText("");
tfnom.setText("");
tfdesc.setText("");
tflink.setText("");

category.getSelectionModel().select("");
         root = FXMLLoader.load(getClass().getResource("Game_Admin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
       }else if(event.getSource()==del){
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Vous etes entrain de Supprimer, Confirmer la Suppression");
            if (alert.showAndWait().get() == ButtonType.OK) {

                deleteTournois();
                tfid.setText("");
tfnom.setText("");
tfdesc.setText("");
tflink.setText("");
imageView = null;
category.getSelectionModel().select("");
            }
       }else if(event.getSource()==modif){
             Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setHeaderText(null);
            alert1.setContentText("Vous etes entrain de Modifier, Confirmer la Modification");
            if (alert1.showAndWait().get() == ButtonType.OK) {

       updateTournois();
            tfid.setText("");
tfnom.setText("");
tfdesc.setText("");
tflink.setText("");
imageView = null;
category.getSelectionModel().select("");
       }
       
       
    
    }
    }

    @FXML
    private void on_click_import_image(ActionEvent event) {
          fc.setTitle("Choose an image");
        fc.setInitialDirectory(new File("C:\\Users\\oussa\\PhpstormProjects\\gaming_app\\public\\uploads\\photos"));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif","*.jfif","*.jpeg"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            //image_path= file.getName();
            image_path = file.getAbsolutePath();
                  imageName=file.getName();
            imageView.setImage(new Image(file.toURI().toString()));

        } else {

            System.out.println("Invalid file");
        }
    }

    
    
         @FXML
    private void on_click_dashboard_button(ActionEvent event)throws IOException {
      root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void on_click_users_button(ActionEvent event)throws IOException {
        
          root = FXMLLoader.load(getClass().getResource("AdminSpace.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void on_click_tournaments(ActionEvent event) throws IOException{
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

    @FXML
    private void on_click_sign_out(ActionEvent event)throws IOException {
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

    private void on_click_live_tournaments(ActionEvent event) throws IOException {
           root = FXMLLoader.load(getClass().getResource("Live_tournois.fxml"));
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
                stage.setTitle("Participation");
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
    private void on_click_games(ActionEvent event) throws IOException {
          root = FXMLLoader.load(getClass().getResource("Game_Admin.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
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
    private void on_click_products(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    private void on_click_promotion(ActionEvent event) {
    }

      @FXML
    private void on_click_blog(ActionEvent event) throws IOException {
           root = FXMLLoader.load(getClass().getResource("PubCom.fxml")); 
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
  
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

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
import com.mysql.cj.admin.ServerController;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import gestion.utilisateur.serverController;
import gestion.utilisateur.ClientController;
import gestion.utilisateur.Server;
import gestion.utilisateur.Client;
import gestion.utilisateur.Commentaires;
import gestion.utilisateur.Publications;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;



import java.lang.*;

import static java.lang.Thread.sleep;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class PubComController implements Initializable {
   private Stage stage;
    private Scene scene;
    private Parent root;
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
    
    @FXML
    private TextField idField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField pagesField;
    @FXML
    private Button serveurr;
    @FXML
    private Button insertButton;

    @FXML
    private Button insertButton1;


    @FXML
    private Button updateButton;

    @FXML
    private Button updateButton1;


    @FXML
    private Button deleteButton;

    @FXML
    private Button deleteButton1;




    @FXML
    private Button clientt;



    private Client client;


    @FXML
    private TableView<Publications> TableView;

    @FXML
    private TableView<Commentaires> TableView1;

  //  @FXML
    //private javafx.scene.control.TableView<Publications> TableView;


    @FXML
    private TableColumn<Publications, Integer> idColumn;

    @FXML
    private TableColumn<Publications, String> titleColumn;

    @FXML
    private TableColumn<Publications, String> authorColumn;

    @FXML
    private TableColumn<Commentaires, Integer> yearColumn;

    @FXML
    private TableColumn<Commentaires, String> pagesColumn;
    
    
    
    
    
    
    
    

    
    public void showCommentaires() {
        ObservableList<Commentaires> list = getCommentairesList();


      yearColumn.setCellValueFactory(new PropertyValueFactory<Commentaires,Integer>("id_com"));
        pagesColumn.setCellValueFactory(new PropertyValueFactory<Commentaires,String>("description_com"));

        TableView1.setItems(list);
    }

    @FXML
    private void insertButton() {
        String query = "insert into Publications(id_pub,titre_pub,description_pub) values("+idField.getText()+",'"+titleField.getText()+"','"+authorField.getText()+"')";
        String query2 = "insert into Publications(id_pub,titre_pub)  values('"+idField.getText()+"','***','"+authorField.getText()+"')";
        if (titleField.getText().matches("lee")){
            executeQuery(query2);
            }else

        executeQuery(query);

        showPublication();

    }

    @FXML
    private void insertButton1() {
        String query = "insert into Commentaires values("+pagesField.getText()+",'"+yearField.getText()+"')";
        String query3 = "insert into Commentaires values("+pagesField.getText()+",'***')";
        if (yearField.getText().matches("lee")){
            executeQuery(query3);
        }else
        executeQuery(query);
        showCommentaires();
    }


    @FXML
    private void updateButton() {
        String query = "UPDATE Publications SET titre_pub ='"+titleField.getText()+"',description_pub='"+authorField.getText()+"' WHERE id_pub='"+idField.getText()+"';";
        String query2 = "UPDATE Publications SET titre_pub ='***',description_pub='"+authorField.getText()+"' WHERE id_pub='"+idField.getText()+"';";
        if (titleField.getText().matches("lee")){
            executeQuery(query2);
        }else
        executeQuery(query);
        showPublication();
    }

    @FXML
    private void updateButton1() {
        String query = "UPDATE commentaires SET description_com = '"+yearField.getText()+"' WHERE id_com = '"+pagesField.getText()+"';";
        String query2 = "UPDATE commentaires SET description_com = '***' WHERE id_com = '"+pagesField.getText()+"';";
        if (yearField.getText().matches("lee")){
            executeQuery(query2);
        }else
        executeQuery(query);
        showCommentaires();
    }

    @FXML
    private void deleteButton() {
        String query = "DELETE FROM Publications WHERE id_pub="+idField.getText()+"";
        executeQuery(query);
        showPublication();
    }



    @FXML
    private void deleteButton1() {
        String query = "DELETE FROM Commentaires WHERE id_com="+pagesField.getText()+"";
        executeQuery(query);
        showCommentaires();
    }





    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         retrievedata a = retrievedata.getInstance("", "",0);
        test.setText(a.getUsername());
        int jj = a.getImage().lastIndexOf('\\');
        admin_image.setImage(new Image(DashboardController.class.getResourceAsStream(a.getImage().substring(jj + 1))));
          showPublication();
        showCommentaires();
        // TODO
    }   
    public Connection getConnection() {

        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_java","root","");

            return conn;

        }
        catch (Exception e){
            e.printStackTrace();
            return null;

        }
    }

    public ObservableList<Publications> getPublicationsList(){
        ObservableList<Publications> PublicationList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM Publications";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Publications Publications;
            while(rs.next()) {
                Publications = new Publications(rs.getInt("id_pub"),rs.getString("titre_pub"),rs.getString("description_pub"));
                PublicationList.add(Publications);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PublicationList;
    }





    public void showPublication() {
        ObservableList<Publications> list = getPublicationsList();

        idColumn.setCellValueFactory(new PropertyValueFactory<Publications, Integer>("id_pub"));
        //idColumn.setCellValueFactory(cellData -> cellData.getValue().getId_pub());
       // titleColumn.setCellValueFactory(cellData -> cellData.getValue().getId_pub());
        titleColumn.setCellValueFactory(new PropertyValueFactory<Publications,String>("titre_pub"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Publications,String>("description_pub"));

        TableView.setItems(list);
    }

    public ObservableList<Commentaires> getCommentairesList(){
        ObservableList<Commentaires> CommentaireList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM Commentaires ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Commentaires Commentaires;
            while(rs.next()) {
                Commentaires = new Commentaires(rs.getInt("id_com"),rs.getString("description_com"));
               CommentaireList.add(Commentaires);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommentaireList;
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

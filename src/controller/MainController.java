package controller;
import library.Commentaires;
import library.Publications;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ResourceBundle;





public class MainController implements Initializable {

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
    private TableView<Publications> TableView;

    @FXML
    private javafx.scene.control.TableView<Commentaires> TableView1;


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

    @FXML
    private void insertButton() {
        String query = "insert into Publications values("+idField.getText()+",'"+titleField.getText()+"','"+authorField.getText()+"')";
        executeQuery(query);
        showPublication();
    }

    @FXML
    private void insertButton1() {
        String query = "insert into Commentaires values("+pagesField.getText()+",'"+yearField.getText()+"')";
        executeQuery(query);
        showCommentaires();
    }


    @FXML
    private void updateButton() {
        String query = "UPDATE Publications SET titre_pub ='"+titleField.getText()+"',description_pub='"+authorField.getText()+"' WHERE id_pub='"+idField.getText()+"';";
        executeQuery(query);
        showPublication();
    }

    @FXML
    private void updateButton1() {
        String query = "UPDATE commentaires SET description_com = '"+yearField.getText()+"' WHERE id_com = '"+pagesField.getText()+"';";
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
    public void initialize(URL location, ResourceBundle resources) {
        showPublication();
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blog","root","");
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Publications> getPublicationsList(){
        ObservableList<Publications> PublicationsList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM Publications ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Publications Publications;
            while(rs.next()) {
                Publications = new Publications(rs.getInt("id_pub"),rs.getString("titre_pub"),rs.getString("description_pub"));
                PublicationsList.add(Publications);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PublicationsList;
    }





    public void showPublication() {
        ObservableList<Publications> list = getPublicationsList();

        idColumn.setCellValueFactory(new PropertyValueFactory<Publications,Integer>("id_pub"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Publications,String>("titre_pub"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Publications,String>("description_pub"));

        TableView.setItems(list);
    }

    public ObservableList<Commentaires> getCommentairesList(){
        ObservableList<Commentaires> CommentairesList = FXCollections.observableArrayList();
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
               CommentairesList.add(Commentaires);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommentairesList;
    }

    public void showCommentaires() {
        ObservableList<Commentaires> list = getCommentairesList();


        yearColumn.setCellValueFactory(new PropertyValueFactory<Commentaires,Integer>("id_com"));
        pagesColumn.setCellValueFactory(new PropertyValueFactory<Commentaires,String>("description_com"));

        TableView1.setItems(list);
    }

}
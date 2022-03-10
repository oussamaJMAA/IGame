/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.sql.Date;
import Entities.Category;
import Service.CategoryService;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.sql.Date;
import Entities.Category;
import Service.CategoryService;

import java.io.IOException;

//import com.itextpdf.text.Document;

//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

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
import javafx.scene.control.DateCell;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/*import javafx.stage.Stage;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
*/



/**
 * FXML Controller class
 *
 * @author Malak
 */
public class GestionCategorieController implements Initializable {

   
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    private Label label;
    @FXML
    private TextField tdCategory;
    @FXML
    private TableView<Category> tabCategory;
 
    @FXML
    private Button annuler;
    @FXML
    private TextField keywordtextfield;
    @FXML
    private Button back;
    @FXML
    private Label warning;
    @FXML
    private Button btn_addC;
    @FXML
    private Button btn_updateC;
    @FXML
    private Button btn_deleteC;
    @FXML
    private TextField tddescreption;
    @FXML
    private TableColumn<Category, String> colCcat;
    @FXML
    private TableColumn<Category, String> colCdescreption;
    @FXML
    private TextField tdid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showCategory();
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
        // To change body of generated methods, choose Tools | Templates.
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);

            showCategory();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

   

    public boolean verif(String t) {

        if (t.isEmpty()) {

            return false;
        }
        return true;
    }

    public boolean verif_date(int d) {
        if (d == 0) {
            return false;
        }
        return true;
    }
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        if (event.getSource() == btn_addC) {

             String query = "insert into game(category_name,discription) value('"+ tdCategory.getText()+"','"+tddescreption.getText()+"')";
        executeQuery(query);
              // AddCategory();
               
                //label.setText("l'Ajout est effectué !");
            
        } else if (event.getSource() == btn_updateC) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Vous etes entrain de modifier, Confirmer la modification");
            if (alert.showAndWait().get() == ButtonType.OK) {

              //  updateCategory();
            }
            label.setText("Modification effectué !");
        } else if (event.getSource() == btn_deleteC) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Vous etes entrain de Supprimer, Confirmer la Suppression");
          
            label.setText("Suppression effectué !");

        } else if (event.getSource() == annuler) {
        
            tdCategory.setText("");
          
         
            //tfdate.setValue(null);

            warning.setVisible(false);
        }
    }
     public void showCategory() {

        ObservableList<Category> list = getCategoryList();
        Category category;
        colCcat.setCellValueFactory(new PropertyValueFactory<Category, String>("category_name"));
        colCdescreption.setCellValueFactory(new PropertyValueFactory<Category, String>("discription"));
        tabCategory.setItems(list);

        FilteredList<Category> filteredData = new FilteredList<>(list, b -> true);

       keywordtextfield.textProperty().addListener((observable, oldvalue, newvalue) -> {
            filteredData.setPredicate(t -> {
                if (newvalue.isEmpty() || newvalue == null) {
                    return true;
                }
                String searchkeyword = newvalue.toLowerCase();

                String CategoryNameLower = t.getCategory_name().toLowerCase();
                
                
                if (CategoryNameLower.indexOf(searchkeyword) > -1) {
                    return true;
                }
               

                return false;

            });
        });

        SortedList<Category> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabCategory.comparatorProperty());
        tabCategory.setItems(sortedData);

    }
     public ObservableList<Category> getCategoryList() {
        ObservableList<Category> CategoryList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select * from Category";
        Statement st;

        ResultSet rs;
        try {
            st = conn.createStatement();

            rs = st.executeQuery(query);
            Category Category;
            while (rs.next()) {
                Category = new Category(rs.getString("Category_name"),rs.getString("discription"));
                CategoryList.add(Category);

            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());

        }
        return CategoryList;
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
    }

    @FXML
    private void updateCategory(ActionEvent event) {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        //String a = time_choice.getValue();
       // java.sql.Time timeValue = new java.sql.Time(formatter.parse(a).getTime());
        String query = "UPDATE Category SET discription = '" + tddescreption.getText() + "'where category_name = '" + tdCategory.getText() + "';";
       
        executeQuery(query);
        showCategory();
    }

    @FXML
    private void deleteCategory(ActionEvent event) {
       String query = "delete from Category where Category_name = '" + tdCategory.getText() + "'; ";

       executeQuery(query);
        showCategory();
    }

    @FXML
    private void AddCategory(ActionEvent event) {
         DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
       // String a = time_choice.getValue();
       // java.sql.Time timeValue = new java.sql.Time(formatter.parse(a).getTime());
        String query = "insert into category (category_name, discription) value('"+ tdCategory.getText()+"','"+ tddescreption.getText()+"')";
        executeQuery(query);
       showCategory();
    }

   @FXML
    private void swtichToGame(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("GestionJeu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
       stage.setScene(scene);
        stage.show();
    }
   
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import Entities.Tournois;

import java.io.IOException;

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
import javafx.scene.control.Button;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import javax.mail.Address;
import javax.mail.Session;

/**
 *
 * @author rouka
 */
public class FXMLDocumentController implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Label label;
    @FXML
    private TextField tdnomtournois;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tfcapacite;
    @FXML
    private TextField tfplatforme;

    @FXML
    private TextField tfrecompense;
    @FXML
    private Button btn_conf;
    @FXML
    private TableView<Tournois> tvtournois;
    @FXML
    private TableColumn<Tournois, Integer> colid;
    @FXML
    private TableColumn<Tournois, String> colnomtournois;
    @FXML
    private TableColumn<Tournois, java.sql.Date> coldate;
    @FXML
    private TableColumn<Tournois, Integer> colcapacite;
    @FXML
    private TableColumn<Tournois, String> colplatforme;

    @FXML
    private TableColumn<Tournois, String> colrecompense;
    @FXML
    private Button btn_conf1;
    @FXML
    private Button btn_conf11;
    @FXML
    private TextField tdid;
    @FXML
    private Button annuler;
    @FXML
    private TextField keywordtextfield;

    @FXML
    private Button back;



    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == btn_conf) {
            insertTournois();
            label.setText("l'Ajout est effectué !");
        } else if (event.getSource() == btn_conf1) {
            updateTournois();
            label.setText("Modification effectué !");
        } else if (event.getSource() == btn_conf11) {
            deleteTournois();
            label.setText("Suppression effectué !");
        } 

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        showTournois();
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

    public List<Tournois> getTournoisList() {
       
        Connection conn = getConnection();
        String query = "select * from tournois";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Tournois tournois;
            while (rs.next()) {
                tournois = new Tournois(rs.getInt("id"), rs.getString("nom_tournois"), rs.getDate("date_tournois"), rs.getInt("capacite"), rs.getString("platforme"), rs.getString("recompense"));
                TournoisList.add(tournois);

            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());

        }
        return TournoisList;
    }

    public void showTournois() {
        Tournois tournois;
        tvtournois.setItems(list); }

    public void insertTournois() {

        String query = "insert into tournois"
                + " values('" + tdnomtournois.getText() + "',"
                + "" + tfcapacite.getText() + ",'" + tfplatforme.getText() + "','" + tfrecompense.getText() + "',DATE'" + tfdate.getValue() + "') ";
        executeQuery(query);
        try {
            mail();
        } catch (MessagingException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public void updateTournois() {
        String query = "update tournois set capacite = " + tfcapacite.getText() + ", platforme = '" + tfplatforme.getText() + "',recompense = '" + tfrecompense.getText() + "',date_tournois = DATE'" + tfdate.getValue() + "', nom_tournois = '" + tdnomtournois.getText() + "' where id = " + tdid.getText() + "";

        executeQuery(query);
        showTournois();
    }

    public void deleteTournois() {
        String query = "delete from tournois where id = " + tdid.getText() + " ";

        executeQuery(query);
        showTournois();
    }





}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import Entities.Equipes;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author rouka
 */
public class EquipeController implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private TableColumn<Equipes, Integer> colid;
    @FXML
    private TableColumn<Equipes, String> colnom;
    @FXML
    private TableColumn<Equipes, Integer> colmembres;
    @FXML
    private TableColumn<Equipes, Integer> colptsxp;
    @FXML
    private TableColumn<Equipes, Integer> coltg;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfmembres;
    @FXML
    private TextField tfptsxp;
    @FXML
    private TextField tftg;
    @FXML
    private TableView<Equipes> tvequipe;
    @FXML
    private Button btnback;
    @FXML
    private Button btnmodif;
    @FXML
    private TextField tfid;
    @FXML
    private Button btnsupp;
    @FXML
    private TextField keywordfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showEquipes();
 
    }

  



    public List<Equipes> getEquipesList() {
      
        Connection conn = getConnection();
        String query = "select * from equipe";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Equipes equipes;
            while (rs.next()) {
                //int id,String nom_equipe, int membres, int pts_xp, int tournois_gagne
                equipes = new Equipes(rs.getInt("id"), rs.getString("nom_equipe"), rs.getInt("membres"), rs.getInt("pts_exp"), rs.getInt("tournois_gagne"));
                EquipesList.add(equipes);

            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());

        }
        return EquipesList;
    }

    public void showEquipes() {
        ObservableList<Equipes> list = getEquipesList();
        colid.setCellValueFactory(new PropertyValueFactory<Equipes, Integer>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<Equipes, String>("nom_equipe"));
        colmembres.setCellValueFactory(new PropertyValueFactory<Equipes, Integer>("membres"));
        colptsxp.setCellValueFactory(new PropertyValueFactory<Equipes, Integer>("pts_xp"));
        coltg.setCellValueFactory(new PropertyValueFactory<Equipes, Integer>("tournois_gagne"));

        tvequipe.setItems(list);

    }


   
    public void updateEquipe() {
        String query = "update equipe set nom_equipe = '" + tfnom.getText() + "', membres = " + tfmembres.getText() + " ,pts_exp = " + tfptsxp.getText() + ", tournois_gagne = " + tftg.getText() + " where id = " + tfid.getText() + "";

        executeQuery(query);
        showEquipes();

    }

    public void deleteEquipe() {
        String query = "delete from equipe where id = " + tfid.getText() + " ";

        executeQuery(query);
        showEquipes();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnmodif) {
            updateEquipe();

        } else if (event.getSource() == btnsupp) {
            deleteEquipe();

        }

    }

}

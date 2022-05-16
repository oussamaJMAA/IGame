/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import gestion.utilisateur.entities.Equipes;
import gestion.utilisateur.entities.Participation;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rouka
 */
public class Live_tournoisController implements Initializable {

    @FXML
    private Button btn_win_e1;
    @FXML
    private Label e1;
    @FXML
    private Button btn_win_e2;
    @FXML
    private Label e2;
    @FXML
    private Button btn_win_e3;
    @FXML
    private Label e3;
    @FXML
    private Button btn_win2_e1;
    @FXML
    private Label e1_2;
    @FXML
    private Button btn_win_e4;
    @FXML
    private Label e4;
    @FXML
    private Button btn_win2_e2;
    @FXML
    private Label e2_2;
    @FXML
    private Label winner;
    private Parent root;
    private Stage stage;
    private Scene scene;
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
    private ImageView admin_image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      retrievedata a = retrievedata.getInstance("", "",0);
       // test.setText(a.getUsername());
      try{
        InputStream stream = new FileInputStream("C:\\Users\\oussa\\PhpstormProjects\\gaming_app\\public\\uploads\\photos\\"+a.getImage());
      Image image4 = new Image(stream);
      admin_image.setImage(image4);
      }catch(Exception e){
          System.out.println(e);
      }
        try {

            int equipe1 = getParticipationList().get(0).getId_equipe();
            int equipe2 = getParticipationList().get(1).getId_equipe();
            int equipe3 = getParticipationList().get(2).getId_equipe();
            int equipe4 = getParticipationList().get(3).getId_equipe();
            String ee1 = name_equipe(equipe1);
            e1.setText(ee1);

            String ee2 = name_equipe(equipe2);
            e2.setText(ee2);

            String ee3 = name_equipe(equipe3);
            e3.setText(ee3);

            String ee4 = name_equipe(equipe4);
            e4.setText(ee4);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public String name_equipe(int n) throws SQLException {
        String query = "select e.nom_equipe from participation inner join equipe e on participation.id_equipe=e.id where e.id = " + n + "";
        Connection conn = getConnection();

        Statement st;
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        String nom = "";
        if (rs.next()) {
            nom = rs.getString(1);
        }

        return nom;
    }

    @FXML
    private void winner(ActionEvent event) {

        if (event.getSource() == btn_win_e1) {
            e1_2.setText(e1.getText());
            e1.setText("");

        } else if (event.getSource() == btn_win_e2) {
            e1_2.setText(e2.getText());
            e2.setText("");
        }

        if (event.getSource() == btn_win_e3) {
            e2_2.setText(e3.getText());
            e3.setText("");
        } else if (event.getSource() == btn_win_e4) {
            e2_2.setText(e4.getText());
            e4.setText("");
        }

        if (e1_2.getText() != "") {
            if (event.getSource() == btn_win2_e1) {
                winner.setText(e1_2.getText());
                e1_2.setText("");
            } else if (event.getSource() == btn_win2_e2) {
                winner.setText(e2_2.getText());
                winner.setStyle("-fx-background-color:red");
                e2_2.setText("");
            }

        }

    }

    private void back(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Participation> getParticipationList() {
        ObservableList<Participation> PartList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select * from participation";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Participation parati;
            while (rs.next()) {
                //int id,String nom_equipe, int membres, int pts_xp, int tournois_gagne
                parati = new Participation(rs.getInt("id"), rs.getInt("id_equipe"), rs.getInt("id_tournois"));
                PartList.add(parati);

            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());

        }
        return PartList;
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

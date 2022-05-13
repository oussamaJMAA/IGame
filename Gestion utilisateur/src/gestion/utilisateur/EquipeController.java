/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import gestion.utilisateur.entities.Equipes;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    private Button btnmodif;
    @FXML
    private TextField tfid;
    @FXML
    private Button btnsupp;
    @FXML
    private TextField keywordfield;
    private Button back;
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
    @FXML
    private Label warning;
    @FXML
    private Button btnmodif1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showEquipes();
        tfid.setVisible(false);
        colid.setVisible(false);
              retrievedata a = retrievedata.getInstance("", "",0);
        test.setText(a.getUsername());
        int jj = a.getImage().lastIndexOf('\\');
        admin_image.setImage(new Image(DashboardController.class.getResourceAsStream(a.getImage().substring(jj + 1))));
    }

    public void mail(String to) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.transport.protocl", "smtp");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("jemaaoussama64@gmail.com", "sousourourou9899@");
            }
        });
        Message message = new MimeMessage(session);
        message.setSubject("Email from my Java Program");
        message.setContent("<h1>Your are in a TEAM ! </h1>" + tfnom, "text/html");
        Address addressTo = new InternetAddress(to);
        message.setRecipient(Message.RecipientType.TO, addressTo);
        Transport.send(message);
    }

    private void swtichToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
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

    public ObservableList<Equipes> getEquipesList() {
        ObservableList<Equipes> EquipesList = FXCollections.observableArrayList();
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

        FilteredList<Equipes> filteredData = new FilteredList<>(list, b -> true);

        keywordfield.textProperty().addListener((observable, oldvalue, newvalue) -> {
            filteredData.setPredicate(t -> {
                if (newvalue.isEmpty() || newvalue == null) {
                    return true;
                }
                String searchkeyword = newvalue.toLowerCase();

                String tournoisNameLower = t.getNom_equipe().toLowerCase();
                int tournoismembres = t.getMembres();
                String m = Integer.toString(tournoismembres);
                int tournoisptsxp = t.getPts_xp();
                String p = Integer.toString(tournoisptsxp);
                int tg = t.getTournois_gagne();
                String tgg = Integer.toString(tg);

                if (tournoisNameLower.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (m.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (p.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (tgg.indexOf(searchkeyword) > -1) {
                    return true;
                }

                return false;

            });
        });

        SortedList<Equipes> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvequipe.comparatorProperty());
        tvequipe.setItems(sortedData);

    }

    @FXML
    private void handleMouseAction(MouseEvent event) {

        Equipes e = tvequipe.getSelectionModel().getSelectedItem();
        tfnom.setText(e.getNom_equipe());
        tfmembres.setText("" + e.getMembres());

        tfptsxp.setText("" + e.getPts_xp());
        tftg.setText("" + e.getTournois_gagne());
        tfid.setText("" + e.getId());
        System.out.println("Vous Avez Selectionnez la colonne dont l'id est = " + e.getId());
    }

    private void executeQuery(String query) {
        Connection conn = getConnection();
        //To change body of generated methods, choose Tools | Templates.
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
            showEquipes();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateEquipe() {
        String query = "update equipe set pts_exp = " + tfptsxp.getText() + ", tournois_gagne = " + tftg.getText() + " where id = " + tfid.getText() + "";

        executeQuery(query);
        showEquipes();

    }

    public void deleteEquipe() {
        String query = "delete from equipe where id = " + tfid.getText() + " ";

        executeQuery(query);
        showEquipes();
    }
 public boolean verif(String t) {

        if (t.isEmpty()) {

            return false;
        }
        return true;
    }
 public boolean verif_capacite(String s ){
        Pattern p = Pattern.compile("^\\d{2}$");
        Matcher m = p.matcher(s);
        return (m.matches());
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnmodif) {
           if(verif(tfptsxp.getText())==false || verif(tftg.getText())==false){
           warning.setText("Verifier Vos Informations");
           }else if(!verif_capacite(tfptsxp.getText())){
           warning.setText("Verifier les Points XP");
           }else if(!verif_capacite(tftg.getText())){
           warning.setText("Verifier les Tournois Gagné");
           }
           else{
            
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Vous etes entrain de modifier, Confirmer la modification");
            if(alert.showAndWait().get() == ButtonType.OK){
            
            
            updateEquipe();
            }}
        } else if (event.getSource() == btnsupp) {
            
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Vous etes entrain de supprimer, Confirmer la Suppression");
            if(alert.showAndWait().get() == ButtonType.OK){
            
            deleteEquipe();
            }
        }else if(event.getSource()==back){
            try {
                root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(EquipeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }

    }

     @FXML
    private void handleClicks(ActionEvent event) throws IOException{
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

    @FXML
    private void on_click_dashboard_button(ActionEvent event) throws IOException {
          root = FXMLLoader.load(getClass().getResource("dashboard.fxml")); //khali hedhi pour le moment
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void on_click_supprimer(ActionEvent event) {
        tfptsxp.setText("");
        tftg.setText("");
        tfnom.setText("");
        tfmembres.setText("");
        warning.setVisible(false);
    }

}

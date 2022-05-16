package gestion.utilisateur;

import gestion.utilisateur.entities.Equipes;
import java.io.File;
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
import java.util.List;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tools.Connexion;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class DashboardController implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Label nshalah;
    @FXML
    private ImageView user_image;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private ImageView admin_image;
    @FXML
    private PieChart pieChart;
    Connection cnx = null;
    @FXML
    private PieChart pieChart2;
    @FXML
    private Button btnMenus1;
    @FXML
    private PieChart pieChart3;
    @FXML
    private Button btnMenus11;
    @FXML
    private Button btn_promotion;
    @FXML
    private Button btnCustomers1;
    @FXML
    private Button btnCustomers11;
    @FXML
    private Button btnOrders1;

    /**
     * Initializes the controller class.
     */
    public int all_users() throws SQLException {
        int count = 0;
        cnx = Connexion.getInstance().getCnx();
        String sql = "SELECT count(*) from user";
        Statement statement = cnx.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            count = rs.getInt(1);
        }

        return count;

    }

    public int all_admins() throws SQLException {
        int count = 0;
        cnx = Connexion.getInstance().getCnx();
        String sql = "SELECT count(*) from user where role = '" + "admin" + "'";
        Statement statement = cnx.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            count = rs.getInt(1);
        }

        return count;

    }

    public int all_coachs() throws SQLException {
        int count = 0;
        cnx = Connexion.getInstance().getCnx();
        String sql = "SELECT count(*) from user where role = '" + "coach" + "'";
        Statement statement = cnx.createStatement();

        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            count = rs.getInt(1);
        }

        return count;

    }

    public int all_clients() throws SQLException {
        int count = 0;
        cnx = Connexion.getInstance().getCnx();
        String sql = "SELECT count(*) from user where role = '" + "client" + "'";
        Statement statement = cnx.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            count = rs.getInt(1);
        }

        return count;

    }

    public int number_of_males() throws SQLException {
        int count = 0;
        cnx = Connexion.getInstance().getCnx();
        String sql = "SELECT count(*) from user where gender = '" + "Male" + "'";
        Statement statement = cnx.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            count = rs.getInt(1);
        }

        return count;

    }

    public int number_of_females() throws SQLException {
        int count = 0;
        cnx = Connexion.getInstance().getCnx();
        String sql = "SELECT count(*) from user where gender = '" + "Female" + "'";
        Statement statement = cnx.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            count = rs.getInt(1);
        }

        return count;

    }

    public String equipe_plus_gagne() throws SQLException {
        String query = "select nom_equipe from equipe where tournois_gagne = (select max(tournois_gagne) from equipe)";
        Connection conn = getConnection();

        Statement st;
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        String max = "";
        if (rs.next()) {
            max = rs.getString(1);
        }

        return max;
    }

    public String equipe_moins_gagnante() throws SQLException {
        String query = "select nom_equipe from equipe where tournois_gagne = (select min(tournois_gagne) from equipe)";
        Connection conn = getConnection();

        Statement st;
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        String min = "";
        if (rs.next()) {
            min = rs.getString(1);
        }

        return min;
    }

    public List<String> test() throws SQLException {
        String query = "select nom_equipe from equipe where tournois_gagne = (select min(tournois_gagne) from equipe)";
        Connection conn = getConnection();

        Statement st;
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        String min = "";
        List a = new ArrayList();
        while (rs.next()) {
            a.add(rs.getString("nom_equipe"));
        }

        return a;
    }

    public int equipe_moins_gagnante1() throws SQLException {
        String query = "select min(tournois_gagne) from equipe";
        Connection conn = getConnection();

        Statement st;
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        int min = 0;
        if (rs.next()) {
            min = rs.getInt(1);
        }

        return min;
    }

    public int equipe_moins_gagnante2() throws SQLException {
        String query = "select max(tournois_gagne) from equipe";
        Connection conn = getConnection();

        Statement st;
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        int min = 0;
        if (rs.next()) {
            min = rs.getInt(1);
        }

        return min;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        retrievedata a = retrievedata.getInstance("testtt", "", 0);
        nshalah.setText(a.getUsername());
     try{
           InputStream stream = new FileInputStream("C:\\Users\\oussa\\PhpstormProjects\\gaming_app\\public\\uploads\\photos\\"+a.getImage());
      Image image4 = new Image(stream);
      admin_image.setImage(image4);   
              
          }catch(Exception ex){
              System.out.println(ex);
          }

        try {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Admins", all_admins()),
                    new PieChart.Data("Coachs", all_coachs()),
                    new PieChart.Data("Clients", all_clients())
            );
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Males", number_of_males()),
                    new PieChart.Data("Females", number_of_females())
            );
            String t = equipe_plus_gagne();
            ObservableList<PieChart.Data> pieChartData3 = FXCollections.observableArrayList(
                    new PieChart.Data(t, equipe_moins_gagnante2()),
                    new PieChart.Data(equipe_moins_gagnante(), equipe_moins_gagnante1())
            );
            pieChart.setData(pieChartData);
            pieChart.setTitle("Number of users : ");
            pieChart2.setData(pieChartData2);
            pieChart2.setTitle("Genders : ");
            pieChart3.setData(pieChartData3);
            pieChart3.setTitle("Résultat des equipes : ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void handleClicks(ActionEvent actionEvent) {

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
    private void on_click_sign_out(ActionEvent event) {

    }

    @FXML
    private void on_click_message_button(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ChatBot.fxml"));

        stage = new Stage();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("ChatBot");
        stage.show();
    }

    @FXML
    private void on_click_tournaments(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void on_click_teams(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("equipe.fxml"));
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
    private void on_click_promotion(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Promotion.fxml"));
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

}

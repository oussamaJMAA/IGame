/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/*****/
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.sql.Date;
import Entities.Game;
import Entities.Category;
import Service.GameService;
import java.io.File;

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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;
/*import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/
 import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * FXML Controller class
 *
 * @author Malak
 */
public class GestionJeuController implements Initializable {

      private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Label label;


    @FXML
    private VBox tdlien;
    private TextField tdlienimg;

    @FXML
     private TableView<Game> tabGame;
   // @FXML
  //  private TableView<Game> TabGame;

    GameService serviceG = new GameService();
    private ObservableList<Game> GameData = FXCollections.observableArrayList();


    @FXML
    private TableColumn<Game, String> colGcat;
   
   @FXML
    private TableColumn<Game, String> colGnom;
    @FXML
    private TableColumn<Game, String> colGlien;
    @FXML
    private TableColumn<Game, String> colGimg;

   
    @FXML
    private TextField tdid;
    @FXML
    private Button annuler;
    @FXML
    private TextField keywordtextfield;
    @FXML
    private Button pdf;
    @FXML
    private Button back;
    @FXML
    private Label warning;
    @FXML
    private TextField tdGlienimg;
 
    @FXML
    private TextField tddescreeption;
    @FXML
    private Button btn_addG;
   
    @FXML
    private Button btn_updateG;
    @FXML
    private Button btn_deleteG;
 
    @FXML
    private TextField nom_jeu;
    @FXML
    private Button btn_Cat;
    @FXML
    private TextField tdGlien;
    @FXML
    private TextField tdGcategorie;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        // Connection conn = getConnection();
        // session test = ;
       // colid.setVisible(false);
      /*  warning.setVisible(false);
        time_choice.getItems().addAll(Game_time);
        time_choice.setOnAction(this::tt);
        LocalDate minDate = LocalDate.now();
        LocalDate maxDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.MAX.getMonth(),
                LocalDate.MAX.getDayOfMonth());
        tfdate.setDayCellFactory(d -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
            }
        });
      */
        showGame();
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

            showGame();

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
    private void handleButtonAction(ActionEvent event) throws ParseException, SQLException, MessagingException {

        if (event.getSource() == btn_addG) {

             String query = "insert into game(game_name,category_name,game_description,game_link,game_img) values('"+ nom_jeu.getText()+"','"+tdGcategorie.getText()+"','"+tddescreeption.getText()+"','"+tdGlien.getText()+"','"+tdGlienimg.getText()+"';)";
        executeQuery(query);
       
            
        } else if (event.getSource() == btn_updateG) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Vous etes entrain de modifier, Confirmer la modification");
            if (alert.showAndWait().get() == ButtonType.OK) {

                updateGame();
            }
            label.setText("Modification effectué !");
        } else if (event.getSource() == btn_deleteG) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Vous etes entrain de Supprimer, Confirmer la Suppression");
          
            label.setText("Suppression effectué !");

        } else if (event.getSource() == annuler) {
        
           tdGcategorie.setText("");

            tdGlien.setText("");
            tdlienimg.setText("");

            warning.setVisible(false);
        }

    }

    public ObservableList<Game> getGameList() {
        ObservableList<Game> GameList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select * from Game";
        Statement st;

        ResultSet rs;
        try {
            st = conn.createStatement();

            rs = st.executeQuery(query);
            Game Game;
            while (rs.next()) {
                Game = new Game(rs.getInt("game_id"), rs.getString("game_name"), rs.getString("category_name"),
                        rs.getString("game_description"), rs.getString("game_link"), rs.getString("game_img"));
                GameList.add(Game);

            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());

        }
        return GameList;
    }

    public void showGame() {

        ObservableList<Game> list = getGameList();
        Game Game;
       // colIdGame.setCellValueFactory(new PropertyValueFactory<Game, Integer>("game_id"));
        colGnom.setCellValueFactory(new PropertyValueFactory<Game, String>("game_name"));
        colGcat.setCellValueFactory(new PropertyValueFactory<Game, String>("category_name"));
        colGlien.setCellValueFactory(new PropertyValueFactory<Game, String>("game_link"));
        colGimg.setCellValueFactory(new PropertyValueFactory<Game, String>("game_img"));
        tabGame.setItems(list);

        FilteredList<Game> filteredData = new FilteredList<>(list, b -> true);

       keywordtextfield.textProperty().addListener((observable, oldvalue, newvalue) -> {
            filteredData.setPredicate(t -> {
                if (newvalue.isEmpty() || newvalue == null) {
                    return true;
                }
                String searchkeyword = newvalue.toLowerCase();

                String GameNameLower = t.getGame_name().toLowerCase();
                
                
                if (GameNameLower.indexOf(searchkeyword) > -1) {
                    return true;
                }
              

                return false;

            });
        });

        SortedList<Game> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tabGame.comparatorProperty());
        tabGame.setItems(sortedData);

    }

      @FXML
    public void insertGame() throws ParseException {
               DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        String query = "insert into game (game_name,category_name,game_description,game_link,game_img) value('"+ nom_jeu.getText()+"','"+ tdGcategorie.getText()+"','"+tddescreeption.getText()+"','"+tdGlien.getText()+"','"+tdGlienimg.getText()+"')";
        executeQuery(query);
       showGame();
    }

    @FXML
    public void updateGame() throws ParseException {
               DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        String query = "UPDATE Game SET category_name = '" + tdGcategorie.getText()
                + "', game_description = '" + tddescreeption.getText() + "',game_link = '" + tdGlien.getText()
                +"',game_img = '" + tdlienimg.getText()+ "' where game_name = '" + nom_jeu.getText() + "';";
       
        executeQuery(query);
        showGame();

    }

    @FXML
    public void deleteGame() {
               DateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        String query = "delete from Game where game_name = " + nom_jeu.getText() + " ";

       executeQuery(query);
        showGame();
    }

   

    private void swtichTomenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /*private void createPdf() throws IOException {
      //  Document document = new Document();
        try {

            JFileChooser choose = new JFileChooser();

            choose.setFileFilter(new FileNameExtensionFilter("PDF Documents", "*.pdf"));
            choose.showOpenDialog(null);

            Path sourcePath = choose.getSelectedFile().toPath();

            String p = sourcePath.toString();

            //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(p));
            //document.open();
            //PdfPTable table = new PdfPTable(7);
           // table.addCell("Nom Game");
            //table.addCell("category ");
            //table.addCell(" description");
            //table.addCell("image");
            
            for (int i = 0; i < getGameList().size(); i++) {
                String nom_jeu = getGameList().get(i).getGame_name();
                String category_name = getGameList().get(i).getCategory_name();
                String game_description = getGameList().get(i).getgame_description();
                
            }

            //document.add(table);
            //document.close();
            //writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

  /*  @FXML
    private void on_click_pdf(ActionEvent event) throws IOException {
        //createPdf();
    }
*/
   
   /* public List<String> get_email(int id) throws SQLException {
        String query = "select u.email as 'em' from user u inner join equipe e on u.equipe = e.id inner join participation p on p.id_equipe = e.id where p.id_Game = "
                + id + "";
        Connection conn = getConnection();

        Statement st;
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        int i = 1;
        List test = new ArrayList();

        while (rs.next()) {
            test.add(rs.getString("em"));
            i++;
        }
        System.out.println(test.toString());
        return test;

    }*/

   /* public void mail(String to, String eq) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.transport.protocl", "smtp");

       // Session session = Session.getInstance(properties, new Authenticator() {
      //      @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("54@gmail.com", "");
            }
        });
        Message message = new MimeMessage(session);
        message.setSubject(eq + "Is Canceled");
        message.setContent("<h1>chere Membre <br>" + eq + " on a un nouveau jeu pour vous !</h1>", "text/html");
        Address addressTo = new InternetAddress(to);
        message.setRecipient(Message.RecipientType.TO, addressTo);
        Transport.send(message);
    }*/


    @FXML
    private void swtichToCategory(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GestionCategorie.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
       stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
    }

  /*  @FXML
    private void insertImage(ActionEvent event) {
        
        FileChooser open = new FileChooser();

        Stage stage = (Stage) tdlienimg.getScene().getWindow();

        File file = open.showOpenDialog(stage);

        if (file != null) {

            String path = file.getAbsolutePath();

            path = path.replace("\\", "\\\\");

           // file_path.setText(path);

            //game image = new Image(file.toURI().toString(), 110, 110, false, true);

            //image_view.setImage(image);

        } else {

            System.out.println("NO DATA EXIST!");

        }
    
    }*/

    @FXML
    private void on_click_pdf(ActionEvent event) {
    }

}

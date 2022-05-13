/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;
import gestion.utilisateur.entities.Tournois;
import java.io.IOException;

import com.itextpdf.text.Document;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;

import javafx.scene.control.DatePicker;
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
 import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    private Button pdf;
    private Button back;
    @FXML
    private TableColumn<Tournois, Integer> colpts;
    @FXML
    private TextField tfpts;
    @FXML
    private Label warning;
    @FXML
    private TableColumn<Tournois, String> time;

    String[] tournois_time = {"17:00:00", "18:00:00", "19:00:00", "20:00:00", "21:00:00"};
    @FXML
    private ChoiceBox<String> time_choice;
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
    private Button participation;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
              retrievedata a = retrievedata.getInstance("", "",0);
        test.setText(a.getUsername());
        int jj = a.getImage().lastIndexOf('\\');
        admin_image.setImage(new Image(DashboardController.class.getResourceAsStream(a.getImage().substring(jj + 1))));
        // TODO

        //Connection conn = getConnection();
        // session test = ;
        colid.setVisible(false);
        warning.setVisible(false);
        time_choice.getItems().addAll(tournois_time);
        time_choice.setOnAction(this::tt);
        LocalDate minDate = LocalDate.now();
        LocalDate maxDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.MAX.getMonth(), LocalDate.MAX.getDayOfMonth());
tfdate.setDayCellFactory(d ->
           new DateCell() {
               @Override public void updateItem(LocalDate item, boolean empty) {
                   super.updateItem(item, empty);
                   setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
               }});
        showTournois();
        time_choice.setValue("17:00:00");
        tfdate.setValue(minDate);
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

    public void tt(ActionEvent event) {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String a = time_choice.getValue();
        try {
            java.sql.Time timeValue = new java.sql.Time(formatter.parse(a).getTime());
            //System.out.println(timeValue);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean verif(String t) {

        if (t.isEmpty()) {

            return false;
        }
        return true;
    }

 
   
        public static boolean isStringOnlyAlphabet(String str)
    {
 
        return ((str != null) && (!str.equals(""))
                && (str.matches("^[a-zA-Z]*$"))&&str.length()<=12);
    }
    public boolean verif_capacite(String s ){
        Pattern p = Pattern.compile("^\\d{2}$");
        Matcher m = p.matcher(s);
        return (m.matches());
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws ParseException, SQLException, MessagingException {

        if (event.getSource() == btn_conf) {

            if (verif(time_choice.getValue()) == false||verif(tfdate.getValue().toString()) == false||verif(tdnomtournois.getText()) == false || verif(tfcapacite.getText()) == false || verif(tfrecompense.getText()) == false || verif(tfplatforme.getText()) == false|| verif(tfpts.getText()) == false) {
                warning.setText("Verifier Vos Informations!");
                warning.setVisible(true);
            } 
             else if( !verif_capacite(tfcapacite.getText())){
                   warning.setText("Verifier la capacité !");
                warning.setVisible(true);
            }
               else if(  !isStringOnlyAlphabet(tfplatforme.getText())){
                   warning.setText("Verifier le nom du plateforme !");
                warning.setVisible(true);
       
             }
             else if( !verif_capacite(tfpts.getText())){
                   warning.setText("Verifier les points XP !");
                warning.setVisible(true);
            }
           
           
            
            
            else {
                warning.setVisible(false);
                insertTournois();
                label.setText("l'Ajout est effectué !");
            }
        } else if (event.getSource() == btn_conf1) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Vous etes entrain de modifier, Confirmer la modification");
            if (alert.showAndWait().get() == ButtonType.OK) {

                updateTournois();
            }
            label.setText("Modification effectué !");
        } else if (event.getSource() == btn_conf11) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("Vous etes entrain de Supprimer, Confirmer la Suppression");
            if (alert.showAndWait().get() == ButtonType.OK) {
notif_del(Integer.parseInt(tdid.getText()),tdnomtournois.getText());
                deleteTournois();
         
   
            }
            label.setText("Suppression effectué !");

        } else if (event.getSource() == annuler) {
            tdnomtournois.setText("");
            tfdate.setValue(null);
            tfcapacite.setText("");
            tfplatforme.setText("");
            tfrecompense.setText("");
            tfpts.setText("");
         //   time_choice.getSelectionModel().select("");
            warning.setVisible(false);
        } else if (event.getSource() == back) {
            try {
                root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    public ObservableList<Tournois> getTournoisList() {
        ObservableList<Tournois> TournoisList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select * from tournois";
        Statement st;
       
        ResultSet rs;
        try {
            st = conn.createStatement();
           
            rs = st.executeQuery(query);
            Tournois tournois;
            while (rs.next()) {
                tournois = new Tournois(rs.getInt("id"), rs.getString("nom_tournois"), rs.getDate("date_tournois"), rs.getInt("capacite"), rs.getString("platforme"), rs.getTime("time_t"), rs.getString("recompense"), rs.getInt("pts_xp"));
                TournoisList.add(tournois);

            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());

        }
        return TournoisList;
    }

    public void showTournois() {

        ObservableList<Tournois> list = getTournoisList();
        Tournois tournois;
        colid.setCellValueFactory(new PropertyValueFactory<Tournois, Integer>("id"));
        colnomtournois.setCellValueFactory(new PropertyValueFactory<Tournois, String>("nom_tournois"));
        coldate.setCellValueFactory(new PropertyValueFactory<Tournois, java.sql.Date>("date"));
        colcapacite.setCellValueFactory(new PropertyValueFactory<Tournois, Integer>("capacite"));
        colplatforme.setCellValueFactory(new PropertyValueFactory<Tournois, String>("platforme"));

        colrecompense.setCellValueFactory(new PropertyValueFactory<Tournois, String>("recompense"));
        colpts.setCellValueFactory(new PropertyValueFactory<Tournois, Integer>("pts_xp"));
        time.setCellValueFactory(new PropertyValueFactory<Tournois, String>("time"));
        tvtournois.setItems(list);

        FilteredList<Tournois> filteredData = new FilteredList<>(list, b -> true);

        keywordtextfield.textProperty().addListener((observable, oldvalue, newvalue) -> {
            filteredData.setPredicate(t -> {
                if (newvalue.isEmpty() || newvalue == null) {
                    return true;
                }
                String searchkeyword = newvalue.toLowerCase();

                String tournoisNameLower = t.getNom_tournois().toLowerCase();
                String tournoisDate = t.getDate().toString();

                String tournoisplatforme = t.getPlatforme();
                String tournoisrecompense = t.getRecompense();
                String pts = Integer.toString(t.getPts_xp());
                int tournoiscapacite = t.getCapacite();
                String c = Integer.toString(tournoiscapacite);
String ttt = t.getTime().toString();
                if (tournoisNameLower.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (tournoisDate.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (tournoisplatforme.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (tournoisrecompense.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (c.indexOf(searchkeyword) > -1) {
                    return true;
                }

                if (pts.indexOf(searchkeyword) > -1) {
                    return true;
                }
               if(ttt.indexOf(searchkeyword)>-1){return true;}

                return false;

            });
        });

        SortedList<Tournois> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvtournois.comparatorProperty());
        tvtournois.setItems(sortedData);

    }

    public void insertTournois() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String a = time_choice.getValue();
        java.sql.Time timeValue = new java.sql.Time(formatter.parse(a).getTime());
        String query = "insert into tournois (nom_tournois,capacite,platforme,recompense,date_tournois,pts_xp,time_t)"
                + " values('" + tdnomtournois.getText() + "',"
                + "" + tfcapacite.getText() + ",'" + tfplatforme.getText() + "','" + tfrecompense.getText() + "',DATE'" + tfdate.getValue() + "',"
                + "" + tfpts.getText() + ",'" + timeValue + "') ";
        executeQuery(query);
    }

    public void updateTournois() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String a = time_choice.getValue();
        java.sql.Time timeValue = new java.sql.Time(formatter.parse(a).getTime());
        String query = "update tournois set pts_xp = " + tfpts.getText() + ",capacite = " + tfcapacite.getText() + ", platforme = '" + tfplatforme.getText() + "',recompense = '" + tfrecompense.getText() + "',date_tournois = DATE'" + tfdate.getValue() + "', nom_tournois = '" + tdnomtournois.getText() + "',time_t = '" + timeValue + "' where id = " + tdid.getText() + "";
 executeQuery(query);
        showTournois();
       
    }

    public void deleteTournois() {
        String query = "delete from tournois where id = " + tdid.getText() + " ";

        executeQuery(query);
        showTournois();
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Tournois t = tvtournois.getSelectionModel().getSelectedItem();
        tdnomtournois.setText(t.getNom_tournois());
        tfdate.setValue(t.getDate().toLocalDate());

        tfcapacite.setText("" + t.getCapacite());
        tfplatforme.setText(t.getPlatforme());
        tfrecompense.setText(t.getRecompense());
        tfpts.setText("" + t.getPts_xp());
        tdid.setText("" + t.getId());
        time_choice.setValue(t.getTime().toString());
        System.out.println("Vous Avez Selectionnez la colonne dont l'id est = " + tdid.getText());
    }

    private void swtichTomenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void createPdf() throws IOException {
        Document document = new Document();
        try {
            
            JFileChooser choose = new JFileChooser();

 choose.setFileFilter(new FileNameExtensionFilter("PDF Documents","*.pdf"));
            choose.showOpenDialog(null);

            Path sourcePath = choose.getSelectedFile().toPath();
            
String p = sourcePath.toString();
            
PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(p));
            document.open();
            PdfPTable table = new PdfPTable(7);
            table.addCell("Nom Tournois");

            table.addCell("Capacite");
            table.addCell("Platforme");
            table.addCell("Recompense");
            table.addCell("POINTS XP A GAGNE");
            table.addCell("Date");
            table.addCell("Time");
            for (int i = 0; i < getTournoisList().size(); i++) {
                String nom_t = getTournoisList().get(i).getNom_tournois();
                String date_t = getTournoisList().get(i).getDate().toString();
                int capacite_t = getTournoisList().get(i).getCapacite();
                String plat_t = getTournoisList().get(i).getPlatforme();
                String recom_t = getTournoisList().get(i).getRecompense();
                int pts = getTournoisList().get(i).getPts_xp();
                String time1 = getTournoisList().get(i).getTime().toString();
                table.addCell(nom_t);

                table.addCell(Integer.toString(capacite_t));
                table.addCell(plat_t);
                table.addCell(recom_t);
                table.addCell(Integer.toString(pts));
                table.addCell(date_t);
                table.addCell(time1);
            }

            document.add(table);
            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void on_click_pdf(ActionEvent event) throws IOException {
        createPdf();
    }

    public void notif_del(int id,String n) throws SQLException, MessagingException{
    for(int i =0;i<get_email(id).size();i++){
    mail(get_email(id).get(i),n);
    }
    }
    
 public List<String> get_email(int id) throws SQLException {
        String query = "select u.email as 'em' from user u inner join equipe e on u.equipe = e.id inner join participation p on p.id_equipe = e.id where p.id_tournois = "+id+"";
        Connection conn = getConnection();

        Statement st;
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
         int i=1;
     List test = new ArrayList();
    
     
       while(rs.next()) {
   test.add(rs.getString("em"));
i++;
}
System.out.println(test.toString());
        return test;

    }

    public void mail(String to, String eq) throws MessagingException {
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
        message.setSubject(eq+"Is Canceled");
        message.setContent("<h1>Dear Membre <br>"+eq+" Tournament is Canceled</h1>", "text/html");
        Address addressTo = new InternetAddress(to);
        message.setRecipient(Message.RecipientType.TO, addressTo);
        Transport.send(message);
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
}

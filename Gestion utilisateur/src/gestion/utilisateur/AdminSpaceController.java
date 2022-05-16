/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import gestion.utilisateur.entities.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
 import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import tools.Connexion;


/**
 * FXML Controller class
 *
 * @author oussa
 */
public class AdminSpaceController implements Initializable {

    private ImageView photo;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Label hiLabel;
    @FXML
    private TableView<User> usersTable;
    // @FXML
    //private TableColumn<User, String> idcol;
    @FXML
    private TableColumn<User, String> firstnamecol;
    @FXML
    private TableColumn<User, String> lastnamecol;
    @FXML
    private TableColumn<User, String> emailcol;
    @FXML
    private TableColumn<User, String> usernamecol;
    @FXML
    private TableColumn<User, String> gendercol;
    @FXML
    private TableColumn<User, String> rolecol;
    @FXML
    private TableColumn<User, String> action;
    @FXML
    private TableColumn<User, String> addresscol;
    @FXML
    private TableColumn<User, String> phonecol;
    @FXML
    private TableColumn<User, String> nationalitycol;
    @FXML
    private TableColumn<User, String> imagecol;
    @FXML
    private TableColumn<?, ?> datecol;
    @FXML
    private TextField searchtf;
    @FXML
    private ImageView admin_image;
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
    private Button btnSignout;
    @FXML
    private Label total_coachs;
    @FXML
    private Label total_admins;
    @FXML
    private Label total_users;
    @FXML
    private Label total_clients;

 

    String query = null;
    Connection cnx = null;
    PreparedStatement p = null;
    PreparedStatement preparedStatement = null;

    ResultSet rs = null;
    ObservableList<User> UserList = FXCollections.observableArrayList();
    User user = null;
    @FXML
    private Label nshalah;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnMenus1;
    @FXML
    private Button btnMenus11;
    @FXML
    private Button btnCustomers1;
    @FXML
    private Button btnCustomers11;
    @FXML
    private Button btnOrders1;
    @FXML
    private Button btn_promotion;

    public int all_users() throws SQLException {
        int count = 0;
        cnx = Connexion.getInstance().getCnx();
        String sql = "SELECT count(*) from user";
        Statement statement = cnx.createStatement();
        rs = statement.executeQuery(sql);

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

        rs = statement.executeQuery(sql);

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
         rs = statement.executeQuery(sql);

        if (rs.next()) {
            count = rs.getInt(1);
        }

        return count;

    }

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
              
          }catch(Exception ex){
              System.out.println(ex);
          }
        loadDate();
        try {
            total_users.setText(Integer.toString(all_users()));
            total_admins.setText(Integer.toString(all_admins()));
            total_coachs.setText(Integer.toString(all_coachs()));
            total_clients.setText(Integer.toString(all_clients()));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void getAddView(MouseEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("add.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void print(MouseEvent event) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        try {
    JFileChooser choose = new JFileChooser();

 choose.setFileFilter(new FileNameExtensionFilter("PDF Documents","*.pdf"));
            choose.showOpenDialog(null);

            java.nio.file.Path sourcePath = choose.getSelectedFile().toPath();
            
String p = sourcePath.toString();
            
PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(p));
                      
            document.open();
            PdfPTable table = new PdfPTable(5);
            table.addCell("Firstname");
            table.addCell("Lastname");
            table.addCell("Username");
            table.addCell("Role");
            table.addCell("Phone");
           
            for (int i = 0; i < UserList.size(); i++) {
                String fn = UserList.get(i).getFirstname();
                String ln = UserList.get(i).getLastname();
                String un = UserList.get(i).getUsername();
                String r = UserList.get(i).getRole();
                int phn = UserList.get(i).getPhone();
              
                table.addCell(fn);
                table.addCell(ln);
                table.addCell(un);
                table.addCell(r);
                table.addCell(Integer.toString(phn));
                

            }
            document.add(table);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("You have just generated a pdf File");
            alert.showAndWait();
            document.close();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void refreshTable() {
        try {
            UserList.clear();
            query = " SELECT firstname,lastname,email,username,gender,role,address,phone,nationality FROM user ";
            p = cnx.prepareStatement(query);
            rs = p.executeQuery();
            while (rs.next()) {
          
                UserList.add(new User(
                        //rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("role"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("nationality"),
                        rs.getInt("phone")
                       
                ));
                usersTable.setItems(UserList);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void loadDate() {

        cnx = Connexion.getInstance().getCnx();
        refreshTable();

        //idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        //imagecol.setCellValueFactory(new PropertyValueFactory<>("photo"));
        firstnamecol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnamecol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        usernamecol.setCellValueFactory(new PropertyValueFactory<>("username"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        rolecol.setCellValueFactory(new PropertyValueFactory<>("role"));
        addresscol.setCellValueFactory(new PropertyValueFactory<>("address"));
        phonecol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        nationalitycol.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        //datecol.setCellValueFactory(new PropertyValueFactory<>("created_at"));
           
        //add cell of button edit 
        Callback<TableColumn<User, String>, TableCell<User, String>> cellFoctory = (TableColumn<User, String> param) -> {
            // make cell containing buttons
            final TableCell<User, String> cell = new TableCell<User, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Delete");
                            alert.setHeaderText("You're about to delete this user!");
                            alert.setContentText("Are you sure you want to delete this user ?");
                            if (alert.showAndWait().get() == ButtonType.OK) {
                                try {
                                    user = usersTable.getSelectionModel().getSelectedItem();
                                    query = "DELETE FROM `user` WHERE username  = '" + user.getUsername() + "' ";
                                    cnx = Connexion.getInstance().getCnx();
                                    preparedStatement = cnx.prepareStatement(query);
                                    preparedStatement.execute();
                                    refreshTable();

                                } catch (SQLException ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }

                        });

                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            user = usersTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("add.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }

                            AddController add = loader.getController();
                            add.setUpdate(true);
                            add.setTextField(user.getUsername(), user.getFirstname(),
                                    user.getLastname(), user.getPassword(), user.getUsername(), user.getEmail(), user.getAddress(), user.getPhone());
                            Parent parent = loader.getRoot();
                            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene = new Scene(parent);
                            stage.setScene(scene);
                            stage.show();

                            /* Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                             */
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        action.setCellFactory(cellFoctory);
        usersTable.setItems(UserList);

        FilteredList<User> filteredData = new FilteredList<>(UserList, b -> true);

        searchtf.textProperty().addListener((observable, oldvalue, newvalue) -> {
            filteredData.setPredicate(t -> {
                if (newvalue.isEmpty() || newvalue == null) {
                    return true;
                }
                String searchkeyword = newvalue.toLowerCase();
                //String user_firstname = t.getFirstname().toLowerCase();
                //String user_lastname = t.getFirstname().toLowerCase();
                String user_username = t.getUsername().toLowerCase();
                //String user_role = t.getRole().toLowerCase();
                //String user_gender = t.getGender().toLowerCase();
                //String user_email = t.getEmail().toLowerCase();
                //String user_address = t.getAddress().toLowerCase();
               // String user_nationality = t.getNationality().toLowerCase();
                //int user_phone = t.getPhone();
                //String k = Integer.toString(user_phone);
               //String user_date = t.getCreated_at().toString();
/*
                if (user_firstname.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (user_lastname.indexOf(searchkeyword) > -1) {
                    return true;
                }
             
                if (user_role.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (user_gender.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (user_email.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (user_address.indexOf(searchkeyword) > -1) {
                    return true;
                }
                if (user_nationality.indexOf(searchkeyword) > -1) {
                    return true;
                }*/
               
                  if (user_username.indexOf(searchkeyword) > -1) {
                    return true;
                }
                /*
                if (user_date.indexOf(searchkeyword) > -1) {
                    return true;
                }

                if (k.indexOf(searchkeyword) > -1) {
                    return true;
                }
*/
                return false;

            });
        });
        SortedList<User> s = new SortedList<>(filteredData);
        s.comparatorProperty().bind(usersTable.comparatorProperty());
        usersTable.setItems(s);
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
    private void on_click_users_button(ActionEvent event) throws IOException {
       root = FXMLLoader.load(getClass().getResource("AdminSpace.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void on_click_sign_out(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
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

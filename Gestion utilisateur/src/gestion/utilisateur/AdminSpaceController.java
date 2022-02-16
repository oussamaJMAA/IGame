/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import tools.Connexion;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class AdminSpaceController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label hiLabel;
    @FXML
    private TableView<User> usersTable;
    @FXML
    private TableColumn<User, String> idcol;
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
    private TableColumn<User,String> action;

    public void display(String username) {
        hiLabel.setText("Welcome " + username);
    }

    String query = null;
    Connection cnx = null;
    PreparedStatement p = null;
          PreparedStatement  preparedStatement = null;

    ResultSet rs = null;
    ObservableList<User> UserList = FXCollections.observableArrayList();
    User user = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }
      @FXML
    private void getAddView(MouseEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("add.fxml"));  
           stage= (Stage)((Node)event.getSource()).getScene().getWindow();
           scene= new Scene(root);
           stage.setScene(scene);
           stage.show();
    }

    @FXML
    private voi
                           

                          

                        });
                        
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            user = usersTable.getSelectionModel().getSelectedItem();
                          FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("add.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                               System.out.println(ex.getMessage());
                            }
                            
                            AddController add = loader.getController();
                          add.setUpdate(true);
                            add.setTextField(user.getId(),user.getFirstname(), 
                                   user.getLastname(),user.getPassword(), user.getUsername(),user.getEmail());
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
        
        
        
        
        
        
        
        
        
        
        
    }

    @FXML
    private void getSettings(MouseEvent event) {
        
        
    }

    @FXML
    private void getLogout(MouseEvent event) throws IOException {
         Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Are you sure you want to logout ?");
        if (alert.showAndWait().get() == ButtonType.OK) {
             Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));  
           stage= (Stage)((Node)event.getSource()).getScene().getWindow();
           scene= new Scene(root);
           stage.setScene(scene);
           stage.show();

        }
    }

 
  
}

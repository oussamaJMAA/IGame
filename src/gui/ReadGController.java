/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import Entities.Game;
 import Entities.Like;
import static java.awt.SystemColor.desktop;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Malak
 */
public class ReadGController implements Initializable {
     private Parent root;
    private Stage stage;
    private Scene scene;
    
    private GridPane grid;

    @FXML
    private Label Game_name;
    @FXML
    private Label descreption;
    @FXML
    private Button btn_like;
    @FXML
    private Button btn_tournois;
    @FXML
    private Button btn_lien;
    @FXML
    private Pane chosenGameCard;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /***************/
    }
 /*private List<Game> getData() {
        int column = 0;
        int row = 1;
        try {
           // GameList = executeQuery("select * from game");
                      ObservableList<Game> GameList = FXCollections.observableArrayList();

                   String query = "select * from Game";

           for (int i = 0; i < GameList.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(GameList.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (Exception e) {
 e.printStackTrace();
        }
       return GameList;
    
 }*/
        /***************/
 

  private void setChosenFruit(Game game) {
     /*   Game_name.setText(game.getGame_name());
        descreption.setText(Main.CURRENCY + game.getgame_desreption());
        image = new Image(getClass().getResourceAsStream(game.getImgSrc()));
        game_Img.setImage(image);
        chosenGameCard.setStyle("-fx-background-color: #"  +
                "    -fx-background-radius: 30;");*/
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

           // showCategory();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void like(ActionEvent event) {
        //String id = game_id.getText();
      //String iduser=user_id.getText();
      //like like =new Likes(id,iduser);
      // LikesService likeSurv =new LikesService();
      //String mylike= likeSurv.gererlikes(like);
     //text_likes.setText(mylike);
       
     
    }

    @FXML
    private void GOTOtournois(ActionEvent event) {
        //  root = FXMLLoader.load(getClass().getResource(tournois));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
       stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void GOTOgame_link(ActionEvent event) throws IOException {
              java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://www.google.com/search?channel=trow5&client=firefox-b-d&q=games"));

    }
    
}

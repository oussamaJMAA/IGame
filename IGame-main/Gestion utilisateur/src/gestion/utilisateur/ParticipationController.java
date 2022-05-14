/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;
import gestion.utilisateur.entities.Participation;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author rouka
 */
public class ParticipationController implements Initializable {

    @FXML
    private TableView<Participation> table_participation;
    @FXML
    private TableColumn<Participation,String> col_equipe;
    @FXML
    private TableColumn<Participation, String> col_tournois;
    @FXML
    private TextField keywordtextfield;
    @FXML
    private Button delete;
    @FXML
    private TextField id_equipe;
    @FXML
    private TextField id_tournois;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         showParticipation();
         id_equipe.setVisible(false);
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

           // showTournois();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public ObservableList<Participation> getParticipationList() {
        ObservableList<Participation> ParticipationList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "select e.nom_equipe, t.nom_tournois from participation p inner join equipe e on p.id_equipe = e.id inner join tournois t on p.id_tournois = t.id";
        Statement st;
       
        ResultSet rs;
        try {
            st = conn.createStatement();
           
            rs = st.executeQuery(query);
            Participation p;
            while (rs.next()) {
               p = new Participation(rs.getString(1),rs.getString(2));
                ParticipationList.add(p);

            }
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());

        }
        return ParticipationList;
    }
    
   
    public void showParticipation() {

        ObservableList<Participation> list = getParticipationList();
     Participation p;
        col_equipe.setCellValueFactory(new PropertyValueFactory<Participation, String>("equipe"));
        col_tournois.setCellValueFactory(new PropertyValueFactory<Participation, String>("tournois"));
  
     
        table_participation.setItems(list);

        FilteredList<Participation> filteredData = new FilteredList<>(list, b -> true);

        keywordtextfield.textProperty().addListener((observable, oldvalue, newvalue) -> {
            filteredData.setPredicate(t -> {
                if (newvalue.isEmpty() || newvalue == null) {
                    return true;
                }
                String searchkeyword = newvalue.toLowerCase();

String tt = t.getTournois();
              String equipe = t.getEquipe();
             
                if (equipe.indexOf(searchkeyword) > -1) {
                    return true;
                }
               if(tt.indexOf(searchkeyword)>-1){
               return true;
               }

                return false;

            });
        });

        SortedList<Participation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_participation.comparatorProperty());
       table_participation.setItems(sortedData);

    }

        @FXML
    private void on_click_table(MouseEvent event) {
      Participation p= table_participation.getSelectionModel().getSelectedItem();
        id_equipe.setText(p.getEquipe());
    id_tournois.setText(p.getTournois());
    }

    
    @FXML
    private void on_click_delete(ActionEvent event) {
      String query = "delete from participation where id_equipe = (select id from equipe where nom_equipe = '"+id_equipe.getText()+"') and id_tournois=(select id from tournois where nom_tournois = '"+id_tournois.getText()+"')";

        executeQuery(query);
        showParticipation();
    }


    
    
}

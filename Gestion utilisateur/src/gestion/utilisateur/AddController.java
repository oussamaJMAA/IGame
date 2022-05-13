package gestion.utilisateur;

import static gestion.utilisateur.RegisterController.isEmailValid;
import static gestion.utilisateur.RegisterController.isMobileValid;
import static gestion.utilisateur.RegisterController.isStringOnlyAlphabet;
import static gestion.utilisateur.RegisterController.isadressValid;
import static gestion.utilisateur.RegisterController.ispasswordValid;
import static gestion.utilisateur.RegisterController.isusernameValid;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import tools.Connexion;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class AddController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    FileChooser fc = new FileChooser();
    @FXML
    private TextField firstnametf;
    @FXML
    private TextField lastnametf;
    @FXML
    private TextField usernametf;
    @FXML
    private RadioButton femaler;
    @FXML
    private RadioButton maler;
    @FXML
    private PasswordField passwordtf;
    @FXML
    private RadioButton coachr;
    @FXML
    private RadioButton adminr;
    @FXML
    private TextField emailtf;
    String query = null;
    Connection cnx = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement;
    private boolean update;
    String userId = "";
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private TextField addresstf;
    @FXML
    private TextField phonetf;
    @FXML
    private Button imagebtn;
    @FXML
    private ImageView imageview;
    String image_path = "";
    Label l = new Label();
    @FXML
    private ToggleGroup rolegroup;
    @FXML
    private ToggleGroup gendergroup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox.getItems().removeAll(combobox.getItems());
combobox.getItems().addAll("Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea North", "Korea South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation", "Rwanda", "St Kitts & Nevis", "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino", "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe");
        combobox.getSelectionModel().select("Tunisia");
    }
    public static boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    public static boolean isMobileValid(String s) {
        Pattern p = Pattern.compile("^\\d{8}$");
        Matcher m = p.matcher(s);
        return (m.matches());
    }
    public static boolean isusernameValid(String s){
 // accepts 5 to 15 characters with any lower case character, digit or special symbol “_-” only.
              Pattern p = Pattern.compile("^[A-Za-z]\\w{5,29}$");
                Matcher m = p.matcher(s);
        return (m.matches());
    }
      public static boolean isStringOnlyAlphabet(String str)
    {
 
        return ((str != null) && (!str.equals(""))
                && (str.matches("^[a-zA-Z]*$"))&&str.length()<=12);
    }
          public static boolean isadressValid(String str)
    {
 
        return ((str != null) && (!str.equals(""))
                && (str.matches("^\\s*[\\da-zA-Z][\\da-zA-Z\\s]*$"))&&str.length()<=12);
    }
                 public static boolean ispasswordValid(String str)
    {
 
        return ((str != null) && (!str.equals(""))
                && (str.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{5,20}$")));
    }

    @FXML
    private void AddUser(ActionEvent event) throws IOException, SQLException {
        
     
        
        if (firstnametf.getText().isEmpty() || lastnametf.getText().isEmpty() || emailtf.getText().isEmpty() || usernametf.getText().isEmpty() || passwordtf.getText()
                .isEmpty() || addresstf.getText().isEmpty() || phonetf.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error");
            alert.showAndWait();
            
            
        } 
            else if (!isStringOnlyAlphabet(lastnametf.getText())){
            lastnametf.setStyle("-fx-background-color:red");
        }
               else if (!isStringOnlyAlphabet(firstnametf.getText())){
            firstnametf.setStyle("-fx-background-color:red");
        }
                 else if(!isusernameValid(usernametf.getText())){
            usernametf.setStyle("-fx-background-color:red");
        }
               else if (!isEmailValid(emailtf.getText())) {
            emailtf.setStyle("-fx-background-color:red");
            
        } 
       else if(!ispasswordValid(passwordtf.getText())){
             
             passwordtf.setStyle("-fx-background-color:red");
         }
     
      
         else if (!isadressValid(addresstf.getText())){
           addresstf.setStyle("-fx-background-color:red");
        }else if (!isMobileValid(phonetf.getText())){
            phonetf.setStyle("-fx-background-color:red");       
        }
        else {
      
            getQuery();
            insert();
             root = FXMLLoader.load(getClass().getResource("AdminSpace.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        

    }

    private void getQuery()  {
       

        if (update == false) {

            query = "INSERT INTO user(firstname,lastname,username,email,password,gender,role,phone,address,nationality,image) values(?,?,?,?,?,?,?,?,?,?,?)";

        } else {
            query = "UPDATE `user` SET "
                    + "`firstname`=?,"
                    + "`lastname`=?,"
                    + "`username`=?,"
                    + "`email`=?,"
                    + "`password`=?,"
                    + "`gender`=?,"
                    + "`role`=?,"
                    + "`phone`=?,"
                    + "`address`=?,"
                    + "`nationality`=?,"
                    + "`image`= ? WHERE username = '" + userId + "'";
        }

    }

    private void insert() {

        try {
            String gender = "";
            if (gendergroup.getSelectedToggle() != null) {
                RadioButton selectedGroup = (RadioButton) gendergroup.getSelectedToggle();
                gender = selectedGroup.getText();

            }
            String role = "client";

            if (rolegroup.getSelectedToggle() != null) {
                RadioButton s = (RadioButton) rolegroup.getSelectedToggle();
                role = s.getText();

            }
             String salt = BCrypt.gensalt(12);
   String hashed_password = BCrypt.hashpw(passwordtf.getText(), salt);
            cnx = Connexion.getInstance().getCnx();
            preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, firstnametf.getText());
            preparedStatement.setString(2, lastnametf.getText());
            preparedStatement.setString(3, usernametf.getText());
            preparedStatement.setString(4, emailtf.getText());
            preparedStatement.setString(5,hashed_password);
            preparedStatement.setString(6, gender);
            preparedStatement.setString(7, role);
            preparedStatement.setInt(8, Integer.parseInt(phonetf.getText()));
            preparedStatement.setString(9, addresstf.getText());
            preparedStatement.setString(10, combobox.getValue());
            preparedStatement.setString(11, image_path);

            preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void BACK(ActionEvent event) throws IOException {
         root = FXMLLoader.load(getClass().getResource("AdminSpace.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1400, 800);
        stage.setScene(scene);
        stage.show();
    }

    void setTextField(String u, String firstname, String lastname, String password, String username, String email, String address, int phone) {

        userId = u;
        firstnametf.setText(firstname);
        lastnametf.setText(lastname);
        passwordtf.setText(password);
        emailtf.setText(email);
        usernametf.setText(username);
        addresstf.setText(address);
        phonetf.setText("" + phone);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    @FXML
    private void on_click_import_image(ActionEvent event) {
        fc.setTitle("Choose an image");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            //image_path= file.getName();
            image_path = file.getAbsolutePath();
            imageview.setImage(new Image(file.toURI().toString()));

        } else {

            System.out.println("Invalid file");
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import tools.Connexion;

/**
 *
 * @author oussa
 */
public class RegisterController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button btnr;
    @FXML
    private ComboBox<String> nationalitycb;
    @FXML
    private TextField phonetf;
    @FXML
    private Button imagebtn;
    FileChooser fc = new FileChooser();
    @FXML
    private ImageView imageview;
    String image_path = "";
    String imageName="";
    Connection cnx = null;
    PreparedStatement insert = null;
    PreparedStatement exist = null;
    PreparedStatement mail = null;
    ResultSet rs = null;
    String user_email;

    @FXML
    public void LoginLink(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private TextField usernametf;
    @FXML
    private TextField passwordtf;
    @FXML
    private TextField firstnametf;
    @FXML
    private TextField lastnametf;
    @FXML
    private TextField addresstf;
    @FXML
    private TextField emailtf;
    @FXML
    private Label messagelabel;
    @FXML
    private RadioButton maler;
    @FXML
    private RadioButton femaler;
    String name_email;

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean check_name(String s) {
        /*boolean ok = false;
        if (s.matches("[a-zA-Z]+") && s.length() < 20) {
            ok = true;
        }*/
        return s.length() < 3;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        nationalitycb.getItems().removeAll(nationalitycb.getItems());
        nationalitycb.getItems().addAll("Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea North", "Korea South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation", "Rwanda", "St Kitts & Nevis", "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino", "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe");
        nationalitycb.getSelectionModel().select("Tunisia");

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
    public void ButtonRegister(ActionEvent event) throws IOException, MessagingException {
        //controle de saisie 

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
            if (Register()) {
                System.out.println("hi");
                try {
                    cnx = Connexion.getInstance().getCnx();
                    String sql = "SELECT  firstname ,email FROM user WHERE username= '" + usernametf.getText() + "'";
                    Statement statement = cnx.createStatement();
                    rs = statement.executeQuery(sql);

                    if (rs.next()) {
                        name_email = rs.getString(1);
                        user_email = rs.getString(2);

                    }
                    mail(user_email, name_email);
                } catch (SQLException e) {
                    e.getMessage();
                }

               root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    public boolean Register() {
        boolean ok = false;

        String firstname = firstnametf.getText();
        String lastname = lastnametf.getText();
        String username = usernametf.getText();
        String email = emailtf.getText();
        String password = passwordtf.getText();
        String salt = BCrypt.gensalt(12);
        String hashed_password = BCrypt.hashpw(password, salt);
        String address = addresstf.getText();
        String nationality = nationalitycb.getValue();
        String gender = "";
        String role = "client";
        String phone = phonetf.getText();
        if (maler.getText().isEmpty() == false) {
            gender = maler.getText();

        }
        if (femaler.getText().isEmpty() == false) {
            gender = femaler.getText();
        }

        try {
            cnx = Connexion.getInstance().getCnx();
            exist = cnx.prepareStatement("SELECT * FROM user where username = ? or email = ? ");
            exist.setString(1, username);
            exist.setString(2, email);
            rs = exist.executeQuery();
            if (rs.isBeforeFirst()) {
                System.out.println("Username or email already exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username or email already taken");
                alert.show();
            } else {
                ok = true;
                insert = cnx.prepareStatement("INSERT INTO user(firstname,lastname,username,email,password,gender,role,phone,address,nationality,image,java_password,is_verified) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                insert.setString(1, firstname);
                insert.setString(2, lastname);
                insert.setString(3, username);
                insert.setString(4, email);
                insert.setString(5, hashed_password);
                insert.setString(6, gender);
                insert.setString(7, role);
                insert.setString(8, phone);
                insert.setString(9, address);
                insert.setString(10, nationality);
                insert.setString(11, imageName);
                insert.setString(12, password);
                insert.setBoolean(13,false);
                insert.executeUpdate();

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (exist != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (insert != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            if (cnx != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
        return ok;
    }

    @FXML
    private void on_click_import_image(ActionEvent event) {

        fc.setTitle("Choose an image");
       fc.setInitialDirectory(new File("C:\\Users\\oussa\\PhpstormProjects\\gaming_app\\public\\uploads\\photos"));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            //image_path= file.getName();
            imageName=file.getName();
            image_path = file.getAbsolutePath();
            imageview.setImage(new Image(file.toURI().toString()));

        } else {

            System.out.println("Invalid file");
        }

    }

    public void mail(String string, String name) throws MessagingException {
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
        message.setSubject("Email From iGame");
        message.setContent("<h1>Welcome " + name + " to our Application</h1>", "text/html");
        Address addressTo = new InternetAddress(string);
        message.setRecipient(Message.RecipientType.TO, addressTo);
        Transport.send(message);
    }

}

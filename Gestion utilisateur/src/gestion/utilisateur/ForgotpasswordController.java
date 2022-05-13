/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.utilisateur;
import java.util.regex.Matcher;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import tools.Connexion;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class ForgotpasswordController implements Initializable {

    @FXML
    private TextField emailtf;
    private Stage stage;
    private Scene scene;
    private Parent root;
    Connection cnx = null;
    PreparedStatement p = null;
    String code = "";
    @FXML
    private Label warning_label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }
public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    @FXML
    private void on_click_confirm(ActionEvent event) throws IOException, MessagingException {
        if (emailtf.getText().isEmpty()) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill all data");
            alert.showAndWait(); 
        }
        else if ( !isValid(emailtf.getText())){
          warning_label.setText("Wrong email");  
        }
        else {
            try {
                cnx = Connexion.getInstance().getCnx();
                String sql = "SELECT  email FROM user WHERE email= '" + emailtf.getText() + "'";
                Statement statement = cnx.createStatement();
                ResultSet rs = statement.executeQuery(sql);
                if (rs.isBeforeFirst()) {
                   code = generate_random_password();
                    mail(emailtf.getText(),code);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Forgot2.fxml"));
                    root = loader.load();
                    Forgot2Controller forgot2Controller=loader.getController();
                    forgot2Controller.setcodelabel(code);
                    forgot2Controller.setusername(emailtf.getText());
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root, 1400, 800);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    warning_label.setText("This email doesn't exist");

                }
            } catch (SQLException e) {
                e.getMessage();
            }
        }
    }

    public void mail(String email, String code) throws MessagingException {
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
        message.setContent("<h1>Password recovery:</h1><br><p>Here is the code " + code + "<p>", "text/html");
        Address addressTo = new InternetAddress(email);
        message.setRecipient(Message.RecipientType.TO, addressTo);
        Transport.send(message);
    }

    private String generate_random_password() {
        // create a string of uppercase and lowercase characters and numbers
        String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        // combine all strings
        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 10;

        for (int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphaNumeric.length());

            // get character specified by index
            // from the string
            char randomChar = alphaNumeric.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;

    }

}

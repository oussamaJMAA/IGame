package application;
import javafx.scene.control.Button;
import library.*;
import controller.MainController;
import com.mysql.cj.admin.ServerController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main extends Application {



    @Override
    public void start(Stage stage) throws Exception {

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(
                "/view/MainPane.fxml")));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("blog");
        stage.show();

    }



    public static void main(String[] args) {
        launch(args);

    }
}

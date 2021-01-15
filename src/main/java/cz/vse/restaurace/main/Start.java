package cz.vse.restaurace.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.InputStream;

public class Start extends Application {

    public static void main(String[] args) {
        launch();
    }

    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("scene_login.fxml");
        Parent root = loader.load(stream);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        //Ikona
        InputStream streamIcon = getClass().getClassLoader().getResourceAsStream("img/reserved.png");
        Image imageIcon = new Image(streamIcon);
        stage.getIcons().add(imageIcon);

        stage.show();
    }
}
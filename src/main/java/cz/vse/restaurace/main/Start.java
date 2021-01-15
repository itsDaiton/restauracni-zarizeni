package cz.vse.restaurace.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;

public class Start extends Application {

    public static void main(String[] args) {
        launch();
    }

    public void start(Stage stage) throws Exception {
        //stage.setMaximized(true);

        FXMLLoader loader = new FXMLLoader();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("screen/scene_main.fxml");
        Parent root = loader.load(stream);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }
}
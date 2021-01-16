package cz.vse.restaurace.main;

import cz.vse.restaurace.MainController;
import cz.vse.restaurace.model.App;
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
        stage.setResizable(false);
        stage.setTitle("Restaurační zařízení");

        FXMLLoader loader = new FXMLLoader();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("scene_main.fxml");
        Parent root = loader.load(stream);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        InputStream streamIcon = getClass().getClassLoader().getResourceAsStream("img/icon.png");
        Image imageIcon = new Image(streamIcon);
        stage.getIcons().add(imageIcon);

        MainController mainController = loader.getController();
        App app = new App();
        mainController.init(app);

        stage.show();
    }
}
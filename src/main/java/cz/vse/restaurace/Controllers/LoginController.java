package cz.vse.restaurace.Controllers;

import cz.vse.restaurace.model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class LoginController {

    public Button btnOpenRegistration;
    public Button btnLogin;
    public TextField textUserName;
    public PasswordField textPassword;

    private App app;

    public void init(App app) {
        this.app = app;
        update();
    }

    public void update() {
        openRegister();
        login();
    }

    public void openRegister() {
        btnOpenRegistration.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader();
            InputStream stream = getClass().getClassLoader().getResourceAsStream("scene_register.fxml");
            Parent root = null;
            try {
                root = loader.load(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Restaurační zařízení");

            InputStream streamIcon = getClass().getClassLoader().getResourceAsStream("img/icon.png");
            Image imageIcon = new Image(streamIcon);
            stage.getIcons().add(imageIcon);

            RegisterController registerController = loader.getController();
            registerController.init(app);
            ((Node)(event.getSource())).getScene().getWindow().hide();
            stage.show();
        });
    }

    public void login() {
        btnLogin.setOnMouseClicked(event -> {
            FXMLLoader loader = new FXMLLoader();
            InputStream stream = getClass().getClassLoader().getResourceAsStream("scene_main.fxml");
            Parent root = null;
            try {
                root = loader.load(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Restaurační zařízení");

            InputStream streamIcon = getClass().getClassLoader().getResourceAsStream("img/icon.png");
            Image imageIcon = new Image(streamIcon);
            stage.getIcons().add(imageIcon);

            MainController mainController = loader.getController();
            mainController.init(app);
            ((Node)(event.getSource())).getScene().getWindow().hide();
            stage.show();
        });
    }
}

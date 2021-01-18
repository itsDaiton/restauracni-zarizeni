package cz.vse.restaurace.Controllers;

import cz.vse.restaurace.AlertWindow;
import cz.vse.restaurace.model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

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
        openMain();
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
            stage.initModality(Modality.APPLICATION_MODAL);

            InputStream streamIcon = getClass().getClassLoader().getResourceAsStream("img/icon.png");
            Image imageIcon = new Image(streamIcon);
            stage.getIcons().add(imageIcon);

            RegisterController registerController = loader.getController();
            registerController.init(app);
            stage.showAndWait();
        });
    }

    public void openMain() {
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
            if(login()) {
                MainController mainController = loader.getController();
                mainController.init(app);
                ((Node)(event.getSource())).getScene().getWindow().hide();
                stage.show();
            } else {
                AlertWindow.displayAlert("Přihlášení", "Zadali jste špatné Jméno/Heslo.");
            }

        });
    }

    public boolean login() {
        boolean ret = false;
        JSONObject user = new JSONObject();
        user.put("userName", textUserName.getText());
        user.put("userPassword", textPassword.getText());

        if(app.usersArrayContainsUser(user)) {
            ret = true;
        }
        return ret;
    }
}

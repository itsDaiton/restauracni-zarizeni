package cz.vse.restaurace.Controllers;

import cz.vse.restaurace.AlertWindow;
import cz.vse.restaurace.model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class RegisterController {
    public Button btnRegistrate;
    public TextField textUserNameRegister;
    public PasswordField textPasswordRegister;

    private App app;

    public void init(App app) {
        this.app = app;
        openLogin();
    }

    public void openLogin() {
        btnRegistrate.setOnMouseClicked(event -> {
                    if(register()) {
                        AlertWindow.displayAlert("Registrace", "Registrace proběhla úspěšně.");
                        ((Node)(event.getSource())).getScene().getWindow().hide();
                    } else {
                        AlertWindow.displayAlert("Registrace", "Toto jméno je již obsazené.");
                    }
                });
    }

    public boolean register() {
            boolean ret = false;
            JSONObject user = new JSONObject();
            user.put("userName", textUserNameRegister.getText());
            user.put("userPassword", textPasswordRegister.getText());

            if(app.usersArrayContainsUser(user)) {
                return ret;
            } else {
                app.addUser(user);
                ret = true;
            }
            return ret;
        }
}

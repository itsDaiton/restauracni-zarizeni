package cz.vse.restaurace.Controllers;

import cz.vse.restaurace.AlertWindow;
import cz.vse.restaurace.model.*;
import cz.vse.restaurace.persistence.JsonPersistence;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
                    }
                });
    }

    public boolean register() {
            boolean ret = false;
            if(textUserNameRegister.getText().equals("") | textPasswordRegister.getText().equals("")) {
                AlertWindow.displayAlert("Pozor!", "Musíte zadat jméno a heslo účtu!");
                return ret;
            }
            User user = new User(textUserNameRegister.getText(),textPasswordRegister.getText());

            if(app.collectionContainsUserName(user)) {
                AlertWindow.displayAlert("Registrace", "Toto jméno je již obsazené.");
                return ret;
            } else {
                app.addUser(user);
                ret = true;
            }
            return ret;
        }
}

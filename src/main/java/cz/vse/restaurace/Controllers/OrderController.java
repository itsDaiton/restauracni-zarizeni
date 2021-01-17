package cz.vse.restaurace.Controllers;

import cz.vse.restaurace.model.App;
import cz.vse.restaurace.model.Order;
import cz.vse.restaurace.model.OrderingSystem;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class OrderController {

    public Button btn_confirmOrder;

    private App app;

    //public Label order_text;


    public void init(App app) {
        this.app = app;
        confirmOrder();
    }

    public void confirmOrder() {
        btn_confirmOrder.setOnMouseClicked(event -> {
            /*FXMLLoader loader = new FXMLLoader();
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
            javafx.scene.image.Image imageIcon = new Image(streamIcon);
            stage.getIcons().add(imageIcon);

            MainController mainController = loader.getController();
            mainController.init(app);*/
            ((Node) (event.getSource())).getScene().getWindow().hide();
        });
    }
    /*public void update() {
            OrderingSystem os = new OrderingSystem();
            Order o = app.getCurrentOrder();
            order_text.setText(String.valueOf(o.getOrderID()));
        }*/
}

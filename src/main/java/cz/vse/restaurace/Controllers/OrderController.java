package cz.vse.restaurace.Controllers;

import cz.vse.restaurace.AlertWindow;
import cz.vse.restaurace.model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class OrderController {

    public Button btn_confirmOrder;
    public Label order_text;
    public TextArea order_info;
    public ComboBox food_box;
    public ComboBox drinks_box;
    public Button btn_addFood;
    public Button btn_addDrink;
    public Button btn_addNote;
    public TextArea note_field;

    private App app;
    private OrderingSystem os;
    private Order o;

    public void init(App app) {
        this.app = app;
        this.os = new OrderingSystem();
        this.o = app.getCurrentOrder();
        showOrderID();
        confirmOrder();
        update();
        addFood();
        addDrinks();
        addNote();
    }

    public void confirmOrder() {
        btn_confirmOrder.setOnMouseClicked(event -> {
            ((Node) (event.getSource())).getScene().getWindow().hide();
        });
    }

    public void update() {
            order_info.setText(o.getOrderInfo());
            updateFoodAndDrinks();
    }

    public void showOrderID() {
        order_text.setText(order_text.getText() + o.getOrderID());
    }

    public void updateFoodAndDrinks() {
        Collection<Food> listOfFood = app.getFood();
        food_box.getItems().clear();

        Collection<Drink> listOfDrinks = app.getDrinks();
        drinks_box.getItems().clear();

        for (Food f : listOfFood) {
            food_box.getItems().add(f.getName());
        }

        for (Drink d : listOfDrinks) {
            drinks_box.getItems().add(d.getName());
        }
    }

    public void addFood() {
        btn_addFood.setOnMouseClicked(event -> {
            String s  = String.valueOf(food_box.getValue());
            Food food = app.getFoodByName(s);

            if (food != null) {
                o.addFood(food);
                update();
            }
            else {
                AlertWindow.displayAlert("Výběr jídla", "Vyberte prosím jídlo, které chce přidat.");
            }
        });
    }

    public void addDrinks() {
        btn_addDrink.setOnMouseClicked(event -> {
            String s = String.valueOf(drinks_box.getValue());
            Drink drink = app.getDrinkByName(s);

            if (drink != null) {
                o.addDrink(drink);
                update();
            }
            else {
                AlertWindow.displayAlert("Výběr nápojů","Vyberte prosím nápoj, které chcte přidat.");
            }
        });
    }

    public void addNote() {
        btn_addNote.setOnMouseClicked(event -> {
            String s = note_field.getText();

            if (s != null && !s.isEmpty()) {
                o.editNote(s);
                update();
            }
            else {
                AlertWindow.displayAlert("Přidání poznámky","Vyplňte prosím text poznámky.");
            }
        });
    }
}

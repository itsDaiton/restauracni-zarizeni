package cz.vse.restaurace.Controllers;

import com.google.gson.*;

import cz.vse.restaurace.AlertWindow;
import cz.vse.restaurace.model.App;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AddItemController {
    public Button food;
    public Button drink;

    public TextField foodName;
    public TextField foodPrice;
    public TextField drinkName;
    public TextField drinkPrice;

    public App app;


    public void init(App app) {
        this.app = app;
        update();
    }

    public void update() {
        addFood();
        addDrink();
    }


    private void addFood() {
        foodPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                foodPrice.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        food.setOnMouseClicked(event -> {
            if(!foodName.getText().trim().isEmpty() && !foodPrice.getText().trim().isEmpty()) {
            String fName = foodName.getText();

            Integer fPrice = Integer.parseInt(foodPrice.getText());

            String fileName = "appData\\food.json";

            FileReader reader = null;
            try {
                reader = new FileReader(fileName);
                Object obj = JsonParser.parseReader(reader);

                JsonArray jArray = (JsonArray) obj;

                JsonObject foodDetails = new JsonObject();
                foodDetails.addProperty("name",fName);
                foodDetails.addProperty("price",fPrice);

                jArray.add(foodDetails);

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(jArray);

                Files.write(Paths.get(fileName), json.getBytes());

                app.loadAppData();
                foodName.clear();
                foodPrice.clear();
            }  catch (IOException e) {
                e.printStackTrace();
            }} else {
                AlertWindow.displayAlert("Chybné zadání","Vyplntě název i cenu jídla.");
            }
        });
    }

    private void addDrink() {
        drinkPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                foodPrice.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        drink.setOnMouseClicked(event -> {
            if(!drinkName.getText().trim().isEmpty() && !drinkPrice.getText().trim().isEmpty()) {
                String dName = drinkName.getText();

                Integer dPrice = Integer.parseInt(drinkPrice.getText());

                String fileName = "appData\\drinks.json";

                FileReader reader = null;
                try {
                    reader = new FileReader(fileName);
                    Object obj = JsonParser.parseReader(reader);

                    JsonArray jArray = (JsonArray) obj;

                    JsonObject drinkDetails = new JsonObject();
                    drinkDetails.addProperty("name",dName);
                    drinkDetails.addProperty("price",dPrice);

                    jArray.add(drinkDetails);

                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String json = gson.toJson(jArray);

                    Files.write(Paths.get(fileName), json.getBytes());

                    app.loadAppData();
                    drinkName.clear();
                    drinkPrice.clear();
                }  catch (IOException e) {
                    e.printStackTrace();
                }} else {
                AlertWindow.displayAlert("Chybné zadání","Vyplntě název i cenu pití.");
            }
        });

    }
}

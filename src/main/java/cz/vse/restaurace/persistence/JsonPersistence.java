package cz.vse.restaurace.persistence;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cz.vse.restaurace.model.App;
import cz.vse.restaurace.model.Order;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonPersistence {

    public static void write(JSONArray users) {

        try (FileWriter file = new FileWriter("data.json")) {

            file.write(users.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeOrder(App app, Order order) {
        JSONObject orderDetails = new JSONObject();
        orderDetails.put("orderID", order.getOrderID());
        orderDetails.put("orderDate", order.getDate());
        orderDetails.put("orderTable", order.getTable().getTableNumber());
        orderDetails.put("orderFood", order.getFoodAsString());
        orderDetails.put("orderDrink", order.getDrinksAsString());
        orderDetails.put("orderNote", order.getNote());
        orderDetails.put("orderPrice", order.calculateTotalPrice());

        JSONObject currentOrder = new JSONObject();
        currentOrder.put("order", orderDetails);

        app.addOrderToUser(currentOrder);

        app.getLoggedUser().put("orderHistory", app.getOrders());

        app.updateUser(app.getLoggedUser());

        write(app.getUsers());
    }

    public static JSONArray readLoginData() {
        JSONParser jsonParser = new JSONParser();
        JSONArray users = new JSONArray();

        try (FileReader reader = new FileReader("data.json"))
        {
            Object obj = jsonParser.parse(reader);

            users = (JSONArray) obj;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void readAll(JSONObject user) {

    }
}

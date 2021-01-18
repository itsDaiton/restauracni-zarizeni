package cz.vse.restaurace.persistence;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonPersistence {

    public static void write() {
        /*Zkouska*/
        JSONObject orderDetails = new JSONObject();
        orderDetails.put("orderID", "20");
        orderDetails.put("orderDate", "18.01.2021");
        orderDetails.put("orderTable", "5");
        orderDetails.put("orderFood", "Hranolky\"");
        orderDetails.put("orderDrink", "Pepsi cola");
        orderDetails.put("orderNote", "Bez kečupu");
        orderDetails.put("orderPrice", "110");

        JSONObject order = new JSONObject();
        order.put("order", orderDetails);

        JSONObject userDetails = new JSONObject();
        userDetails.put("userName", "Jonáš");
        userDetails.put("userPassword", "heslo");
        userDetails.put("orderHistory", order);

        JSONObject user = new JSONObject();
        user.put("user", userDetails);;

        JSONArray userList = new JSONArray();
        userList.add(user);

        try (FileWriter file = new FileWriter("users.json")) {

            file.write(userList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeOrder(JSONObject user) {

    }

    public static void readLoginData() {

    }

    public static void readAll(JSONObject user) {

    }
}

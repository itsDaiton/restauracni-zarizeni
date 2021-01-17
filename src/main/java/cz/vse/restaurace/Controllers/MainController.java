package cz.vse.restaurace.Controllers;

import cz.vse.restaurace.model.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

public class MainController {
    public ComboBox tables_available;
    public ComboBox tables_occupied;
    public Button btn_createOrder;

    private App app;

    public void init(App app) {
        this.app = app;
        update();
        createOder();
    }

    public void update() {
        updateTables();
    }

    public void updateTables() {
        Collection<Table> availableTables = app.getAvailableTables();
        Collection<Table> occupiedTables = app.getOccupiedTables();
        tables_available.getItems().clear();
        tables_occupied.getItems().clear();

        for (Table t : availableTables) {
            tables_available.getItems().add(t.getTableNumber());
        }

        for (Table t : occupiedTables) {
            tables_occupied.getItems().add(t.getTableNumber());
        }
    }

    public void createOder() {
        OrderingSystem os = new OrderingSystem();
        Random rnd = new Random();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY HH:MM:SS");
        String dateString = dateFormat.format(date);
        int orderID = rnd.nextInt(1000);

        btn_createOrder.setOnMouseClicked(event -> {
            Integer output = (Integer) tables_available.getValue();
            Table currentTable = app.getTableByNumber(output);

            if (output != null) {
                Order order = new Order(orderID, dateString, currentTable);
                os.addOrder(order);
                app.occupyTable(currentTable);
                update();
            }
        });
    }
}
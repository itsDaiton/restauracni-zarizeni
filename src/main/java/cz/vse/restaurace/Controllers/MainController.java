package cz.vse.restaurace.Controllers;

import cz.vse.restaurace.AlertWindow;
import cz.vse.restaurace.model.*;
import cz.vse.restaurace.persistence.JsonPersistence;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import java.util.*;

public class MainController {
    public ComboBox tables_available;
    public ComboBox tables_occupied;
    public Button btn_createOrder;
    public Button btn_finishOrder;
    public Button btn_editOrder;
    public MenuItem itemTerminate;
    public MenuItem itemHistory;
    public MenuItem itemLogout;
    public MenuBar menuBar;

    private App app;
    private OrderingSystem os;

    public void init(App app) {
        this.app = app;
        this.os = new OrderingSystem();
        update();
        createOder();
        finishOrder();
        editOrder();
        showHistory();
        closeApp();
        logout();
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
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
            String dateString = dateFormat.format(date);

            List<Integer> orderIDs = new ArrayList<Integer>();
            for (int i = 0; i < 1000; i++) {
                orderIDs.add(i);
            }
            Collections.shuffle(orderIDs);

            btn_createOrder.setOnMouseClicked(event -> {
                Integer tableNumber = getCurrentTableNumber(tables_available);
                Table currentTable = app.getTableByNumber(tableNumber,"available");

                if (currentTable != null) {
                    int orderID = orderIDs.get(1);
                    orderIDs.remove(1);
                    Order order = new Order(orderID, dateString, currentTable);
                    os.addOrder(order);
                    app.occupyTable(currentTable);
                    update();
                }
                else {
                    AlertWindow.displayAlert("Vytváření objednávky","Vyberte prosím volný stůl.");
                }
            });
    }

    public void finishOrder() {
            btn_finishOrder.setOnMouseClicked(event -> {
                Integer tableNumber = getCurrentTableNumber(tables_occupied);
                Table currentTable = app.getTableByNumber(tableNumber,"occupied");

                if (currentTable != null) {
                    Order order = os.getOrderByOrderTable(currentTable);
                    app.addFinishedOrder(order);
                    os.removeOrder(order);
                    app.freeTable(currentTable);
                    update();
                }
                else {
                    AlertWindow.displayAlert("Vyřízení objednávky","Vyberte prosím upravovaný stůl.");
                }
            });
        }

    /**
     * Metoda sloužící pro ukončení aplikace
     */
    public void closeApp() {
        itemTerminate.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public void  editOrder() {
            btn_editOrder.setOnMouseClicked(event -> {
                Integer tableNumber = getCurrentTableNumber(tables_occupied);
                Table currentTable = app.getTableByNumber(tableNumber,"occupied");

                if (currentTable != null) {
                    Order o = os.getOrderByOrderTable(currentTable);
                    app.setCurrentOrder(o);
                    FXMLLoader loader = new FXMLLoader();
                    InputStream stream = getClass().getClassLoader().getResourceAsStream("scene_order.fxml");
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

                    OrderController orderController = loader.getController();
                    orderController.init(app);
                    stage.showAndWait();
            } else {
                    AlertWindow.displayAlert("Pozor!","Musí být vybrán stůl pro úpravu objednávky.");
                }
        });
    }

    public void  showHistory() {
        itemHistory.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            InputStream stream = getClass().getClassLoader().getResourceAsStream("scene_history.fxml");
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

            HistoryController historyController = loader.getController();
            historyController.init(app);
            stage.showAndWait();
        });
    }

    public void logout() {
        itemLogout.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            InputStream stream = getClass().getClassLoader().getResourceAsStream("scene_login.fxml");
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


            AlertWindow.displayAlert("Odhlášení", "Došlo k odhlášení uživatele.\nBudete přesměrováni na přihlašovací obrazovku.");
            Stage currentStage = (Stage) menuBar.getScene().getWindow();
            currentStage.hide();
            LoginController loginController = loader.getController();
            loginController.init(app);
            stage.show();
        });
    }

    private Integer getCurrentTableNumber(ComboBox comboBox) {
        return (Integer) comboBox.getValue();
    }
}
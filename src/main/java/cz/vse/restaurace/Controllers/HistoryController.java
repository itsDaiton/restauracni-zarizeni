package cz.vse.restaurace.Controllers;

import cz.vse.restaurace.model.App;
import cz.vse.restaurace.model.Order;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class HistoryController {
    public Button btn_closeHistory;
    public TextArea order_info;

    private App app;


    public void init(App app) {
        this.app = app;
        showHistory();
        closeHistory();
    }

    public void showHistory() {
        String history = new String();
        for(Order item : app.getFinishedOrders()) {
            history += (item.getOrderInfo() + "\n************************************************************************\n");
        }
        order_info.setText(history);
    }

    public void closeHistory() {
        btn_closeHistory.setOnMouseClicked(event -> {
            ((Node) (event.getSource())).getScene().getWindow().hide();
        });
    }
}

package cz.vse.restaurace.Controllers;

import cz.vse.restaurace.model.App;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class HistoryController {
    public Button btn_closeHistory;

    private App app;


    public void init(App app) {
        this.app = app;
        closeHistory();
    }

    public void closeHistory() {
        btn_closeHistory.setOnMouseClicked(event -> {
            ((Node) (event.getSource())).getScene().getWindow().hide();
        });
    }
}

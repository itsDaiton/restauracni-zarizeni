package cz.vse.restaurace.model;

public class Table {
    private int tableNumber;

    public Table(int tableNumber) {
       this.tableNumber = tableNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}

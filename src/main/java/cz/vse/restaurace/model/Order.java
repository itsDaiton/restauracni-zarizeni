package cz.vse.restaurace.model;

import java.util.*;

public class Order {

    private int orderID;
    private String date;
    private Table table;

    private List<Food> listOfFood;
    private List<Drink> listOfDrinks;
    private String note;

    public Order(int orderID, String date, Table table) {
        this.orderID = orderID;
        this.date = date;
        this.table = table;

        listOfFood = new ArrayList<>();
        listOfDrinks = new ArrayList<>();
        note = "";
    }

    public int getOrderID() {
        return orderID;
    }

    public String getDate() {
        return date;
    }

    public Table getTable() {
        return table;
    }

    public Collection<Drink> getListOfDrinks() {
        return new ArrayList<>(listOfDrinks);
    }

    public Collection<Food> getListOfFood() {
        return new ArrayList<>(listOfFood);
    }

    public String getNote() {
        return note;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void editNote(String note) {
        this.note = note;
    }

    public void addDrink(Drink drink) {
        if (drink != null) {
            listOfDrinks.add(new Drink(drink.getName(), drink.getPrice()));
        }
    }

    public void addFood(Food food) {
        if (food != null) {
            listOfFood.add(new Food(food.getName(),food.getPrice()));
        }
    }

    public String getOrderInfo() {
            String ret = "Číslo objednávky: " + getOrderID() + "\nDatum objednávky: " + getDate() + "\nStůl: " + getTable().getTableNumber();

            if (!listOfFood.isEmpty()) {
                ret += "\nJídlo: ";

                for(Food f : listOfFood) {
                    ret += "\n - " + f.getName();
                }
            }

            if (!listOfDrinks.isEmpty()) {
                ret += "\nNápoje: ";

                for (Drink d : listOfDrinks) {
                    ret += "\n - " + d.getName();
                }
            }

            if (getNote() != "") {
                ret += "\nPoznámka: " + getNote();
            }

            if (calculateTotalPrice() > 0) {
                ret += "\nCena: " + calculateTotalPrice();
            }

            return ret;
    }

    public Integer calculateTotalPrice() {
        int totalPrice = 0;
        if (!listOfDrinks.isEmpty()) {
            for (Drink d : listOfDrinks) {
                totalPrice += d.getPrice();
            }
        }
        if (!listOfFood.isEmpty()) {
            for (Food f : listOfFood) {
                totalPrice += f.getPrice();
            }
        }
        return totalPrice;
    }

}

package cz.vse.restaurace.model;

import java.util.*;

public class Order {

    private int orderID;
    private String date;
    private Table table;

    private Set<Food> listOfFood;
    private Set<Drink> listOfDrinks;
    private String note;

    public Order(int orderID, String date, Table table) {
        this.orderID = orderID;
        this.date = date;
        this.table = table;

        listOfFood = new HashSet<>();
        listOfDrinks = new HashSet<>();
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
        return new HashSet<Drink>(listOfDrinks);
    }

    public Collection<Food> getListOfFood() {
        return new HashSet<Food>(listOfFood);
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

    public boolean containsDrink(Drink drink) {
        return listOfDrinks.contains(drink);
    }

    public boolean containsFood(Food food) {
        return listOfFood.contains(food);
    }

    public String getOrderInfo() {
        String ret = "Číslo objednávky: " + getOrderID() + "\nDatum objednávky: " + getDate() + "\nStůl: " + getTable().getTableNumber();
        int price = 0;

        if (!listOfFood.isEmpty()) {
            ret += "\nJídlo: ";

            for(Food f : listOfFood) {
                ret += "\n - " + f.getName();
                price += f.getPrice();
            }
        }

        if (!listOfDrinks.isEmpty()) {
            ret += "\nNápoje: ";

            for (Drink d : listOfDrinks) {
                ret += "\n - " + d.getName();
                price += d.getPrice();
            }
        }

        if (getNote() != "") {
            ret += "\nPoznámka: " + getNote();
        }

        if (price > 0) {
            ret += "\nCena: " + price;
        }

        return ret;
    }

}

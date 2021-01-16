package cz.vse.restaurace.model;

import java.util.*;

public class Order {

    private int orderID;
    private Date date;
    private Table table;

    private Set<Food> listOfFood;
    private Set<Drink> listOfDrinks;
    private String note;

    public Order(int orderID, Date date, Table table) {
        this.orderID = orderID;
        this.date = date;
        this.table = table;

        listOfFood = new HashSet<Food>();
        listOfDrinks = new HashSet<Drink>();
        note = "";
    }

    public int getOrderID() {
        return orderID;
    }

    public Date getDate() {
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

    public void setDate(Date date) {
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
            listOfDrinks.add(drink);
        }
    }

    public void addFood(Food food) {
        if (food != null) {
            listOfFood.add(food);
        }
    }

    public boolean containsDrink(Drink drink) {
        return listOfDrinks.contains(drink);
    }

    public boolean containsFood(Food food) {
        return listOfFood.contains(food);
    }

    public String getOrderInfo() {
        String ret = "Číslo objednávky: " + getOrderID() + "\nAktuální datum: " + getDate() + "\nStůl: " + getTable();
        int price = 0;

        if (!listOfFood.isEmpty()) {
            ret += "\nJídlo: ";

            for(Food f : listOfFood) {
                ret += "\n -" + f.getName();
                price += f.getPrice();
            }
        }

        if (!listOfDrinks.isEmpty()) {
            ret += "\nPití: ";

            for (Drink d : listOfDrinks) {
                ret += "\n -" + d.getName();
                price += d.getPrice();
            }
        }

        if (price > 0) {
            ret += "\nCena: " + price;
        }

        return ret;
    }

}

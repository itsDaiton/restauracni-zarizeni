package cz.vse.restaurace.model;

import java.util.*;

import cz.vse.restaurace.persistence.JsonPersistence;
import cz.vse.restaurace.persistence.PersistenceException;

public class App {

    private List<Food> food;
    private List<Drink> drinks;
    private List<Table> availableTables;
    private List<Table> occupiedTables;

    private Order currentOrder;

    private List<User> users;
    private User currentUser;

    private List<Order> finishedOrders;

    private JsonPersistence jsonPersistence;

    public App() {
        this.food = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.availableTables = new ArrayList<>();
        this.occupiedTables = new ArrayList<>();
        this.jsonPersistence = new JsonPersistence();
        this.finishedOrders = new ArrayList<>();
        fillUsersList();
        loadAppData();
    }

    public void addUser(User user) {
        users.add(user);
        try {
            jsonPersistence.saveData(users);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    public boolean collectionContainsUserName(User user) {
        boolean ret = false;
        if(users != null) {
            for (User item : users) {
                if (item.getUserName().equals(user.getUserName())) {
                    ret = true;
                }
            }
        }
        return ret;
    }

    public boolean collectionContainsUser(User user) {
        boolean ret = false;
        if(users != null) {
            for(User item : users) {
                if ((item.getUserName().equals(user.getUserName())) && (item.getPassword().equals(user.getPassword()))) {
                    ret = true;
                }
            }
        }
        return ret;
    }

    public void fillUsersList() {
        try {
            users = jsonPersistence.loadData();
            if(users==null) {
                users = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    public void loadOrderHistory() {
        try {
            finishedOrders = jsonPersistence.loadUserData(currentUser);
            if(finishedOrders == null) {
                finishedOrders = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getFinishedOrders() {
        return finishedOrders;
    }

    public void addFinishedOrder(Order order)
    {
        finishedOrders.add(order);
        try {
            jsonPersistence.saveUserData(finishedOrders, getCurrentUser());
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void loadAppData() {
        try {
            food = jsonPersistence.loadFoodData();
            drinks = jsonPersistence.loadDrinksData();
            availableTables = jsonPersistence.loadTableData();

            if (food == null) {
                food = new ArrayList<>();
            }
            if (drinks == null) {
                drinks = new ArrayList<>();
            }
            if (availableTables == null) {
                availableTables = new ArrayList<>();
            }

        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    public Collection<Food> getFood() {
        return Collections.unmodifiableCollection(food);
    }

    public Collection<Drink> getDrinks() {
        return Collections.unmodifiableCollection(drinks);
    }

    public Collection<Table> getAvailableTables() {
        return Collections.unmodifiableCollection(availableTables);
    }

    public Collection<Table> getOccupiedTables() {
        return Collections.unmodifiableCollection(occupiedTables);
    }

    public void occupyTable(Table table) {
        occupiedTables.add(table);
        availableTables.remove(table);
    }

    public void freeTable(Table table) {
        occupiedTables.remove(table);
        availableTables.add(table);
    }

    public Table getTableByNumber(Integer number, String string) {
            if (number != null) {
                if (string.equals("occupied")) {
                    for (Table t : occupiedTables) {
                        if (t.getTableNumber() == number) {
                            return t;
                        }
                    }
                }
                else if (string.equals("available")) {
                    for (Table t : availableTables) {
                        if (t.getTableNumber() == number) {
                            return t;
                        }
                    }
                }
                else {
                    return null;
                }
            }
            return null;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public Food getFoodByName(String string) {
        if (string != null) {
            for (Food f : food) {
                if (f.getName().equals(string)) {
                    return f;
                }
            }
        }
        else {
            return null;
        }
        return null;
    }

    public Drink getDrinkByName(String string) {
        if (string != null) {
            for (Drink d : drinks) {
                if (d.getName().equals(string)) {
                    return d;
                }
            }
        }
        else {
            return null;
        }
        return null;
    }
}

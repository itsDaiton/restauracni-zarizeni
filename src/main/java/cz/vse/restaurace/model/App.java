package cz.vse.restaurace.model;

import java.util.*;

import cz.vse.restaurace.persistence.JsonPersistence;
import cz.vse.restaurace.persistence.PersistenceException;

public class App {

    private Set<Food> food;
    private Set<Drink> drinks;
    private List<Table> availableTables;
    private List<Table> occupiedTables;

    private Order currentOrder;

    private List<User> users;
    private User currentUser;

    private List<Order> finishedOrders;

    private JsonPersistence jsonPersistence;

    public App() {
        this.food = new HashSet<Food>();
        this.drinks = new HashSet<Drink>();
        this.availableTables = new ArrayList<>();
        this.occupiedTables = new ArrayList<>();
        this.jsonPersistence = new JsonPersistence();
        this.finishedOrders = new ArrayList<>();

        fillUsersList();
        createData();
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
    
    public void createData() {
            Table t1 = new Table(1);
            Table t2 = new Table(2);
            Table t3 = new Table(3);
            Table t4 = new Table(4);
            Table t5 = new Table(5);
            Table t6 = new Table(6);
            Table t7 = new Table(7);
            Table t8 = new Table(8);
            
            Drink d1 = new Drink("Kofola",25);
            Drink d2 = new Drink("Fanta", 35);
            Drink d3 = new Drink("Sprite", 40);
    
            Food f1 = new Food("BigMac", 85);
            Food f2 = new Food("Kuřecí Řízek", 125);
            Food f3 = new Food("Hovězí steak", 150);

            createDatabase(t1);
            createDatabase(t2);
            createDatabase(t3);
            createDatabase(t4);
            createDatabase(t5);
            createDatabase(t6);
            createDatabase(t7);
            createDatabase(t8);

            createDatabase(d1);
            createDatabase(d2);
            createDatabase(d3);

            createDatabase(f1);
            createDatabase(f2);
            createDatabase(f3);
    }

    public void createDatabase(Object o) {
        if (o instanceof Table) {
            Table table = (Table)o;
            availableTables.add(table);
        }
        else if (o instanceof Drink) {
            Drink drink = (Drink)o;
            drinks.add(drink);
        }
        else {
            Food f = (Food)o;
            food.add(f);
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

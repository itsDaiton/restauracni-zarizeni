package cz.vse.restaurace.model;

public class App {
    private User currentUser;

    public App() {
        createDatabase();
    }
    
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void createDatabase() {
            Table t1 = new Table(1);
            Table t2 = new Table(2);
            Table t3 = new Table(3);
            Table t4 = new Table(4);
            Table t5 = new Table(5);
            Table t6 = new Table(6);
            Table t7 = new Table(7);
            Table t8 = new Table(8);
    
            t1.setOccupied(false);
            t2.setOccupied(false);
            t3.setOccupied(false);
            t4.setOccupied(false);
            t5.setOccupied(false);
            t6.setOccupied(false);
            t7.setOccupied(false);
            t8.setOccupied(false);
            
            Drink d1 = new Drink("Kofola",25);
            Drink d2 = new Drink("Fanta", 35);
            Drink d3 = new Drink("Sprite", 40);
    
            Food f1 = new Food("BigMac", 85);
            Food f2 = new Food("Kuřecí Řízek", 125);
            Food f3 = new Food("Hovězí steak", 150);  
        }
}

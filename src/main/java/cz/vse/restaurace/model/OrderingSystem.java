package cz.vse.restaurace.model;

import java.util.*;

public class OrderingSystem {
    Set<Order> orders;

    public OrderingSystem() {
        orders = new HashSet<>();
    }

    public void addOrder(Order order) {
        if (order != null) {
            orders.add(order);
        }
    }

    public void removeOrder(Order order) {
        if (order != null) {
            orders.remove(order);
        }
    }

    public Collection<Order> getOrders() {
        return new HashSet<>(orders);
    }

    public Order getOrderByOrderTable(Table table) {
        if (table == null) {
            return null;
        }
        for (Order o : orders) {
            if (o.getTable().equals(table)) {
                return o;
            }
        }
        return null;
    }
}


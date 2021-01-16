package cz.vse.restaurace.model;

import java.io.Serializable;

public class User implements Serializable {

    static final long serialVersionUID=1L;

    private String userName;
    private String password;
    private Order order;
    private String history;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Order getOrder() {return order; }
    public void setOrder() {this.order = order; }

    public String getUserHistory() {
        return history;
    }

    /*Bude potřeba ???*/public void setUserHistory(String history)
    {
        this.history = history;
    }
}



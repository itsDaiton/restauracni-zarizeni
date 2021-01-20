package cz.vse.restaurace.model;

import java.io.Serializable;
import java.lang.reflect.Array;

public class User implements Serializable {

    static final long serialVersionUID=1L;

    private String userName;
    private String password;
    private Order[] orders;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

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

    public Order[] getOrder() {return orders; }
    public void setOrder() {this.orders = orders; }
}



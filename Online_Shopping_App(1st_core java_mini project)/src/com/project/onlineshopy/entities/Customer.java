package com.project.onlineshopy.entities;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int userId;
    private String username;
    private String email;
    private ShoppingCart shoppingCart;
    private List<Order> orders;
    
    
    public Customer() {
        this.shoppingCart = new ShoppingCart();
        this.orders = new ArrayList<>();
    }
    
    public Customer(int userId, String username, String email) {
        this();
        this.userId = userId;
        this.username = username;
        this.email = email;
    }
    
   
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    
    public List<Order> getOrders() {
        return orders;
    }
    
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    @Override
    public String toString() {
        return "Customer{id=" + userId + ", username='" + username + "', email='" + email + "'}";
    }
}
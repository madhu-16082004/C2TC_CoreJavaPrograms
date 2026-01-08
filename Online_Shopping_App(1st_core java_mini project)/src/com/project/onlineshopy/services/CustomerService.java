package com.project.onlineshopy.services;

import com.project.onlineshopy.entities.*;
import java.util.*;

public class CustomerService {

    private ProductService productService;
    private CartService cartService; 
    private OrderService orderService; 
    
    
    private static int nextOrderId = 1000;

    public CustomerService(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }


    public void browseProducts() {
        productService.viewAllProducts();
    }

    public void addToCart(String username, int productId, int quantity) {
        boolean success = cartService.addToCart(username, productId, quantity);
        if (success) {
            System.out.println(" Product added to cart");
        }
    }


    public void viewCart(String username) {
        cartService.viewCart(username);
    }


    public Order placeOrder(String username) {
        if (cartService.isCartEmpty(username)) {
            System.out.println(" Cart is empty. Cannot place order.");
            return null;
        }
        
        Customer customer = getCustomerByUsername(username);
        if (customer == null) {
            System.out.println(" Customer not found");
            return null;
        }
        
        ShoppingCart cart = cartService.getCart(username);
        if (cart == null) {
            System.out.println(" Cart not found");
            return null;
        }
     
        Map<Product, Integer> cartItems = cart.getItems();
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            
            if (product.getStockQuantity() < quantity) {
                System.out.println(" Insufficient stock for: " + product.getName());
                System.out.println("   Available: " + product.getStockQuantity() + 
                                 ", Requested: " + quantity);
                return null;
            }
        }
        
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            productService.reduceStock(
                entry.getKey().getProductId(),
                entry.getValue()
            );
        }
        
        List<ProductQuantityPair> orderItems = convertCartToOrderItems(cart);
        
        Order order = new Order(nextOrderId++, customer, orderItems);
        
        if (customer.getOrders() == null) {
            customer.setOrders(new ArrayList<>());
        }
        customer.getOrders().add(order);
        
        cartService.clearCart(username);
        
        if (orderService != null) {
        }
        
        System.out.println(" Order placed successfully!");
        System.out.println("   Order ID: " + order.getOrderId());
        System.out.println("   Total Amount: ₹" + order.getTotalAmount());
        
        return order;
    }

    private List<ProductQuantityPair> convertCartToOrderItems(ShoppingCart cart) {
        List<ProductQuantityPair> orderItems = new ArrayList<>();
        Map<Product, Integer> cartItems = cart.getItems();
        
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            ProductQuantityPair pair = new ProductQuantityPair(
                entry.getKey(),
                entry.getValue()
            );
            orderItems.add(pair);
        }
        
        return orderItems;
    }

    public void viewCustomerOrders(Customer customer) {
        if (customer.getOrders() == null || customer.getOrders().isEmpty()) {
            System.out.println(" No orders found.");
            return;
        }

        System.out.println(" Order History for " + customer.getUsername() + ":");
        for (Order order : customer.getOrders()) {
            order.displayOrderDetails();
        }
    }

    public void viewCustomerOrders(String username) {
        Customer customer = getCustomerByUsername(username);
        if (customer != null) {
            viewCustomerOrders(customer);
        } else {
            System.out.println(" Customer not found: " + username);
        }
    }
    public double calculateCartTotal(String username) {
        return cartService.getCartTotal(username);
    }
    public void removeFromCart(String username, int productId) {
        boolean success = cartService.removeFromCart(username, productId);
        if (success) {
            System.out.println(" Product removed from cart");
        }
    }
    public void updateCartItem(String username, int productId, int newQuantity) {
        boolean success = cartService.updateCartItem(username, productId, newQuantity);
        if (success) {
            System.out.println(" Cart updated");
        }
    }

    public int getCartItemCount(String username) {
        return cartService.getCartItemCount(username);
    }

    public void addToCartInteractive(String username) {
        if (cartService != null) {
            cartService.addToCartInteractive(username);
        } else {
            System.out.println(" Cart service not available");
        }
    }
    public Customer getCustomerByUsername(String username) {
        Customer customer = new Customer();
        customer.setUsername(username);
        if (username.equals("john_doe")) {
            customer.setUserId(1);
            customer.setUsername("john_doe");
            customer.setEmail("john@example.com");
        } else if (username.equals("jane_doe")) {
            customer.setUserId(2);
            customer.setUsername("jane_doe");
            customer.setEmail("jane@example.com");
        }
        
        return customer;
    }
    
    public Customer createCustomer(String username, String email) {
        Customer customer = new Customer();
        customer.setUserId(nextCustomerId++);
        customer.setUsername(username);
        customer.setEmail(email);
        customer.setOrders(new ArrayList<>());
        
        System.out.println(" New customer created: " + username);
        return customer;
    }
    
    private static int nextCustomerId = 1;
    
    public Customer getCustomerById(int customerId) {
        Customer customer = new Customer();
        customer.setUserId(customerId);
        customer.setUsername("Customer_" + customerId);
        customer.setEmail("customer" + customerId + "@example.com");
        return customer;
    }
}
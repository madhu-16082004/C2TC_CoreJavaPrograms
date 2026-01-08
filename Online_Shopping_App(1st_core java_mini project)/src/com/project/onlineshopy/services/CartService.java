package com.project.onlineshopy.services;

import com.project.onlineshopy.entities.*;
import java.util.*;


public class CartService {
    
    private Map<String, ShoppingCart> customerCarts;
    private ProductService productService;
    private Scanner scanner;
    
    public CartService(ProductService productService, Scanner scanner) {
        this.customerCarts = new HashMap<>();
        this.productService = productService;
        this.scanner = scanner;
    }

    private ShoppingCart getOrCreateCart(String username) {
        ShoppingCart cart = customerCarts.get(username);
        if (cart == null) {
            cart = new ShoppingCart();
            customerCarts.put(username, cart);
        }
        return cart;
    }
    
 
    public boolean addToCart(String username, int productId, int quantity) {
        if (quantity <= 0) {
            System.out.println(" Quantity must be positive");
            return false;
        }
        
        Product product = productService.getProductById(productId);
        if (product == null) {
            System.out.println(" Product not found");
            return false;
        }
        
        if (product.getStockQuantity() < quantity) {
            System.out.println(" Insufficient stock. Available: " + product.getStockQuantity());
            return false;
        }
        
        ShoppingCart cart = getOrCreateCart(username);
        cart.addItem(product, quantity);
        System.out.println(" Added " + product.getName() + " x" + quantity + " to cart");
        return true;
    }
    
  
    public boolean removeFromCart(String username, int productId) {
        ShoppingCart cart = customerCarts.get(username);
        if (cart == null || cart.getItems().isEmpty()) {
            System.out.println(" Cart is empty");
            return false;
        }
        
        Product product = productService.getProductById(productId);
        if (product == null) {
            System.out.println(" Product not found");
            return false;
        }
        
        cart.removeItem(product);
        System.out.println(" Removed " + product.getName() + " from cart");
        return true;
    }
    
  
    public boolean updateCartItem(String username, int productId, int newQuantity) {
        if (newQuantity <= 0) {
           
            return removeFromCart(username, productId);
        }
        
        Product product = productService.getProductById(productId);
        if (product == null) {
            System.out.println(" Product not found");
            return false;
        }
        
        if (product.getStockQuantity() < newQuantity) {
            System.out.println(" Insufficient stock. Available: " + product.getStockQuantity());
            return false;
        }
        
        ShoppingCart cart = getOrCreateCart(username);
        cart.updateQuantity(product, newQuantity);
        System.out.println(" Updated " + product.getName() + " quantity to " + newQuantity);
        return true;
    }
    
  
    public void viewCart(String username) {
        ShoppingCart cart = customerCarts.get(username);
        if (cart == null || cart.getItems().isEmpty()) {
            System.out.println(" Your cart is empty");
        } else {
            System.out.println("\n Your Shopping Cart:");
            cart.displayCart();
        }
    }
    
  
    public void clearCart(String username) {
        ShoppingCart cart = customerCarts.get(username);
        if (cart != null) {
            cart.clearCart();
            System.out.println(" Cart cleared");
        } else {
            System.out.println(" No cart found");
        }
    }
    
 
    public double getCartTotal(String username) {
        ShoppingCart cart = customerCarts.get(username);
        return (cart != null) ? cart.getCartTotal() : 0.0;
    }
    
 
    public boolean isCartEmpty(String username) {
        ShoppingCart cart = customerCarts.get(username);
        return (cart == null || cart.getItems().isEmpty());
    }
    
 
    public int getCartItemCount(String username) {
        ShoppingCart cart = customerCarts.get(username);
        return (cart != null) ? cart.getItems().size() : 0;
    }
    
  
    public void addToCartInteractive(String username) {
        if (scanner == null) {
            System.out.println(" Scanner not available");
            return;
        }
        
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); 
        
        addToCart(username, productId, quantity);
    }
    
 
    public void updateCartItemInteractive(String username) {
        if (scanner == null) {
            System.out.println(" Scanner not available");
            return;
        }
        
        System.out.print("Enter Product ID to update: ");
        int productId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter new Quantity: ");
        int newQuantity = scanner.nextInt();
        scanner.nextLine();
        
        updateCartItem(username, productId, newQuantity);
    }
    
  
    public ShoppingCart checkout(String username) {
        ShoppingCart cart = customerCarts.get(username);
        
        if (cart == null || cart.getItems().isEmpty()) {
            System.out.println(" Cannot checkout: Cart is empty");
            return null;
        }
        
        System.out.println("\n Checkout Summary:");
        cart.displayCart();
        System.out.println("\nProceeding to payment...");
        
        return cart;
    }
    
    public ShoppingCart getCart(String username) {
        return customerCarts.get(username);
    }
}


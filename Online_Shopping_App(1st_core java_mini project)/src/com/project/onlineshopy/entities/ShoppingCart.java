package com.project.onlineshopy.entities;

	import java.util.HashMap;
	import java.util.Map;

	public class ShoppingCart {

	    
	    private Map<Product, Integer> items = new HashMap<>();

	    
	    public void addItem(Product product, int quantity) {
	        if (items.containsKey(product)) {
	            items.put(product, items.get(product) + quantity);
	        } else {
	            items.put(product, quantity);
	        }
	    }

	   
	    public void removeItem(Product product) {
	        items.remove(product);
	    }

	    
	    public void updateQuantity(Product product, int quantity) {
	        if (quantity <= 0) {
	            items.remove(product);
	        } else {
	            items.put(product, quantity);
	        }
	    }

	    
	    public Map<Product, Integer> getItems() {
	        return items;
	    }

	    
	    public void clearCart() {
	        items.clear();
	    }

	    
	    public double getCartTotal() {
	        double total = 0;
	        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
	            total += entry.getKey().getPrice() * entry.getValue();
	        }
	        return total;
	    }

	    
	    public void displayCart() {
	        if (items.isEmpty()) {
	            System.out.println("Shopping cart is empty.");
	            return;
	        }

	        System.out.println("Shopping Cart:");
	        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
	            Product p = entry.getKey();
	            int qty = entry.getValue();
	            System.out.println(p.getName() + " | Qty: " + qty + 
	                               " | Price: ₹" + (p.getPrice() * qty));
	        }
	        System.out.println("Total Amount: ₹" + getCartTotal());
	    }
	}

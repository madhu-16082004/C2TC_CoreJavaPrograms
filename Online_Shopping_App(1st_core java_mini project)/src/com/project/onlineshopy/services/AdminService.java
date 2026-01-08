package com.project.onlineshopy.services;

	import java.util.List;

	import com.project.onlineshopy.entities.Product;
	import com.project.onlineshopy.entities.Order;

	public class AdminService {

	   
	    public void addProduct(List<Product> products, Product product) {
	        products.add(product);
	        System.out.println("Product added: " + product.getName());
	    }

	    
	    public boolean updateProduct(List<Product> products, int productId,
	                                 double newPrice, int newStockQuantity) {
	        for (Product p : products) {
	            if (p.getProductId() == productId) {
	                p.setPrice(newPrice);
	                p.setStockQuantity(newStockQuantity);
	                System.out.println("Product updated: " + p.getName());
	                return true;
	            }
	        }
	        System.out.println("Product not found!");
	        return false;
	    }

	    
	    public boolean removeProduct(List<Product> products, int productId) {
	        boolean removed = products.removeIf(p -> p.getProductId() == productId);
	        if (removed) System.out.println("Product removed: ID " + productId);
	        else System.out.println("Product not found!");
	        return removed;
	    }

	   
	    public void viewAllOrders(List<Order> orders) {
	        if (orders.isEmpty()) {
	            System.out.println("No orders placed yet.");
	            return;
	        }
	        for (Order o : orders) {
	            o.displayOrderDetails();
	        }
	    }

	    
	    public boolean updateOrderStatus(List<Order> orders, int orderId, String status) {
	        for (Order o : orders) {
	            if (o.getOrderId() == orderId) {
	                o.setStatus(status);
	                System.out.println("Order status updated: " + status);
	                return true;
	            }
	        }
	        System.out.println("Order not found!");
	        return false;
	    }
	}


package com.project.onlineshopy.services;


import java.util.*;
import com.project.onlineshopy.entities.Product;

public class ProductService {
	private Map<Integer, Product> productCatalog = new HashMap<>();
	public void addProduct(Product product) {
		if (productCatalog.containsKey(product.getProductId())) {
			System.out.println("Product already exists.");
	        return;
	       }
	        productCatalog.put(product.getProductId(), product);
	        System.out.println("Product added successfully.");
	    }

	public void updateProduct(int productId, double newPrice, int newStock) {
	       Product product = productCatalog.get(productId);
	       if (product == null) {
	         System.out.println("Product not found.");
	          return;
	        }
	        product.setPrice(newPrice);
	        product.setStockQuantity(newStock);
	        System.out.println("Product updated successfully.");
	    }

	    
	public void removeProduct(int productId) {
	      if (productCatalog.remove(productId) != null) {
	           System.out.println("Product removed successfully.");
	        } else {
	            System.out.println("Product not found.");
	        }
	    }

	    public void viewAllProducts() {
	        if (productCatalog.isEmpty()) {
	            System.out.println("No products available.");
	            return;
	        }

	        System.out.println("Product List:");
	        for (Product p : productCatalog.values()) {
	            System.out.println(p);
	        }
	    }

	    public Product getProductById(int productId) {
	        return productCatalog.get(productId);
	    }

	    public boolean reduceStock(int productId, int quantity) {
	        Product product = productCatalog.get(productId);
	        if (product == null || product.getStockQuantity() < quantity) {
	            return false;
	        }
	        product.setStockQuantity(product.getStockQuantity() - quantity);
	        return true;
	    }

	    public void searchProductByName(String keyword) {
	        boolean found = false;
	        for (Product p : productCatalog.values()) {
	            if (p.getName().toLowerCase().contains(keyword.toLowerCase())) {
	                System.out.println(p);
	                found = true;
	            }
	        }
	        if (!found) {
	            System.out.println("No matching products found.");
	        }
	    }
	}

package com.project.onlineshopy.entities;

public class ProductQuantityPair {
	    private Product product;
	    private int quantity;

	    public ProductQuantityPair(Product product, int quantity) {
	        this.product = product;
	        this.quantity = quantity;
	    }

	    public Product getProduct() {
	        return product;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }

	    public double getTotalPrice() {
	        return product.getPrice() * quantity;
	    }

	    @Override
	    public String toString() {
	        return product.getName() +
	                "Qty: " + quantity +
	                "Total: ₹" + getTotalPrice();
	    }
	}

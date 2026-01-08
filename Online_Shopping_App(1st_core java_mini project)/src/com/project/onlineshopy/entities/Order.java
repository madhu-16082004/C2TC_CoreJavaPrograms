package com.project.onlineshopy.entities;

	import java.util.List;

	public class Order {

	    private int orderId;
	    private Customer customer; 
	    private List<ProductQuantityPair> items; 
	    private double totalAmount;
	    private String status; 

	    public Order(int orderId, Customer customer,
	                 List<ProductQuantityPair> items) {
	        this.orderId = orderId;
	        this.customer = customer;
	        this.items = items;
	        this.totalAmount = calculateTotal(); 
	        this.status = "PLACED"; 
	    }

	    private double calculateTotal() {
	        double total = 0;
	        for (ProductQuantityPair pq : items) {
	            total += pq.getProduct().getPrice() * pq.getQuantity();
	        }
	        return total;
	    }

	  
	    public int getOrderId() {
			return orderId;
		}

		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public List<ProductQuantityPair> getItems() {
			return items;
		}

		public void setItems(List<ProductQuantityPair> items) {
			this.items = items;
		}

		public double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}


	    public void displayOrderDetails() {
	        System.out.println("Order ID: " + orderId);
	        System.out.println("Customer: " + customer.getUsername());
	        System.out.println("Status: " + status);
	        System.out.println("Items:");
	        for (ProductQuantityPair pq : items) {
	            System.out.println("- " + pq.getProduct().getName() +
	                               " x " + pq.getQuantity() +
	                               " = ₹" + pq.getProduct().getPrice() * pq.getQuantity());
	        }
	        System.out.println("Total Amount: ₹" + totalAmount);
	    }
	}


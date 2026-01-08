package com.project.onlineshopy.services;

import com.project.onlineshopy.entities.*;
import java.util.*;

public class OrderService {
    
    private Map<Integer, Order> orderDatabase;  
    private List<Order> orderList;              
    private static int nextOrderId = 1000;
    private CustomerService customerService;
    private Scanner scanner;
 
    public OrderService(Scanner scanner, CustomerService customerService) {
        this.orderDatabase = new HashMap<>();
        this.orderList = new ArrayList<>();
        this.scanner = scanner;
        this.customerService = customerService;
        System.out.println(" OrderService initialized");
    }

    public OrderService(CustomerService customerService) {
        this(null, customerService);
    }
 
    public Order createOrderFromCart(Customer customer, ShoppingCart cart) {
        if (customer == null) {
            System.out.println(" Cannot create order: Customer not found");
            return null;
        }
        
        if (cart == null || cart.getItems().isEmpty()) {
            System.out.println("Cannot create order: Cart is empty");
            return null;
        }
        
        List<ProductQuantityPair> orderItems = convertCartToOrderItems(cart);
        
        int orderId = generateOrderId();
        
        Order newOrder = new Order(orderId, customer, orderItems);
        
        orderDatabase.put(orderId, newOrder);
        orderList.add(newOrder);
        
        cart.clearCart();
        
        System.out.println("Order created successfully!");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getUsername());
        System.out.println("Total Amount: ₹" + newOrder.getTotalAmount());
        
        return newOrder;
    }

    private List<ProductQuantityPair> convertCartToOrderItems(ShoppingCart cart) {
        List<ProductQuantityPair> items = new ArrayList<>();
        Map<Product, Integer> cartItems = cart.getItems();
        
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            ProductQuantityPair pair = new ProductQuantityPair(
                entry.getKey(),  
                entry.getValue() 
            );
            items.add(pair);
        }
        
        return items;
    }
    
 
    private synchronized int generateOrderId() {
        return nextOrderId++;
    }
    
    public Order getOrderById(int orderId) {
        Order order = orderDatabase.get(orderId);
        if (order == null) {
            System.out.println("Order not found with ID: " + orderId);
        }
        return order;
    }
    
    public List<Order> getOrdersByCustomer(String username) {
        List<Order> customerOrders = new ArrayList<>();
        
        for (Order order :orderList) {
            if (order.getCustomer().getUsername().equalsIgnoreCase(username)) {
                customerOrders.add(order);
            }
        }
        
        if (customerOrders.isEmpty()) {
            System.out.println(" No orders found for customer: " + username);
        } else {
            System.out.println(" Found " + customerOrders.size() + 
                             " order(s) for " + username);
        }
        
        return customerOrders;
    }
    
    public List<Order> getAllOrders() {
        if (orderList.isEmpty()) {
            System.out.println(" No orders in the system");
        }
        return new ArrayList<>(orderList);
    }
    
    public List<Order> getOrdersByStatus(String status) {
        List<Order> filteredOrders = new ArrayList<>();
        
        for (Order order : orderList) {
            if (order.getStatus().equalsIgnoreCase(status)) {
                filteredOrders.add(order);
            }
        }
        
        System.out.println(" Found " + filteredOrders.size() + " order(s) with status: " + status);
        return filteredOrders;
    }
    
    public boolean updateOrderStatus(int orderId, String newStatus) {
        Order order = orderDatabase.get(orderId);
        
        if (order == null) {
            System.out.println(" Order not found: " + orderId);
            return false;
        }
        
        String oldStatus = order.getStatus();
        String[] validStatuses = {"PLACED", "PROCESSING", "SHIPPED", "DELIVERED", "CANCELLED"};
        
        boolean isValid = false;
        for (String status : validStatuses) {
            if (status.equalsIgnoreCase(newStatus)) {
                isValid = true;
                break;
            }
        }
        
        if (!isValid) {
            System.out.println(" Invalid status. Valid statuses: " + Arrays.toString(validStatuses));
            return false;
        }
        
        order.setStatus(newStatus.toUpperCase());
        System.out.println(" Order " + orderId + " status updated: " + oldStatus + " → " + newStatus);
        return true;
    }
    

    public boolean cancelOrder(int orderId) {
        Order order = orderDatabase.get(orderId);
        
        if (order == null) {
            System.out.println(" Order not found: " + orderId);
            return false;
        }
        
        String currentStatus = order.getStatus();
        
        if (currentStatus.equals("DELIVERED") || 
            currentStatus.equals("SHIPPED")) {
            System.out.println(" Cannot cancel order. Current status: " + currentStatus);
            return false;
        }
        
        if (currentStatus.equals("CANCELLED")) {
            System.out.println(" Order is already cancelled");
            return true;
        }
        
        order.setStatus("CANCELLED");
        System.out.println(" Order " + orderId + " has been cancelled");
        
        System.out.println(" Items returned to inventory");
        
        return true;
    }
    
    public void displayOrder(int orderId) {
        Order order = orderDatabase.get(orderId);
        
        if (order == null) {
            System.out.println(" Order not found: " + orderId);
            return;
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println(" ORDER DETAILS");
        System.out.println("=".repeat(50));
        order.displayOrderDetails();
    }
    
    public void displayAllOrders() {
        if (orderList.isEmpty()) {
            System.out.println(" No orders to display");
            return;
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println(" ALL ORDERS (" + orderList.size() + " orders)");
        System.out.println("=".repeat(60));
        
        for (Order order : orderList) {
            System.out.printf("Order #%d | Customer: %-15s | Status: %-12s | Total: ₹%.2f\n",
                order.getOrderId(),
                order.getCustomer().getUsername(),
                order.getStatus(),
                order.getTotalAmount());
        }
        System.out.println("=".repeat(60));
    }
    
    public void displayCustomerOrders(Customer customer) {
        displayCustomerOrders(customer.getUsername());
    }
    
    public void displayCustomerOrders(String username) {
        List<Order> customerOrders = getOrdersByCustomer(username);
        
        if (customerOrders.isEmpty()) {
            return;
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println(" ORDER HISTORY FOR: " + username);
        System.out.println("=".repeat(60));
        
        for (Order order : customerOrders) {
            System.out.printf("Order #%d | Date: %s | Status: %-12s | Total: ₹%.2f\n",
                order.getOrderId(),
                "Today",
                order.getStatus(),
                order.getTotalAmount());
        }
        
        double totalSpent = calculateTotalSpent(customerOrders);
        System.out.println("-".repeat(60));
        System.out.printf(" TOTAL SPENT: ₹%.2f\n", totalSpent);
        System.out.println("=".repeat(60));
    }
    
 
    public double calculateTotalRevenue() {
        double totalRevenue = 0.0;
        
        for (Order order : orderList) {
            if (!order.getStatus().equals("CANCELLED")) {
                totalRevenue += order.getTotalAmount();
            }
        }
        
        System.out.printf(" Total Revenue: ₹%.2f\n", totalRevenue);
        return totalRevenue;
    }
    
    private double calculateTotalSpent(List<Order> orders) {
        double total = 0.0;
        for (Order order : orders) {
            if (!order.getStatus().equals("CANCELLED")) {
                total += order.getTotalAmount();
            }
        }
        return total;
    }
    
    public Map<String, Integer> getOrderCountByStatus() {
        Map<String, Integer> statusCount = new HashMap<>();
        
        String[] allStatuses = {"PLACED", "PROCESSING", "SHIPPED", "DELIVERED", "CANCELLED"};
        for (String status : allStatuses) {
            statusCount.put(status, 0);
        }
        
        for (Order order : orderList) {
            String status = order.getStatus();
            statusCount.put(status, statusCount.get(status) + 1);
        }
        
        return statusCount;
    }
    
    public void displayOrderStatistics() {
        Map<String, Integer> statusCount = getOrderCountByStatus();
        
        System.out.println("\n" + "=".repeat(40));
        System.out.println(" ORDER STATISTICS");
        System.out.println("=".repeat(40));
        
        int totalOrders = orderList.size();
        System.out.println("Total Orders: " + totalOrders);
        
        for (Map.Entry<String, Integer> entry : statusCount.entrySet()) {
            double percentage = totalOrders > 0 ? 
                (entry.getValue() * 100.0 / totalOrders) : 0;
            System.out.printf("%-12s: %3d (%.1f%%)\n", 
                entry.getKey(), entry.getValue(), percentage);
        }
        
        System.out.println("Total Revenue: ₹" + calculateTotalRevenue());
        System.out.println("=".repeat(40));
    }
    
    public void placeOrderInteractive() {
        if (scanner == null) {
            System.out.println(" Scanner not available for interactive mode");
            return;
        }
        
        if (customerService == null) {
            System.out.println(" Customer service not available");
            return;
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println(" PLACE NEW ORDER");
        System.out.println("=".repeat(50));
        
        System.out.print("Enter customer username: ");
        String username = scanner.nextLine();
        
        Customer customer = customerService.getCustomerByUsername(username);
        if (customer == null) {
            System.out.println(" Customer not found. Please register first.");
            return;
        }
        
        ShoppingCart cart = customer.getShoppingCart(); 
        if (cart == null || cart.getItems().isEmpty()) {
            System.out.println(" Your shopping cart is empty");
            cart.displayCart(); 
            return;
        }
       
        System.out.println("\n Review your order:");
        cart.displayCart();
       
        System.out.print("\nConfirm order? (yes/no): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("yes")) {
            Order newOrder = createOrderFromCart(customer, cart);
            
            if (newOrder != null) {
                System.out.println("\n ORDER PLACED SUCCESSFULLY!");
                System.out.println("Thank you for your purchase, " + customer.getUsername() + "!");
            }
        } else {
            System.out.println(" Order cancelled by user");
        }
    }
    
    public void viewOrderInteractive() {
        if (scanner == null) {
            System.out.println(" Scanner not available");
            return;
        }
        
        System.out.print("\nEnter Order ID to view: ");
        try {
            int orderId = Integer.parseInt(scanner.nextLine());
            displayOrder(orderId);
        } catch (NumberFormatException e) {
            System.out.println(" Invalid Order ID. Please enter a number.");
        }
    }
    
    public void cancelOrderInteractive() {
        if (scanner == null) {
            System.out.println(" Scanner not available");
            return;
        }
        
        System.out.print("\nEnter Order ID to cancel: ");
        try {
            int orderId = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Are you sure you want to cancel order #" + 
                           orderId + "? (yes/no): ");
            String confirm = scanner.nextLine();
            
            if (confirm.equalsIgnoreCase("yes")) {
                cancelOrder(orderId);
            } else {
                System.out.println("Cancellation aborted");
            }
        } catch (NumberFormatException e) {
            System.out.println(" Invalid Order ID");
        }
    }
    
    public List<Order> searchOrdersByProduct(String productName) {
        List<Order> result = new ArrayList<>();
        
        for (Order order : orderList) {
            for (ProductQuantityPair item : order.getItems()) {
                if (item.getProduct().getName().toLowerCase()
                    .contains(productName.toLowerCase())) {
                    result.add(order);
                    break;
                }
            }
        }
        
        System.out.println(" Found " + result.size() + 
                         " order(s) containing: " + productName);
        return result;
    }
    
    public List<Order> getOrdersAboveAmount(double minAmount) {
        List<Order> result = new ArrayList<>();
        
        for (Order order : orderList) {
            if (order.getTotalAmount() >= minAmount) {
                result.add(order);
            }
        }
        
        System.out.println(" Found " + result.size() + 
                         " order(s) above ₹" + minAmount);
        return result;
    }
    
    public List<Order> getRecentOrders(int count) {
        int startIndex = Math.max(0, orderList.size() - count);
        List<Order> recent = new ArrayList<>(
            orderList.subList(startIndex, orderList.size())
        );
        Collections.reverse(recent); 
        return recent;
    }
    

    public void cleanupCancelledOrders() {
        Iterator<Order> iterator = orderList.iterator();
        int removedCount = 0;
        
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getStatus().equals("CANCELLED")) {
                iterator.remove();
                orderDatabase.remove(order.getOrderId());
                removedCount++;
            }
        }
        
        if (removedCount > 0) {
            System.out.println(" Cleaned up " + removedCount + " cancelled orders");
        }
    }
}



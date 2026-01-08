package com.project.onlineshopy.application;

import com.project.onlineshopy.entities.*;
import com.project.onlineshopy.services.*;
import java.util.*;

public class OnlineShopy {
    
   
    private static Scanner scanner = new Scanner(System.in);
    
    
    private static ProductService productService;
    private static CartService cartService;
    private static CustomerService customerService;
    private static OrderService orderService;
    private static AdminService adminService;
    
    
    private static User currentUser;
    private static Customer currentCustomer;
    private static Admin currentAdmin;
    
    
    private static List<Admin> adminList = new ArrayList<>();
    private static List<Customer> customerList = new ArrayList<>();
    private static List<Product> productList = new ArrayList<>();
    private static List<Order> orderList = new ArrayList<>();
    
    
    private static int nextAdminId = 101;
    private static int nextCustomerId = 1001;
    private static int nextProductId = 10001;
    private static int nextOrderId = 100001;
    
    public static void main(String[] args) {
    System.out.println("    WELCOME TO ONLINE SHOPPING SYSTEM\n");
    
    initializeServices();
    initializeSampleData();
        
        boolean exit = false;
        
        while (!exit) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    customerLogin();
                    break;
                case 3:
                    customerRegistration();
                    break;
                case 4:
                    System.out.println("\nThank you for using Online Shopping System!");
                    System.out.println("Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        
        scanner.close();
    }
    
    
    private static void initializeServices() {
        
        productService = new ProductService();
        
        
        cartService = new CartService(productService, scanner);
        
        
        customerService = new CustomerService(productService, cartService);
        
        
        orderService = new OrderService(scanner, customerService);
        
        
        customerService.setOrderService(orderService);
        
        
        adminService = new AdminService();
        
        System.out.println(" All services initialized successfully!");
    }
    
    
    private static void initializeSampleData() {
       
        Product p1 = new Product(nextProductId++, "Apple MacBook Pro", 129999.0, 10);
        Product p2 = new Product(nextProductId++, "iPhone 15 Pro", 89999.0, 25);
        Product p3 = new Product(nextProductId++, "Sony Headphones", 12999.0, 50);
        Product p4 = new Product(nextProductId++, "Logitech Mouse", 2999.0, 100);
        Product p5 = new Product(nextProductId++, "Samsung Monitor", 24999.0, 30);
        
        
        productService.addProduct(p1);
        productService.addProduct(p2);
        productService.addProduct(p3);
        productService.addProduct(p4);
        productService.addProduct(p5);
        
        
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
        productList.add(p4);
        productList.add(p5);
        
        
        Admin defaultAdmin = new Admin(nextAdminId++, "hindu", "hindu-2026", 
                                      "hindu@tnsif.com", "9867451230", 
                                      "System Administrator", "SUPER ADMIN", "Management");
        adminList.add(defaultAdmin);
        
        
        Customer sampleCustomer = new Customer(nextCustomerId++, "jackie", "jackie@gmail.com");
        customerList.add(sampleCustomer);
      
    }
    
   
    private static void displayMainMenu() {
        System.out.println("\n MAIN MENU \n");
        System.out.println("1. Admin Login");
        System.out.println("2. Customer Login");
        System.out.println("3. Customer Registration");
        System.out.println("4. Exit");
    }
    
    
    private static void adminLogin() {
        System.out.println("\n ADMIN LOGIN\n ");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
       
        for (Admin admin : adminList) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            	currentAdmin = admin;
            	currentUser = admin;
                System.out.println(" Login successful! Welcome " + admin.getFullName() + "!");
                adminMenu();
                return;
            }
        }
        
        System.out.println(" Invalid username or password!");
    }
    
    
    private static void adminMenu() {
        boolean backToMain = false;
        
        while (!backToMain) {
            System.out.println("\n  ADMIN MENU \n");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Remove Product");
            System.out.println("4. View All Products");
            System.out.println("5. View All Orders");
            System.out.println("6. Update Order Status");
            System.out.println("7. Create New Admin");
            System.out.println("8. View All Admins");
            System.out.println("9. View Order Statistics");
            System.out.println("10. Logout");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    viewProducts();
                    break;
                case 5:
                    viewOrders();
                    break;
                case 6:
                    updateOrderStatus();
                    break;
                case 7:
                    createAdmin();
                    break;
                case 8:
                    viewAdmins();
                    break;
                case 9:
                    viewOrderStatistics();
                    break;
                case 10:
                    System.out.println("you were log out successfully");
                    currentAdmin = null;
                    currentUser = null;
                    backToMain = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
  
    private static void customerLogin() {
        System.out.println("\n CUSTOMER LOGIN ");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        
        boolean customerFound = false;
        for (Customer customer : customerList) {
            if (customer.getUsername().equals(username)) {
                currentCustomer = customer;
                System.out.println(" Login successful! Welcome " + username + "!");
                customerFound = true;
                customerMenu();
                break;
            }
        }

        if (!customerFound) {
            System.out.println(" Customer not found! Please register first.");
        }
    }

    
    private static void customerRegistration() {
        System.out.println("\n CUSTOMER REGISTRATION ");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

       
        boolean usernameExists = false;
        for (Customer customer : customerList) {
            if (customer.getUsername().equals(username)) {
                usernameExists = true;
                break;
            }
        }

        if (usernameExists) {
            System.out.println("Username" + username + "already exists!");
            System.out.println("Please choose a different username.");
            return;
        }

        System.out.print("Enter email:");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        
        Customer newCustomer = new Customer(nextCustomerId++, username, email);
        
        customerList.add(newCustomer);
        
       
        customerService.createCustomer(username, email);
        
        System.out.println("\n Registration successful!\n");
        System.out.println("Your customer ID: " + newCustomer.getUserId());
        System.out.println("Your username: " + username);
        System.out.println("You can now login with your username.");
    }
    
   
    private static void customerMenu() {
        if (currentCustomer == null) {
            System.out.println(" No customer logged in!");
            return;
        }
        
        String username = currentCustomer.getUsername();
        boolean backToMain = false;
        
        while (!backToMain) {
           
            displayCartSummary(username);
            
            System.out.println("\n CUSTOMER MENU\n ");
            System.out.println("1. Browse Products");
            System.out.println("2. Search Product");
            System.out.println("3. View Cart");
            System.out.println("4. Add to Cart");
            System.out.println("5. Remove from Cart");
            System.out.println("6. Update Cart Item");
            System.out.println("7. Place Order");
            System.out.println("8. View My Orders");
            System.out.println("9. View Order Details");
            System.out.println("10. Clear Cart");
            System.out.println("11. Logout");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    browseProducts();
                    break;
                case 2:
                    searchProduct();
                    break;
                case 3:
                    viewCart(username);
                    break;
                case 4:
                    addToCart(username);
                    break;
                case 5:
                    removeFromCart(username);
                    break;
                case 6:
                    updateCartItem(username);
                    break;
                case 7:
                    placeOrder(username);
                    break;
                case 8:
                    viewCustomerOrders(username);
                    break;
                case 9:
                    viewOrderDetails();
                    break;
                case 10:
                    clearCart(username);
                    break;
                case 11:
                    System.out.println("Logging out...");
                    currentCustomer = null;
                    currentUser = null;
                    backToMain = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    
    
    private static void addProduct() {
        System.out.println("\n ADD NEW PRODUCT\n");
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Price:");
        double price = getDoubleInput("");
        
        System.out.print("Enter Stock Quantity:");
        int stock = getIntInput("");
        
        Product newProduct = new Product(nextProductId++, name, price, stock);
        productService.addProduct(newProduct);
        productList.add(newProduct);
    }
    
    private static void updateProduct() {
        System.out.println("\n UPDATE PRODUCT\n");
        System.out.print("Enter Product ID:");
        int id = getIntInput("");
        
        System.out.print("Enter New Price:");
        double newPrice = getDoubleInput("");
        
        System.out.print("Enter New Stock:");
        int newStock = getIntInput("");
        
        productService.updateProduct(id, newPrice, newStock);
    }
    
    private static void removeProduct() {
        System.out.println("\n REMOVE PRODUCT \n");
        System.out.print("Enter Product ID:");
        int id = getIntInput("");
        
        productService.removeProduct(id);
        
        
        productList.removeIf(p -> p.getProductId() == id);
    }
    
    private static void viewProducts() {
        System.out.println("\n ALL PRODUCTS \n");
        productService.viewAllProducts();
    }
    
    private static void viewOrders() {
        System.out.println("\n ALL ORDERS \n");
        List<Order> allOrders = orderService.getAllOrders();
        if (allOrders.isEmpty()) {
            System.out.println("No orders placed yet.");
        } else {
            orderService.displayAllOrders();
        }
    }
    
    private static void updateOrderStatus() {
        System.out.println("\n UPDATE ORDER STATUS \n");
        System.out.print("Enter Order ID:");
        int orderId = getIntInput("");
        
        System.out.println("Available statuses: PLACED, PROCESSING, SHIPPED, DELIVERED, CANCELLED");
        System.out.print("Enter New Status:");
        String newStatus = scanner.nextLine().toUpperCase();
        
        orderService.updateOrderStatus(orderId, newStatus);
    }
    
    private static void createAdmin() {
        System.out.println("\n CREATE NEW ADMIN \n ");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter Password:");
        String password = scanner.nextLine();
        
        System.out.print("Enter Email:");
        String email = scanner.nextLine();
        
        System.out.print("Enter Phone Number:");
        String phone = scanner.nextLine();
        
        System.out.print("Enter Full Name:");
        String fullName = scanner.nextLine();
        
        System.out.print("Enter Admin Level (SUPER_ADMIN/PRODUCT_MANAGER/ORDER_MANAGER):");
        String adminLevel = scanner.nextLine().toUpperCase();
        
        System.out.print("Enter Department:");
        String department = scanner.nextLine();
        
        Admin newAdmin = new Admin(nextAdminId++, username, password, email, 
                                  phone, fullName, adminLevel, department);
        adminList.add(newAdmin);
        
        System.out.println(" Admin created successfully!");
        System.out.println("Admin ID:" + newAdmin.getUserId());
    }
    
    private static void viewAdmins() {
        System.out.println("\n ALL ADMINS \n");
        if (adminList.isEmpty()) {
            System.out.println("No admins found.");
            return;
        }
        
        for (Admin admin : adminList) {
            System.out.println("ID:" + admin.getUserId() + 
                             ", Username:" + admin.getUsername() + 
                             ", Name:" + admin.getFullName() + 
                             ", Level:" + admin.getAdminLevel() + 
                             ", Department:" + admin.getDepartment());
        }
    }
    
    private static void viewOrderStatistics() {
        System.out.println("\n ORDER STATISTICS \n");
        orderService.displayOrderStatistics();
    }
    

    
    private static void browseProducts() {
        System.out.println("\n BROWSE PRODUCTS \n");
        customerService.browseProducts();
    }
    
    private static void searchProduct() {
        System.out.println("\n SEARCH PRODUCT \n");
        System.out.print("Enter product name or keyword:");
        String keyword = scanner.nextLine();
        
        productService.searchProductByName(keyword);
    }
    
    private static void viewCart(String username) {
        System.out.println("\n YOUR SHOPPING CART\n");
        customerService.viewCart(username);
    }
    
    private static void addToCart(String username) {
        System.out.println("\n ADD TO CART\n");
        
       
        productService.viewAllProducts();
        
        System.out.print("\nEnter Product ID:\n");
        int productId = getIntInput("");
        
        System.out.print("Enter Quantity:");
        int quantity = getIntInput("");
        
        customerService.addToCart(username, productId, quantity);
    }
    
    private static void removeFromCart(String username) {
        System.out.println("\n REMOVE FROM CART \n");
        
      
        customerService.viewCart(username);
        
        System.out.print("Enter Product ID to remove:");
        int productId = getIntInput("");
        
        customerService.removeFromCart(username, productId);
    }
    
    private static void updateCartItem(String username) {
        System.out.println("\n UPDATE CART ITEM \n");
        
       
        customerService.viewCart(username);
        
        System.out.print("Enter Product ID to update:");
        int productId = getIntInput("");
        
        System.out.print("Enter new Quantity: ");
        int newQuantity = getIntInput("");
        
        customerService.updateCartItem(username, productId, newQuantity);
    }
    
    private static void placeOrder(String username) {
        System.out.println("\n PLACE ORDER \n");
        
       
        if (customerService.getCartItemCount(username) == 0) {
            System.out.println(" Your cart is empty. Add items to cart first.");
            return;
        }
        
      
        customerService.viewCart(username);
        
        System.out.print("\nDo you want to place this order? (yes/no): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("yes")) {
            Order order = customerService.placeOrder(username);
            if (order != null) {
                orderList.add(order);
                System.out.println("Order placed successfully!");
                System.out.println("Order ID:" + order.getOrderId());
                System.out.println("Total Amount: ₹" + order.getTotalAmount());
            }
        } else {
            System.out.println("Order cancelled.");
        }
    }
    
    private static void viewCustomerOrders(String username) {
        System.out.println("\n YOUR ORDER HISTORY \n");
        customerService.viewCustomerOrders(username);
    }
    
    private static void viewOrderDetails() {
        System.out.println("\n VIEW ORDER DETAILS \n");
        System.out.print("Enter Order ID: ");
        int orderId = getIntInput("");
        
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            order.displayOrderDetails();
        }
    }
    
    private static void clearCart(String username) {
        System.out.println("\n  CLEAR CART \n");
        System.out.print("Are you sure you want to clear your cart? (yes/no): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("yes")) {
            cartService.clearCart(username);
            System.out.println(" Cart cleared successfully!");
        } else {
            System.out.println("Cart not cleared.");
        }
    }
    
   
    private static void displayCartSummary(String username) {
        int itemCount = customerService.getCartItemCount(username);
        double total = customerService.calculateCartTotal(username);
        
        if (itemCount > 0) {
            System.out.println("\n Cart:" + itemCount + " items | Total: ₹" + String.format("%.2f", total));
        }
    }
    
   
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine(); 
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }
    
  
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine(); 
            }
        }
    }
}
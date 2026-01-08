package com.project.onlineshopy.entities;

	public class Admin extends User {
	    private String adminLevel;  
	    private String department;
	    private int managedProductsCount;
	    private int managedOrdersCount;
	    private String permissions; 
	    
	    public Admin(int userId, String username, String password, String email, 
	                 String phoneNumber, String fullName, String adminLevel, String department) {
	        super(userId, username, password, email, phoneNumber, fullName);
	        this.adminLevel = adminLevel;
	        this.department = department;
	        this.managedProductsCount = 0;
	        this.managedOrdersCount = 0;
	        this.permissions = "ALL";  
	        setUserType("ADMIN");
	    }
	    
	  
	   
	    public String getAdminLevel() {
			return adminLevel;
		}



		public void setAdminLevel(String adminLevel) {
			this.adminLevel = adminLevel;
		}



		public String getDepartment() {
			return department;
		}



		public void setDepartment(String department) {
			this.department = department;
		}



		public int getManagedProductsCount() {
			return managedProductsCount;
		}



		public void setManagedProductsCount(int managedProductsCount) {
			this.managedProductsCount = managedProductsCount;
		}



		public int getManagedOrdersCount() {
			return managedOrdersCount;
		}



		public void setManagedOrdersCount(int managedOrdersCount) {
			this.managedOrdersCount = managedOrdersCount;
		}



		public String getPermissions() {
			return permissions;
		}



		public void setPermissions(String permissions) {
			this.permissions = permissions;
		}



		@Override
	    public String toString() {
	        return super.toString() + " Admin [adminLevel=" + adminLevel + 
	               ", department=" + department + ", managedProductsCount=" + 
	               managedProductsCount + ", managedOrdersCount=" + managedOrdersCount + "]";
	    }
	}


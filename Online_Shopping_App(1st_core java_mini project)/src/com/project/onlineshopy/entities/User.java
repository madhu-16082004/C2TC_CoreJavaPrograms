package com.project.onlineshopy.entities;

	import java.time.LocalDateTime;

	public abstract class User {
	    private int userId;
	    private String username;
	    private String password;  
	    private String email;
	    private String phoneNumber;
	    private String fullName;
	    private LocalDateTime registrationDate;
	    private boolean isActive;
	    private String userType;  
	    
	    public User(int userId, String username, String password, String email, 
	                String phoneNumber, String fullName) {
	        this.userId = userId;
	        this.username = username;
	        this.password = password;
	        this.email = email;
	        this.phoneNumber = phoneNumber;
	        this.fullName = fullName;
	        this.registrationDate = LocalDateTime.now();
	        this.isActive = true;
	    }
	    
	   
	    
	    public int getUserId() {
			return userId;
		}



		public void setUserId(int userId) {
			this.userId = userId;
		}



		public String getUsername() {
			return username;
		}



		public void setUsername(String username) {
			this.username = username;
		}



		public String getPassword() {
			return password;
		}



		public void setPassword(String password) {
			this.password = password;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public String getPhoneNumber() {
			return phoneNumber;
		}



		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}



		public String getFullName() {
			return fullName;
		}



		public void setFullName(String fullName) {
			this.fullName = fullName;
		}



		public LocalDateTime getRegistrationDate() {
			return registrationDate;
		}



		public void setRegistrationDate(LocalDateTime registrationDate) {
			this.registrationDate = registrationDate;
		}



		public boolean isActive() {
			return isActive;
		}



		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}



		public String getUserType() {
			return userType;
		}



		public void setUserType(String userType) {
			this.userType = userType;
		}



		@Override
	    public String toString() {
	        return "User [userId=" + userId + ", username=" + username + 
	               ", email=" + email + ", fullName=" + fullName + 
	               ", phoneNumber=" + phoneNumber + ", registrationDate=" + 
	               registrationDate + ", isActive=" + isActive + "]";
	    }
	}


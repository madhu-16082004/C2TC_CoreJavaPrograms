package com.info.oopsconcept;

public class Encapsulation {
	
	private String name;
	private int age;
	private int jersyNo;
	private String role;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getJersyNo() {
		return jersyNo;
	}

	public void setJersyNo(int jersyNo) {
		this.jersyNo = jersyNo;
	}
	
	public String getrole() {
		return name;
	}
	
	public void setrole(String role) {
		this.role = role;
	}
	public String toString() {
		return "EncapsulationDemo [name=" + name + ", age=" + age + ", jersyNo=" + jersyNo + "role=" + role +"]";
	}
}
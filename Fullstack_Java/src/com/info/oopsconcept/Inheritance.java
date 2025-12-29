package com.info.oopsconcept;

class Animal1{
	
	String name;
		
	Animal1(String name){
		this.name = name;
	}
	
	void display() {
		System.out.println("Animal name: "+name);
	}
	
}

class Dogss extends Animal1{
	
	String breed;
	
	Dogss(String name, String breed){
		super(name);
		this.breed = breed;
	}
	
	void showDetails() {
		display();
		System.out.println("Dog breed is: "+breed);
	}
}

class GrandParent {
	
	void gp() {
		System.out.println("Grand Parent Class");
	}
	
}

class Parent extends GrandParent{
	
	void p() {
		System.out.println("Parent Class");
	}
	
}
class Child1 extends Parent{
	
	void c() {
		System.out.println("Child Class");
	}
	
}

class Subject{
	void allSubject() {
		System.out.println("States all subjects");
	}
}

class Maths extends Subject{
	void onlyMath() {
		System.out.println("shows only math");
	}
}

class Science extends Subject{
	void onlyScience() {
		System.out.println("shows only Science");
	}
}

interface A{
	
}

interface B{
	
}

class C implements A,B
{
	
}

public class Inheritance {

	public static void main(String[] args) {// TODO Auto-generated method stub

		Dogss d = new Dogss("Chop","Rottweiler");
		d.showDetails();
		
		Child1 c = new Child1();
		c.gp();
		c.p();
		c.c();
		
		Parent p = new Parent();
		p.gp();
		p.p();
				
		Maths m = new Maths();
		m.allSubject();
		m.onlyMath();
		
		
		Science s = new Science();
		s.allSubject();
		s.onlyScience();
	}

}
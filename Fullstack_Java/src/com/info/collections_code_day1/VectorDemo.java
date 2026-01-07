package com.info.collections_code_day1;

import java.util.Iterator;
import java.util.Vector;

public class VectorDemo {

	public static void main(String[] args) {
		Vector<String> animals = new Vector<>();
		animals.add("Dog");
		animals.add("Horse");
		animals.add("Cat");

		System.out.println("Initial Vector: " + animals);
	
		String element = animals.get(2);
		System.out.println("Element at index 2: " + element);

	
		Iterator<String> iterate = animals.iterator();
		System.out.print("Vector: ");
		while (iterate.hasNext()) {
			System.out.print(iterate.next());
			System.out.print(", ");
		}
		
		element = animals.remove(1);
		System.out.println("Removed Element: " + element);
		System.out.println("New Vector: " + animals);

	
		animals.clear();
		System.out.println("Vector after clear(): " + animals);
	}

}
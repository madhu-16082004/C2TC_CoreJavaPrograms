package com.info.collections_code_day2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Spliterator;

public class HashSetDemo {
	static void addElements(HashSet<Integer> numberHashSet) {
		numberHashSet.add(61);
		numberHashSet.add(41);
		numberHashSet.add(91);
		numberHashSet.add(51);
		numberHashSet.add(80);
		numberHashSet.add(11);
		numberHashSet.add(20);
		System.out.println(numberHashSet.add(51)); 
	}

	public static void main(String[] args) {
		
		HashSet<Integer> numberHashSet = new HashSet<Integer>();
		addElements(numberHashSet);

		HashSet<Integer> numberHashSet1 = new HashSet<Integer>();
		numberHashSet1.add(20);
		numberHashSet1.add(80);
		numberHashSet1.add(11);
		numberHashSet1.add(50);
		numberHashSet1.add(60);
		numberHashSet1.add(10);
		numberHashSet1.add(51);

		System.out.println("Set 1: " + numberHashSet);
		System.out.println("Set 2: " + numberHashSet1);

		HashSet<Integer> numberHashSet2 = new HashSet<Integer>();
		addElements(numberHashSet2);

		numberHashSet2.addAll(numberHashSet1);
		System.out.println("Resultant Set : " + numberHashSet2);

		numberHashSet2.clear();
		addElements(numberHashSet2);
		
		numberHashSet2.retainAll(numberHashSet1);
		System.out.println("Resultant Set : " + numberHashSet2);

		numberHashSet2.clear();
		addElements(numberHashSet2);

		numberHashSet2.removeAll(numberHashSet1);
		System.out.println("Resultant Set : " + numberHashSet2);

		Spliterator<Integer> split=numberHashSet.spliterator();
		split.forEachRemaining(System.out::println);
	}

}
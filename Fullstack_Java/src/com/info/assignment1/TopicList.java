package com.info.assignment1;
import java.util.ArrayList;
  public class TopicList { 
	    public static void main(String[] args) {
	        ArrayList<String> topics = new ArrayList<>();
	        String[] topicNames = {
	            "Introduction to Java",
	            "Wrapper Classes",
	            "User Input",
	            "Runnable Interface",
	            "Polymorphism",
	            "Packages and Access Modifiers",
	            "OOPS Concepts",
	            "Non-Access Modifiers",
	            "Multithreading",
	            "Interfaces",
	            "Exception Handling"
	        };
	        for (String t : topicNames) {
	            topics.add(t);
	        }
	        for (String topic : topics) {
	            System.out.println(topic);
	        }
	    }
	}
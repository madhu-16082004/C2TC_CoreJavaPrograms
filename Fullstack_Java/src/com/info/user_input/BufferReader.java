package com.info.user_input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferReader{

	public static void main(String[] args) throws IOException {


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter Country Name: ");
		String country = br.readLine();
		
		System.out.println("Enter Country Code: ");
		int code = Integer.parseInt(br.readLine());
		
		System.out.println("The country name is: "+ country + " and the country code is: "+code);

	}

}
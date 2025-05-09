package string_generator;

import java.nio.*;
import java.nio.charset.*;

public class Main {

	public static void main(String[] args) {
		System.out.println(Charset.availableCharsets());
		String_generator sg = new String_generator();
		String str = "TEST";
		for (int i = 0; i < str.length(); i++ ) {
			/*
			 * hashcode turns hex to decimal, meaning i can use the decimal values
			 * easier to debug when iterating through the byte array 
			 */
			System.out.println(sg.getBytes(str, "UTF-8")[i]);
		}
	}

}

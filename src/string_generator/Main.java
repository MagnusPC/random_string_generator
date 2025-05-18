package string_generator;

import java.nio.charset.Charset;
import java.util.List;

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
			System.out.print(sg.getBytes(str, "UTF-8")[i] + " ");
		}
		System.out.println();
		List<String> rstr = sg.generateStrings(str, "US-ASCII", 10);
		for (int i = 0; i < rstr.size(); i++) {
			System.out.print(rstr.get(i).hashCode() + " ");
		}
		System.out.println();
		System.out.println(sg.generateStringsAlt(str, "US-ASCII", 10));
		
		// ^ old, new below
		
		String iui = sg.interpretUserInput("abc,US-ASCII,10");
		System.out.println(iui);
		
		String ir = sg.interpretRegex("[a-zA-Z^mnoMNO]");
		System.out.println(ir);
	}

}

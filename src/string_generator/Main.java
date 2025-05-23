package string_generator;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println(Charset.availableCharsets());
		
		String_generator sg = new String_generator();
		String str = "TEST";
		
		/*
		 * Generate a list of string sequences where 
		 * 	the length of the string input corresponds to the length of each sequence,
		 *  chars are set according to charset input,
		 *  list-length is set according to integer param
		 */
		System.out.println(sg.generateStringList(str, "US-ASCII", 10));

		/*
		 * Get bytes corresponding to input string chars
		 */
		for (int i = 0; i < str.length(); i++ ) {
			/*
			 * hashcode turns hex to decimal, meaning i can use the decimal values
			 * easier to debug when iterating through the byte array 
			 */
			System.out.print(sg.getBytes(str, "US-ASCII")[i] + " ");
		}
		System.out.println();
		
		/*
		 * Get hashcode of each string sequence
		 * 
		 */
		List<String> rstr = sg.generateStringList(str, "US-ASCII", 10);
		for (int i = 0; i < rstr.size(); i++) {
			System.out.print(rstr.get(i).hashCode() + " ");
		}
		System.out.println();
		
		/*
		 * Newest impl. methods below
		 * Methods above are mostly for archival purposes
		 */
		String ipRgx = "[a-kG-M^mnoMNO]";
		String ir = sg.interpretRegex(ipRgx);
		System.out.println("InterpretRegex for input " + ipRgx + ": " + ir);
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Write your input, structured as 'text,charset,stringlength':"); //e.g.: "abc,US-ASCII,10"
		String consoleInput = scan.nextLine();
		scan.close();
		String iuInput = sg.interpretUserInput(consoleInput);
		System.out.println("Interpreted userinput: " + iuInput);
	}

}

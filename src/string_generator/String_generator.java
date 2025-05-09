package string_generator;

import java.util.ArrayList;
import java.util.HexFormat; //Use to convert decimal to hex, and from hex to char unicode values
import java.util.regex.*; //bounding of requested strings
import java.util.stream.IntStream; //rnd number stream of requested size
import java.util.random.*;
import java.lang.StringBuilder; //Alt. tool to generate strings

public class String_generator {

	//TODO make interface
	//TODO make accessor method
	//TODO make random string generator method
		//TODO refactor to take regex and length as input parameter
		//TODO possibly convert strings to binary or other to increase generation time when comparing created str with new str
	
	//TODO dont run from the generator-file itself
	public static void main(String[] args) {
		System.out.println("Job done");
	}
	
	protected void GeneratorWithUnicode() {
		
		//local regex pattern to use when generating the strings
		Pattern regexToUse = Pattern.compile("[a-zA-Z][0-9]"); //params: string pattern, int flags (binary mask)
		
		//length of requested strings
		int lengthOfString = 10;
		
		//initialize the random generator
		IntStream rng = RandomGenerator.getDefault().ints(lengthOfString); //creates a stream of count/size/length 10
		convertRandomNumberToHex(rng);
		
		//list to keep track of generated strings, and to return all strings as output, TODO make unique value
		ArrayList<String> listOfStrings = new ArrayList<String>();

		for (int i = 0; i < lengthOfString; i++) {
			System.out.println(rng.toArray()[i]);
		}
		
	}
	
	protected void GeneratorWithStringBuilder() {
		int lengthOfString = 10;
		StringBuilder strBuild = new StringBuilder(lengthOfString);
		
		int rnd = (int)(Math.random() * 100); //TODO lav en metode der opretter et arr ud fra x length, hvert index skal være et random int mellem 48 og 122 jf. unicode
		
		for (int i = 0; i < lengthOfString; i++) {
//			strBuild. //append a char corresponding to the value at index i in the intstream
			
		}
		
	}
	
	protected HexFormat convertRandomNumberToHex(IntStream numbers) {
//		numbers.
		return null;
		
	}
	
	
}

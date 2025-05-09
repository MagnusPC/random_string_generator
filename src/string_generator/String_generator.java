package string_generator;

import java.util.ArrayList;
import java.util.regex.*;
import java.util.stream.IntStream;
import java.util.random.*;

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
	
	public String[] Generator() {
		
		//local regex pattern to use when generating the strings
		Pattern regexToUse = Pattern.compile("[a-zA-Z][0-9]"/*String pattern, int flags*/);
		
		//length of requested strings
		int lengthOfString = 10;
		
		//initialize the randoom generator
		IntStream rng = RandomGenerator.getDefault().ints(lengthOfString); //creates a stream of count/size/length 10
		
		//list to keep track of generated strings, and to return all strings as output, TODO make unique value
		ArrayList<String> listOfStrings = new ArrayList<String>();

		for (int i = 0; i < lengthOfString; i++) {
			System.out.println(rng.toArray()[i]);
		}
		
		return null;
	}
}

package string_generator;

import java.math.MathContext;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;


public class String_generator {

	//TODO make interface
	//TODO make accessor method
	//TODO make random string generator method
		//TODO refactor to take regex and length as input parameter
		//TODO possibly convert strings to binary or other to increase generation time when comparing created str with new str
	
	public String[] generateStrings(String strToConvert, String charsetAlias) {
		//currently only generates from an inputstring and not a regex, TODO generate regex from inputstring?
		int strLen = strToConvert.length();
		
//		Map<String, String> strMap = new HashMap<>(); 
		//no reason to return a map as im only interested in the string values and not the decimal keys
		String[] strArr;

		//get the bytes of each char in inputstring, they will be used to insert chars into a randomized string		
		byte[] byteNums = getBytes(strToConvert, charsetAlias); //NB running .decode will make the chars compareable with rnd array
		
		//we need to generate an array of random integers, corresponding with the unicode decimals
		//TODO make rnd into method
		IntStream rndIntStream = new Random(strLen).ints(strLen); 
		//TODO! NB we want to use the int array to assert where the chars from strarr must be placed...
		
		//TODO either add all int to an int array or compare as is with the bytenums array? 
		for (int i = 0; i < strToConvert.length(); i++) {
			
		}
		
		/*TODO main func
		 * we want to input a string, e.g. through console
		 * the string will be converted to bytes, so we can compare it with a random number generators output
		 * 
		 * 
		 */

		
		
		return null;// strArr;
	}
	
	
	protected byte[] getBytes(String strToConvert, String charsetAlias) {
		// Set requested charset
		Charset cs;
		try {
			cs = Charset.forName(charsetAlias);
		} catch (Exception e) {
			System.out.println("Exception occured: " + e);
			cs = Charset.defaultCharset();
		}
		
		// Return input string as byte array
		byte[] bArr = strToConvert.getBytes(cs);

		return bArr;
	}
	
//	protected void Generator() {
//		//initialize the random generator
//		IntStream rng = RandomGenerator.getDefault().ints(); //creates a stream of count/size/length 10
//	}
	
}

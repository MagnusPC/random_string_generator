package string_generator;

import java.util.ArrayList;
//import java.util.HexFormat; //Use to convert decimal to hex, and from hex to char unicode values
import java.util.random.RandomGenerator;
import java.util.regex.Pattern;
import java.util.stream.IntStream; //rnd number stream of requested size
import java.nio.charset.*;
import java.nio.*;
import java.util.HashMap;
import java.util.Map;


public class String_generator {

	//TODO make interface
	//TODO make accessor method
	//TODO make random string generator method
		//TODO refactor to take regex and length as input parameter
		//TODO possibly convert strings to binary or other to increase generation time when comparing created str with new str
	
	public Map<String, String> generateStrings(String strToConvert, String charsetAlias) {
		//currently only generates from an inputstring and not a regex, TODO generate regex from inputstring?
		Map<String, String> strMap = new HashMap<>();

		
		return strMap;
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

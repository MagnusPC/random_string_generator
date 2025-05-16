package string_generator;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


public class String_generator implements IString_generator<String, List<String>, Integer> {

	//TODO make accessor method
	//TODO make random string generator method
		//TODO refactor to take regex and length as input parameter
		//TODO possibly convert strings to binary or other to increase generation time when comparing created str with new str
	public List<String> generateStrings(String strToConvert, String charsetAlias, int strCount) {
		//currently only generates from an inputstring and not a regex, TODO generate regex from inputstring?
		int strLen = strToConvert.length();

		//get the bytes of each char in inputstring, they will be used to insert chars into a randomized string		
		byte[] byteNums = getBytes(strToConvert, charsetAlias); //NB running .decode will make the chars compareable with rnd array
		
		//we need to generate an array of random integers, corresponding with the unicode decimals
		
		//no reason to return a map as im only interested in the string values and not the decimal keys
		List<String> strList = new ArrayList<>(strCount);//would rather save as "String[] strArr = {};" but alas
		
		//TODO make random indexer below into a method
		for (int i = 0; i < strCount; i++) {
			//to know where to place the chars we need a sort of pointer for each char, generated here
			int rndOriginIncl = 97; 
			int rndBoundExcl = 123; //temp hardcoding to only choose between lowercase a-z
			IntStream rndIntStream = new Random().ints(rndOriginIncl, rndBoundExcl); //.ints(32, strLen + 1);
			
			//place the pointers into the string list - TODO dont do tostring, instead return a temp list with intstreams
			strList.add(rndIntStream.toString());
			//strArr[i].codePoints() //nb returns intstream of codepoints for char at index
		}
		
		//TODO either add all int to an int array or compare as is with the bytenums array? 
		for (int i = 0; i < strToConvert.length(); i++) {
			
		}
		
		/*TODO main func
		 * we want to input a string, e.g. through console
		 * the string will be converted to bytes, so we can compare it with a random number generators output
		 * 
		 * 
		 */

		
		
		return strList;// strArr;
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
	
	public List<String> generateStringsAlt(String strToConvert, String charsetAlias, int strCount) {
	    int strLen = strToConvert.length();
	    List<String> strList = new ArrayList<>(strCount);
	    Random random = new Random(); // Create just one Random instance
	    
	    for (int i = 0; i < strCount; i++) {
	        StringBuilder sb = new StringBuilder(strLen);
	        
	        // Generate a random string with same length as input
	        for (int j = 0; j < strLen; j++) {
	            // Generate random lowercase letter (a-z)
	            char randomChar = (char) (random.nextInt(26) + 'a');
	            sb.append(randomChar);
	        }
	        
	        strList.add(sb.toString());
	    }
	    
	    return strList;
	}


	@Override
	public String interpretUserInput(String input) {
		String rgx = input.substring(0, input.indexOf(","));
		String cset = input.substring(input.indexOf(","), input.lastIndexOf(","));
		int amount = 0;
		try {
			amount = Integer.parseInt(input.substring(input.lastIndexOf(","), input.length()));
		}
		catch(NumberFormatException nfe) {
			amount = 3;
			System.out.println(Integer.parseInt(input.substring(input.lastIndexOf(",")+1, input.length())) + " returned from parsing int, of type ");
			System.out.println(nfe.getMessage() + "\nSetting default amount: " + amount);
		}
		//TODO should be handled in main instead
		return rgx + cset + amount;
	}


	@Override
	public String interpretRegex(String input) {
		if(input.substring(0, 1) != "[" & input.substring(input.length() - 1, input.length()) != "]") { //may be -2 and -1
			System.out.println("Error in stating bounds for regex.");
		}
		for (int i = 0; i < input.length(); i++) {
			if(input.substring(i, i+1) == "-") {
				//TODO method call?
			}
		}

		return null;
	}

	@Override
	public String handleMetaCharacters(String regex) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String handleQuantifiers(String regex) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String handleCharacterClasses(String charset) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String generateStrings(String regex, String charset, Integer amount) {
		String rgx = interpretRegex(regex);
		
		for (int i = 0; i < amount; i++) {
			for (int j = 0; j < rgx.length(); j++) {
				//TODO dont actually use .length() instead get the quantifier
				//pseudo: generate random string
			}
			//pseudo: generat another rnd string until i = amount
		}
		
		return rgx;
	}
}

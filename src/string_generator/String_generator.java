package string_generator;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


public class String_generator implements IString_generator<String, List<String>, Integer> {

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
		String cset = input.substring(input.indexOf(",")+1, input.lastIndexOf(","));
		int amount = 0;
		try {
			amount = Integer.parseInt(input.substring(input.lastIndexOf(",")+1, input.length()));
		}
		catch(NumberFormatException nfe) {
			amount = 3;
			System.out.println("exception caught, " + nfe + "\nsetting default amount: " + amount);
		}
		//TODO should be handled in main instead - what did i mean by this
		return rgx + " " + cset + " " + amount;
	}


	@Override
	public String interpretRegex(String input) {
		//Instead of just breaking input into smaller pieces we simply just find the ranges, meta chars and so on, and figure the pattern out as the last thing
		List<String> ranges = new ArrayList<>();
		for(int i = 0; i < input.length(); i++) {
			//prep switchcase
			char rgxC = input.charAt(i);
			
			switch (rgxC) {
			/*
			 * Get a range 
			 * we will ignore end square bracket as it is included here
			 */
			case '[':
				String rangeSB = handleSquareBracket(input, i);
				ranges.add(rangeSB);
				break;
			/*
			 * Get a range
			 */
			case '-': 
				if (i > 0) {
					String rangeDash = handleDash(input, i);
					ranges.add(rangeDash);
					System.out.println("rangeDash '" + rangeDash + "' was added to 'ranges'");
					
				} 
				else {
					System.out.println("Error in switch-case for char: " + rgxC);
				}
			/*
			 * Get exclusion
			 * Alt. assert start of regular expression
			 */
			case '^':
				//TODO decide whether or not to only handle outside of ranges
				//e.g. if input.charAt(0) = ^, then anything after would be placed at start of word
				//but if case '[' of index 0 is followed by ^ of index 1, then the following chars would have to be excluded
				if (input.charAt(i+1) == '[') {
					//beginning of input
				} else {
					//assume exclusion of chars
				}
			case '\\':
				
			case '+':
			
			case '{': 
				
			default : 
				//any other chars, just continue the for-loop
			}
		}
		
		return ranges.toString();
	}

	private String handleSquareBracket(String input, int inputIdx) {
		int endIdx = input.indexOf(']', inputIdx);
		String range = input.substring(inputIdx, endIdx);
		
		return range;
	}
	
	private String handleDash(String input, int inputIdx) {
		//currently only working with the english alphabet, TODO should be generalized
		String cl = "ABCDEFGHIJKLMNOPQRSTUVWXYZ", sl = cl.toLowerCase(); // extend with numbers
		
		char rStart = input.charAt(inputIdx-1);
		char rEnd = input.charAt(inputIdx+1);
		int rsIdx, reIdx;

		String letterRange = "";
		
		if (cl.indexOf(rStart) == -1) {
			rsIdx = sl.indexOf(rStart); //TODO must be a better way than an if-else, maybe by converting from lowercase to uppercase and vice versa
			reIdx = sl.indexOf(rEnd) + 1;
			
			letterRange = letterRange.concat(sl.substring(rsIdx, reIdx));
		} else {
			rsIdx = cl.indexOf(rStart);
			reIdx = cl.indexOf(rEnd) + 1;
			
			letterRange = letterRange.concat(cl.substring(rsIdx, reIdx));
		}
		
		return letterRange;
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
		StringBuilder sb = new StringBuilder(); //with charsequenece and/or capacity as param inputs
		
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

package string_generator;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class String_generator implements IString_generator<String, List<String>, Integer>, Runnable {

	@Override
	public List<String> generateStringList(String strToConvert, String charsetAlias, int strCount) {
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
	
	
	/*
	 * Probably finally the actual entrance method
	 */
	@Override
	public List<String> generateListOfStrings(String input, String charsetAlias, Integer strLength, Integer strCount){
		//Interpret the inputs
		String convertedRgx = interpretRegex(input);
		Charset cSet = getCharsetFromInput(charsetAlias);
		int len = strLength; //revise
		
		//Generate the strings and add to list
		List<String> rndStrings = new ArrayList<>();
		
		for (int i = 0; i < strCount; i++) {
			String genString = generateStrings(convertedRgx, cSet, len);
			rndStrings.add(genString);
		}
		
		return rndStrings;
		
	}

	//may be public for singular str generation purposes?
	private String generateStrings(String regex, Charset charset, int lenOfStr) {
		//TODO implement
		return null;
	}

	
	private Charset getCharsetFromInput(String charsetAlias) {
		return Charset.forName(charsetAlias); //TODO refactor
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

		return bArr; //TODO revise, probs build from scratch
	}
	
	
	@Override
	public String interpretUserInput(String input) {
		String retStr = "^[a-zA-Z]$"; //hardcoded default
		int amount = 3;
		
		try {
			
			String rgx = input.substring(0, input.indexOf(","));
			String cset = input.substring(input.indexOf(",")+1, input.lastIndexOf(","));
			
			amount = Integer.parseInt(input.substring(input.lastIndexOf(",")+1, input.length()));
			retStr = rgx + " " + cset + " " + amount;
			//TODO call the generateListOfStrings method
		}
		catch(NumberFormatException nfe) {
			System.out.println("exception caught, " + nfe + ", setting default amount: " + amount);
		}
		catch(StringIndexOutOfBoundsException sioob) {
			System.out.println("exception caught, " + sioob + ", setting default regex: " + retStr);
		}
		
		return retStr;
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
				if (i > 0 && !(input.charAt(i-1) == '\\')) {
					//TODO should also not run if input is say '[abc-]'
					String rangeDash = handleDash(input, i);
					ranges.add(rangeDash);
				} 
				else {
					System.out.println("Error in switch-case for char: " + rgxC);
				}
				break;
			/*
			 * Get exclusion
			 * Alt. assert start of regular expression
			 */
			case '^':
				//TODO decide whether or not to only handle outside of ranges
				//e.g. if input.charAt(0) = ^, then anything after would be placed at start of word
				//but if '[' is followed by '^', then the following chars until ']' would have to be excluded
				if (input.charAt(i+1) == '[') {
					//beginning of input
				} else {
					//assume exclusion of chars
				}
				break;
			/*
			 * Handle backslashes
			 * May be a whole switch case in itself
			 */
			case '\\':
				break;
			case '+':
				break;
			case '{': 
				break;
			default : 
				//any other chars, just continue the for-loop
			}
		}
		
		return ranges.toString();
	}

	private String handleSquareBracket(String input, int inputIdx) {
		int endIdx = input.indexOf(']', inputIdx);
		String range = input.substring(inputIdx, endIdx);
		
//		TODO add check to see if range contains dashes, call handleDash
		//if !hasDash, run code, else, call handledash
		
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
	
	public String handleMetaCharacters(String regex) {
		// TODO Auto-generated method stub
		return null;
	}


	public String handleQuantifiers(String regex) {
		// TODO Auto-generated method stub
		return null;
	}


	public String handleCharacterClasses(String charset) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public void run() {
		/*
		 * Would be simplest to have one point of entry
		 * Meaning a generateStrings method should assert whether the input is a console input or external method call
		 * Which then would call a method to interpret the input which in turn, as best as possible, converts input to regex
		 * 
		 * Implement a divide an conquer esque approach and use threading for all handleXYZ methods
		 */
		
	}

}

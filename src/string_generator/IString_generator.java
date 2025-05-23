package string_generator;

import java.util.List;

public interface IString_generator<T, U, V> {

	List<String> generateStringList(String strToConvert, String charsetAlias, int strCount);
	
	/*
	 * we know that we want a method to handle user inputs and convert them to regex, method 1
	 * we know that external method calls most likely contains a regex pattern, handle if not, method 2
	 * meta characters may be handled as switches, as an internal method call, method 3 
	 * the same is valid for quantifiers but not high priority, method 4
	 * character classes (a-z, 0-9, A-Z, special characters) should be handled according to a charset?, method 5
	 * method for generating the actual string, based on charset, regex and amount of returned strings
	 * TODO handleXYZ should not be publics
	 */
	public T interpretUserInput(T input);
	public T interpretRegex(T input);
//	public T handleMetaCharacters(T regex); //interpretRegex would call this
//	public T handleQuantifiers(T regex); //interpretRegex would call this
//	public T handleCharacterClasses(T charset); //interpretRegex would call this
	public U generateListOfStrings(T input, T charsetAlias, V strLength, V strCount); //need naming standard for lists
//	public T generateStrings(T regex, T charset, V amount); //unused
	
}

package string_generator;

import java.util.List;

public interface IString_generator {

	//TODO generalize types
	List<String> generateStrings(String strToConvert, String charsetAlias, int strCount);
//	private byte[] getBytes(String strToConvert, String charsetAlias); //will be private at a later point
}

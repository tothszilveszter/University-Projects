package Validator;
/**
 * Checks if the input has the correct format/characters.
 * @author Toth Szilveszter
 *
 */
public class Validator {
	
	public Validator() {
		
	}
	
	public boolean isValid(String s) {
		
		for (int i=0; i<s.length(); i++)
		{
			if (Character.isDigit(s.charAt(i))==false && Character.isLetter(s.charAt(i))==false && s.charAt(i)!=':' && s.charAt(i)!=',' && s.charAt(i)!=' ' && s.charAt(i)!='-' && s.charAt(i)!='.')
				return false;
		}
		return true;
	}
}

package Framework.Utils;

public class NumberFormatter {

	public static double brazilianToJavaDouble(String value) {
		if (value.substring(0, "R$ ".length()).equals("R$ "))
			value = value.substring("R$ ".length());
		String formattedValue = "";
		
		for (char c: value.toCharArray()) {
			if (c == ',') formattedValue += ".";
			else if (c != '.') formattedValue += c;
		}
		
		return Double.parseDouble(formattedValue);	
	}
	
}

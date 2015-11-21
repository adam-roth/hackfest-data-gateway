package au.com.suncoastpc.auth.util;

import java.util.UUID;

/**
 * Static utility-class for simple string-manipulation operations.
 * 
 * @author Adam
 */
public class StringUtilities {
	public static String randomStringWithLengthBetween(int minLength, int maxLength) {
		int delta = maxLength - minLength;
		int length = minLength + (int)(Math.random() * delta);
		
		return randomStringOfLength(length);
	}
	
	public static String randomStringOfLength(int length) {
		StringBuffer buffer = new StringBuffer();
		while (buffer.length() < length) {
			buffer.append(uuidString());
		}
		
		return buffer.substring(0, length);
	}
	
	public static boolean isEmpty(String test) {
		return test == null || "".equals(test.trim());
	}
	
	public static String escapeUnicode(String text) {
		StringBuffer escaped = new StringBuffer("");
		for (char current : text.toCharArray()) {
			if (current <= 127) {
				escaped.append(current);
			}
			else {
				escaped.append( "\\u" ).append( padUnicodeSequence(Integer.toHexString(current)) );
			}
		}
		
		return escaped.toString();
		//return StringEscapeUtils.escapeJava(text).replace(oldChar, newChar).replace("\\\"", "\"");
	}
	
	private static String padUnicodeSequence(String sequence) {
		while (sequence.length() < 4) {
			sequence = "0" + sequence;
		}
		
		return sequence;
	}
	
	private static String uuidString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}

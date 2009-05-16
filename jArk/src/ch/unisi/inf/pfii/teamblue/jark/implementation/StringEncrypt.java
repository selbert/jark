package ch.unisi.inf.pfii.teamblue.jark.implementation;

public class StringEncrypt {

	public static final String encrypt(final String string, final int code) {
		String encryptedString;
		
		char[] array = string.toCharArray();
		
		int j = code;
		
		for (int i = 0; i < array.length; i++) {
			array[i] = (char)(array[i] - ((i + j) % 30));
		}
		
		encryptedString = new String(array);
		
		return encryptedString;
	}
	
	public static final String dencrypt(final String string, final int code) {
		String dencryptedString;
		
		char[] array = string.toCharArray();
		
		int j = code;
		
		for (int i = 0; i < array.length; i++) {
			array[i] = (char)(array[i] + ((i + j) % 100));
		}
		
		dencryptedString = new String(array);
		
		return dencryptedString;
	}
}

package ch.unisi.inf.pfii.teamblue.jark.implementation;
/**
 * Method to encrypt and decrypt strings using a simple algorithm.
 * 
 * @author Stefano.Pongelli@lu.unisi.ch, Thomas.Selber@lu.unisi.ch
 * @version $LastChangedDate$
 * 
 */
public final class StringEncrypt {

	
	/**
	 * Encrypt a string using a code
	 * 
	 * @param string
	 * 				the string you want to encrypt
	 * @param code
	 * 				the selected code you want to use to encrypt the string, it will have to be the same to decrypt
	 * 
	 * @return encrypted string
	 */
	
	public static final String encrypt(final String string, final int code) {
		String encryptedString;

		final char[] array = string.toCharArray();

		for (int i = 0; i < array.length; i++) {
			array[i] = (char) (array[i] - ((i + code) % 30));
		}

		encryptedString = new String(array);

		return encryptedString;
	}

	/**
	 * Encrypt a string using a the same code used to encrypt
	 * 
	 * @param string
	 * 				the string you want to decrypt
	 * @param code
	 * 				the code you want to use to decrypt the string, it will have to be the same you selected for encrypt
	 * 
	 * @return encrypted string
	 */
	
	public static final String decrypt(final String string, final int code) {
		String dencryptedString;

		final char[] array = string.toCharArray();

		for (int i = 0; i < array.length; i++) {
			array[i] = (char) (array[i] + ((i + code) % 30));
		}

		dencryptedString = new String(array);

		return dencryptedString;
	}
}

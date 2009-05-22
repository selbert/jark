package ch.unisi.inf.pfii.teamblue.jark.implementation;

public final class StringEncrypt {

	public static final String encrypt(final String string, final int code) {
		String encryptedString;

		final char[] array = string.toCharArray();

		for (int i = 0; i < array.length; i++) {
			array[i] = (char) (array[i] - ((i + code) % 30));
		}

		encryptedString = new String(array);

		return encryptedString;
	}

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
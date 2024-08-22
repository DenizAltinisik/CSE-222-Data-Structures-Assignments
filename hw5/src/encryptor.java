// Class for encrypting plaintext using the Vigenere cipher
import java.util.Map;

public class encryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text;
	private String cipher_text = "";

	// Constructor
	public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		map = _map;
		key = _key;
		plain_text = text;
	}

	// Method to encrypt the plaintext
	public void encrypt() {
		generate_keystream();
		generate_cipher_text();
	}

	// Method to generate the keystream
	private void generate_keystream() {
		int textLength = plain_text.length();
		int keyLength = key.length();

		for (int i = 0; i < textLength; i++) {
			char keyChar = key.charAt(i % keyLength);
			keystream += keyChar;
		}
	}

	// Method to generate the ciphertext from the plaintext
	private void generate_cipher_text() {
		for (int i = 0; i < plain_text.length(); i++) {
			char plainChar = plain_text.charAt(i);
			char keyChar = keystream.charAt(i);
			char encryptedChar = map.get(plainChar).get(keyChar);
			cipher_text += encryptedChar;
		}
	}

	// Method to get the keystream
	public String get_keystream() {
		return keystream;
	}

	// Method to get the ciphertext
	public String get_cipher_text() {
		return cipher_text;
	}
}
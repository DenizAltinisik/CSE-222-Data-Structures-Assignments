import java.util.Map;
import java.util.Iterator;

// Class for decrypting ciphertext using the Vigenere cipher
import java.util.Map;
import java.util.Iterator;

public class decryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text = "";
	private String cipher_text;

	// Constructor
	public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		map = _map;
		key = _key;
		cipher_text = text;
	}

	// Method to decrypt the ciphertext
	public void decrypt() {
		generate_keystream();
		generate_plain_text();
	}

	// Method to generate the keystream
	private void generate_keystream() {
		int textLength = cipher_text.length();
		int keyLength = key.length();

		for (int i = 0; i < textLength; i++) {
			char keyChar = key.charAt(i % keyLength);
			keystream += keyChar;
		}
	}

	// Method to generate the plaintext from the ciphertext
	private void generate_plain_text() {
		for (int i = 0; i < cipher_text.length(); i++) {
			char cipherChar = cipher_text.charAt(i);
			char keyChar = keystream.charAt(i);

			// Find the row in the table based on the keyChar (keystream)
			for (char row : map.keySet()) {
				if (map.get(row).get(keyChar) == cipherChar) {
					plain_text += row;
					break;
				}
			}
		}
	}

	// Method to get the keystream
	public String get_keystream() {
		return keystream;
	}

	// Method to get the plaintext
	public String get_plain_text() {
		return plain_text;
	}
}


// Class for preprocessing strings
public class preprocessor {
	private String initial_string;
	private String preprocessed_string;

	// Constructor
	public preprocessor(String str) {
		initial_string = str;
		preprocess();
	}

	// Method to preprocess the string
	public void preprocess() {
		capitalize();
		clean();
	}

	// Method to capitalize the string and remove Turkish characters
	private void capitalize() {
		String[] array = {"ı" , "ö", "ç", "ğ", "ü", "ş", "ç", "İ", "Ö", "Ç", "Ğ", "Ü", "Ş"};
		for (int i = 0; i < initial_string.length(); i++) {
			for (String c : array){
				if(initial_string.contains(c)){
					initial_string = initial_string.replace(c,"");
				}
			}
		}
		preprocessed_string = initial_string.toUpperCase();
		preprocessed_string = preprocessed_string.replace("İ", "I");
	}

	// Method to remove non-English characters from the string
	private void clean() {
		StringBuilder cleanedString = new StringBuilder();
		for (int i = 0; i < preprocessed_string.length(); i++) {
			char c = preprocessed_string.charAt(i);
			// Ignore non-English characters
			if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
				cleanedString.append(c);
			}
		}
		preprocessed_string = cleanedString.toString();
	}

	// Method to get the preprocessed string
	public String get_preprocessed_string() {
		return preprocessed_string;
	}
}
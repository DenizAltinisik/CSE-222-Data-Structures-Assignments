// Class for generating and managing the Vigenere cipher table
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class alphabet {
	private Set<Character> english_alphabet = new LinkedHashSet<Character>();
	private Map<Character, Map<Character, Character>> map = new HashMap<Character,  Map<Character, Character>>();

	// Constructor
	public alphabet() {
		fill_english_alphabet();
		fill_map();
	}

	// Method to fill the English alphabet set
	private void fill_english_alphabet() {
		for(char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
			english_alphabet.add(c);
		}
	}

	// Method to fill the Vigenere cipher table
	private void fill_map() {
		Iterator<Character> rowIterator = english_alphabet.iterator();
		Iterator<Character> colIterator = english_alphabet.iterator();

		while (rowIterator.hasNext()) {
			char row = rowIterator.next();
			Map<Character, Character> rowMap = new HashMap<>();
			char currentChar = colIterator.next();
			for (char col : english_alphabet) {
				rowMap.put(col, currentChar);
				currentChar = (char) ('A' + (currentChar - 'A' + 1) % 26); // Circular shift
			}
			map.put(row, rowMap);
		}
	}

	// Method to print the Vigenere cipher table
	public void print_map() {
		System.out.println("*** Viegenere Cipher ***\n\n");
		System.out.println("    " + english_alphabet);
		System.out.print("    ------------------------------------------------------------------------------");
		for(Character k: map.keySet()) {
			System.out.print("\n" + k + " | ");
			System.out.print(map.get(k).values());
		}
		System.out.println("\n");
	}

	// Method to get the Vigenere cipher table
	public Map get_map() {
		return map;
	}
}
package wwwordz.server.puzzle;

import java.io.*;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wwwordz.server.puzzle.Trie.Search;

public class Dictionary {

	public static Dictionary dictionary = new Dictionary();
	Trie trie = new Trie();
	final String DIC_FILE = "wwwordz/server/puzzle/pt-PT-AO.dic";
	Pattern pattern = Pattern.compile("[A-Z]+");

	/**
	 * Constructor for dictionary. Reads text from file, eliminates all the junk
	 * and puts all the "good words" in the trie;
	 */
	public Dictionary() {
		//super();
		try (InputStream in = ClassLoader.getSystemResourceAsStream(DIC_FILE);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in, "UTF-8"));) {
			reader.readLine();
			String word = reader.readLine();
			while (word != null) {
				word = Normalizer.normalize(word.toUpperCase(Locale.ENGLISH),
						Form.NFD).replaceAll(
						"\\p{InCombiningDiacriticalMarks}+", "");
				Matcher match = pattern.matcher(word);
				boolean isThisAWord = match.find();
				boolean isItLongEnough = (match.group().length() >= 3);
				if (isThisAWord && isItLongEnough) {
					trie.put(match.group());
				}
				word = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Returns an instance of a Dictionary
	 * 
	 * @return dictionary
	 */
	public static Dictionary getInstance() {
		if (dictionary == null) {
			return dictionary = new Dictionary();
		}
		return dictionary;
	}

	/**
	 * Gets a random generated word from the trie
	 * 
	 * @return random generated word
	 */
	public String getRandomLargeWord() {
		return trie.getRandomLargeWord();
	}

	/**
	 * Starts a search in its trie
	 * 
	 * @return search
	 */
	Search startSearch() {
		return trie.startSearch();
	}

}

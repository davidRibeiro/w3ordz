package wwwordz.server.puzzle;

import java.util.HashMap;
import java.util.Random;

public class Trie {

	Node root;

	/**
	 * Constructor for a trie
	 */
	public Trie() {
		super();
		this.root = new Node();
	}

	/**
	 * Puts word in trie, starting from the root
	 * 
	 * @param word
	 */
	void put(String word) {
		root.put(word, 0);
	}

	/**
	 * Creates a search starting in the root
	 * 
	 * @return search
	 */
	Search startSearch() {
		Search start = new Search(root);
		return start;
	}

	/**
	 * Creates a random word already in the trie
	 * 
	 * @return word
	 */
	String getRandomLargeWord() {
		StringBuffer randomWord = new StringBuffer();
		String word = root.collectRandomLargeWord(randomWord);
		return word;
	}

	public static class Node extends HashMap<Character, Node> {

		private static final long serialVersionUID = 1L;
		boolean isWord;

		/**
		 * Constructor for a node
		 */
		public Node() {
			super();
			this.isWord = false;
		}

		/**
		 * Places the letter of the index-position of the word in this Node,
		 * putting all the following letters recursively
		 * 
		 * @param word
		 *            , index
		 */
		void put(String word, int index) {
			Node node;
			if (index < word.length()) {
				char letter = word.charAt(index);
				if (!containsKey(letter)) {
					node = new Node();
					put(letter, node);
				} else {
					node = get(letter);
				}
				if (index == word.length() - 1) {
					this.get(word.charAt(index)).isWord = true;
				}
				index++;
				node.put(word, index);
			}
		}

		/**
		 * Selects a random descendant and add its letter to the buffer, if
		 * there are no descendants then returns word at buffer
		 * 
		 * @param buffer
		 * @return random generated word
		 */
		private String collectRandomLargeWord(StringBuffer word) {
			if (this.size() > 0) {
				Object keys[] = keySet().toArray();
				Random r = new Random();
				int randomNumber = r.nextInt(keys.length);
				word.append(keys[randomNumber]);
				Node node = get(keys[randomNumber]);
				node.collectRandomLargeWord(word);
			}
			return word.toString();
		}
	}

	public class Search {
		Node node;

		/**
		 * Constructor for a search starting in given node
		 * 
		 * @param node
		 */
		Search(Node node) {
			super();
			this.node = node;
		}

		/**
		 * Constructor for a search copying given search
		 * 
		 * @param search
		 */
		Search(Search search) {
			super();
			this.node = search.node;
		}

		/**
		 * Continues search with given letter, returning true if if prefix is
		 * valid
		 * 
		 * @param letter
		 * @return true if node contains given letter, otherwise false
		 */
		boolean continueWith(char letter) {
			if (this.node.containsKey(letter)) {
				node = node.get(letter);
				return true;
			}
			return false;
		}

		/**
		 * check if state of search is a complete word
		 * 
		 * @return true if node completes a word, otherwise false
		 */
		boolean isWord() {
			return node.isWord;
		}
	}
}

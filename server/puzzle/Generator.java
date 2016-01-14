package wwwordz.server.puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import wwwordz.server.puzzle.Trie.Search;
import wwwordz.shared.Puzzle;
import wwwordz.shared.Puzzle.Solution;
import wwwordz.shared.Table;
import wwwordz.shared.Table.Cell;

public class Generator {

	private static final int WORDS_GENERATOR = 4;
	private static final int WORDS_SIZE = 4;
	private static final int ALPHABET_SIZE = 26;
	Dictionary dictionary;
	Puzzle puzzle;
	Random random;
	Trie trie;

	/**
	 * Constructor for generator
	 */
	public Generator() {
		//super();
		puzzle = new Puzzle();
		random = new Random();
		dictionary = Dictionary.getInstance();
		trie = dictionary.trie;

	}

	/**
	 * Generates a puzzle, randomly placing letters in the puzzle's cells
	 * 
	 * @return randomly generated puzzle
	 */
	public Puzzle random() {
		Random random = new Random();
		puzzle = new Puzzle();
		Table table = puzzle.getTable();
		for (Cell c : table) {
			int aux = random.nextInt(ALPHABET_SIZE);
			char letter = (char) (aux + 'A');
			c.setLetter(letter);

		}
		puzzle.setSolutions(getSolutions(table));
		puzzle.setTable(table);
		return puzzle;
	}

	/**
	 * Generates a puzzle with a bigger number of solutions than the one created
	 * randonmly
	 * 
	 * @return better generated puzzle
	 */
	public Puzzle generate() {
		puzzle = new Puzzle();
		Table table = puzzle.getTable();
		for (int i = 0; i < WORDS_GENERATOR; i++) {
			String word = dictionary.getRandomLargeWord();
			while (word.length() != WORDS_SIZE) {
				word = dictionary.getRandomLargeWord();
			}
			List<Cell> emptyCells = table.getEmptyCells();
			int randomPos = random.nextInt(emptyCells.size());
			Cell target = emptyCells.remove(randomPos);
			target.setLetter(word.charAt(0));

			String subWord = word.substring(1);
			char subWordArray[] = subWord.toCharArray();
			for (char letter : subWordArray) {
				List<Cell> neighbors = table.getNeighbors(target);

				randomPos = random.nextInt(emptyCells.size());
				while (!neighbors.contains(emptyCells.get(randomPos))
						&& (table.getEmptyNeighbors(target).size() > 0)) {
					randomPos = random.nextInt(emptyCells.size());
				}

				target = emptyCells.remove(randomPos);
				target.setLetter(letter);
			}
		}
		puzzle.setTable(table);
		puzzle.setSolutions(getSolutions(table));
		return puzzle;
	}

	/**
	 * Checks solutions of a given table
	 * 
	 * @param table
	 * @return list of solutions
	 */
	public List<Solution> getSolutions(Table table) {

		List<Solution> solutions = puzzle.getSolutions();
		ArrayList<Cell> visitedCells = new ArrayList<Cell>();
		for (Cell c : table) {
			Search search = trie.startSearch();
			if (search.continueWith(c.getLetter())) {
				visitedCells.clear();
				Solution solution = new Solution();
				StringBuffer word = new StringBuffer();

				checkWords(table, c, visitedCells, solutions, search, solution,
						word);
			}
		}
		return solutions;
	}

	/**
	 * Checks recursively all the words in the table, adding them to the list of
	 * solutions
	 * 
	 * @param table
	 *            , c, visitedCells, solutions, search, solution, word
	 */
	private void checkWords(Table table, Cell c, ArrayList<Cell> visitedCells,
			List<Solution> solutions, Search search, Solution solution,
			StringBuffer word) {
		List<Cell> neighbors = table.getNeighbors(c);
		word.append(c.getLetter());
		solution.addCell(c);
		visitedCells.add(c);

		if (search.isWord()) {
			solution.setWord(word.toString());
			solutions.add(solution);
		}

		for (Cell n : neighbors) {
			if (!visitedCells.contains(n)) {
				Search newSearch = trie.new Search(search);
				if (newSearch.continueWith(n.getLetter())) {
					ArrayList<Cell> newVisitedCells = new ArrayList<Cell>(
							visitedCells);
					StringBuffer newWord = new StringBuffer();
					newWord.setLength(0);
					newWord.append(word);
					Solution newSolution = new Solution(solution);
					checkWords(table, n, newVisitedCells, solutions, newSearch,
							newSolution, newWord);
				}
			}
		}
	}

}

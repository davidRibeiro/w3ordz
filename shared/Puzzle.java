package wwwordz.shared;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import wwwordz.shared.Table.Cell;

public class Puzzle implements Serializable{
	private static final long serialVersionUID = 1L;

	private Table table = new Table();
	private List<Solution> solutions = new LinkedList<Solution>();
		
	/**
	 * Empty constructor for class Puzzle
	 */
	public Puzzle(){}
	

	/**
	 * Constructor for class Puzzle
	 * 
	 * @param table
	 *            for puzzle
	 * @param solutions
	 *            of puzzle
	 */	
	public Puzzle(Table table, List<Solution> solutions) {
			super();
			this.table = table;
			this.solutions = solutions;
	}

	/**
	 * Getter for puzzle's table
	 * 
	 * @return table
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Setter for puzzle's table
	 * 
	 * @param table
	 */
	public void setTable(Table table) {
		this.table = table;
	}

	/**
	 * Getter for puzzle' solutions
	 * 
	 * @return solutions
	 */
	public List<Solution> getSolutions() {
		return solutions;
	}

	/**
	 * Setter for puzzle' solutions
	 * 
	 * @param solutions
	 */
	public void setSolutions(List<Solution> solutions) {
		this.solutions = solutions;
	}

	public static class  Solution implements Serializable{
		private static final long serialVersionUID = 1L;

		public String word;
		private List <Cell> cells = new LinkedList <Cell>();
			
		/**
		 * Empty constructor for sub-class solution
		 */
		public Solution() {super();}

		/**
		 * Constructor for sub-class solution
		 * 
		 * @param word
		 *            that is a solutions
		 * @param cells
		 *            where each letter of the word is displayed
		 */
		public Solution(String word, List<Cell> cells) {
			super();
			this.word = word;
			this.cells = cells;
		}

		/**
		 * Constructor for sub-class solution It creates a copy of given
		 * solution
		 * 
		 * @param solution
		 */
		public Solution(Solution solution) {
			super();
			this.cells = solution.cells;
			this.word = solution.word;
		}

		/**
		 * Getter for solution's word
		 * 
		 * @return word
		 */
		public String getWord() {
			return word;
		}

		/**
		 * Setter for solution's word
		 * 
		 * @param word
		 */
		public void setWord(String word) {
			this.word = word;
		}

		/**
		 * Getter for solution's cells
		 * 
		 * @return cells
		 */
		public List<Cell> getCells() {
			return cells;
		}

		/**
		 * Setter for solution's cells
		 * 
		 * @param cells
		 */
		public void setCells(List<Cell> cells) {
			this.cells = cells;
		}

		/**
		 * Adds a cell for solution's cells
		 * 
		 * @param c
		 */
		public void addCell(Cell c) {
			cells.add(c);

		}

		public int getPoints(int length) {
			if(length==3) return 1;
			return 1+(2*getPoints(--length));
		}
	}
}

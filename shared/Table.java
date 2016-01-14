package wwwordz.shared;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import wwwordz.shared.Table.Cell;

public class Table implements Iterable<Cell>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final int NLINES = 6;
	public final int NCOLUMNS = 6;

	Cell cell[][] = new Cell[NLINES][NCOLUMNS];

	/**
	 * Empty constructor for a table If no words are given, than the table isn't
	 * null; It's filled with blank spaces.
	 */
	public Table() {
		super();
		for (int i = 1; i < NLINES - 1; i++) {
			for (int j = 1; j < NCOLUMNS - 1; j++) {
				cell[i][j] = new Cell(i, j, ' ');
			}
		}
	}

	/**
	 * Constructor for a table
	 * 
	 * @param words
	 *            of table
	 */
	public Table(String words[]) {
		super();
		for (int i = 1; i < NLINES - 1; i++) {
			for (int j = 1; j < NCOLUMNS - 1; j++) {
				char c = words[i - 1].charAt(j - 1);
				cell[i][j] = new Cell(i, j, c);
			}
		}
	}

	/**
	 * Getter for cell at i-line and j-column
	 * 
	 * @param i
	 *            , j
	 * @return letter of cell at i-line and j-column
	 */
	public char getLetter(int i, int j) {
		return cell[i][j].getLetter();
	}

	/**
	 * Setter for letter at table's cell at i-line and j-column
	 * 
	 * @param i
	 *            , j
	 * @param new letter of modified cell
	 */
	public void setLetter(int i, int j, char c) {
		cell[i][j].setLetter(c);
	}

	/**
	 * Getter for table's empty cells
	 * 
	 * @return list of cells
	 */
	public List<Cell> getEmptyCells() {
		List<Cell> empty = new LinkedList<Cell>();
		for (int i = 1; i < NCOLUMNS - 1; i++) {
			for (int j = 1; j < NLINES - 1; j++) {
				if (cell[i][j].isEmpty()) {
					empty.add(cell[i][j]);
				}
			}
		}
		return empty;
	}

	/**
	 * Getter for target's neighbors
	 * 
	 * @param target
	 * @return list of target's neighbors
	 */
	public List<Cell> getNeighbors(Cell target) {
		List<Cell> neighbors = new LinkedList<Cell>();
		int targetline = target.getLine();
		int targetColumn = target.getColumn();

		for (int i = targetline - 1; i <= targetline + 1; i++) {
			for (int j = targetColumn - 1; j <= targetColumn + 1; j++) {
				if (cell[i][j] != null && !cell[i][j].equals(target)) {
					neighbors.add(cell[i][j]);
				}
			}
		}
		return neighbors;
	}

	/**
	 * Getter for target's empty neighbors
	 * 
	 * @param target
	 * @return list of target's empty neighbors
	 */
	public List<Cell> getEmptyNeighbors(Cell target) {
		List<Cell> neighbors = new LinkedList<Cell>();
		int cellline = target.getLine();
		int cellColumn = target.getColumn();

		for (int i = cellline - 1; i <= cellline + 1; i++) {
			for (int j = cellColumn - 1; j <= cellColumn + 1; j++) {
				if (cell[i][j] != null && cell[i][j] != target
						&& cell[i][j].isEmpty())
					neighbors.add(cell[i][j]);
			}
		}
		return neighbors;
	}

	/**
	 * Getter for cell at i-line and j-column
	 * 
	 * @param i
	 *            , j
	 * @return cell at i-line and j-column
	 */
	public Cell getCell(int i, int j) {
		return cell[i][j];
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Table other = (Table) obj;
		if (!Arrays.deepEquals(cell, other.cell))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		int count = 0;
		for (Cell cell : this) {
			buffer.append(cell.getLetter());
			if (++count % 4 == 0)
				buffer.append('\n');
		}
		return buffer.toString();
	}

	@Override
	public Iterator<Cell> iterator() {
		List<Cell> table = new LinkedList<Cell>();
		for (int i = 1; i < NLINES - 1; i++) {
			for (int j = 1; j < NCOLUMNS - 1; j++) {
				table.add(cell[i][j]);
			}
		}
		return table.iterator();
	}

	public static class Cell implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int line, column;
		private char letter;

		/**
		 * Empty constructor for cell
		 */
		Cell() {
			super();
		}

		/**
		 * Constructor for cell with cell's line, column and letter
		 * 
		 * @param line
		 *            , column, letter
		 */
		Cell(int line, int column, char letter) {
			super();
			this.line = line;
			this.column = column;
			this.letter = letter;
		}

		/**
		 * Getter for cell's line
		 * 
		 * @return cell's line
		 */
		public int getLine() {
			return line;
		}

		/**
		 * Setter for cell's line
		 * 
		 * @param cell
		 *            's new line
		 */
		public void setLine(int line) {
			this.line = line;
		}

		/**
		 * Getter for cell's column
		 * 
		 * @return cell's new column
		 */
		public int getColumn() {
			return column;
		}

		/**
		 * Setter for cell's column
		 * 
		 * @param cell
		 *            's new column
		 */
		public void setColumn(int column) {
			this.column = column;
		}

		/**
		 * Getter for cell's letter
		 * 
		 * @return cell's letter
		 */
		public char getLetter() {
			return letter;
		}

		/**
		 * Setter for cell's letter
		 * 
		 * @param cell
		 *            's new letter
		 */
		public void setLetter(char letter) {
			this.letter = letter;
		}

		/**
		 * Check if cell's letter is a blank space
		 * 
		 * @return true if cell is empty, otherwise returns false
		 */
		boolean isEmpty() {
			return (this.letter == ' ');
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cell other = (Cell) obj;
			if (column != other.column)
				return false;
			if (letter != other.letter)
				return false;
			if (line != other.line)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Cell [line=" + line + ", column=" + column + ", letter="
					+ letter + "]";
		}

	}
}

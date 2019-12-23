package sk.tsystems.gamestudio.game.minesweeper.core;

import java.util.Random;

import sk.tsystems.gamestudio.game.minesweeper.core.Tile.State;

/**
 * Field represents playing field and game logic.
 */
public class Field {
	/**
	 * Playing field tiles.
	 */
	// mine a clue dedia od tiles
	private final Tile[][] tiles;

	/**
	 * Field row count. Rows are indexed from 0 to (rowCount - 1).
	 */
	private final int rowCount;

	/**
	 * Column count. Columns are indexed from 0 to (columnCount - 1).
	 */
	private final int columnCount;

	/**
	 * Mine count.
	 */
	private final int mineCount;

	private final int tilesCount;
	
	public int score;
	
	private long startMillis;
	
	/**
	 * Game state.
	 */
	private GameState state = GameState.PLAYING;

	/**
	 * Constructor.
	 *
	 * @param rowCount    row count
	 * @param columnCount column count
	 * @param mineCount   mine count
	 */

	public Field(int rowCount, int columnCount, int mineCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
		tiles = new Tile[rowCount][columnCount];
		tilesCount = columnCount * rowCount;
		// generate the field content
		generate();
	}

	/**
	 * o
	 * 
	 * Opens tile at specified indeces.
	 *
	 * @param row    row number
	 * @param column column number
	 */
	public void openTile(int row, int column) {
		Tile tile = tiles[row][column];
		if (tile.getState() == Tile.State.CLOSED) {
			tiles[row][column].setState(Tile.State.OPEN);
//           tile.setState(Tile.State.OPEN);
			if (tile instanceof Mine) {
				state = GameState.FAILED;
				return;
			}
				if (tile instanceof Clue) {
     		    if (((Clue) tile).getValue() == 0)
				openAdjacentTiles(row, column);
			}

			if (isSolved()) {
				state = GameState.SOLVED;

				return;
			}
		}
	}

	/**
	 * Marks tile at specified indeces.
	 *
	 * @param row    row number
	 * @param column column number
	 */
	public void markTile(int row, int column) {
		Tile tile = tiles[row][column];
		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.MARKED);
		}
		if (tile.getState() == Tile.State.MARKED) {
			tile.setState(Tile.State.CLOSED);
		}
		// throw new UnsupportedOperationException("Method markTile not yet
		// implemented");
	}

	/**
	 * Generates playing field.
	 */
	// pridana metoda - vygeneruje miny a dlazdice s cislicami
	private void generate() {
		Random rand = new Random();
		int pocetMin = 0;
		while (pocetMin < mineCount) {
			int random1 = rand.nextInt(rowCount);
			int random2 = rand.nextInt(columnCount);
			if (tiles[random1][random2] == null) {
				tiles[random1][random2] = new Mine();
				pocetMin++;
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (tiles[i][j] == null) {
					tiles[i][j] = new Clue(countAdjacentMines(i, j));

				}
			}
		}
	}

	// throw new UnsupportedOperationException("Method generate not yet
	// implemented");

	/**
	 * Returns true if game is solved, false otherwise.
	 *
	 * @return true if game is solved, false otherwise
	 */
	private boolean isSolved() {
		return (tilesCount - getNumberOf(State.OPEN) == mineCount);
	}

	private int getNumberOf(Tile.State state) {
		int counter = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (this.getTile(i, j).getState() == state)
					counter++;
			}
		}
		return counter;
	}

	/**
	 * Returns number of adjacent mines for a tile at specified position in the
	 * field.
	 *
	 * @param row    row number.
	 * @param column column number.
	 * @return number of adjacent mines.
	 */
	private int countAdjacentMines(int row, int column) {
		int count = 0;
		for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
			int actRow = row + rowOffset;
			if (actRow >= 0 && actRow < getRowCount()) {
				for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
					int actColumn = column + columnOffset;
					if (actColumn >= 0 && actColumn < getColumnCount()) {
						if (tiles[actRow][actColumn] instanceof Mine) {
							count++;
						}
					}
				}
			}
		}

		return count;
	}

	private void openAdjacentTiles(int row, int column) {
		for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
			int actRow = row + rowOffset;
			if (actRow >= 0 && actRow < getRowCount()) {
				for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
					int actColumn = column + columnOffset;
					if (actColumn >= 0 && actColumn < getColumnCount()) {
					
					if (tiles[actRow][actColumn] instanceof Clue
							&& tiles[actRow][actColumn].getState() == State.CLOSED) {
						tiles[actRow][actColumn].setState(State.OPEN);
						if (((Clue) tiles[actRow][actColumn]).getValue() == 0) {
							openAdjacentTiles(actRow, actColumn);
						}
					}
				}
				}
			}
		}
	}


//pridane getRowCount()
	public int getRowCount() {
		return rowCount;
	}

//pridane getColumnCount()
	public int getColumnCount() {
		return columnCount;
	}

//pridane getMineCount()
	public int getMineCount() {
		return mineCount;
	}

//pridane getState
	public GameState getState() {
		return state;
	}

//pridane getTile(int row, int column)

	public void setState(GameState state) {
		this.state = state;
	}

	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}
	
	public int getScore() {
        if (state == GameState.PLAYING) {
            int seconds = (int) ((System.currentTimeMillis() - startMillis) / 1000);
            score = rowCount * columnCount * 10;
            return score;
        }
        return score;
    }
}

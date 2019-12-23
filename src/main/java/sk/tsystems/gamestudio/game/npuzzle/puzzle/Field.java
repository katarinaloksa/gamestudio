package sk.tsystems.gamestudio.game.npuzzle.puzzle;
import java.util.Random;

public class Field {

	private final Tile[][] tiles;
	private int rowCount;
	private int columnCount;
	private final int fieldSize;
	private int rowEmpty;
	private int columnEmpty;

	public int getFieldSize() {
		return fieldSize;
	}

	public Field(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		tiles = new Tile[rowCount][columnCount];
		fieldSize = rowCount * columnCount;
		// generate the field content
		generate();
		shuffleField();
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}


	 private boolean isSolved() {
		 int count = 0;
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					count++;
					if (count==fieldSize) {
						
						return true;
					}
					if (tiles[i][j].getValue() != count) {
						return false;
					}	
				}
			}
			
		 return true;
	 }
	
	
	
	private void generate() {
		int count = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				count++;
				if (count == fieldSize) {
					tiles[i][j] = new Tile(0);
				}
				if (tiles[i][j] == null) {
					tiles[i][j] = new Tile(count);
				}
			}
		}
		rowEmpty = rowCount - 1;
		columnEmpty = columnCount - 1;
	}

	public boolean move(int tileNumber) {
		if(rowEmpty > 0 && tiles[rowEmpty - 1][columnEmpty].getValue() == tileNumber) {
			tiles[rowEmpty][columnEmpty] = tiles[rowEmpty - 1][columnEmpty];
			tiles[rowEmpty - 1][columnEmpty] = new Tile(0);
			rowEmpty = rowEmpty - 1;
			return true;
		}
		if(rowEmpty + 1 < rowCount && tiles[rowEmpty + 1][columnEmpty].getValue() == tileNumber) {
			tiles[rowEmpty][columnEmpty] = tiles[rowEmpty + 1][columnEmpty];
			tiles[rowEmpty + 1][columnEmpty] = new Tile(0);
			rowEmpty = rowEmpty + 1;
			return true;
		}
		if(columnEmpty > 0 && tiles[rowEmpty][columnEmpty-1].getValue() == tileNumber) {
			tiles[rowEmpty][columnEmpty] = tiles[rowEmpty][columnEmpty-1];
			tiles[rowEmpty][columnEmpty-1] = new Tile(0);
			columnEmpty = columnEmpty - 1;
			return true;
		}
		if(columnEmpty + 1 < rowCount && tiles[rowEmpty ][columnEmpty+ 1].getValue() == tileNumber) {
			tiles[rowEmpty][columnEmpty] = tiles[rowEmpty][columnEmpty+ 1];
			tiles[rowEmpty][columnEmpty+ 1] = new Tile(0);
			columnEmpty = columnEmpty + 1;
			return true;
		}
		
		return false;
	}
	
	public void shuffleField() {
		for (int i = 0; i < 1000; i++) {			
			Random random = new Random();
			int ranInt = random.nextInt(fieldSize);
			move(ranInt);
		}
	}

	public boolean isState() {
		return isSolved();
	}
	
	
}

package sk.tsystems.gamestudio.game.minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sk.tsystems.gamestudio.game.minesweeper.core.*;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile.State;
import sk.tsystems.gamestudio.game.minesweeper.*; 
//import minesweeper.core.Clue;
//import minesweeper.core.Field;
//import minesweeper.core.GameState;
//import minesweeper.core.Tile;
//import minesweeper.core.Tile.State;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	/** Playing field. */
	private Field field;

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Starts the game.
	 * 
	 * @param field field of mines and clues
	 */
	@Override
	public void newGameStarted(Field field) {
		this.field = field;
		do {
			update();
			if(field.getState() == GameState.SOLVED) {
				System.out.println("You won!!!");
				System.exit(0);
			
			}
			else if(field.getState() == GameState.FAILED) {
//				update();
				System.out.println("You failed!!!");
				System.exit(0);
				
			}
			processInput();
			//throw new UnsupportedOperationException("Resolve the game state - winning or loosing condition.");
		} while (true);
	}

	/**
	 * Updates user interface - prints the field.
	 */
	@Override
	public void update() {
		System.out.println(" ");
		System.out.print("   0  1  2  3  4  5  6  7  8\n");
		for (int i = 0; i < field.getRowCount(); i++) {
			System.out.printf("%c", 65 + i);
			System.out.print(" ");
			for (int j = 0; j < field.getColumnCount(); j++) {
				Tile tile = field.getTile(i, j);
				if (field.getTile(i, j).getState() == State.CLOSED) {
					System.out.print(" - ");
				} else if (tile.getState() == State.MARKED) {
					System.out.print(" ");
					System.out.printf("%c", 'M');
					System.out.print(" ");
				} else if (tile.getState() == State.OPEN) {
					if (tile instanceof Clue) {
						Clue clue = (Clue) tile;
						System.out.print(" " + clue.getValue() + " ");
					} else {
						System.out.print(" ");
						System.out.printf("%c", 'X');
						System.out.print(" ");
					}
				}

			}
			System.out.print("\n");

		}
	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */
	private void processInput() {
		System.out.print("Please enter your selection <X> EXIT, <MA1> MARK, <OB4> OPEN:");
		try {
			handleInput(readLine());
		} catch (WrongFormatException ex) {
			ex.getMessage();
		} 
	}
	private void handleInput(String input) throws WrongFormatException{
		String answer = input.trim().toUpperCase();
		Pattern pattern = Pattern.compile("(M|O)([A-I])([0-8])");
		Matcher matcher = pattern.matcher(answer);
		try {
			if (answer.equals("X")) {
				System.err.println("end game");
				System.exit(0);
				}
			if (matcher.matches()) {
				char c = matcher.group(2).charAt(0);
				int axisX = (int) c - 65;
				int axisY = Integer.parseInt(matcher.group(3));
				if (matcher.group(1).equals("M")) {					
					field.markTile(axisX, axisY);
				}
				if (matcher.group(1).equals("O")) {
	
					field.openTile(axisX, axisY);
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	}


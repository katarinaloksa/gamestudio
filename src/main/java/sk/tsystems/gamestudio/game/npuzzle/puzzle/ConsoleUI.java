package sk.tsystems.gamestudio.game.npuzzle.puzzle;

import java.util.Scanner;

public class ConsoleUI implements UserInterface {

	private Field field;

	// SCANNER
	private Scanner scanner = new Scanner(System.in);

	private String readLine() {
		return scanner.nextLine();
	}

	@Override
	public void newGameStarted(Field field) {
		this.field = field;

		
		do {

			update();
			processInput();
		} while (!field.isState());
		update();
		System.out.println("YOU WON!");
		
	}

	@Override
	public void update() {

		for (int i = 0; i < field.getRowCount(); i++) {
			System.out.println();
			for (int j = 0; j < field.getColumnCount(); j++) {

				if (field.getTile(i, j).getValue() == 0) {
					System.out.print("|    |");
					continue;
				}
				if (field.getTile(i, j).getValue() >= 10) {
					System.out.print("| " + field.getTile(i, j).getValue() + " |");
				} else {

					System.out.print("|  " + field.getTile(i, j).getValue() + " |");
				}
			}
		}
		System.out.println();

	}

	private void processInput() {

		System.out.print("Insert number of tile to move or (X) to exit:");

		String input = readLine();
		try {
			int number = Integer.parseInt(input);
			if (field.move(number)) {
				return;
			} else {
				System.err.println("wrong tile");
			}

		} catch (Exception e) {
			if (input.toLowerCase().equals("x")) {
				System.out.println("Game ended.");
				System.exit(0);
			} else {
				System.err.println("Wrong input!");
			}
		}

	}

}

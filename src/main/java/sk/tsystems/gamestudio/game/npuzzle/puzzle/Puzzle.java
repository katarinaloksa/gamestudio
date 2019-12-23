package sk.tsystems.gamestudio.game.npuzzle.puzzle;

public class Puzzle {
	private UserInterface userInterface;

	public Puzzle() {
		userInterface = new ConsoleUI();
		Field field = new Field(4, 4);
		userInterface.newGameStarted(field);
	}

	public static void main(String[] args) {

		new Puzzle();

	}

}

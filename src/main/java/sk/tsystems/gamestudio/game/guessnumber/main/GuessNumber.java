package sk.tsystems.gamestudio.game.guessnumber.main;

import java.util.Random;

import sk.tsystems.gamestudio.game.guessnumber.consoleUI.*;

public class GuessNumber {

	public static void main(String[] args) {
		Random random = new Random();
		int number = 0;
		int guessNumber = random.nextInt(100)+1;

		ConsoleUI consol = new ConsoleUI ();
		
		consol.game(number, guessNumber);
}
	
		
	}


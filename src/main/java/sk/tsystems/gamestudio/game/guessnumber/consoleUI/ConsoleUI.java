package sk.tsystems.gamestudio.game.guessnumber.consoleUI;

import java.util.Scanner;

public class ConsoleUI {

	Scanner scan = new Scanner(System.in);

	public void game(int number, int guessNumber) {
		System.out.println("Enter number from 0 to 100:");
		try {
			while (number != guessNumber) {
				number = Integer.parseInt(scan.nextLine());
				if (number > guessNumber) {
					System.out.println("Enter lower number!");
				}
				if (number < guessNumber) {
					System.out.println("Enter higher number!");
				}
			}
			System.out.println("You won!!!");
		} catch (NumberFormatException e) {
			System.err.println("Wrong input.");
		}
	}

}

package sk.tsystems.gamestudio.consoleUI;

import java.util.Scanner;

import sk.tsystems.gamestudio.game.guessnumber.main.GuessNumber;
import sk.tsystems.gamestudio.game.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.game.npuzzle.puzzle.Puzzle;

public class Menu {
	Scanner scan = new Scanner(System.in);

	public void printWelcome() {
		System.out.println("*************************");
		System.out.println("       WELCOME IN     ");
		System.out.println("      GAME  STUDIO     ");
		System.out.println("*************************");	
	}
	public void printMenu() {
		System.out.println("Select your game:");
		System.out.println("Press 1 for Minesweeper");
		System.out.println("Press 2 for Puzzle");
		System.out.println("Press 3 for Guess the number");
		System.out.println("Press 4 for exit");
		selectGame();
	}
	
	private int getInput() {
		int option = 0;
		while (option < 1 || option >4) {
		try {
		System.out.print("Insert number of game:");
		option = Integer.parseInt(scan.nextLine());
		}
		catch(NumberFormatException e){
		System.out.println("Wrong format!Please, try again.");
		}
		}
		return option;
		}

		private void selectGame () {	
		int option = Integer.parseInt(scan.nextLine());
		while (true) {
		switch (option) {
		case 1:
			new Minesweeper();
			break;
		case 2:
			new GuessNumber();
		case 3:
			new Puzzle();
		case 4:
		System.out.println("Bye bye!!!");
		System.exit(0);
		break;
		}
					}
		
			}
		
	}



package sk.tsystems.gamestudio;

import java.util.Scanner;

import sk.tsystems.gamestudio.consoleUI.Menu;
import sk.tsystems.gamestudio.entity.*;
import sk.tsystems.gamestudio.service.*;

public class Main {

	public static void main(String[] args) {
		//ScoreService scoreService = new ScoreServiceJDBC();
		// scoreService.addScore(new Score("janko", "npuzzle", 250));
		//for (Score score : scoreService.getTopScores("npuzzle"))
			//System.out.println(score.getUsername() + " " + score.getValue());
		var menu = new Menu();
		menu.printWelcome();
		menu.printMenu();

		
	}

}

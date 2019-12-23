package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.guessnumber.main.GuessNumber;
import sk.tsystems.gamestudio.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class GuessNumberController {
	private int number = 0;
	private int guessNumber;
	Random random = new Random();
	private String message;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private MainController mainController;

	@RequestMapping("/guessnumber")
	public String index() {
		guessNumber = random.nextInt(100) + 1;
		return "guessnumber";
	}

	@RequestMapping("/guessnumber/guess")
	public String enterNumber(String number) {
		try {
			int i = Integer.parseInt(number);
			this.number = i;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "guessnumber";
	}

//@RequestMapping("/guessnumber/guess")
//public String move(int tile) {
//	field.move(tile);
//	if(field.isState() && mainController.isLogged()) {
//		scoreService.addScore(new Score (mainController.getLoggedPlayer().getName(), "puzzle", 10));
//	} //field.getScore()
//	return "guessnumber";
//}

	public String getHtmlField() {
		Formatter f = new Formatter();

		f.format("<table>\n");
		f.format("<form action=\"/guessnumber/guess\">");
		f.format("<input type=\"number\" name=\"number\"><br>");
		f.format("<input type=\"submit\" value=\"Submit\">");
		f.format("</form>");
		f.format("</table>\n");
		return f.toString();

	}

	public String getMessage() {
		try {
			if (number > guessNumber) {
				return "Enter lower number!";
			}
			if (number < guessNumber) {
				return "Enter higher number!";
			}
			if (number == guessNumber) {
				return "You won!!!";
			}
		} catch (NumberFormatException e) {
			System.err.println("Wrong input.");
		}
		return message;
	}

	public boolean isSolved() {
		return (number == guessNumber);

	}
	
	public List<Score> getScores() {
		return scoreService.getTopScores("guessnumber");
	}


//public List<Score> getScore() {
//	return scoreService.getTopScores("guess");
//}
//
public Number getNumber() {
	return number;
}
}
//
//public void setNumber(Number number) {
//	this.number = number;
//}

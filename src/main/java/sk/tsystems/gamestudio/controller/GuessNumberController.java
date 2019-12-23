package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.guessnumber.main.GuessNumber;
import sk.tsystems.gamestudio.service.CommentService;
import sk.tsystems.gamestudio.service.RatingService;
import sk.tsystems.gamestudio.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class GuessNumberController {
	private int number = 0;
	private int guessNumber;
	Random random = new Random();
	private String message;
	private int score;
	private long startMillis;
	
	@Autowired
	private ScoreService scoreService;

	@Autowired
	private MainController mainController;

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private RatingService ratingService;
	
	@RequestMapping("/guessnumber")
	public String index() {
		guessNumber = random.nextInt(100) + 1;
		startMillis = System.currentTimeMillis();
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
	
	@RequestMapping("/guessnumber/comment")
	public String comment(String content) {
		if (mainController.isLogged())
			commentService.addComment(new Comment(mainController.getLoggedPlayer().getName(), "guessnumber", content));
		return "guessnumber";
	}

	@RequestMapping("/guessnumber/rate")
	public String rate(int rating) {
		if (mainController.isLogged())
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), "guessnumber", rating));
		return "guessnumber";
	}
	
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
				if (mainController.isLogged())
					scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), "guessnumber", getScore()));
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


	public Number getNumber() {
	return number;
}
	
	public List<Comment> getComments() {
		return commentService.getComments("guessnumber");

	}
	public double getAverageRating () {
		double rating = 0;
		try {
			rating = ratingService.getAverageRating("guessnumber");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return rating;
	}
	
	public int getPlayingTime() {
		return (int) (System.currentTimeMillis() - startMillis) / 1000;
	}

	public int getScore() {
		 score = 10000 - getPlayingTime();
		 return score;
	}
}


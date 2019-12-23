package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.minesweeper.core.Clue;
import sk.tsystems.gamestudio.game.minesweeper.core.Field;
import sk.tsystems.gamestudio.game.minesweeper.core.GameState;
import sk.tsystems.gamestudio.game.minesweeper.core.Mine;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile;
import sk.tsystems.gamestudio.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MinesweeperController {
	private Field field;

	private boolean marking;
	
	@Autowired
	ScoreService scoreService;
	
	@RequestMapping("/minesweeper")
	public String index() {
		field = new Field(9, 9, 10);
		return "minesweeper";
	}

	@RequestMapping("/minesweeper/action")
	public String action(int row, int column) {
		if (field.getState() == GameState.PLAYING)
			if (marking)
				field.markTile(row, column);
			else
				field.openTile(row, column);
		return "minesweeper";
	}

	@RequestMapping("/minesweeper/change")
	public String change() {
		marking = !marking;
		return "minesweeper";
	}

	public String getHtmlField() {
		Formatter f = new Formatter();

		f.format("<table>\n");
		for (int row = 0; row < field.getRowCount(); row++) {
			f.format("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {
				f.format("<td>\n");
				Tile tile = field.getTile(row, column);
				f.format(
						"<a href='/minesweeper/action?row=%d&column=%d'><img src='/images/minesweeper/mines/%s.png'/></a>",
						row, column, getImageName(tile));
				f.format("</td>\n");
			}

			f.format("</tr>\n");
		}
		f.format("</table>\n");

		return f.toString();

	}

	private String getImageName(Tile tile) {
		switch (tile.getState()) {
		case CLOSED:
			return "closed";
		case MARKED:
			return "marked";
		case OPEN:
			if (tile instanceof Clue)
				return "open" + ((Clue) tile).getValue();
			else
				return "mine";
		default:
			throw new IllegalArgumentException();
		}
	}

	public boolean isMarking() {
		return marking;
	}
	
	public List<Score> getScores() {
		return scoreService.getTopScores("minesweeper");
	}
	
	public boolean isSolved() {
		return field.getState() == GameState.SOLVED;
	}
}

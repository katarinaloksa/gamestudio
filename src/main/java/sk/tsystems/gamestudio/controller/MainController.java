package sk.tsystems.gamestudio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.service.PlayerService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MainController {
	private Player loggedPlayer;
	private String message;
	
	@Autowired
	private PlayerService playerService;
	
	@RequestMapping("/")
	public String index() {
		message = "";
		return "index";
	}
	
	@RequestMapping("/wronglogin")
	public String wrongLogin() {
		return "index";
	}
	@RequestMapping("/login")
	public String index(Player player) {
		try {
			if(playerService.getPlayerName(player.getName()).getPasswd().equals(player.getPasswd())) {
				loggedPlayer = player;
			}
			else {
				message = "Wrong username or password.";
				return "redirect:/wronglogin"; 
				}
		} catch (Exception e) {
			message = "Wrong username or password.";
			return "redirect:/wronglogin"; 
			// TODO Auto-generated catch block
			
		}
	return "redirect:/"; 
}
	@RequestMapping("/logout")
	public String logout() {
		loggedPlayer = null;
		return "redirect:/";
	}
	
	public boolean isLogged() {
		return loggedPlayer != null;
	}
	public Player getLoggedPlayer() {
		return loggedPlayer;
	}

	public String getMessage() {
		return message;
	}
}

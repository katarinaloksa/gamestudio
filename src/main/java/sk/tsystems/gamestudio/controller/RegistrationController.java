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
public class RegistrationController {
	private Player registratedPlayer;
	private String message;
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	MainController mainController;
	
	
	@RequestMapping("/registration")
	public String index() {
		message = "";
		return "registration";
	}
	
	@RequestMapping("/registration/shortname")
	public String shortName() {
		return "registration";
	}
	
	@RequestMapping("/registration/shortpasswd")
	public String shortPasswd() {
		return "registration";
	}
	
	@RequestMapping("/registration/unavailablename")
	public String unavailableName() {
		return "registration";
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping("/registration/register")
	public String registration(Player player) {	
		
		try {
			if(player.getName().length()<= 2) {
				message = "Username is too short.";
				return "redirect:/registration/shortname";
			}
			else if(player.getPasswd().length() <= 4) {
				message = "Password is too short.";
				return "redirect:/registration/shortpasswd";
			}
			else if(playerService.getPlayerName(player.getName()) != null) {  
				message = "This username is unavailable.";
				return "redirect:/registration/unavailablename";
			}
			else if(player.getName().trim().length()> 2 && player.getPasswd().length() > 4 && playerService.getPlayerName(player.getName()) == null) { 
				playerService.addUser(new Player(player.getName(), player.getPasswd()));
				mainController.index(player);}
		} catch (Exception e) {
		}
		
		return "redirect:/";
	}
	  
	public boolean isRegistered() {
		return registratedPlayer != null;
	}
	public Player getRegisteredPlayer() {
		return registratedPlayer;
	}

	
	public void setRegistratedPlayer(Player registratedPlayer) {
		this.registratedPlayer = registratedPlayer;
	}

	public String getMessage() {
		return message;
	}


}

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

	
	
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	MainController mainController;
	
	
	@RequestMapping("/registration")
	public String index() {
		return "registration";
	}
	
	@RequestMapping("/registration/register")
	public String registration(Player player) {
		if(player.getName().length()> 2 && player.getPasswd().length() > 4 && playerService.getPlayerName(player.getName()) == null) { 
			playerService.addUser(new Player(player.getName(), player.getPasswd()));
			mainController.index(player);}
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

}

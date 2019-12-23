package sk.tsystems.gamestudio.service;

import sk.tsystems.gamestudio.entity.Player;

public interface PlayerService {
	void addUser(Player player);
	Player getPlayerName (String name);
}

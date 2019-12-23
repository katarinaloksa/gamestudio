package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rating {
	
 @Id
 @GeneratedValue
 private int ident;	
 private String username;
 private int rating;
 private String game; 
 public Rating() {
	 
 }
 

 public Rating(String username, String game, int value) {
	 this.username = username;
	 this.game = game;
	 this.rating = value;
 }
 public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getGame() {
	return game;
}

public void setGame(String game) {
	this.game = game;
}

public int getRating() {
	return rating;
}

public void setRating(int value) {
	this.rating = value;
}


public int getIdent() {
	return ident;
}


public void setIdent(int ident) {
	this.ident = ident;
}

}


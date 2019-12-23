package sk.tsystems.gamestudio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Player {
	
@Id
@GeneratedValue	
private int ident;
private String name;
private String passwd;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Player(String name, String passwd) {
	super();
	this.name = name;
	this.passwd = passwd;
}

public Player() {
}

public String getPasswd() {
	return passwd;
}

public void setPasswd(String passwd) {
	this.passwd = passwd;
}

}

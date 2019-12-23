package sk.tsystems.gamestudio.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import sk.tsystems.gamestudio.entity.Player;



@Component
@Transactional
public class PlayerServiceJPA implements PlayerService {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void addUser(Player player) {
		entityManager.persist(player);
	}
	
	@Override
	public Player getPlayerName(String name){
		try {
			return (Player) entityManager.createQuery(
					"select p from Player p where p.name = :name").setParameter("name", name).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

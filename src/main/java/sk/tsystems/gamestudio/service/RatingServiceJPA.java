package sk.tsystems.gamestudio.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import sk.tsystems.gamestudio.entity.Rating;

@Component
@Transactional
public class RatingServiceJPA implements RatingService {
	@PersistenceContext
	private EntityManager entityManager;

	public void setRating(Rating rating) {
		try {
			Rating dbRating = (Rating) entityManager
					.createQuery("select r from Rating r where r.username = :username and r.game = :game")
					.setParameter("username", rating.getUsername()).setParameter("game", rating.getGame())
					.getSingleResult();
			dbRating.setRating(rating.getRating());
		} catch (NoResultException e) {
			entityManager.persist(rating);
		}
	}

	public double getAverageRating(String game) {
		Object rating;

		rating = entityManager.createQuery("select trunc(avg(r.rating), 1) from Rating r where r.game = :game")
				.setParameter("game", game).getSingleResult();

		return rating == null ? 0.0 : ((Double) rating).doubleValue();
	}
}

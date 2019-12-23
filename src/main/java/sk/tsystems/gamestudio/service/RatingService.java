package sk.tsystems.gamestudio.service;

import sk.tsystems.gamestudio.entity.Rating;

public interface RatingService {
	void setRating(Rating rating);
	
	double getAverageRating(String game);
}

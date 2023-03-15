package oovies.tools;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import oovies.dal.*;
import oovies.model.*;
import reviewapp.model.Reviews;



/**
 * main() runner, used for the app demo.
 */
public class Inserter {

	public static void main(String[] args) throws SQLException, ParseException {
		ReviewsDao reviewsDao = ReviewsDao.getInstance();
		RatingDao ratingDao = RatingDao.getInstance();
		
		// Reviews
		// Create reviews
		Date date = new Date();
		Reviews review1 = new Reviews(date, "Great movie", user1, movie1);
		review1 = reviewsDao.create(review1);
		Reviews review2 = new Reviews(date, "Superb!", user1, movie2);
		review2 = reviewsDao.create(review2);
		Reviews review3 = new Reviews(date, "Not good", user2, movie3);
		review3 = reviewsDao.create(review3);
		Reviews review4 = new Reviews(date, "Not good at all", user2, movie1);
		review4 = reviewsDao.create(review4);
		
		// Get review by reviewId
		Reviews reviewOne = reviewsDao.getReviewById(1);
		System.out.format("Reading reviewOne: reviewId:%d content:%s userId:%d movieId:%d\n", 
				reviewOne.getReviewId(), reviewOne.getContent(), 
				reviewOne.getUser().getUserId(),
				reviewOne.getMovie().gerMovieId());
		
		// Get review list by given UserId
		List<Reviews> rUserList = reviewsDao.getReviewsByUserId(1);
		for (Reviews r : rUserList) {
			System.out.format("Looping reviews created by UserId 1: reviewId:%d "
					+ "content:%s\n", r.getReviewId(), r.getContent());
			}
				
		List<Reviews> rMovieList = reviewsDao.getReviewsByMovieId(1);
			for (Reviews r : rMovieList) {
				System.out.format("Looping reviews for movie1: reviewId:%db"
						+ "content:%s userId:%d\n", r.getReviewId(), r.getContent(),
						r.getUser().getUserId());
			}
				
		System.out.println("Deleting review2 ...");
		Reviews review2Deleted = reviewsDao.delete(review2);
		if (review2Deleted == null) {
			System.out.println("review2 has been successfully deleted.");
		}
		
		// Rating
		// Create ratings
		Rating rating1 = new Rating(10.0, user1, movie1);
		rating1 = ratingDao.create(rating1);
		Rating rating2 = new Rating(9.5, user1, movie2);
		rating2 = ratingDao.create(rating2);
		Rating rating3 = new Rating(5.0, user2, movie3);
		rating3 = ratingDao.create(rating3);
		Rating rating4 = new Rating(1.0, user2, movie1);
		rating4 = ratingDao.create(rating4);
				
		// Get rating by ratingId
		Rating ratingOne = ratingDao.getRatingById(1);
		System.out.format("Reading ratingOne: ratingId:%d score:%d userId:%d movieId:%d\n", 
		ratingOne.getRatingId(), ratingOne.getScore(), 
		ratingOne.getUser().getUserId(),
		ratingOne.getMovie().gerMovieId());
				
		// Get rating list by given UserId
		List<Rating> rUserList = ratingDao.getRatingByUserId(1);
		for (Rating r : rUserList) {
		System.out.format("Looping ratings created by UserId 1: ratingId:%d "
				+ "score:%d movieId:%d\n", 
				r.getRatingId(), r.getScore(), r.getMovie().getMovieId());
		}
						
		List<Rating> rMovieList = ratingDao.getRatingByMovieId(1);
		for (Rating r : rMovieList) {
			System.out.format("Looping ratings for movie1: ratingId:%db"
				+ "score:%d userId:%d\n", r.getRatingId(), r.getScore(),
				r.getUser().getUserId());
		}
						
		System.out.println("Deleting rating2 ...");
		Rating rating2Deleted = ratingDao.delete(rating2);
		if (rating2Deleted == null) {
			System.out.println("rating2 has been successfully deleted.");
		}
	}
}

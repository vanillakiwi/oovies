package oovies.model;


/**
 * Rating is a simple, plain old java objects (POJO).
 */
public class Rating {
	protected int ratingId;
	protected double score;
	protected Person user;
	protected Movie movie;
	
	public Rating(int ratingId, double score, Person user, Movie movie) {
		this.ratingId = ratingId;
		this.score = score;
		this.user = user;
		this.movie = movie;
	}
	
	public Rating(int ratingId) {
		this.ratingId = ratingId;
	}
	
	public Rating(double score, Person user, Movie movie) {
		this.score = score;
		this.user = user;
		this.movie = movie;
	}

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Person getUser() {
		return user;
	}

	public void setUser(Person user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
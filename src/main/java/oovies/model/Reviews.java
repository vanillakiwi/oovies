package oovies.model;

import java.util.Date;

/**
 * Reviews is a simple, plain old java objects (POJO).
 */
public class Reviews {
	protected int reviewId;
	protected Date created;
	protected String content;
	protected Person user;
	protected Movie movie;
	
	public Reviews(int reviewId, Date created, String content, Person user, Movie movie) {
		this.reviewId = reviewId;
		this.created = created;
		this.content = content;
		this.user = user;
		this.movie = movie;
	}
	
	public Reviews(int reviewId) {
		this.reviewId = reviewId;
	}
	
	public Reviews(Date created, String content, Person user, Movie movie) {
		this.created = created;
		this.content = content;
		this.user = user;
		this.movie = movie;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
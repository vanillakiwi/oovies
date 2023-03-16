package oovies.model;

public class Casts {

	protected int castId;
	protected Movie movie;
	protected Actor actor;
	
	public Casts(int castId) {
		this.castId = castId;
	}
	
	public Casts(Movie movie, Actor actor ) {
		this.movie = movie;
		this.actor = actor;
	}
	
	public Casts(int castId, Movie movie, Actor actor ) {
		this.castId = castId;
		this.movie = movie;
		this.actor = actor;
	}
	
	
	
	
	/** Getters and setters. */

	public int getCastId() {
		return castId;
	}

	public void setCastId(int castId) {
		this.castId = castId;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	
}
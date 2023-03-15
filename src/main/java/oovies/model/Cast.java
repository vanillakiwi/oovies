package oovies.model;

public class Cast {

	protected int castId;
	protected Movie movie;
	protected Actor actor;
	
	public Cast(int castId) {
		this.castId = castId;
	}
	
	public Cast(Movie movie, Actor actor ) {
		this.movie = movie;
		this.actor = actor;
		
	}
	
	public Cast(int castId, Movie movie, Actor actor ) {
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

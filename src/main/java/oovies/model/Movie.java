package oovies.model;

import java.util.Date;


public class Movie {
	protected int movieId;
	protected String title;
	protected Date releaseDate;
	protected double rating;
	protected int duration;
	protected String summary;
	protected Director director;
	protected Studio studio;
	protected Genre genre;
	
	
	public enum Genre{
		DRAMA, COMEDY, ACTION, FANTASY, HORROR, ROMANCE, WESTERN, THRILLER, ANIMATION;
	}
	
	public Movie(int movieId) {
		this.movieId = movieId;
	}
	
	
	public Movie(String title, Date releaseDate, double rating, int duration,String summary, Director director, Studio studio, Genre genre) {
		this.title = title;
		this.releaseDate = releaseDate;
		this.rating = rating;
		this.duration = duration;
		this.summary = summary;
		this.director = director;
		this.studio = studio;
		this.genre = genre;
		
	}
	
	public Movie(int movieId, String title, Date releaseDate, double rating, int duration,String summary, Director director, Studio studio, Genre genre) {
		this.movieId = movieId;
		this.title = title;
		this.releaseDate = releaseDate;
		this.rating = rating;
		this.duration = duration;
		this.summary = summary;
		this.director = director;
		this.studio = studio;
		this.genre = genre;
		
	}
	
	
	

	/** Getters and setters. */


	public int getMovieId() {
		return movieId;
	}


	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Date getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}


	public Director getDirector() {
		return director;
	}


	public void setDirector(Director director) {
		this.director = director;
	}


	public Studio getStudio() {
		return studio;
	}


	public void setStudio(Studio studio) {
		this.studio = studio;
	}
	
	
	public Genre getGenre() {
		return genre;
	}


	public void setGenre(Genre genre) {
		this.genre = genre;
	}


}

package oovies.tools;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import oovies.dal.*;
import oovies.model.*;

public class inserter {

	public static void main(String[] args) throws SQLException {
		//DAO instance
		MovieDao movieDao = MovieDao.getInstance();
		CastDao castDao = CastDao.getInstance();
		
		//Movie
		//create movies
		Date date = new Date();
		Movie movie1 = new Movie ("Mulan",date, 4.0, 300, "Mulan is a 2020 American fantasy action drama film produced by Walt Disney Pictures", director1, studio1, Movie.Genre.ACTION);
		movie1 = movieDao.create(movie1);
		Movie movie2 = new Movie ("Call me by your name",date, 5.0, 300, "romance blossoms between a seventeen-year-old student and the older man", director2, studio2, Movie.Genre.DRAMA);
		movie2 = movieDao.create(movie2);
		Movie movie3 = new Movie ("Die hard",date, 1.0, 132, "a 1988 American action film directed by John McTiernan", director3, studio1, Movie.Genre.ACTION);
		movie3 = movieDao.create(movie3);
		
		
		//get movie by movieId
		Movie movieOne = movieDao.getMovieById(1);
		System.out.format("Reading movieOne: movieId:%d title:%s releaseDate:%tx rating:%f durating:%d summary:%s directorId:%d studioId:%d genre:%s \n", 
				movieOne.getMovieId(), movieOne.getTitle(), movieOne.getReleaseDate(), movieOne.getRating(), movieOne.getDuration(), movieOne.getSummary(), 
				movieOne.getDirector().getDirectorId(),
				movieOne.getStudio().gerStudioId(),
				movieOne.getGenre().name());
		
		
		
		// Get movie list by given directorId
		List<Movie> movieDirectorList = movieDao.getMovieByDirectorId(1);
		for (Movie m : movieDirectorList) {
					System.out.format("Looping movies directed by DirectorId 1: movieId:%d title:%s directorId:%d studioId:%d\n", 
					m.getMovieId(), m.getTitle(),
					movieOne.getDirector().getDirectorId(),
					movieOne.getStudio().gerStudioId());
		}
				
				
		// Get movie list by given studioId
		List<Movie> movieStudioList = movieDao.getMovieByStudioId(1);
		for (Movie m : movieStudioList) {
					System.out.format("Looping movies by StudioId 1: movieId:%d title:%s directorId:%d studioId:%d\n", 
					m.getMovieId(), m.getTitle(),
					movieOne.getDirector().getDirectorId(),
					movieOne.getStudio().gerStudioId());
		}
				
		// Delete movie
		System.out.println("Deleting movie2 ...");
		Movie movie2Deleted = movieDao.delete(movie2);
		if (movie2Deleted == null) {
			System.out.println("movie2 has been successfully deleted.");
		}
		
		//Update
		System.out.format("Before updadte movie summary, summary:%s\n", movie1.getSummary());
		Movie movie4 = movieDao.updateSummary(movie1, "nothing to tell");
		System.out.format("After updadte movie summary, summary:%s\n", movie4.getSummary());

		
		//Cast
		//Create cast
		Cast cast1 = new Cast(actor1, movie1);
		Cast cast2 = new Cast(actor1, movie2);
		Cast cast3 = new Cast(actor1, movie3);
		Cast cast4 = new Cast(actor2, movie1);
		
		
		//get cast by castId
		Cast castOne = castDao.getCastById(1);
		System.out.format("Reading castOne: castId:%d movieTitle:%s, actorName:%s \n",
				castOne.getCastId(), castOne.getMovie().getTitle(), castOne.getActor().getName());
		
		// Get cast list by given movieId
		List<Cast> castMovieList = castDao.getCastByMovieId(1);
		for (Cast c : castMovieList) {
			System.out.format("Looping cast by MovieId 1: castId:%d title:%s actorName:%s\n", 
					c.getCastId(), c.getMovie().getTitle(),c.getActor().getName());
		}
		
		// Get cast list by given movieId
		List<Cast> castActorList = castDao.getCastByActorId(1);
		for (Cast c : castActorList) {
			System.out.format("Looping cast by ActorId 1: castId:%d title:%s actorName:%s\n", 
				c.getCastId(), c.getMovie().getTitle(),c.getActor().getName());
		}
		
		// Delete cast
		System.out.println("Deleting cast2 ...");
		Cast cast2Deleted = castDao.delete(cast2);
		if (cast2Deleted == null) {
			System.out.println("cast2 has been successfully deleted.");
		}
		
	}
}

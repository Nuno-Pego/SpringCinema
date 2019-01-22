package io.altar.CinemaTicketSystem.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import io.altar.CinemaTicketSystem.ModelsDTO.MovieDTO;

@Entity
@NamedQueries({ @NamedQuery(name = Movie.GET_ALL_MOVIES_QUERY_NAME, query = "SELECT m FROM Movie m"),
		@NamedQuery(name = Movie.DELETE_ALL_MOVIES_QUERY_NAME, query = "DELETE FROM Movie") })
// ________________________________________________________________________________________________

public class Movie extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	// Attributes
	private String title;
	private String image;
	private int minimumAge;
	private int duration; // Minutes
	private Date releaseDate;
	private Date endDate;
	private String director;
	private String cast;
	@Lob
	private String synopsis;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "movie")
	private List<Room> rooms = new ArrayList<Room>();

	// ________________________________________________________________________________________________

	// NamedQuerys
	public static final String GET_ALL_MOVIES_QUERY_NAME = "getAllMovies";
	public static final String DELETE_ALL_MOVIES_QUERY_NAME = "deleteAllMovies";

	// ________________________________________________________________________________________________

	// Constructor
	public Movie() {
	}

	// ________________________________________________________________________________________________

	// Get and Setters:	
	public String getTitle() {
		return title;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getMinimumAge() {
		return minimumAge;
	}

	public void setMinimumAge(int minimumAge) {
		this.minimumAge = minimumAge;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	// ________________________________________________________________________________________________
	
	// Extra Methods
	
	public MovieDTO turnToDTO(Movie movie) {

		return new MovieDTO(movie.getId(),movie.getTitle(),movie.getImage(),movie.getMinimumAge(),movie.getDuration(),movie.getReleaseDate(),movie.getEndDate(),movie.getDirector(),movie.getCast(),movie.getSynopsis());
		}
}

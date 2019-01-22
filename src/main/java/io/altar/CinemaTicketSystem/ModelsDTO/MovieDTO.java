package io.altar.CinemaTicketSystem.ModelsDTO;
import java.util.Date;

import io.altar.CinemaTicketSystem.Models.BaseEntity;

public class MovieDTO extends BaseEntity{
	
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
	private String synopsis;

	// ________________________________________________________________________________________________
	
	// Constructor	
	public MovieDTO(long id, String title, String image, int minimumAge, int duration, Date releaseDate, Date endDate,
			String director, String cast, String synopsis) {
		super();
		this.setId(id);
		this.title = title;
		this.image = image;
		this.minimumAge = minimumAge;
		this.duration = duration;
		this.releaseDate = releaseDate;
		this.endDate = endDate;
		this.director = director;
		this.cast = cast;
		this.synopsis = synopsis;
	}
	// ________________________________________________________________________________________________
	
	// Get and setters	
	public String getTitle() {
		return title;
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

}

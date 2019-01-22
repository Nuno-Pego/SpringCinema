package io.altar.CinemaTicketSystem.ModelsDTO;

import io.altar.CinemaTicketSystem.Models.BaseEntity;

public class RoomDTO extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// Attributes
	private CinemaDTO cinemaDTO;
	private MovieDTO movieDTO;
	private int numberOfQueues;
	private int numberOfSeatsPerQueue;
	private int totalSeats;

	
	// ________________________________________________________________________________________________

	// Constructor
	public RoomDTO(long id, CinemaDTO cinemaDTO, MovieDTO movieDTO, int numberOfQueues, int numberOfSeatsPerQueue, int totalSeats) {
		super();
		this.setId(id);
		this.cinemaDTO = cinemaDTO;
		this.movieDTO = movieDTO;
		this.numberOfQueues = numberOfQueues;
		this.numberOfSeatsPerQueue = numberOfSeatsPerQueue;
		this.totalSeats=totalSeats;
	}
	// ________________________________________________________________________________________________

	// Get and setters
	public CinemaDTO getCinema() {
		return cinemaDTO;
	}

	public void setCinema(CinemaDTO cinemaDTO) {
		this.cinemaDTO = cinemaDTO;
	}


	public MovieDTO getMovie() {
		return movieDTO;
	}

	public void setMovie(MovieDTO movie) {
		this.movieDTO = movie;
	}

	public int getNumberOfQueues() {
		return numberOfQueues;
	}

	public void setNumberOfQueues(int numberOfQueues) {
		this.numberOfQueues = numberOfQueues;
	}

	public int getNumberOfSeatsPerQueue() {
		return numberOfSeatsPerQueue;
	}

	public void setNumberOfSeatsPerQueue(int numberOfSeatsPerQueue) {
		this.numberOfSeatsPerQueue = numberOfSeatsPerQueue;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	

}

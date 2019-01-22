package io.altar.CinemaTicketSystem.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import io.altar.CinemaTicketSystem.ModelsDTO.RoomDTO;

@Entity
@NamedQueries({ @NamedQuery(name = Room.GET_ALL_ROOMS_QUERY_NAME, query = "SELECT r FROM Room r"),
		@NamedQuery(name = Room.DELETE_ALL_ROOMS_QUERY_NAME, query = "DELETE FROM Room") })
// ________________________________________________________________________________________________

public class Room extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// Attributes:
	@ManyToOne(cascade = CascadeType.ALL)
	private Movie movie;
	@ManyToOne(cascade = CascadeType.ALL)
	private Cinema cinema;
	private int numberOfQueues;
	private int numberOfSeatsPerQueue;
	private int totalSeats;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "room", cascade = CascadeType.ALL)
	List<ExibitionDay> exibitionDays = new ArrayList<ExibitionDay>();

	// ________________________________________________________________________________________________

	// NamedQuerys
	public static final String GET_ALL_ROOMS_QUERY_NAME = "getAllRooms";
	public static final String DELETE_ALL_ROOMS_QUERY_NAME = "deleteAllRooms";

	// ________________________________________________________________________________________________

	// Constructor
	public Room() {
	}

	// ________________________________________________________________________________________________

	// Gets and Setters:
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
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

	public List<ExibitionDay> getExibitionDays() {
		return exibitionDays;
	}

	public void setExibitionDays(List<ExibitionDay> exibitionDays) {
		this.exibitionDays = exibitionDays;
	}

	// ________________________________________________________________________________________________
	// Extra Methods	

	public void setTotalSeats() {
		this.totalSeats=this.numberOfQueues*this.numberOfSeatsPerQueue;
	}

	public void createExibionDays(Room room) {

		long startDay = room.getMovie().getReleaseDate().getTime();
		long endDay = room.getMovie().getEndDate().getTime();

		for (long i = startDay; i <= endDay; i=i+(1000*60*60*24)) {

			Date date = new Date(i);

			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int dayOfWeekIndex = cal.get(Calendar.DAY_OF_WEEK);

			String dayOfWeek = room.getDayOfWeek(dayOfWeekIndex);

			ExibitionDay exibitionDay = new ExibitionDay(room, day, month, year, dayOfWeek);
			exibitionDays.add(exibitionDay);
		}

	}

	public String getDayOfWeek(int dayOfWeek) {

		if (dayOfWeek == 1) {
			return "Sunday";
		}
		if (dayOfWeek == 2) {
			return "Monday";
		}
		if (dayOfWeek == 3) {
			return "Tuesday";
		}
		if (dayOfWeek == 4) {
			return "Wednesday";
		}
		if (dayOfWeek == 5) {
			return "Thusday";
		}
		if (dayOfWeek == 6) {
			return "Friday";
		}
		if (dayOfWeek == 7) {
			return "Saturday";
		} else {
			return "Undefned DayOfWeek";
		}
	}

	public RoomDTO turnToDTO(Room room) {
		
		if(room.getMovie()!=null) {
			return new RoomDTO(room.getId(), room.getCinema().turnToDTO(room.getCinema()),
				room.getMovie().turnToDTO(room.getMovie()),room.getNumberOfQueues(),room.getNumberOfSeatsPerQueue(),room.getTotalSeats());
			}
		else {
			return new RoomDTO(room.getId(), room.getCinema().turnToDTO(room.getCinema()),
					null,room.getNumberOfQueues(),room.getNumberOfSeatsPerQueue(),room.getTotalSeats());
			}
		}
}

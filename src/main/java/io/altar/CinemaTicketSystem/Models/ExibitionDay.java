package io.altar.CinemaTicketSystem.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import io.altar.CinemaTicketSystem.ModelsDTO.ExibitionDayDTO;

@Entity
@NamedQueries({
		@NamedQuery(name = ExibitionDay.GET_ALL_EXIBITIONDAYS_QUERY_NAME, query = "SELECT e FROM ExibitionDay e"),
		@NamedQuery(name = ExibitionDay.DELETE_ALL_EXIBITIONDAYS_QUERY_NAME, query = "DELETE FROM ExibitionDay") })
// ________________________________________________________________________________________________

public class ExibitionDay extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// Attributes
	@ManyToOne(cascade = CascadeType.MERGE)
	private Room room;
	private int day;
	private int month;
	private int year;
	private String dayOfWeek;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "exibitionDay", cascade = CascadeType.ALL)
	List<Schedule> schedules = new ArrayList<Schedule>();

	// ________________________________________________________________________________________________

	// NamedQuerys
	public static final String GET_ALL_EXIBITIONDAYS_QUERY_NAME = "getAllExibitionDays";
	public static final String DELETE_ALL_EXIBITIONDAYS_QUERY_NAME = "deleteExibitionDays";

	// ________________________________________________________________________________________________

	// Constructor
	public ExibitionDay() {
	}
	
	public ExibitionDay(Room room, int day, int month, int year, String dayOfWeek) {
		super();
		this.room = room;
		this.day = day;
		this.month = month;
		this.year = year;
		this.dayOfWeek = dayOfWeek;
		this.createSchedule(this.getRoom().getCinema(), this.getRoom().getMovie());
	}

	// ________________________________________________________________________________________________

	// Gets and Setters:
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}	

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	// ________________________________________________________________________________________________

	// Extra Methods
	
	public void createSchedule(Cinema cinema, Movie movie) {
		int openTime;
		int numberOfSessions;
		int sessionBegin = cinema.getTimeOpen();
		int sessionEnd = cinema.getTimeOpen() + movie.getDuration() + cinema.getPause();

		if (cinema.getTimeClose() < cinema.getTimeOpen()) {
			openTime = ((24 * 60) - cinema.getTimeOpen()) + cinema.getTimeClose();
		} else {
			openTime = cinema.getTimeClose() - cinema.getTimeOpen();
		}

		numberOfSessions = (int) (openTime / (movie.getDuration() + cinema.getPause()));

		for (int i = 1; i <= numberOfSessions; i++) {
			if (sessionBegin >= 24 * 60) {
				sessionBegin = sessionBegin - (24 * 60);
			}
			if (sessionEnd >= 24 * 60) {
				sessionEnd = sessionEnd - (24 * 60);
			}

			Schedule newSchedule = new Schedule(sessionBegin, sessionEnd, this, this.room.getTotalSeats());
			schedules.add(newSchedule);
			sessionBegin = sessionEnd;
			sessionEnd = sessionEnd + (movie.getDuration() + cinema.getPause());
		}

	}

	public ExibitionDayDTO turnToDTO(ExibitionDay exibitionDay) {
		return new ExibitionDayDTO(exibitionDay.getId(),exibitionDay.getRoom().turnToDTO(exibitionDay.getRoom()),exibitionDay.getDay(), exibitionDay.getMonth(),exibitionDay.getYear(),exibitionDay.getDayOfWeek());
	}
}

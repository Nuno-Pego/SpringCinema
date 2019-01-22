package io.altar.CinemaTicketSystem.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import io.altar.CinemaTicketSystem.ModelsDTO.CinemaDTO;

@Entity
@NamedQueries({ @NamedQuery(name = Cinema.GET_ALL_CINEMAS_QUERY_NAME, query = "SELECT c FROM Cinema c"),
		@NamedQuery(name = Cinema.DELETE_ALL_CINEMAS_QUERY_NAME, query = "DELETE FROM Cinema") })
//________________________________________________________________________________________________

public class Cinema extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// Attributes
	private String name;
	private int timeOpen; // Minutes
	private int timeClose; // Minutes
	private int pause; // Minutes	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cinema")
	private List<Room> rooms = new ArrayList<Room>();	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cinema")
	private List<TypeOfTicket> typeOfTicket = new ArrayList<TypeOfTicket>();

	// ________________________________________________________________________________________________
	
	// NamedQuerys
	public static final String GET_ALL_CINEMAS_QUERY_NAME = "getAllCinemas";
	public static final String DELETE_ALL_CINEMAS_QUERY_NAME = "deleteAllCinemas";

	// ________________________________________________________________________________________________

	// Constructor
	public Cinema() {
	}

	// ________________________________________________________________________________________________

	// Get and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTimeOpen() {
		return timeOpen;
	}

	public void setTimeOpen(int timeOpen) {
		this.timeOpen = timeOpen;
	}

	public int getTimeClose() {
		return timeClose;
	}

	public void setTimeClose(int timeClose) {
		this.timeClose = timeClose;
	}

	public int getPause() {
		return pause;
	}

	public void setPause(int pause) {
		this.pause = pause;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public List<TypeOfTicket> getTypeOfTicket() {
		return typeOfTicket;
	}
	
	public void setTypeOfTicket(List<TypeOfTicket> typeOfTicket) {
		this.typeOfTicket = typeOfTicket;
	}
	
	// ________________________________________________________________________________________________

	// Extra Methods
	
	public void addTypeOfTicket(TypeOfTicket typeOfTicket) {
		this.typeOfTicket.add(typeOfTicket);
	}
	
	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	
	public CinemaDTO turnToDTO(Cinema cinema) {
		return new CinemaDTO(cinema.getId(),cinema.getName(),cinema.getTimeOpen(),cinema.getTimeClose(),cinema.getPause());
	}
}

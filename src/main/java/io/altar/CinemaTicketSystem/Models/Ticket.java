package io.altar.CinemaTicketSystem.Models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import io.altar.CinemaTicketSystem.ModelsDTO.TicketDTO;

@Entity
@NamedQueries({ @NamedQuery(name = Ticket.GET_ALL_TICKETS_QUERY_NAME, query = "SELECT t FROM Ticket t"),
		@NamedQuery(name = Ticket.DELETE_ALL_TICKETS_QUERY_NAME, query = "DELETE FROM Ticket") })

// ________________________________________________________________________________________________

public class Ticket extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	// Attributes
	@ManyToOne(cascade = CascadeType.ALL)
	private Room room;
	@ManyToOne(cascade = CascadeType.ALL)
	private TypeOfTicket typeOfTicket;

	// ________________________________________________________________________________________________

	// Named Query:
	public static final String GET_ALL_TICKETS_QUERY_NAME = "getAllTickets";
	public static final String DELETE_ALL_TICKETS_QUERY_NAME = "deleteAllTickets";

	// ________________________________________________________________________________________________

	// Constructor
	public Ticket() {
	}
	
	public Ticket(Room room, TypeOfTicket typeOfTicket) {
		super();
		this.room = room;
		this.typeOfTicket = typeOfTicket;
	}

	// ________________________________________________________________________________________________


	// Gets and Setters:
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public TypeOfTicket getTypeOfTicket() {
		return typeOfTicket;
	}

	public void setTypeOfTicket(TypeOfTicket typeOfTicket) {
		this.typeOfTicket = typeOfTicket;
	}

	// ________________________________________________________________________________________________

	// Extra Methods	
	
	public TicketDTO turnToDTO(Ticket ticket) {
		return new TicketDTO(ticket.getId(), ticket.getRoom().turnToDTO(ticket.getRoom()),ticket.getTypeOfTicket().turnToDTO(ticket.getTypeOfTicket()));
	}
}
package io.altar.CinemaTicketSystem.Models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import io.altar.CinemaTicketSystem.ModelsDTO.TypeOfTicketDTO;

@Entity
@NamedQueries({
		@NamedQuery(name = TypeOfTicket.GET_ALL_TYPEOFTICKETS_QUERY_NAME, query = "SELECT tt FROM TypeOfTicket tt"),
		@NamedQuery(name = TypeOfTicket.DELETE_ALL_TYPEOFTICKETS_QUERY_NAME, query = "DELETE FROM TypeOfTicket") })
// ________________________________________________________________________________________________

public class TypeOfTicket extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	// Attributes
	private String typeOfTicket;
	private double priceOfTicket;
	@ManyToOne(cascade = CascadeType.ALL)
	private Cinema cinema;	
	
	// ________________________________________________________________________________________________

	// NamedQuerys
	public static final String GET_ALL_TYPEOFTICKETS_QUERY_NAME = "getAllTypeOfTickets";
	public static final String DELETE_ALL_TYPEOFTICKETS_QUERY_NAME = "deleteAllTypeOfTickets";

	// ________________________________________________________________________________________________

	// Constructor
	public TypeOfTicket() {
	}
	
	// ________________________________________________________________________________________________

	// Gets and Setters:
	public String getTypeOfTicket() {
		return typeOfTicket;
	}

	public void setTypeOfTicket(String typeOfTicket) {
		this.typeOfTicket = typeOfTicket;
	}

	public double getPriceOfTicket() {
		return priceOfTicket;
	}

	public void setPriceOfTicket(double priceOfTicket) {
		this.priceOfTicket = priceOfTicket;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	// Extra Methods	
	
		public TypeOfTicketDTO turnToDTO(TypeOfTicket typeOfTicket) {
			return new TypeOfTicketDTO(typeOfTicket.getId(), typeOfTicket.getTypeOfTicket(),typeOfTicket.getPriceOfTicket(),typeOfTicket.getCinema().turnToDTO(typeOfTicket.getCinema()));
		}
}

package io.altar.CinemaTicketSystem.ModelsDTO;

import io.altar.CinemaTicketSystem.Models.BaseEntity;


public class TypeOfTicketDTO extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	// Attributes
	private String typeOfTicket;
	private double priceOfTicket;
	private CinemaDTO cinemaDto;
	
	// ________________________________________________________________________________________________

	// Constructor
	
	public TypeOfTicketDTO(long id, String typeOfTicket, double priceOfTicket, CinemaDTO cinemaDto) {
		super();
		this.setId(id);
		this.typeOfTicket = typeOfTicket;
		this.priceOfTicket = priceOfTicket;
		this.cinemaDto = cinemaDto;
	}

	// ________________________________________________________________________________________________
	
	// Get and Setters:		
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

	public CinemaDTO getCinemaDto() {
		return cinemaDto;
	}

	public void setCinemaDto(CinemaDTO cinemaDto) {
		this.cinemaDto = cinemaDto;
	}	

}

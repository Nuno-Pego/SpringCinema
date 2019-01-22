package io.altar.CinemaTicketSystem.ModelsDTO;

import io.altar.CinemaTicketSystem.Models.BaseEntity;

public class TicketDTO extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	// Attributes	
	private RoomDTO roomDTO;	
	private TypeOfTicketDTO typeOfTicketDto;	
	
	// ________________________________________________________________________________________________

	// Constructor		
	public TicketDTO(long id, RoomDTO roomDTO, TypeOfTicketDTO typeOfTicketDto) {
		super();
		this.setId(id);
		this.roomDTO = roomDTO;
		this.typeOfTicketDto = typeOfTicketDto;
	}
	
	// ________________________________________________________________________________________________
	
	// Get and Setters:
	public RoomDTO getRoomDTO() {
		return roomDTO;
	}


	public void setRoomDTO(RoomDTO roomDTO) {
		this.roomDTO = roomDTO;
	}


	public TypeOfTicketDTO getTypeOfTicket() {
		return typeOfTicketDto;
	}


	public void setTypeOfTicket(TypeOfTicketDTO typeOfTicketDto) {
		this.typeOfTicketDto = typeOfTicketDto;
	}	
}

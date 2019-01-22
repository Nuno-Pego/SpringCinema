package io.altar.CinemaTicketSystem.ModelsDTO;

import io.altar.CinemaTicketSystem.Models.BaseEntity;

public class ScheduleDTO extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// Attributes
	private int sessionBegin; // Minutes
	private int sessionEnd; // Minutes
	private int availableSeats;
	private ExibitionDayDTO exibitionDayDTO;

	// ________________________________________________________________________________________________

	// Constructor
	public ScheduleDTO(long id, int sessionBegin, int sessionEnd, int availableSeats, ExibitionDayDTO exibitionDayDTO) {
		super();
		this.setId(id);
		this.sessionBegin = sessionBegin;
		this.sessionEnd = sessionEnd;
		this.availableSeats = availableSeats;
		this.exibitionDayDTO = exibitionDayDTO;
	}
	// ________________________________________________________________________________________________

	// Get and Setters
	public int getSessionBegin() {
		return sessionBegin;
	}

	public void setSessionBegin(int sessionBegin) {
		this.sessionBegin = sessionBegin;
	}

	public int getSessionEnd() {
		return sessionEnd;
	}

	public void setSessionEnd(int sessionEnd) {
		this.sessionEnd = sessionEnd;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public ExibitionDayDTO getExibitionDayDTO() {
		return exibitionDayDTO;
	}

	public void setExibitionDayDTO(ExibitionDayDTO exibitionDayDTO) {
		this.exibitionDayDTO = exibitionDayDTO;
	}

}

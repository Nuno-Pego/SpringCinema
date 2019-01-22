package io.altar.CinemaTicketSystem.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import io.altar.CinemaTicketSystem.ModelsDTO.ScheduleDTO;

@Entity
@NamedQueries({ @NamedQuery(name = Schedule.GET_ALL_SCHEDULES_QUERY_NAME, query = "SELECT s FROM Schedule s"),
		@NamedQuery(name = Schedule.DELETE_ALL_SCHEDULES_QUERY_NAME, query = "DELETE FROM Schedule") })
// ________________________________________________________________________________________________

public class Schedule extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	//Attributes
	@ManyToOne(cascade = CascadeType.ALL)
	private ExibitionDay exibitionDay;
	private int sessionBegin; // Minutes
	private int sessionEnd; // Minutes
	private int availableSeats;
	@ElementCollection
	private List<Boolean> structure = new ArrayList<Boolean>();	
	
	// ________________________________________________________________________________________________

	// NamedQuerys
	public static final String GET_ALL_SCHEDULES_QUERY_NAME = "getAllSchedules";
	public static final String DELETE_ALL_SCHEDULES_QUERY_NAME = "deleteAllSchedules";

	// ________________________________________________________________________________________________

	// Constructor
	public Schedule() {
	}

	public Schedule( int sessionBegin, int sessionEnd,ExibitionDay exibitionDay, int availableSeats) {
		super();
		this.exibitionDay = exibitionDay;
		this.sessionBegin = sessionBegin;
		this.sessionEnd = sessionEnd;
		this.availableSeats = availableSeats;
		this.setStructure();
	}

	// ________________________________________________________________________________________________

	// Get and Setters
	public int getSessionBegin() {
		return sessionBegin;
	}

	public void setSessionBegin(int sessionBegin) {
		this.sessionBegin = sessionBegin;
	}

	public int getsessionEnd() {
		return sessionEnd;
	}

	public void setsessionEnd(int sessionEnd) {
		this.sessionEnd = sessionEnd;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = this.getExibitionDay().getRoom().getTotalSeats();
	}
	
	public ExibitionDay getExibitionDay() {
		return exibitionDay;
	}

	public void setExibitionDay(ExibitionDay exibitionDay) {
		this.exibitionDay = exibitionDay;
	}

	public List<Boolean> getStructure() {
		return structure;
	}

	public void setStructure(List<Boolean> structure) {
		this.structure = structure;
	}
		
	// ________________________________________________________________________________________________	
	
	
	//Extra Methods
	public void takeASeat() {
		this.availableSeats--;
	}
	
	public void setStructure() {
		for (int i = 0; i < this.exibitionDay.getRoom().getNumberOfQueues(); i++) {
			for (int j = 0; j< this.exibitionDay.getRoom().getNumberOfSeatsPerQueue(); j++) {
				this.structure.add(false);
			}
		}
	}	
	
		
	public ScheduleDTO turnToDTO(Schedule schedule) {
		
		return new ScheduleDTO(schedule.getId(),schedule.getSessionBegin(),schedule.getsessionEnd(),schedule.getAvailableSeats(),schedule.getExibitionDay().turnToDTO(schedule.getExibitionDay()));
	}
	
}

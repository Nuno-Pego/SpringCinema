package io.altar.CinemaTicketSystem.Business;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import io.altar.CinemaTicketSystem.Models.Ticket;
import io.altar.CinemaTicketSystem.ModelsDTO.TicketDTO;
import io.altar.CinemaTicketSystem.Repositories.TicketsRepository;

@Component
public class TicketBusiness {
	
	@Inject
	protected TicketsRepository ticketsRepository;

	@Transactional
	public void delete(long id) {
		ticketsRepository.removeByID(id);
	}

	@Transactional
	public Ticket update(Ticket ticket) {
		return ticketsRepository.update(ticket);
	}

	@Transactional
	public TicketDTO create(Ticket ticket) {
		Ticket ticketCreated = ticketsRepository.save(ticket);
		ticketCreated.getTypeOfTicket().setCinema(ticketCreated.getRoom().getCinema());
		return ticketCreated.turnToDTO(ticketCreated);
	}


	public List<TicketDTO> getAll() {
		
		List<Ticket> tickets = ticketsRepository.getAll();
		List<TicketDTO> ticketsDTO = new ArrayList<TicketDTO>();
		
		for (Ticket ticket: tickets) {
			ticketsDTO.add(ticket.turnToDTO(ticket));
		}
		return ticketsDTO;
	}

	public TicketDTO findById(long id) {
		return ticketsRepository.getById(id).turnToDTO(ticketsRepository.getById(id));
	}

}

package io.altar.CinemaTicketSystem.Repositories;

import io.altar.CinemaTicketSystem.Models.Ticket;
import org.springframework.stereotype.Repository;
@Repository
public class TicketsRepository extends EntityRepository<Ticket> {

	private static final TicketsRepository INSTANCE = new TicketsRepository();

	public static TicketsRepository getInstance() {
		return INSTANCE;
	}

	public TicketsRepository() {
	}
	
	protected Class<Ticket> getEntityClass() {
		return Ticket.class;
	}

	protected String getAllEntityQueryName() {
		return Ticket.GET_ALL_TICKETS_QUERY_NAME;
	}

	protected String deleteAllEntityQueryName() {
		return Ticket.DELETE_ALL_TICKETS_QUERY_NAME;
	}
}

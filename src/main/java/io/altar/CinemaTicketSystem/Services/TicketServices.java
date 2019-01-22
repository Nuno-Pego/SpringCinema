package io.altar.CinemaTicketSystem.Services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import io.altar.CinemaTicketSystem.Business.TicketBusiness;
import io.altar.CinemaTicketSystem.Models.Ticket;
import io.altar.CinemaTicketSystem.ModelsDTO.TicketDTO;
@Component
@Path("ticket")
public class TicketServices{
	
	@Inject
	TicketBusiness ticketBusiness;
	
	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TicketDTO create(Ticket ticket) {
		return ticketBusiness.create(ticket);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TicketDTO> getAll() {
		return ticketBusiness.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TicketDTO findById(@PathParam("id") long id) {
		return ticketBusiness.findById(id);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") long id) {
		ticketBusiness.delete(id);
		return Response.ok().build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public void update(Ticket ticket) {
		ticketBusiness.update(ticket);
	}
}

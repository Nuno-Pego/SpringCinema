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

import io.altar.CinemaTicketSystem.Business.ExibitionDayBusiness;
import io.altar.CinemaTicketSystem.Models.ExibitionDay;
import io.altar.CinemaTicketSystem.ModelsDTO.ExibitionDayDTO;

@Component
@Path("exibitionday")
public class ExibitionDayServices {

	@Inject
	ExibitionDayBusiness exibitionDayBusiness;
	
	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void create(ExibitionDay exibitionDay) {
		exibitionDayBusiness.create(exibitionDay);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ExibitionDayDTO> consult() {
		return exibitionDayBusiness.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ExibitionDayDTO findById(@PathParam("id") long id) {
		return exibitionDayBusiness.findById(id);
	}
	
	@GET
	@Path("/room/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ExibitionDayDTO> exibitionDaysFromRoom(@PathParam("id") long id) {
		return exibitionDayBusiness.exibitionDaysFromRoom(id);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") long id) {
		exibitionDayBusiness.delete(id);
		return Response.ok().build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public void update(ExibitionDay exibitionDay) {
		exibitionDayBusiness.update(exibitionDay);
	}
}

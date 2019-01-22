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

import io.altar.CinemaTicketSystem.Business.ScheduleBusiness;
import io.altar.CinemaTicketSystem.Models.Schedule;
import io.altar.CinemaTicketSystem.ModelsDTO.ScheduleDTO;
@Component
@Path("schedule")
public class ScheduleServices{
	
	@Inject
	ScheduleBusiness scheduleBusiness;
	
	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Schedule create(Schedule schedule) {
		return scheduleBusiness.create(schedule);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ScheduleDTO> getAll() {
		return scheduleBusiness.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleDTO findById(@PathParam("id") long id) {
		return scheduleBusiness.findById(id);
	}
	
	@GET
	@Path("/availableseats/{idC}+{idM}+{idS}")
	@Produces(MediaType.APPLICATION_JSON)
	public int getAvailableSeats(@PathParam("idC") long idC, @PathParam("idM") long idM, @PathParam("idS") long idS) {
		return scheduleBusiness.getAvailableSeats(idC,idM,idS);
	}
	
	@GET
	@Path("/structure/{idC}+{idM}+{idS}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Boolean> getStructure(@PathParam("idC") long idC,@PathParam("idM") long idM,@PathParam("idS") long idS) {
		return  scheduleBusiness.getStructure(idC,idM,idS);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") long id) {
		scheduleBusiness.delete(id);
		return Response.ok().build();
	}
	
	@PUT
	@Path("/{idR}+{idQ}")
	@Produces(MediaType.APPLICATION_JSON)
	public ScheduleDTO update(@PathParam("idR") long idR, @PathParam("idQ") long idQ, Schedule schedule) {
		return scheduleBusiness.update(idR, idQ, schedule);
	}
}

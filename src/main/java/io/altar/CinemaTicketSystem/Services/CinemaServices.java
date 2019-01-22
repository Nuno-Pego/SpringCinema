package io.altar.CinemaTicketSystem.Services;

import javax.ws.rs.Path;


import io.altar.CinemaTicketSystem.Business.CinemaBusiness;
import io.altar.CinemaTicketSystem.Models.Cinema;
import io.altar.CinemaTicketSystem.ModelsDTO.CinemaDTO;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
@Component
@Path("cinema")
public class CinemaServices {
	@Inject
	CinemaBusiness cinemaBusiness;
	
	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void create(Cinema cinema) {
		cinemaBusiness.create(cinema);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CinemaDTO> consult() {
		return cinemaBusiness.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CinemaDTO findById(@PathParam("id") long id) {
		return cinemaBusiness.findById(id);
	}
	
	@GET
	@Path("/movie/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CinemaDTO> findByMovieId(@PathParam("id") long id) {
		return cinemaBusiness.findByMovieId(id);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") long id) {
		cinemaBusiness.delete(id);
		return Response.ok().build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public void update(Cinema cinema) {
		cinemaBusiness.update(cinema);
	}
}

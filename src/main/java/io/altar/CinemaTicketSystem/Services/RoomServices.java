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

import io.altar.CinemaTicketSystem.Business.RoomBusiness;
import io.altar.CinemaTicketSystem.Models.Room;
import io.altar.CinemaTicketSystem.ModelsDTO.MovieDTO;
import io.altar.CinemaTicketSystem.ModelsDTO.RoomDTO;
@Component
@Path("room")
public class RoomServices {
	
	@Inject
	RoomBusiness roomBusiness;
	
	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void create(Room room) {
		roomBusiness.create(room);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<RoomDTO> getAll() {
		return roomBusiness.getAll();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public RoomDTO findById(@PathParam("id") long id) {
		return roomBusiness.findById(id);
	}
	
	@GET
	@Path("movie/{idM}+{idC}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RoomDTO> getRoomfromMovieId(@PathParam("idM") long idM,@PathParam("idC") long idC) {
		return roomBusiness.getRoomFromMovie(idM,idC);
	}
	
	@GET
	@Path("cinema/{idC}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RoomDTO> getRoomsfromCinema(@PathParam("idC") long idC) {
		return roomBusiness.getRoomsFromCinema(idC);
	}
	
	@GET
	@Path("cinema/movies/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MovieDTO> getMoviesfromCinema(@PathParam("id") long id) {
		return roomBusiness.getmoviesFromCinema(id);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") long id) {
		roomBusiness.delete(id);
		return Response.ok().build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public void update(Room room) {
		roomBusiness.update(room);
	}
}

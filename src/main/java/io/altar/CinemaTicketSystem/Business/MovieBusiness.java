package io.altar.CinemaTicketSystem.Business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import io.altar.CinemaTicketSystem.Models.Movie;
import io.altar.CinemaTicketSystem.Models.Room;
import io.altar.CinemaTicketSystem.ModelsDTO.ExibitionDayDTO;
import io.altar.CinemaTicketSystem.ModelsDTO.MovieDTO;
import io.altar.CinemaTicketSystem.ModelsDTO.RoomDTO;
import io.altar.CinemaTicketSystem.ModelsDTO.ScheduleDTO;
import io.altar.CinemaTicketSystem.Repositories.MoviesRepository;
import io.altar.CinemaTicketSystem.Repositories.RoomsRepository;
@Component
public class MovieBusiness {

	@Inject
	protected MoviesRepository moviesRepository;	

	@Inject
	protected ScheduleBusiness scheduleBusiness;

	@Inject
	protected ExibitionDayBusiness exibitionDayBusiness;
	
	@Transactional
	public void delete(long id) {
		Movie movie = moviesRepository.getById(id);
		
		if(movie.getRooms().size()>0) {
			for (Room room: movie.getRooms()) {
				room.setMovie(null);
			}
		}
			moviesRepository.removeByID(id);
		
	}

	@Transactional
	public Response update(Movie movie) {
		if (movie.getReleaseDate().getTime() <= movie.getEndDate().getTime()) {
			moviesRepository.update(movie);
			return Response.status(Response.Status.OK).build();
		} else {
			throw new BadRequestException("endDate must be a date equal to releaseDate or after releasedate.");
		}
	}

	@Transactional
	public Response create(Movie movie) {
		if (movie.getReleaseDate().getTime() <= movie.getEndDate().getTime()) {
			moviesRepository.save(movie);
			return Response.status(Response.Status.OK).build();
		} else {
			throw new BadRequestException("endDate must be a date equal to releaseDate or after releasedate.");
		}
	}

	public List<MovieDTO> getAll() {
		List<Movie> movies = moviesRepository.getAll();
		List<MovieDTO> moviesDTO = new ArrayList<MovieDTO>();

		for (Movie movie : movies) {
			moviesDTO.add(movie.turnToDTO(movie));
		}
		return moviesDTO;
	}

	public MovieDTO findById(long id) {
		return moviesRepository.getById(id).turnToDTO(moviesRepository.getById(id));
	}
	

	public List<RoomDTO> getAllRoomsFromMovieID(long id) {
		Movie movie = moviesRepository.getById(id);
		List<Room> rooms = new ArrayList<Room>();
		rooms = movie.getRooms();
		List<RoomDTO> roomsDTO = new ArrayList<RoomDTO>();
		for (Room room : rooms) {
			roomsDTO.add(room.turnToDTO(room));
		}
		return roomsDTO;
	}

	@Transactional
	public List<ScheduleDTO> getSchedulesFromRoom(long id) {

		Calendar cal = Calendar.getInstance();		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minutes = (hour*60)+cal.get(Calendar.MINUTE);

		List<ScheduleDTO> schedulesDto = new ArrayList<ScheduleDTO>();
		List<ScheduleDTO> schedulesDTO = new ArrayList<ScheduleDTO>();
		List<ExibitionDayDTO> exibitionDays = exibitionDayBusiness.exibitionDaysFromRoom(id);
		
		schedulesDto = scheduleBusiness.getAll();

		for(ExibitionDayDTO exibitionDay : exibitionDays) {
			for (ScheduleDTO scheduleDto : schedulesDto) {
				if(scheduleDto.getExibitionDayDTO().getId()==exibitionDay.getId()) {			
					if (exibitionDay.getDay()==day && exibitionDay.getMonth()==month && exibitionDay.getYear()==year) {
						if(scheduleDto.getSessionBegin()>=minutes) {
							schedulesDTO.add(scheduleDto);
						}
					}else if(exibitionDay.getMonth()>=month && exibitionDay.getYear()>=year) {  
						schedulesDTO.add(scheduleDto);
					}
				}		
			}
		}
		return schedulesDTO;
	}

}

package io.altar.CinemaTicketSystem.Business;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import io.altar.CinemaTicketSystem.Models.ExibitionDay;
import io.altar.CinemaTicketSystem.Models.Schedule;
import io.altar.CinemaTicketSystem.ModelsDTO.ScheduleDTO;
import io.altar.CinemaTicketSystem.Repositories.ExibitionDayRepository;
import io.altar.CinemaTicketSystem.Repositories.RoomsRepository;
import io.altar.CinemaTicketSystem.Repositories.ScheduleRepository;
@Component
public class ScheduleBusiness {

	@Inject
	protected ScheduleRepository schedulesRepository;

	@Inject
	protected RoomsRepository roomRepository;
	
	@Inject
	protected ExibitionDayRepository exibitionDayRepository;

	@Transactional
	public void delete(long id) {
		schedulesRepository.removeByID(id);
	}

	@Transactional
	public ScheduleDTO update(long idR, long idQ, Schedule schedule) {
		
		List<ExibitionDay> exibitionDays = exibitionDayRepository.getAll();
		List<Schedule> schedules = schedulesRepository.getAll();
		Schedule scheduleUpdated = schedulesRepository.getById(schedule.getId());	
		ExibitionDay exibitionDay = scheduleUpdated.getExibitionDay();		
		
		ExibitionDay exibitionDayChosen = new ExibitionDay();
		Schedule correctSchedule = new Schedule();
		  
		for(ExibitionDay exibition : exibitionDays) {
			if(exibition.getRoom().getId()==idR && exibition.getDay()==exibitionDay.getDay() && exibition.getMonth()==exibitionDay.getMonth() && exibition.getYear()==exibitionDay.getYear()) {
				exibitionDayChosen = exibition;
			}
		}
		
		for(Schedule sched: schedules) {
			if(sched.getExibitionDay().getId()==exibitionDayChosen.getId() && sched.getSessionBegin()==scheduleUpdated.getSessionBegin()) {
				correctSchedule = sched;
			}
		}
		
		List<Boolean> structure = correctSchedule.getStructure();
		
		for (int i = 0; i < structure.size(); i++) {
			correctSchedule.getStructure().set(i, schedule.getStructure().get(i));
		}		
		
		schedulesRepository.update(correctSchedule);
		
		for (int i = 1; i <= idQ; i++) {
			correctSchedule.takeASeat();
		}
		
		return correctSchedule.turnToDTO(correctSchedule);
	}

	@Transactional
	public Schedule create(Schedule schedule) {
		return schedulesRepository.save(schedule);
	}

	public List<ScheduleDTO> getAll() {
		List<Schedule> schedules = schedulesRepository.getAll();
		List<ScheduleDTO> schedulesDTO = new ArrayList<ScheduleDTO>();

		for (Schedule schedule : schedules) {
			schedulesDTO.add(schedule.turnToDTO(schedule));
		}
		return schedulesDTO;
	}

	public ScheduleDTO findById(long id) {
		return schedulesRepository.getById(id).turnToDTO(schedulesRepository.getById(id));
	}

	public int getAvailableSeats(long idC, long idM, long idS) {		
		
		List<Schedule> schedules = schedulesRepository.getAll();
		Schedule scheduleSelecteted = schedulesRepository.getById(idS);
		int availableSeats=0;

		for (Schedule schedule : schedules) {
			if (schedule.getExibitionDay().getRoom().getMovie().getId() == idM
					&& schedule.getExibitionDay().getRoom().getCinema().getId() == idC
					&& schedule.getExibitionDay().getDay() == scheduleSelecteted.getExibitionDay().getDay()
					&& schedule.getExibitionDay().getMonth() == scheduleSelecteted.getExibitionDay().getMonth()
					&& schedule.getExibitionDay().getYear() == scheduleSelecteted.getExibitionDay().getYear()
					&& schedule.getSessionBegin() == scheduleSelecteted.getSessionBegin()) {
				availableSeats+=schedule.getAvailableSeats();
			}
		}

		return availableSeats;
	}

	@Transactional
	public List<Boolean> getStructure(long idC, long idM, long idS) {

		List<Schedule> schedules = schedulesRepository.getAll();
		Schedule scheduleSelecteted = schedulesRepository.getById(idS);
		List<Boolean> structures = new ArrayList<Boolean>();

		for (Schedule schedule : schedules) {
			if (schedule.getExibitionDay().getRoom().getMovie().getId() == idM
					&& schedule.getExibitionDay().getRoom().getCinema().getId() == idC
					&& schedule.getExibitionDay().getDay() == scheduleSelecteted.getExibitionDay().getDay()
					&& schedule.getExibitionDay().getMonth() == scheduleSelecteted.getExibitionDay().getMonth()
					&& schedule.getExibitionDay().getYear() == scheduleSelecteted.getExibitionDay().getYear()
					&& schedule.getSessionBegin() == scheduleSelecteted.getSessionBegin()) {
				structures.addAll(schedule.getStructure());
			}

		}

		return structures;
	}

}

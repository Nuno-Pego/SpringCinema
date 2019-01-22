package io.altar.CinemaTicketSystem.Business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import io.altar.CinemaTicketSystem.Models.ExibitionDay;
import io.altar.CinemaTicketSystem.ModelsDTO.ExibitionDayDTO;
import io.altar.CinemaTicketSystem.Repositories.ExibitionDayRepository;
@Component
public class ExibitionDayBusiness {

	@Inject
	protected ExibitionDayRepository exibitionDayRepository;

	@Transactional
	public void delete(long id) {
		exibitionDayRepository.removeByID(id);
	}

	@Transactional
	public ExibitionDay update(ExibitionDay exibitionDay) {
		return exibitionDayRepository.update(exibitionDay);
	}

	@Transactional
	public ExibitionDayDTO create(ExibitionDay exibitionDay) {
		exibitionDayRepository.save(exibitionDay);
		return exibitionDay.turnToDTO(exibitionDay);
	}

	public List<ExibitionDayDTO> getAll() {

		List<ExibitionDay> exibitionDays = exibitionDayRepository.getAll();
		List<ExibitionDayDTO> ExibitionDaysDTO = new ArrayList<ExibitionDayDTO>();

		for (ExibitionDay exibitionDay : exibitionDays) {
			ExibitionDaysDTO.add(exibitionDay.turnToDTO(exibitionDay));
		}
		return ExibitionDaysDTO;
	}

	@Transactional
	public List<ExibitionDayDTO> exibitionDaysFromRoom(long id) {

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int count = 0, daysOfWeek = 7;

		List<ExibitionDay> exibitionDays = exibitionDayRepository.getAll();

		List<ExibitionDayDTO> ExibitionDaysDTO = new ArrayList<ExibitionDayDTO>();


		for (ExibitionDay exibitionDay : exibitionDays) {
			if (count == daysOfWeek) {
				break;
			}
		
				if (exibitionDay.getRoom().getMovie().getId() == id && exibitionDay.getDay() >= day
						&& exibitionDay.getMonth() >= month && exibitionDay.getYear() >= year) {
					count++;
					ExibitionDaysDTO.add(exibitionDay.turnToDTO(exibitionDay));
				}			

		}

		return ExibitionDaysDTO;
	}

	public ExibitionDayDTO findById(long id) {
		return exibitionDayRepository.getById(id).turnToDTO(exibitionDayRepository.getById(id));
	}

}

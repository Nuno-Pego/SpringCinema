package io.altar.CinemaTicketSystem.Services;
import io.altar.CinemaTicketSystem.Filters.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyInitializer extends ResourceConfig {
	public JerseyInitializer() {
		registerEndpoints();
		register(CORSFilter.class);
	}
	
	private void registerEndpoints() {
		register(CinemaServices.class);
		register(ExibitionDayServices.class);
		register(MovieServices.class);
		register(RoomServices.class);
		register(ScheduleServices.class);
		register(TicketServices.class);
		register(TypeOfTicketServices.class);
	}
}

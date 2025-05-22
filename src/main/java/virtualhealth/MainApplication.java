package virtualhealth;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import virtualhealth.dao.CalendarDao;
import virtualhealth.model.*;
import virtualhealth.service.*;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserService userService,
								 HistoryService historyService,
								 DataService<Test> dataServiceTest,
								 DataService<Prescription> dataServicePrescr,
								 DataService<Vaccination> dataServiceVacc,
								 DataService<Consultation> dataServiceConsult,
								 DoctorService doctorService,
								 CalendarService calendarService) { // специальный бин, который автоматически запускается после старта приложения.
		return args -> {
			System.out.println("+");

		};
	}

}

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
			Client client = new Client();
			client.setFirstName("Иван");
			client.setLastName("Иванов");
			client.setDateBirth(LocalDate.of(1990, 1, 1));
			client.setEmail("ivanov@example.com");
			client.setPassword("password12QWExc");
			// другие поля клиента

			History history = new History();
			history.setClient(client);

			System.out.println("Клиент с историей сохранён");

		};
	}

}

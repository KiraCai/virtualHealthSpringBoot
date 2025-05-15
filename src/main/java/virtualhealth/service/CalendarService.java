package virtualhealth.service;

import virtualhealth.model.Calendar;

import java.time.LocalDate;
import java.util.List;

public interface CalendarService {
    List<Calendar> findAllCalendar(); // работает
    void addCalendar(Calendar calendar); // работает
    void updateCalendar(Calendar calendar); // работает
    Calendar findCalendarById(Long id);  // работает
    List<Calendar> findByDate(LocalDate date);  // работает
    List<Calendar> findByIdDoctor(Long idDoctor); // работает
    List<Calendar> findByIdClient(Long idClient); // работает
    void deleteCalendarById(Long id); // работает
}

package virtualhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import virtualhealth.model.Calendar;

import java.time.LocalDate;
import java.util.List;


public interface CalendarDao extends JpaRepository<Calendar, Long> {
    List<Calendar> findByDate(LocalDate date);
    List<Calendar> findAllByDoctor_IdDoctor(Long idDoctor);
    List<Calendar> findAllByClient_IdClientOrderByDateDesc(Long idClient);
}

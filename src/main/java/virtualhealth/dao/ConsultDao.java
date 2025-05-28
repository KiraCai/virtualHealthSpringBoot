package virtualhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import virtualhealth.model.Consultation;

import java.time.LocalDate;
import java.util.List;

public interface ConsultDao extends JpaRepository<Consultation, Long> {
    List<Consultation> findAllByHistory_IdHistoryOrderByDateDesc(Long id);
    List<Consultation> findAllByDoctor_IdDoctor(Long id);
    List<Consultation> findAllByDate(LocalDate localDate);
}

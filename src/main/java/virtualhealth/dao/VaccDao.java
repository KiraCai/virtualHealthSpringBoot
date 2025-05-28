package virtualhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import virtualhealth.model.Vaccination;

import java.time.LocalDate;
import java.util.List;

public interface VaccDao extends JpaRepository<Vaccination, Long> {
    List<Vaccination> findAllByHistory_IdHistoryOrderByDateDesc(Long id);
    List<Vaccination> findAllByDoctor_IdDoctor(Long id);
    List<Vaccination> findAllByDate(LocalDate localDate);
}

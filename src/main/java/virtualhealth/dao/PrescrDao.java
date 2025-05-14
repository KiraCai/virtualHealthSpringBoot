package virtualhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import virtualhealth.model.Prescription;

import java.time.LocalDate;
import java.util.List;

public interface PrescrDao extends JpaRepository<Prescription, Long> {
    List<Prescription> findAllByHistory_IdHistory(Long id);
    List<Prescription> findAllByDoctor_IdDoctor(Long id);
    List<Prescription> findAllByDate(LocalDate localDate);
}

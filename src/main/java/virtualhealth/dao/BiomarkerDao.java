package virtualhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import virtualhealth.model.Biomarker;
import virtualhealth.model.Calendar;
import virtualhealth.model.Disease;

import java.util.List;

public interface BiomarkerDao extends JpaRepository<Biomarker, Long> {
    Biomarker findByName(String name);
    void deleteBiomarkerByName(String name);
    List<Biomarker> findAllByDisease_IdDisease(Long idDisease);
}

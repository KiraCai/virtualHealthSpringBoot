package virtualhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import virtualhealth.model.Test;

import java.time.LocalDate;
import java.util.List;

public interface TestDao extends JpaRepository<Test, Long> {
    List<Test> findAllByHistory_IdHistory(Long id);
    //List<Test> findAllByDoctor_IdDoctor(Long id);
    List<Test> findAllByDate(LocalDate localDate);
}

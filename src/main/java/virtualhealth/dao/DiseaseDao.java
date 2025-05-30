package virtualhealth.dao;

import virtualhealth.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseDao extends JpaRepository<Disease, Long>{
    Disease findByName(String name);
    void deleteDiseaseByName(String name);
    boolean existsByName(String name);
}

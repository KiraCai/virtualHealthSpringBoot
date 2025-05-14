package virtualhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import virtualhealth.model.Doctor;

public interface DoctorDao extends JpaRepository<Doctor, Long> {
}

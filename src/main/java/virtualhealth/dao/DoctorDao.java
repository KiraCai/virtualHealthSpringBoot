package virtualhealth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import virtualhealth.model.Doctor;

import java.util.List;

public interface DoctorDao extends JpaRepository<Doctor, Long> {
    List<Doctor> findByfirstName(String firstName);
    List<Doctor> findBylastName(String lastName);
    Doctor findByemailPersonal(String email);
    void deleteDoctorByemailPersonal(String email);
}

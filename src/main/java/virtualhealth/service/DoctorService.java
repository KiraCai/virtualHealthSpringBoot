package virtualhealth.service;

import virtualhealth.model.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findAllDoctor(); //работает
    void addDoctor(Doctor doctor); //работает
    Doctor findDoctorById(Long id);  //работает
}

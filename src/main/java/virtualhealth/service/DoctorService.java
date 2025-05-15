package virtualhealth.service;

import virtualhealth.model.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findAllDoctor(); //работает
    void addDoctor(Doctor doctor); //работает
    Doctor findDoctorById(Long id);  //работает
    void updateDoctor(Doctor doctor); // работает
    List<Doctor> findByfirstName(String name);  // работает
    List<Doctor> findBylastName(String name); // работает
    void deleteDoctorById(Long id); // работает
    Doctor findByEmailPersonal(String email); // работает
    void deleteDoctorByEmailPersonal(String email); // работает

}

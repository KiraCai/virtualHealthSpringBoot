package virtualhealth.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import virtualhealth.model.Doctor;
import virtualhealth.service.DoctorService;
import virtualhealth.dao.DoctorDao;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorServiceImp implements DoctorService {
    private DoctorDao doctorDao;

    @Override
    public List<Doctor> findAllDoctor(){
        return doctorDao.findAll();
    }
    @Override
    public void addDoctor(Doctor doctor){
        doctorDao.save(doctor);
    }
    @Override
    public Doctor findDoctorById(Long id){
        return doctorDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + id));
    }
    @Override
    public void updateDoctor(Doctor doctor) {
        doctorDao.save(doctor);
    }
    @Override
    public List<Doctor> findByfirstName(String name){
        return doctorDao.findByfirstName(name);
    }
    @Override
    public List<Doctor> findBylastName(String name){
        return doctorDao.findBylastName(name);
    }
    @Override
    public Doctor findByEmailPersonal(String email){
        return doctorDao.findByemailPersonal(email);
    }
    @Override
    @Transactional
    public void deleteDoctorById(Long id){
        doctorDao.deleteById(id);
    }
    @Override
    @Transactional
    public void deleteDoctorByEmailPersonal(String email){
        doctorDao.deleteDoctorByemailPersonal(email);
    }
}

package virtualhealth.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import virtualhealth.model.Doctor;
import virtualhealth.service.DoctorService;
import virtualhealth.dao.DoctorDao;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorServiceImp implements DoctorService {
    private final DoctorDao doctorDao;

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
        return doctorDao.findById(id).get();
    }
}

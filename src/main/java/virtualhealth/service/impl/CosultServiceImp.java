package virtualhealth.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import virtualhealth.dao.ConsultDao;
import virtualhealth.model.Consultation;
import virtualhealth.service.DataService;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CosultServiceImp implements DataService<Consultation> {
    private ConsultDao consultDao;

    @Override
    public List<Consultation> findAllData() {
        return consultDao.findAll();
    }

    @Override
    public List<Consultation> findAllByIdHistory(Long idHistory) {
        return consultDao.findAllByHistory_IdHistory(idHistory);
    }
    @Override
    public List<Consultation> findAllByIdDoctor(Long idDoctor) {
        return consultDao.findAllByDoctor_IdDoctor(idDoctor);
    }
    @Override
    public List<Consultation> findAllByDate(LocalDate localDate){
        return consultDao.findAllByDate(localDate);
    }

    @Override
    public Consultation findTestById(Long id) {
        return consultDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Test with id " + id + " not found"));
    }
    @Override
    public void addData(Consultation consult){
        consultDao.save(consult);
    }

    @Override
    public Consultation updateData(Consultation consult){
        return consultDao.save(consult);
    }

    @Override
    @Transactional
    public void deleteDataById(Long id){
        consultDao.deleteById(id);
    }
}

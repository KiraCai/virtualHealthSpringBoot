package virtualhealth.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import virtualhealth.dao.PrescrDao;
import virtualhealth.model.Prescription;
import virtualhealth.service.DataService;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PrescrServiceImp implements DataService<Prescription> {
    private PrescrDao prescrDao;

    @Override
    public List<Prescription> findAllData() {
        return prescrDao.findAll();
    }

    @Override
    public List<Prescription> findAllByIdHistory(Long idHistory) {
        return prescrDao.findAllByHistory_IdHistory(idHistory);
    }
    @Override
    public List<Prescription> findAllByIdDoctor(Long idDoctor) {
        return prescrDao.findAllByDoctor_IdDoctor(idDoctor);
    }
    @Override
    public List<Prescription> findAllByDate(LocalDate localDate){
        return prescrDao.findAllByDate(localDate);
    }

    @Override
    public Prescription findTestById(Long id) {
        return prescrDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Test with id " + id + " not found"));
    }
    @Override
    public void addData(Prescription prescr){
        prescrDao.save(prescr);
    }
    @Override
    public Prescription updateData(Prescription prescr){
        return prescrDao.save(prescr);
    }

    @Override
    @Transactional
    public void deleteDataById(Long id){
        prescrDao.deleteById(id);
    }
}

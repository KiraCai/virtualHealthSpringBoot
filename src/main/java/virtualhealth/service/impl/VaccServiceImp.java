package virtualhealth.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import virtualhealth.dao.VaccDao;
import virtualhealth.model.Vaccination;
import virtualhealth.service.DataService;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class VaccServiceImp implements DataService<Vaccination> {
    private final VaccDao vaccDao;

    @Override
    public List<Vaccination> findAllData() {
        return vaccDao.findAll();
    }

    @Override
    public List<Vaccination> findAllByIdHistory(Long idHistory) {
        return vaccDao.findAllByHistory_IdHistory(idHistory);
    }
    @Override
    public List<Vaccination> findAllByIdDoctor(Long idDoctor) {
        return vaccDao.findAllByDoctor_IdDoctor(idDoctor);
    }
    @Override
    public List<Vaccination> findAllByDate(LocalDate localDate){
        return vaccDao.findAllByDate(localDate);
    }

    @Override
    public Vaccination findTestById(Long id) {
        return vaccDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Test with id " + id + " not found"));
    }
    @Override
    public void addData(Vaccination vacc){
        vaccDao.save(vacc);
    }
    @Override
    public Vaccination updateData(Vaccination vacc){
        return vaccDao.save(vacc);
    }

    @Override
    @Transactional
    public void deleteDataById(Long id){
        vaccDao.deleteById(id);
    }

}

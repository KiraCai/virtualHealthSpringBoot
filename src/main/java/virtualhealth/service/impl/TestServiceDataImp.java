package virtualhealth.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import virtualhealth.dao.TestDao;
import virtualhealth.model.Test;
import virtualhealth.service.DataService;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TestServiceDataImp implements DataService<Test> {
    private final TestDao testDao;

    @Override
    public List<Test> findAllData() {
        return testDao.findAll();
    }

    @Override
    public List<Test> findAllByIdHistory(Long idHistory) {
        return testDao.findAllByHistory_IdHistory(idHistory);
    }
    @Override
    public List<Test> findAllByIdDoctor(Long idDoctor) {
        return testDao.findAllByDoctor_IdDoctor(idDoctor);
    }
    @Override
    public List<Test> findAllByDate(LocalDate localDate){
        return testDao.findAllByDate(localDate);
    }

    @Override
    public Test findTestById(Long id) {
        return testDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Test with id " + id + " not found"));
    }
    @Override
    public void addData(Test test){
        testDao.save(test);
    }
    @Override
    public Test updateData(Test test){
       return testDao.save(test);
    }

    @Override
    @Transactional
    public void deleteDataById(Long id){
        testDao.deleteById(id);
    }
}

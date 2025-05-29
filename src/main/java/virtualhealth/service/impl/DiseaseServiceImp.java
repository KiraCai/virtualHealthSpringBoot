package virtualhealth.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import virtualhealth.dao.DiseaseDao;
import virtualhealth.model.Disease;
import virtualhealth.service.DiseaseService;

import java.util.List;

@Service
@AllArgsConstructor
public class DiseaseServiceImp implements DiseaseService {
    private DiseaseDao diseaseDao;

    @Override
    public List<Disease> findAllDisease() {
        return diseaseDao.findAll();
    }

    @Override
    public void addDisease(Disease disease){
        diseaseDao.save(disease);
    }

    @Override
    public Disease findDiseaseById(Long idDisease){
        return diseaseDao.findById(idDisease).orElseThrow(() -> new EntityNotFoundException("Disease not found with id: " + idDisease));
    }

    @Override
    public void updateDisease(Disease disease){
        diseaseDao.save(disease);
    }

    @Override
    public Disease findByName(String name){
        return diseaseDao.findByName(name);
    }

    @Override
    public void deleteDiseaseById(Long idDisease){
        diseaseDao.deleteById(idDisease);
    }

    @Override
    public void deleteDiseaseByName(String name){
        diseaseDao.deleteDiseaseByName(name);
    }
}

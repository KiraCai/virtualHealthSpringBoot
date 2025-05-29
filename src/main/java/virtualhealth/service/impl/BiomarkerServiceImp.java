package virtualhealth.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import virtualhealth.dao.BiomarkerDao;
import virtualhealth.model.Biomarker;
import virtualhealth.model.Disease;
import virtualhealth.service.BiomarkerService;

import java.util.List;

@Service
@AllArgsConstructor
public class BiomarkerServiceImp implements BiomarkerService {

    private BiomarkerDao biomarkerDao;

    @Override
    public List<Biomarker> findAllBiomarker() {
        return biomarkerDao.findAll();
    }

    @Override
    public List<Biomarker> findByIdDisease(Long idDisease){
        return biomarkerDao.findAllByDisease_IdDisease(idDisease);
    }

    @Override
    public void addBiomarker(Biomarker biomarker){
        biomarkerDao.save(biomarker);
    }

    @Override
    public Biomarker findBiomarkerById(Long idBiomarker){
        return biomarkerDao.findById(idBiomarker).orElseThrow(() -> new EntityNotFoundException("Biomarker not found with id: " + idBiomarker));
    }

    @Override
    public void updateBiomarker(Biomarker biomarker){
        biomarkerDao.save(biomarker);
    }

    @Override
    public Biomarker findByName(String name){
        return biomarkerDao.findByName(name);
    }

    @Override
    public void deleteBiomarkerById(Long id){
        biomarkerDao.deleteById(id);
    }

    @Override
    public void deleteBiomarkerByName(String name){
        biomarkerDao.deleteBiomarkerByName(name);
    }
}

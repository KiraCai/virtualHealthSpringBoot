package virtualhealth.service;

import virtualhealth.model.Biomarker;
import virtualhealth.model.Calendar;

import java.util.List;

public interface BiomarkerService {
    List<Biomarker> findAllBiomarker();
    List<Biomarker> findByIdDisease(Long idDisease);
    void addBiomarker(Biomarker biomarker);
    Biomarker findBiomarkerById(Long id);
    void updateBiomarker(Biomarker disease);
    Biomarker findByName(String name);
    void deleteBiomarkerById(Long id);
    void deleteBiomarkerByName(String name);
}

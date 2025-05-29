package virtualhealth.service;

import virtualhealth.model.Disease;

import java.util.List;

public interface DiseaseService {
    List<Disease> findAllDisease();
    void addDisease(Disease disease);
    Disease findDiseaseById(Long id);
    void updateDisease(Disease disease);
    Disease findByName(String name);
    void deleteDiseaseById(Long id);
    void deleteDiseaseByName(String name);
}

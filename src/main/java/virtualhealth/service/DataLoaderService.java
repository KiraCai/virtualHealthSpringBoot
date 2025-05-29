package virtualhealth.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import virtualhealth.dao.BiomarkerDao;
import virtualhealth.dao.DiseaseDao;
import virtualhealth.model.Biomarker;
import virtualhealth.model.Disease;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class DataLoaderService {
    private DiseaseDao diseaseDao;
    private BiomarkerDao biomarkerDao;

    @PostConstruct
    public void loadData() {
        // Pneumonia
        Disease pneumonia = new Disease(
                "Pneumonia",
                "Pneumonia is an inflammation of the lungs, primarily affecting the alveoli. It is a leading cause of death in Togo.",
                new BigDecimal("2.3"), // Mortality rate in Togo (per 100,000 population)
                4, // Global rank
                4200, // Number of deaths in Togo
                new BigDecimal("0.025") // Mortality percentage
        );
        diseaseDao.save(pneumonia);

        // Malaria
        Disease malaria = new Disease(
                "Malaria",
                "Malaria is a life-threatening disease caused by parasites transmitted to humans through the bites of infected female Anopheles mosquitoes.",
                new BigDecimal("3.5"), // Mortality rate in Togo
                9, // Global rank
                6400, // Number of deaths in Togo
                new BigDecimal("0.042") // Mortality percentage
        );
        diseaseDao.save(malaria);

        // Adding biomarkers related to Malaria
        biomarkerDao.saveAll(Arrays.asList(
                new Biomarker(malaria, "G6PD Deficiency", "Mutations in the G6PD gene may increase susceptibility to malaria."),
                new Biomarker(malaria, "HLA-B*53:01", "This gene may be associated with resistance to malaria.")
        ));

        // HIV/AIDS
        Disease hiv = new Disease(
                "HIV/AIDS",
                "Human Immunodeficiency Virus (HIV) attacks the body's immune system and can lead to Acquired Immunodeficiency Syndrome (AIDS) if not treated.",
                new BigDecimal("1.8"), // Mortality rate in Togo
                6, // Global rank
                3500, // Number of deaths in Togo
                new BigDecimal("0.020") // Mortality percentage
        );
        diseaseDao.save(hiv);

        // Sickle Cell Disease
        Disease sickleCell = new Disease(
                "Sickle Cell Disease",
                "Sickle Cell Disease is a group of inherited red blood cell disorders characterized by abnormal hemoglobin, leading to distorted (sickle-shaped) red blood cells.",
                new BigDecimal("0.7"), // Mortality rate in Togo
                12, // Global rank
                1200, // Number of deaths in Togo
                new BigDecimal("0.010") // Mortality percentage
        );
        diseaseDao.save(sickleCell);

        // Adding biomarker related to Sickle Cell Disease
        biomarkerDao.save(new Biomarker(
                sickleCell, "HBB Gene Mutation", "Mutations in the HBB gene cause sickle cell disease."
        ));

        // Diabetes
        Disease diabetes = new Disease(
                "Diabetes",
                "Diabetes is a chronic disease that occurs when the pancreas does not produce enough insulin or when the body cannot effectively use the insulin it produces.",
                new BigDecimal("2.1"), // Mortality rate in Togo
                3, // Global rank
                2800, // Number of deaths in Togo
                new BigDecimal("0.015") // Mortality percentage
        );
        diseaseDao.save(diabetes);
    }


}

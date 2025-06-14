package virtualhealth.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import virtualhealth.dao.BiomarkerDao;
import virtualhealth.dao.DiseaseDao;
import virtualhealth.dao.HistoryDao;
import virtualhealth.dao.UserDao;
import virtualhealth.model.Biomarker;
import virtualhealth.model.Client;
import virtualhealth.model.Disease;
import virtualhealth.model.History;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class DataLoaderService {
    private DiseaseDao diseaseDao;
    private BiomarkerDao biomarkerDao;
    private UserDao clientDao;
    private HistoryDao historyDao;

    @PostConstruct
    @Transactional
    public void loadData() {
        // Pneumonia
        Disease pneumonia;
        History history;
        if (!clientDao.existsById(Long.parseLong("1"))) {
            history = new History();
            history.setClient(clientDao.getById(Long.parseLong("1")));
        }


        if (!diseaseDao.existsByName("Pneumonia")) {
            pneumonia = new Disease();
            pneumonia.setName("Pneumonia");
            pneumonia.setDescription("Pneumonia is an inflammation of the lungs, primarily affecting the alveoli. It is a leading cause of death in Togo.");
            pneumonia.setMortalityRate(BigDecimal.valueOf(2.3)); // Mortality rate in Togo (per 100,000 population)
            pneumonia.setWorldRank(4);  // Global rank
            pneumonia.setDeath(4200);  // Number of deaths in Togo
            pneumonia.setPercentage(BigDecimal.valueOf(0.025));   // Mortality percentage
            diseaseDao.save(pneumonia);
        } else {
            pneumonia = diseaseDao.findByName("Pneumonia");
        }

        // Adding biomarkers related to Pneumonia
        // Biomarker: Procalcitonin (PCT)
        if (!biomarkerDao.existsByName("Procalcitonin (PCT)")) {
            Biomarker markerPneumonia1 = new Biomarker();
            markerPneumonia1.setDisease(pneumonia);
            markerPneumonia1.setName("Procalcitonin (PCT)");
            markerPneumonia1.setDescription("Elevated levels indicate bacterial infection and help in deciding antibiotic treatment.");
            biomarkerDao.save(markerPneumonia1);
        }

        // Biomarker: C-Reactive Protein (CRP)
        if (!biomarkerDao.existsByName("C-Reactive Protein (CRP)")) {
            Biomarker markerPneumonia2 = new Biomarker();
            markerPneumonia2.setDisease(pneumonia);
            markerPneumonia2.setName("C-Reactive Protein (CRP)");
            markerPneumonia2.setDescription("Inflammatory marker; elevated levels correlate with disease severity.");
            biomarkerDao.save(markerPneumonia2);
        }

        // Biomarker: IL-6 and IL-8
        if (!biomarkerDao.existsByName("IL-6 and IL-8")) {
            Biomarker markerPneumonia3 = new Biomarker();
            markerPneumonia3.setDisease(pneumonia);
            markerPneumonia3.setName("IL-6 and IL-8");
            markerPneumonia3.setDescription("Cytokines associated with severity and prognosis of pneumonia.");
            biomarkerDao.save(markerPneumonia3);
        }

        // Biomarker: Lipopolysaccharide-Binding Protein (LBP)
        if (!biomarkerDao.existsByName("Lipopolysaccharide-Binding Protein (LBP)")) {
            Biomarker markerPneumonia4 = new Biomarker();
            markerPneumonia4.setDisease(pneumonia);
            markerPneumonia4.setName("Lipopolysaccharide-Binding Protein (LBP)");
            markerPneumonia4.setDescription("Elevated levels are linked to severe pneumonia and prognosis.");
            biomarkerDao.save(markerPneumonia4);
        }

        // Biomarker: Calprotectin
        if (!biomarkerDao.existsByName("Calprotectin")) {
            Biomarker markerPneumonia5 = new Biomarker();
            markerPneumonia5.setDisease(pneumonia);
            markerPneumonia5.setName("Calprotectin");
            markerPneumonia5.setDescription("Protein associated with inflammation and mortality risk in pneumonia.");
            biomarkerDao.save(markerPneumonia5);
        }

        // Biomarker: Neutrophil CD64 (nCD64)
        if (!biomarkerDao.existsByName("Neutrophil CD64 (nCD64)")) {
            Biomarker markerPneumonia6 = new Biomarker();
            markerPneumonia6.setDisease(pneumonia);
            markerPneumonia6.setName("Neutrophil CD64 (nCD64)");
            markerPneumonia6.setDescription("An activation marker on neutrophils; elevated expression indicates bacterial infection.");
            biomarkerDao.save(markerPneumonia6);
        }

        // Biomarker: Presepsin (sCD14-subtype)
        if (!biomarkerDao.existsByName("Presepsin (sCD14-subtype)")) {
            Biomarker markerPneumonia7 = new Biomarker();
            markerPneumonia7.setDisease(pneumonia);
            markerPneumonia7.setName("Presepsin (sCD14-subtype)");
            markerPneumonia7.setDescription("A soluble fragment of CD14; elevated levels are associated with sepsis and bacterial infections.");
            biomarkerDao.save(markerPneumonia7);
        }

        // Biomarker: Pentraxin 3 (PTX3)
        if (!biomarkerDao.existsByName("Pentraxin 3 (PTX3)")) {
            Biomarker markerPneumonia8 = new Biomarker();
            markerPneumonia8.setDisease(pneumonia);
            markerPneumonia8.setName("Pentraxin 3 (PTX3)");
            markerPneumonia8.setDescription("An acute-phase protein produced at infection sites; elevated levels indicate inflammation.");
            biomarkerDao.save(markerPneumonia8);
        }

        // Malaria
        Disease malaria;
        if (!diseaseDao.existsByName("Malaria")) {
            malaria = new Disease();
            malaria.setName("Malaria");
            malaria.setDescription("Malaria is a life-threatening disease caused by parasites transmitted to humans through the bites of infected female Anopheles mosquitoes.");
            malaria.setMortalityRate(BigDecimal.valueOf(3.5)); // Mortality rate in Togo
            malaria.setWorldRank(9);  // Global rank
            malaria.setDeath(6400);   // Number of deaths in Togo
            malaria.setPercentage(BigDecimal.valueOf(0.042)); // Mortality percentage
            diseaseDao.save(malaria);
        } else {
            malaria = diseaseDao.findByName("Malaria");
        }

        // Adding biomarker related to Malaria
        // Biomarker: G6PD Deficiency
        if (!biomarkerDao.existsByName("G6PD Deficiency")) {
            Biomarker marker = new Biomarker();
            marker.setDisease(malaria); // Установите объект болезни
            marker.setName("G6PD Deficiency");
            marker.setDescription("Mutations in the G6PD gene may increase susceptibility to malaria.");
            biomarkerDao.save(marker);
        }

        // Biomarker: HLA-B*53:01
        if (!biomarkerDao.existsByName("HLA-B*53:01")) {
            Biomarker marker2 = new Biomarker();
            marker2.setDisease(malaria);
            marker2.setName("HLA-B*53:01");
            marker2.setDescription("This gene may be associated with resistance to malaria.");
            biomarkerDao.save(marker2);
        }

        // Biomarker 1: Histidine-Rich Protein II (HRP2)
        if (!biomarkerDao.existsByName("Histidine-Rich Protein II (HRP2)")) {
            Biomarker markerMalaria1 = new Biomarker();
            markerMalaria1.setDisease(malaria);
            markerMalaria1.setName("Histidine-Rich Protein II (HRP2)");
            markerMalaria1.setDescription("A water-soluble protein expressed by Plasmodium falciparum; widely used in rapid diagnostic tests (RDTs).");
            biomarkerDao.save(markerMalaria1);
        }

        // Biomarker 2: Plasmodium Lactate Dehydrogenase (pLDH)
        if (!biomarkerDao.existsByName("Plasmodium Lactate Dehydrogenase (pLDH)")) {
            Biomarker markerMalaria2 = new Biomarker();
            markerMalaria2.setDisease(malaria);
            markerMalaria2.setName("Plasmodium Lactate Dehydrogenase (pLDH)");
            markerMalaria2.setDescription("An enzyme produced by Plasmodium species; used in RDTs and declines rapidly after treatment.");
            biomarkerDao.save(markerMalaria2);
        }

        // Biomarker 3: Plasmodium 18S rRNA
        if (!biomarkerDao.existsByName("Plasmodium 18S rRNA")) {
            Biomarker markerMalaria3 = new Biomarker();
            markerMalaria3.setDisease(malaria);
            markerMalaria3.setName("Plasmodium 18S rRNA");
            markerMalaria3.setDescription("A molecular marker used to monitor blood-stage infections in malaria clinical trials.");
            biomarkerDao.save(markerMalaria3);
        }

        // Biomarker 4: Hemozoin
        if (!biomarkerDao.existsByName("Hemozoin")) {
            Biomarker markerMalaria4 = new Biomarker();
            markerMalaria4.setDisease(malaria);
            markerMalaria4.setName("Hemozoin");
            markerMalaria4.setDescription("A crystalline pigment produced by Plasmodium species; serves as a visible marker in identifying malarial parasites.");
            biomarkerDao.save(markerMalaria4);
        }

        // Biomarker 5: Angiopoietin-2 (Ang-2)
        if (!biomarkerDao.existsByName("Angiopoietin-2 (Ang-2)")) {
            Biomarker markerMalaria5 = new Biomarker();
            markerMalaria5.setDisease(malaria);
            markerMalaria5.setName("Angiopoietin-2 (Ang-2)");
            markerMalaria5.setDescription("A protein associated with endothelial activation; elevated levels indicate severe malaria and poor prognosis.");
            biomarkerDao.save(markerMalaria5);
        }

        // Biomarker 6: C-Reactive Protein (CRP)
        if (!biomarkerDao.existsByName("C-Reactive Protein (CRP)")) {
            Biomarker markerMalaria6 = new Biomarker();
            markerMalaria6.setDisease(malaria);
            markerMalaria6.setName("C-Reactive Protein (CRP)");
            markerMalaria6.setDescription("An acute-phase protein; elevated levels correlate with malaria severity.");
            biomarkerDao.save(markerMalaria6);
        }

        // Biomarker 7: CD36
        if (!biomarkerDao.existsByName("CD36")) {
            Biomarker markerMalaria7 = new Biomarker();
            markerMalaria7.setDisease(malaria);
            markerMalaria7.setName("CD36");
            markerMalaria7.setDescription("A receptor on endothelial cells; mediates sequestration of Plasmodium falciparum-infected erythrocytes.");
            biomarkerDao.save(markerMalaria7);
        }

        // HIV/AIDS
        Disease hiv;
        if (!diseaseDao.existsByName("HIV/AIDS")) {
            hiv = new Disease();
            hiv.setName("HIV/AIDS");
            hiv.setDescription("Human Immunodeficiency Virus (HIV) attacks the body's immune system and can lead to Acquired Immunodeficiency Syndrome (AIDS) if not treated.");
            hiv.setMortalityRate(BigDecimal.valueOf(1.8)); // Mortality rate in Togo
            hiv.setWorldRank(6);  // Global rank
            hiv.setDeath(3500);   // Number of deaths in Togo
            hiv.setPercentage(BigDecimal.valueOf(0.020)); // Mortality percentage
            diseaseDao.save(hiv);
        } else {
            hiv = diseaseDao.findByName("HIV/AIDS");
        }

        // Adding biomarker related to HIV
        // Biomarker: CD4+ T Lymphocytes
        if (!biomarkerDao.existsByName("CD4+ T Lymphocytes")) {
            Biomarker markerHiv1 = new Biomarker();
            markerHiv1.setDisease(hiv); // Link the biomarker to the HIV disease entity
            markerHiv1.setName("CD4+ T Lymphocytes");
            markerHiv1.setDescription("Key indicator of immune status in HIV patients. A decreasing level suggests disease progression.");
            biomarkerDao.save(markerHiv1);
        }

        // Biomarker: HIV RNA Viral Load
        if (!biomarkerDao.existsByName("HIV RNA Viral Load")) {
            Biomarker markerHiv2 = new Biomarker();
            markerHiv2.setDisease(hiv);
            markerHiv2.setName("HIV RNA Viral Load");
            markerHiv2.setDescription("Measures the amount of HIV in the blood. Used to assess the effectiveness of antiretroviral therapy.");
            biomarkerDao.save(markerHiv2);
        }

        // Biomarker: β2-Microglobulin
        if (!biomarkerDao.existsByName("β2-Microglobulin")) {
            Biomarker markerHiv3 = new Biomarker();
            markerHiv3.setDisease(hiv);
            markerHiv3.setName("β2-Microglobulin");
            markerHiv3.setDescription("Elevated levels indicate active viral replication and disease progression.");
            biomarkerDao.save(markerHiv3);
        }

        // Biomarker: CD8+ T Lymphocytes
        if (!biomarkerDao.existsByName("CD8+ T Lymphocytes")) {
            Biomarker markerHiv4 = new Biomarker();
            markerHiv4.setDisease(hiv);
            markerHiv4.setName("CD8+ T Lymphocytes");
            markerHiv4.setDescription("Cytotoxic T cells that play a role in controlling HIV infection; their count provides insight into immune activation.");
            biomarkerDao.save(markerHiv4);
        }

        // Biomarker: CD4/CD8 Ratio
        if (!biomarkerDao.existsByName("CD4/CD8 Ratio")) {
            Biomarker markerHiv5 = new Biomarker();
            markerHiv5.setDisease(hiv);
            markerHiv5.setName("CD4/CD8 Ratio");
            markerHiv5.setDescription("An indicator of immune system balance; a declining ratio suggests disease progression.");
            biomarkerDao.save(markerHiv5);
        }

        // Biomarker: Lactate Dehydrogenase (LDH)
        if (!biomarkerDao.existsByName("Lactate Dehydrogenase (LDH)")) {
            Biomarker markerHiv6 = new Biomarker();
            markerHiv6.setDisease(hiv);
            markerHiv6.setName("Lactate Dehydrogenase (LDH)");
            markerHiv6.setDescription("An enzyme released during tissue damage; elevated levels may indicate HIV-associated conditions.");
            biomarkerDao.save(markerHiv6);
        }


        // Sickle Cell Disease
        Disease sickleCell;
        if (!diseaseDao.existsByName("Sickle Cell Disease")) {
            sickleCell = new Disease();
            sickleCell.setName("Sickle Cell Disease");
            sickleCell.setDescription("Sickle Cell Disease is a group of inherited red blood cell disorders characterized by abnormal hemoglobin, leading to distorted (sickle-shaped) red blood cells.");
            sickleCell.setMortalityRate(BigDecimal.valueOf(0.7)); // Mortality rate in Togo
            sickleCell.setWorldRank(12);  // Global rank
            sickleCell.setDeath(1200);    // Number of deaths in Togo
            sickleCell.setPercentage(BigDecimal.valueOf(0.010)); // Mortality percentage

            diseaseDao.save(sickleCell);
        } else {
            sickleCell = diseaseDao.findByName("Sickle Cell Disease");
        }

        // Adding biomarker related to Sickle Cell Disease
        // Biomarker: D-Dimer
        if (!biomarkerDao.existsByName("D-Dimer")) {
            Biomarker markerSickleCell1 = new Biomarker();
            markerSickleCell1.setDisease(sickleCell);
            markerSickleCell1.setName("D-Dimer");
            markerSickleCell1.setDescription("Elevated levels indicate activation of coagulation and are linked to vascular complications.");
            biomarkerDao.save(markerSickleCell1);
        }

        // Biomarker: Prothrombin Fragment 1.2 (F1.2)
        if (!biomarkerDao.existsByName("Prothrombin Fragment 1.2 (F1.2)")) {
            Biomarker markerSickleCell2 = new Biomarker();
            markerSickleCell2.setDisease(sickleCell);
            markerSickleCell2.setName("Prothrombin Fragment 1.2 (F1.2)");
            markerSickleCell2.setDescription("Marker of coagulation activation, associated with increased thrombotic risk.");
            biomarkerDao.save(markerSickleCell2);
        }

        // Biomarker: Thrombin-Antithrombin Complexes (TAT)
        if (!biomarkerDao.existsByName("Thrombin-Antithrombin Complexes (TAT)")) {
            Biomarker markerSickleCell3 = new Biomarker();
            markerSickleCell3.setDisease(sickleCell);
            markerSickleCell3.setName("Thrombin-Antithrombin Complexes (TAT)");
            markerSickleCell3.setDescription("Elevated levels indicate a hypercoagulable state typical for sickle cell disease.");
            biomarkerDao.save(markerSickleCell3);
        }

        // Biomarker: sTREM-1
        if (!biomarkerDao.existsByName("sTREM-1")) {
            Biomarker markerSickleCell4 = new Biomarker();
            markerSickleCell4.setDisease(sickleCell);
            markerSickleCell4.setName("sTREM-1");
            markerSickleCell4.setDescription("Linked to severe disease and increased mortality risk.");
            biomarkerDao.save(markerSickleCell4);
        }

        // Biomarker: Glial Fibrillary Acidic Protein (GFAP)
        if (!biomarkerDao.existsByName("Glial Fibrillary Acidic Protein (GFAP)")) {
            Biomarker markerSCD5 = new Biomarker();
            markerSCD5.setDisease(sickleCell);
            markerSCD5.setName("Glial Fibrillary Acidic Protein (GFAP)");
            markerSCD5.setDescription("A marker of astrocyte activation; elevated levels may indicate neurological complications in SCD.");
            biomarkerDao.save(markerSCD5);
        }

        // Biomarker: Brain-Derived Neurotrophic Factor (BDNF)
        if (!biomarkerDao.existsByName("Brain-Derived Neurotrophic Factor (BDNF)")) {
            Biomarker markerSCD6 = new Biomarker();
            markerSCD6.setDisease(sickleCell);
            markerSCD6.setName("Brain-Derived Neurotrophic Factor (BDNF)");
            markerSCD6.setDescription("A neurotrophin involved in neuronal survival; altered levels may be associated with cognitive deficits in SCD.");
            biomarkerDao.save(markerSCD6);
        }

        // Biomarker: Soluble Vascular Cell Adhesion Molecule-1 (sVCAM-1)
        if (!biomarkerDao.existsByName("Soluble Vascular Cell Adhesion Molecule-1 (sVCAM-1)")) {
            Biomarker markerSCD7 = new Biomarker();
            markerSCD7.setDisease(sickleCell);
            markerSCD7.setName("Soluble Vascular Cell Adhesion Molecule-1 (sVCAM-1)");
            markerSCD7.setDescription("An endothelial activation marker; elevated levels correlate with vaso-occlusive episodes.");
            biomarkerDao.save(markerSCD7);
        }

        // Biomarker: Tissue Factor (TF)
        if (!biomarkerDao.existsByName("Tissue Factor (TF)")) {
            Biomarker markerSCD8 = new Biomarker();
            markerSCD8.setDisease(sickleCell);
            markerSCD8.setName("Tissue Factor (TF)");
            markerSCD8.setDescription("Initiates the coagulation cascade; increased expression contributes to the hypercoagulable state in SCD.");
            biomarkerDao.save(markerSCD8);
        }

        // Biomarker: Endothelin-1 (ET-1)
        if (!biomarkerDao.existsByName("Endothelin-1 (ET-1)")) {
            Biomarker markerSCD9 = new Biomarker();
            markerSCD9.setDisease(sickleCell);
            markerSCD9.setName("Endothelin-1 (ET-1)");
            markerSCD9.setDescription("A potent vasoconstrictor; elevated levels are associated with pain episodes and pulmonary hypertension in SCD.");
            biomarkerDao.save(markerSCD9);
        }

        // Diabetes
        Disease diabetes;
        if (!diseaseDao.existsByName("Diabetes")) {
            diabetes = new Disease();
            diabetes.setName("Diabetes");
            diabetes.setDescription("Diabetes is a chronic disease that occurs when the pancreas does not produce enough insulin or when the body cannot effectively use the insulin it produces.");
            diabetes.setMortalityRate(BigDecimal.valueOf(2.1)); // Mortality rate in Togo
            diabetes.setWorldRank(3);  // Global rank
            diabetes.setDeath(2800);   // Number of deaths in Togo
            diabetes.setPercentage(BigDecimal.valueOf(0.015)); // Mortality percentage

            diseaseDao.save(diabetes);
        } else {
            diabetes = diseaseDao.findByName("Diabetes");
        }
        // Adding biomarker related to Diabetes
        // Biomarker: HbA1c
        if (!biomarkerDao.existsByName("HbA1c")) {
            Biomarker markerDiabetes1 = new Biomarker();
            markerDiabetes1.setDisease(diabetes);
            markerDiabetes1.setName("HbA1c");
            markerDiabetes1.setDescription("Reflects the average blood glucose level over the past 2–3 months.");
            biomarkerDao.save(markerDiabetes1);
        }

        // Biomarker: 1,5-Anhydroglucitol (1,5-AG)
        if (!biomarkerDao.existsByName("1,5-Anhydroglucitol (1,5-AG)")) {
            Biomarker markerDiabetes2 = new Biomarker();
            markerDiabetes2.setDisease(diabetes);
            markerDiabetes2.setName("1,5-Anhydroglucitol (1,5-AG)");
            markerDiabetes2.setDescription("A short-term marker of glycemic control, especially for identifying postprandial hyperglycemia.");
            biomarkerDao.save(markerDiabetes2);
        }

        // Biomarker: NT-proBNP and Troponin
        if (!biomarkerDao.existsByName("NT-proBNP and Troponin")) {
            Biomarker markerDiabetes3 = new Biomarker();
            markerDiabetes3.setDisease(diabetes);
            markerDiabetes3.setName("NT-proBNP and Troponin");
            markerDiabetes3.setDescription("Cardiac biomarkers that indicate cardiovascular disease risk in diabetes patients.");
            biomarkerDao.save(markerDiabetes3);
        }

        // Biomarker: SPINA-GBeta
        if (!biomarkerDao.existsByName("SPINA-GBeta")) {
            Biomarker markerDiabetes4 = new Biomarker();
            markerDiabetes4.setDisease(diabetes);
            markerDiabetes4.setName("SPINA-GBeta");
            markerDiabetes4.setDescription("Index reflecting β-cell function and insulin sensitivity, useful for assessing insulin resistance.");
            biomarkerDao.save(markerDiabetes4);
        }

        // Biomarker: Fasting Plasma Glucose (FPG)
        if (!biomarkerDao.existsByName("Fasting Plasma Glucose (FPG)")) {
            Biomarker markerDiabetes5 = new Biomarker();
            markerDiabetes5.setDisease(diabetes);
            markerDiabetes5.setName("Fasting Plasma Glucose (FPG)");
            markerDiabetes5.setDescription("Measures blood glucose levels after fasting; used to diagnose diabetes and monitor glycemic control.");
            biomarkerDao.save(markerDiabetes5);
        }

        // Biomarker: C-Peptide
        if (!biomarkerDao.existsByName("C-Peptide")) {
            Biomarker markerDiabetes6 = new Biomarker();
            markerDiabetes6.setDisease(diabetes);
            markerDiabetes6.setName("C-Peptide");
            markerDiabetes6.setDescription("Reflects endogenous insulin production; helps differentiate between type 1 and type 2 diabetes.");
            biomarkerDao.save(markerDiabetes6);
        }

        // Biomarker: Adiponectin
        if (!biomarkerDao.existsByName("Adiponectin")) {
            Biomarker markerDiabetes7 = new Biomarker();
            markerDiabetes7.setDisease(diabetes);
            markerDiabetes7.setName("Adiponectin");
            markerDiabetes7.setDescription("A hormone involved in glucose regulation and fatty acid oxidation; lower levels are associated with insulin resistance.");
            biomarkerDao.save(markerDiabetes7);
        }

    }

}

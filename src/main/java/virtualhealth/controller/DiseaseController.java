package virtualhealth.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import virtualhealth.config.JwtUtil;
import virtualhealth.dao.BiomarkerDao;
import virtualhealth.dao.DiseaseDao;
import virtualhealth.dto.ClientUpdateDTO;
import virtualhealth.dto.PatientFullProfileDTO;
import virtualhealth.dto.TestDisplayDTO;
import virtualhealth.dto.VaccinationDisplayDTO;
import virtualhealth.mapper.*;
import virtualhealth.model.*;
import virtualhealth.service.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v0.1/users")
@AllArgsConstructor
public class DiseaseController {
    private DiseaseService diseaseService;
    private BiomarkerService biomarkerService;

    // get all disease
    @GetMapping("/diseases")
    public List<Disease> getDiseases() {
        return diseaseService.findAllDisease();
    }

    // we obtain biomarkers for a specific disease
    @GetMapping("/biomarkers/{idDisease}")
    public List<Biomarker> getBiomarkers(@PathVariable Long idDisease) {
        return biomarkerService.findByIdDisease(idDisease);
    }
}

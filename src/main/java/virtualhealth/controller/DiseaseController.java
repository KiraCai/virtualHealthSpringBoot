package virtualhealth.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import virtualhealth.config.JwtUtil;
import virtualhealth.dao.BiomarkerDao;
import virtualhealth.dao.DiseaseDao;
import virtualhealth.dto.*;
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

    private DiseaseMapper diseaseMapper;
    private BiomarkerMapper biomarkerMapper;
    // get all disease
    @GetMapping("/diseases")
    public List<DiseaseDTO> getDiseases() {
        List<Disease> diseases = diseaseService.findAllDisease();
        return diseaseMapper.toDTOs(diseases);
    }

    // we obtain biomarkers for a specific disease
    @GetMapping("/biomarkers/{idDisease}")
    public List<Biomarker> getBiomarkers(@PathVariable Long idDisease) {
        return biomarkerService.findByIdDisease(idDisease);
    }

    /*@GetMapping("/profile/full")
    public ResponseEntity getFullProfile(@RequestHeader("Authorization") String authHeader) {
        if (!authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }

        String token = authHeader.substring(7);
        String email = JwtUtil.validateTokenAndGetEmail(token);
        Disease disease = diseaseService.findByEmail(email);
        History history = historyService.findByIdClient(client.getIdClient());

        PatientFullProfileDTO dto = new PatientFullProfileDTO();
        dto.setClient(clientMapper.toDTO(client));
        dto.setTests(testMapper.toDTOs(dataServiceTest.findAllByIdHistory(history.getIdHistory())));
        dto.setPrescriptions(prescriptionMapper.toDTOs(dataServicePrescr.findAllByIdHistory(history.getIdHistory())));
        dto.setVaccinations(vaccinationMapper.toDTOs(dataServiceVacc.findAllByIdHistory(history.getIdHistory())));
        dto.setConsultations(consultationMapper.toDTOs(dataServiceConsult.findAllByIdHistory(history.getIdHistory())));
        dto.setReservations(scheduleMapper.toDTOs(calendarService.findByIdClient(client.getIdClient())));

        return ResponseEntity.ok(dto);
    }*/
}

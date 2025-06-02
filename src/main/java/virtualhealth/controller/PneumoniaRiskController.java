package virtualhealth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import virtualhealth.dto.PneumoniaRiskDTO;
import virtualhealth.dto.RiskResultDTO;

@RestController
@RequestMapping("/api/v0.1/users")
public class PneumoniaRiskController {

    @PostMapping("/test/pneumonia")
    public ResponseEntity<RiskResultDTO> assess(@RequestBody PneumoniaRiskDTO dto) {
        System.out.println("Request received: " + dto);

        Integer score = 0;

        // Confusion
        if (dto.confusion) score += 1;

        // Blood urea nitrogen level
        if (dto.bloodUreaNitrogen >= 20) score += 1;

        // Respiratory rate
        if (dto.respiratoryRate >= 30) score += 1;

        // Systolic blood pressure
        if (dto.systolicBloodPressure < 90) score += 1;

        // Age ≥ 65
        if (dto.age >= 65) score += 1;

        // Associated diseases
        if (dto.comorbidities != null) {
            if (dto.comorbidities.cancer) score += 1;
            if (dto.comorbidities.liverDisease) score += 1;
            if (dto.comorbidities.heartFailure) score += 1;
            if (dto.comorbidities.cerebrovascularDisease) score += 1;
            if (dto.comorbidities.renalDisease) score += 1;
        }

        // Risk assessment
        String risk = "Risque faible";
        if (score >= 3 && score < 5) {
            risk = "Risque moyen";
        } else if (score >= 5) {
            risk = "Risque élevé";
        }

        return ResponseEntity.ok(new RiskResultDTO(risk));
    }
}


package virtualhealth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import virtualhealth.dto.DiabetesRiskDTO;
import virtualhealth.dto.RiskResultDTO;

@RestController
@RequestMapping("/api/v0.1/users")
public class DiabetesRiskController {

    @PostMapping("/test/diabetes")
    public ResponseEntity<RiskResultDTO> assess(@RequestBody DiabetesRiskDTO dto) {
        System.out.println("Request received: " + dto);
        Integer score = 0;

        // Age
        if (dto.age >= 60) score += 3;
        else if (dto.age >= 50) score += 2;
        else if (dto.age >= 40) score += 1;

        // Gender
        if ("male".equalsIgnoreCase(dto.gender)) score += 1;

        // Family history
        if (dto.familyHistory) score += 1;

        // Blood pressure
        if (dto.hypertension) score += 1;

        // Activity
        if (!dto.physicallyActive) score += 1;

        // BMI
        Double heightInMeters = dto.height / 100.0;
        Double bmi = dto.weight / (heightInMeters * heightInMeters);

        if (bmi >= 25 && bmi < 30) score += 1;
        else if (bmi >= 30 && bmi < 35) score += 2;
        else if (bmi >= 35) score += 3;

        String risk = (score >= 5) ? "Risque élevé" : "Risque faible";
        return ResponseEntity.ok(new RiskResultDTO(risk));
    }
}


package virtualhealth.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import virtualhealth.dto.SickleRiskDTO;
import virtualhealth.dto.RiskResultDTO;

@RestController
@RequestMapping("/api/v0.1/users")
public class SickleRiskController {

    @PostMapping("/test/sickle")
    public ResponseEntity<RiskResultDTO> assess(@RequestBody SickleRiskDTO dto) {
        System.out.println("Request received: " + dto);
        Integer score = 0;

        // Age
        if (dto.age < 10) score += 1;
        else if (dto.age < 20) score += 2;
        else if (dto.age < 30) score += 3;
        else if (dto.age < 40) score += 4;
        else score += 5;

        // Gender
        if ("male".equalsIgnoreCase(dto.gender)) score += 1;

        // Family history
        if (dto.familyHistory) score += 3;

        // Symptoms
        if (dto.fatigue) score += 2;
        if (dto.painEpisodes) score += 3;
        if (dto.jaundice) score += 2;
        if (dto.legUlcers) score += 3;
        if (dto.frequentInfections) score += 2;
        if (dto.delayedGrowth) score += 1;

        // Origin
        if ("Sub-Saharan Africa".equalsIgnoreCase(dto.familyOrigin) || "Sub-Saharan Africa".equalsIgnoreCase(dto.partnerOrigin)) {
            score += 3;
        }

        String risk;
        if (score >= 12) risk = "Risque élevé";
        else if (score >= 7) risk = "Risque moyen";
        else risk = "Risque faible";

        return ResponseEntity.ok(new RiskResultDTO(risk));
    }
}


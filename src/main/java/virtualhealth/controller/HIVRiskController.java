package virtualhealth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import virtualhealth.dto.HIVRiskDTO;
import virtualhealth.dto.RiskResultDTO;

@RestController
@RequestMapping("/api/v0.1/users")
public class HIVRiskController {

    @PostMapping("/test/hiv")
    public ResponseEntity<RiskResultDTO> assess(@RequestBody HIVRiskDTO dto) {
        System.out.println("Request received: " + dto);
        Integer score = 0;

        // Age
        if (dto.age < 25) score += 1;
        else if (dto.age < 35) score += 2;
        else if (dto.age < 45) score += 3;
        else score += 4;

        // Gender
        if ("male".equalsIgnoreCase(dto.gender)) score += 1;

        // Multiple sexual partners
        if (dto.multiplePartners) score += 2;

        // History of STIs
        if (dto.stiHistory) score += 2;

        // Drug use
        if (dto.drugUse) score += 3;

        // Partner with HIV
        if (dto.hivPositivePartner) score += 3;

        String risk;
        if (score >= 8) risk = "Risque élevé";
        else if (score >= 5) risk = "Risque moyen";
        else risk = "Risque faible";

        return ResponseEntity.ok(new RiskResultDTO(risk));
    }
}


package virtualhealth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import virtualhealth.dto.MalariaRiskDTO;
import virtualhealth.dto.RiskResultDTO;

@RestController
@RequestMapping("/api/v0.1/users")
public class MalariaRiskController {

    @PostMapping("/test/malaria")
    public ResponseEntity<RiskResultDTO> assess(@RequestBody MalariaRiskDTO dto) {
        Integer score = 0;

        if (dto.travelToEndemicArea != null && dto.travelToEndemicArea) score += 2;
        if (dto.feverLast2Weeks != null && dto.feverLast2Weeks) score += 2;
        if (dto.mosquitoBites != null && dto.mosquitoBites) score += 1;
        if (dto.bedNetUse != null && !dto.bedNetUse) score += 1;
        if (dto.pregnancy != null && dto.pregnancy) score += 2;
        if (dto.age != null && (dto.age < 5 || dto.age > 60)) score += 1;

        String risk = score >= 5 ? "Risque élevé" : "Risque faible";
        return ResponseEntity.ok(new RiskResultDTO(risk));
    }
}


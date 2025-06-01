package virtualhealth.dto;

import lombok.Data;

@Data
public class RiskResultDTO {
    public String risk;

    public RiskResultDTO(String risk) {
        this.risk = risk;
    }
}


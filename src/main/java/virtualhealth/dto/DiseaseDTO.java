package virtualhealth.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class DiseaseDTO {
    private String name;
    private String description;
    private BigDecimal mortalityRate;
    private Integer worldRank;
    private Integer death;
    private BigDecimal percentage;
    private List<BiomarkerDTO> biomarkers;
}

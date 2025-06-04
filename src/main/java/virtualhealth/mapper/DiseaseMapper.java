package virtualhealth.mapper;
import lombok.Data;
import org.springframework.stereotype.Component;
import virtualhealth.dto.DiseaseDTO;
import virtualhealth.model.Disease;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class DiseaseMapper {

    public DiseaseDTO toDTO(Disease disease) {
        DiseaseDTO dto = new DiseaseDTO();
        dto.setName(disease.getName());
        dto.setDescription(disease.getDescription());
        dto.setMortalityRate(disease.getMortalityRate());
        dto.setWorldRank(disease.getWorldRank());
        dto.setDeath(disease.getDeath());
        dto.setPercentage(disease.getPercentage());
        return dto;
    }

    public List<DiseaseDTO> toDTOs(List<Disease> diseases) {
        return diseases.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

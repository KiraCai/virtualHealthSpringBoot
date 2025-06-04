package virtualhealth.mapper;

import lombok.Data;
import org.springframework.stereotype.Component;
import virtualhealth.dto.BiomarkerDTO;
import virtualhealth.model.Biomarker;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class BiomarkerMapper {
    public BiomarkerDTO toDTO(Biomarker biomarker) {
        BiomarkerDTO dto = new BiomarkerDTO();
        dto.setName(biomarker.getName());
        dto.setDescription(biomarker.getDescription());
        return dto;
    }

    public List<BiomarkerDTO> toDTOs(List<Biomarker> biomarkers) {
        return biomarkers.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

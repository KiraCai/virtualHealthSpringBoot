package virtualhealth.mapper;

import lombok.Data;
import org.springframework.stereotype.Component;
import virtualhealth.dto.VaccinationDisplayDTO;
import virtualhealth.model.Vaccination;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class VaccinationMapper {

    public VaccinationDisplayDTO toDTO(Vaccination vaccination) {
        VaccinationDisplayDTO dto = new VaccinationDisplayDTO();
        dto.setName(vaccination.getName());
        dto.setDate(vaccination.getDate());
        dto.setTime(vaccination.getTime());
        dto.setNameVac(vaccination.getNameVac());
        dto.setPlace(vaccination.getPlace());
        dto.setReason(vaccination.getReason());
        dto.setDocument(vaccination.getDocument());
        return dto;
    }

    public List<VaccinationDisplayDTO> toDTOs(List<Vaccination> vaccinations) {
        return vaccinations.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}


package virtualhealth.mapper;

import org.springframework.stereotype.Component;
import virtualhealth.dto.PrescriptionDisplayDTO;
import virtualhealth.model.Prescription;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PrescriptionMapper {

    public PrescriptionDisplayDTO toDTO(Prescription prescription) {
        PrescriptionDisplayDTO dto = new PrescriptionDisplayDTO();
        dto.setDate(prescription.getDate());
        dto.setTime(prescription.getTime());
        dto.setTreatment(prescription.getTreatment());
        dto.setDocument(prescription.getDocument());
        dto.setDoctor(prescription.getDoctor() != null ? prescription.getDoctor().getFirstName() + " " + prescription.getDoctor().getLastName() : null);
        return dto;
    }

    public List<PrescriptionDisplayDTO> toDTOs(List<Prescription> prescriptions) {
        return prescriptions.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
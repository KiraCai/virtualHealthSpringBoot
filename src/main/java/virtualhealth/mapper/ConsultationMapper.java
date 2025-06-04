package virtualhealth.mapper;

import org.springframework.stereotype.Component;
import virtualhealth.dto.ConsultationDisplayDTO;
import virtualhealth.model.Consultation;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class ConsultationMapper {

    public ConsultationDisplayDTO toDTO(Consultation consultation) {
        ConsultationDisplayDTO dto = new ConsultationDisplayDTO();
        dto.setDate(consultation.getDate());
        dto.setTime(consultation.getTime());
        dto.setComplaints(consultation.getComplaints());
        dto.setDecision(consultation.getDecision());
        dto.setDocument(consultation.getDocument());
        dto.setDoctor(consultation.getDoctor() != null ? consultation.getDoctor().getFirstName() + " " + consultation.getDoctor().getLastName() : null);
        return dto;
    }

    public List<ConsultationDisplayDTO> toDTOs(List<Consultation> consultations) {
        return consultations.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}


package virtualhealth.dto;

import lombok.Data;

import java.util.List;

@Data
public class PatientFullProfileDTO {
    private ClientUpdateDTO client;
    private List<TestDisplayDTO> tests;
    private List<PrescriptionDisplayDTO> prescriptions;
    private List<VaccinationDisplayDTO> vaccinations;
    private List<ConsultationDisplayDTO> consultations;
    private List<ScheduleDisplayDTO> reservations;
}

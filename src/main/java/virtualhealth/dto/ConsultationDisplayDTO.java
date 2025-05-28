package virtualhealth.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ConsultationDisplayDTO {
    private LocalDate date;
    private LocalTime time;
    private String doctor;
    private String complaints;
    private String decision;
    private String document;
}

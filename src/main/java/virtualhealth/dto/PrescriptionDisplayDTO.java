package virtualhealth.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PrescriptionDisplayDTO {
    private LocalDate date;
    private LocalTime time;
    private String treatment;
    private String doctor;
    private String document;
}

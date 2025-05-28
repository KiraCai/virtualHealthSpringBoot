package virtualhealth.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class VaccinationDisplayDTO {
    private String name;
    private LocalDate date;
    private LocalTime time;
    private String nameVac;
    private String place;
    private String reason;
    private String document;
}

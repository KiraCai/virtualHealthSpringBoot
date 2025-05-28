package virtualhealth.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TestDisplayDTO {
    private LocalDate date;
    private LocalTime time;
    private String nameTest;
    private String result;
    private String place;
    private String reason;
    private String document;
}

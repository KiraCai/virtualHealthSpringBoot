package virtualhealth.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleDisplayDTO {
    private LocalDate date;
    private LocalTime time;
    private String doctor;
    private String meetingPlace;
    private String reason;
}

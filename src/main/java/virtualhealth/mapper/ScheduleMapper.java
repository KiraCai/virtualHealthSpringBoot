package virtualhealth.mapper;

import lombok.Data;
import org.springframework.stereotype.Component;
import virtualhealth.dto.ScheduleDisplayDTO;
import virtualhealth.model.Calendar;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class ScheduleMapper {

    // Преобразование одной сущности Calendar в DTO
    public ScheduleDisplayDTO toDTO(Calendar calendar) {
        ScheduleDisplayDTO dto = new ScheduleDisplayDTO();
        dto.setDate(calendar.getDate());
        dto.setTime(calendar.getTime());
        dto.setMeetingPlace(calendar.getMeetingPlace());
        dto.setReason(calendar.getReason());

        // Если у Calendar есть doctor, добавим его имя
        if (calendar.getDoctor() != null) {
            dto.setDoctor(calendar.getDoctor().getFirstName() + " " + calendar.getDoctor().getLastName());
        } else {
            dto.setDoctor("Неизвестный врач");
        }

        return dto;
    }

    // Преобразование списка Calendar -> список DTO
    public List<ScheduleDisplayDTO> toDTOs(List<Calendar> calendars) {
        return calendars.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}


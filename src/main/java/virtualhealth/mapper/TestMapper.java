package virtualhealth.mapper;

import lombok.Data;
import org.springframework.stereotype.Component;
import virtualhealth.dto.TestDisplayDTO;
import virtualhealth.model.Test;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class TestMapper {

    public TestDisplayDTO toDTO(Test test) {
        TestDisplayDTO dto = new TestDisplayDTO();
        dto.setDate(test.getDate());
        dto.setTime(test.getTime());
        dto.setNameTest(test.getNameTest());
        dto.setResult(test.getResult());
        dto.setPlace(test.getPlace());
        dto.setReason(test.getReason());
        dto.setDocument(test.getDocument());
        return dto;
    }

    public List<TestDisplayDTO> toDTOs(List<Test> tests) {
        return tests.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}


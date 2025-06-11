package virtualhealth.webresources.uniprot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class AlternativeSequence {
        private String originalSequence;
        @ToString.Exclude
        private List<String> alternativeSequence;
}

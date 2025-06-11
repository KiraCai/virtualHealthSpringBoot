package virtualhealth.webresources.uniprot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProteinVariantFeature {
    private String type;
    private String category;
    private String cvId;
    private String ftId;
    private String description;
    private String alternativeSequence;
    private String begin;
    private String end;
    private String molecule;
}

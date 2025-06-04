package virtualhealth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProteinInfo {
    private String name;
    private String uniprotId;
    private String pdbId;
    private String entryType;
    private String primaryAccession;
}

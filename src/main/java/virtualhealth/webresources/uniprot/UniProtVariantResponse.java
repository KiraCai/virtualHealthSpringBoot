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
public class UniProtVariantResponse {

    private String accession;
    private String entryName;
    private String proteinName;
    private String geneName;
    private String sequence;

    @ToString.Exclude
    private List<Feature> features;
}


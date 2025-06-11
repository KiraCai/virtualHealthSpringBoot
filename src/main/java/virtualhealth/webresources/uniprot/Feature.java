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
public class Feature {
    private String type;
    private String description;
    //@JsonIgnore
    //private String alternativeSequence;
    private String begin;
    private String end;
}


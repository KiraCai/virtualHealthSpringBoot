package virtualhealth.webresources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import virtualhealth.model.ProteinInfo;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UniProtResponse {
    @JsonProperty("results")
    @ToString.Exclude
    private List<ProteinInfo> proteins;
}

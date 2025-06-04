package virtualhealth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import virtualhealth.webresources.IdList;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PubMedIdListResponse {

    @JacksonXmlProperty(localName = "IdList")
    @JacksonXmlElementWrapper(useWrapping = true)
    private IdList idList;

    public List<String> getIdList() {
        return idList != null ? idList.getIds() : List.of();
    }
}


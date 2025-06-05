package virtualhealth.webresources.pubmed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PubmedArticle {

    @JacksonXmlProperty(localName = "MedlineCitation")
    private MedlineCitation medlineCitation;
}


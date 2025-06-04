package virtualhealth.webresources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedlineCitation {

    @JacksonXmlProperty(localName = "Article")
    private ArticleXml article;
}

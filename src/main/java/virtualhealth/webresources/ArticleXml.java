package virtualhealth.webresources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleXml {

    @JacksonXmlProperty(localName = "ArticleTitle")
    private String title;

    @JacksonXmlProperty(localName = "Abstract")
    private AbstractXml abstractSection;
}


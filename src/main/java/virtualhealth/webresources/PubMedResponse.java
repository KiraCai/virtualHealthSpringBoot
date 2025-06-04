package virtualhealth.webresources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import virtualhealth.model.Article;

import java.util.List;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PubMedResponse {
    @JacksonXmlProperty(localName = "DocSum")
    @ToString.Exclude
    private List<Article> articles;
    public PubMedResponse() {}

}

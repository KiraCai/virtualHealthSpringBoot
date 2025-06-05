package virtualhealth.webresources.pubmed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private String title;
    private String abstractText;
    private String pmid; //unique integer value, starting at 1 , assigned to each PubMed record. A PMID is not the same as a PMCID (PubMed Central identifier)
    private List<String> authors;
    private String journal;
    private String url;

    private List<ArticleId> articleIdList;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class ArticleId {
    @JacksonXmlProperty(isAttribute = true, localName = "IdType")
    private String idType;

    @JacksonXmlText
    private String value;
}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class ArticleIdList {
    @JacksonXmlProperty(localName = "ArticleId")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ArticleId> articleIds;
}
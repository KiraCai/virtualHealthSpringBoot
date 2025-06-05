package virtualhealth.webresources.pubmed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedlineCitation {

    @JacksonXmlProperty(localName = "Article")
    private ArticleDetails article;

    @JacksonXmlProperty(localName = "PMID")
    private String pmid;

    @JacksonXmlProperty(localName = "ArticleIdList")
    private ArticleIdList articleIdList;

}

package virtualhealth.webresources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import virtualhealth.model.Article;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PubMedFetchResponse {

    @JacksonXmlProperty(localName = "PubmedArticle")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<PubmedArticle> pubmedArticles;
    public List<Article> getArticles() {
        if (pubmedArticles == null) {
            return List.of();
        }

        return pubmedArticles.stream()
                .map(pa -> {
                    String title = null;
                    String abstractText = null;
                    try {
                        title = pa.getMedlineCitation().getArticle().getTitle();
                        abstractText = pa.getMedlineCitation().getArticle().getAbstractSection().getAbstractText();
                        return new Article(title, abstractText);
                    } catch (Exception ignored) {return null;}
                }).filter(a -> a != null)
                .toList();
    }

}


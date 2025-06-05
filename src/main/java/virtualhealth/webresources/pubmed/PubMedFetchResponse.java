package virtualhealth.webresources.pubmed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PubMedFetchResponse {

    @JacksonXmlProperty(localName = "PubmedArticle")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<PubmedArticle> pubmedArticles;

    public List<Article> getArticles() {
        if (pubmedArticles == null) return List.of();

        return pubmedArticles.stream()
                .map(pa -> {
                    try {
                        MedlineCitation citation = pa.getMedlineCitation();
                        ArticleDetails ad = citation.getArticle();

                        String pmid = citation.getPmid();
                        String title = ad.getTitle();
                        String abstractText = ad.getAbstractSection() != null ? ad.getAbstractSection().getAbstractText() : null;
                        String journal = ad.getJournal() != null ? ad.getJournal().getTitle() : null;

                        List<String> authors = null;
                        if (ad.getAuthorList() != null && ad.getAuthorList().getAuthors() != null) {
                            authors = ad.getAuthorList().getAuthors().stream()
                                    .map(a -> a.getForeName() + " " + a.getLastName())
                                    .toList();
                        }

                        String url = "https://pubmed.ncbi.nlm.nih.gov/" + pmid + "/";

                        List<ArticleId> articleIds = null;
                        ArticleIdList articleIdList = citation.getArticleIdList();
                        if (articleIdList != null) {
                            articleIds = articleIdList.getArticleIds();
                        }

                        return new Article(title, abstractText, pmid, authors, journal, url, articleIds);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

}


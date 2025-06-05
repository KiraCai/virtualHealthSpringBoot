package virtualhealth.dto;
import lombok.Data;
import virtualhealth.webresources.pubmed.Article;
import virtualhealth.webresources.uniprot.ProteinInfo;

import java.util.List;

@Data
public class SearchResultDTO {
    private List<Article> articles;
    private List<ProteinInfo> proteins;

    public SearchResultDTO() {
    }

    public SearchResultDTO(List<Article> articles, List<ProteinInfo> proteins) {
        this.articles = articles;
        this.proteins = proteins;
    }
}

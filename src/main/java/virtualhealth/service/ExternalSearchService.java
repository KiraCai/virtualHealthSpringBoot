package virtualhealth.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import virtualhealth.dto.SearchResultDTO;
import virtualhealth.webresources.pubmed.Article;
import virtualhealth.webresources.uniprot.ProteinInfo;
import virtualhealth.webresources.pubmed.PubMedUtil;
import virtualhealth.webresources.uniprot.UniProtUtil;

import java.util.List;

@Service
@AllArgsConstructor
public class ExternalSearchService {
    public SearchResultDTO searchByQuery(String query) {
        List<Article> articles = PubMedUtil.searchArticles(query);
        List<ProteinInfo> proteins = UniProtUtil.searchProteins(query);
        return new SearchResultDTO(articles, proteins);
    }
}

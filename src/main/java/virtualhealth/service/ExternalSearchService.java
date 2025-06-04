package virtualhealth.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import virtualhealth.dto.SearchResultDTO;
import virtualhealth.model.Article;
import virtualhealth.model.ProteinInfo;
import virtualhealth.webresources.PubMedUtil;
import virtualhealth.webresources.UniProtUtil;

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

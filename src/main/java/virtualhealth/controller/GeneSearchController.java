package virtualhealth.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import virtualhealth.dto.SearchResultDTO;
import virtualhealth.model.Article;
import virtualhealth.model.ProteinInfo;
import virtualhealth.service.ExternalSearchService;
import virtualhealth.service.PubMedService;
import virtualhealth.service.UniProtService;

import java.util.List;

@RestController
@RequestMapping("/api/v0.1/users")
@AllArgsConstructor
public class GeneSearchController {

    private ExternalSearchService searchService;
    private PubMedService pubMedService;
    private UniProtService uniProtService;

    @GetMapping("/visualization")
    public ResponseEntity<SearchResultDTO> search(@RequestParam String query) {
        List<Article> articles = pubMedService.searchArticles(query);
        List<ProteinInfo> proteins = uniProtService.searchProteins(query);
        //SearchResultDTO result = searchService.searchByQuery(articles, proteins);
        SearchResultDTO result = new SearchResultDTO();
        result.setArticles(articles);
        result.setProteins(proteins);
        return ResponseEntity.ok(result);
    }


}

package virtualhealth.controller;

import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import virtualhealth.dto.SearchResultDTO;
import virtualhealth.webresources.pubmed.Article;
import virtualhealth.webresources.uniprot.ConservationService;
import virtualhealth.webresources.uniprot.Feature;
import virtualhealth.webresources.uniprot.ProteinInfo;
import virtualhealth.service.ExternalSearchService;
import virtualhealth.service.PubMedService;
import virtualhealth.service.UniProtService;
import virtualhealth.webresources.uniprot.Variant;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v0.1/users")
@AllArgsConstructor
public class GeneSearchController {

    private ExternalSearchService searchService;
    private PubMedService pubMedService;
    private UniProtService uniProtService;
    private ConservationService conservationService;

    @GetMapping("/visualization")
    public ResponseEntity<SearchResultDTO> search(@RequestParam String query) {
        List<Article> articles = pubMedService.searchArticles(query);
        List<ProteinInfo> proteins = uniProtService.searchProteins(query);

        for (ProteinInfo protein : proteins) {
            if (protein.getPrimaryAccession() != null) {
                System.out.println("если не пустой начато фэтч");
                List<Feature> variants = uniProtService.fetchVariants(protein.getPrimaryAccession());
                //System.out.println("Запрос визуализации для accession: " + protein.getPrimaryAccession() + ", варианты: " + variants);
                protein.setFeatures(variants); // Добавляем список мутаций внутрь белка

                Map<Integer, Integer> scores = conservationService.fetchConservationScores(protein.getPrimaryAccession());
                protein.setConservationScores(scores);
            }
        }

        SearchResultDTO result = new SearchResultDTO();
        result.setArticles(articles);
        result.setProteins(proteins);
        return ResponseEntity.ok(result);
    }



}

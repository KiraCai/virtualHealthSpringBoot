package virtualhealth.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import virtualhealth.webresources.uniprot.*;
import org.slf4j.Logger;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UniProtService {
    private static final Logger log = LoggerFactory.getLogger(UniProtService.class);

    private static final String UNIPROT_BASE_URL = "https://rest.uniprot.org/uniprotkb/search";
    private static final String UNIPROT_VARIANT_URL = "https://rest.uniprot.org/uniprotkb/{accession}.json?fields=features";

    private RestTemplate restTemplate;
    public List<ProteinInfo> searchProteins(String query) {
        // Строим URL для запроса
        String url = UriComponentsBuilder.fromHttpUrl(UNIPROT_BASE_URL)
                .queryParam("query", query)
                .queryParam("format", "json")  // Ответ в формате JSON
                .toUriString();
        try {
            String response = restTemplate.getForObject(url, String.class);
            System.out.println("____________________________________");
            //System.out.println("UniProt JSON response:\n" + response);
            System.out.println("____________________________________");
            // Парсим JSON и извлекаем информацию о белках
            return parseProteins(response);

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }

    }

    private List<ProteinInfo> parseProteins(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            UniProtResponse uniProtResponse = objectMapper.readValue(jsonResponse, UniProtResponse.class);
            // Возвращаем список белков
            return uniProtResponse.getProteins();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();  // Возвращаем пустой список в случае ошибки
        }
    }

    public List<Feature> fetchVariants(String accession) {
        log.info("⏳ Загружаем варианты для accession: {}", accession);

        String url = "https://www.ebi.ac.uk/proteins/api/variation/" + accession;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            String body = response.getBody();

            System.out.println("📦 Variant JSON response (первые 1000 символов):");
            System.out.println(body.substring(0, Math.min(body.length(), 1000)));

            ObjectMapper objectMapper = new ObjectMapper();
            UniProtVariantResponse parsed = objectMapper.readValue(body, UniProtVariantResponse.class);
            System.out.println("отдельные части");
            //System.out.println("Accession: " + parsed.getAccession());
            return parsed.getFeatures() != null ? parsed.getFeatures() : List.of();

        } catch (Exception e) {
            log.error("❌ Ошибка при получении вариантов: ", e);
            return List.of();
        }
    }




}

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class VariantViewerResponse {
    private List<Variant> variants;
}

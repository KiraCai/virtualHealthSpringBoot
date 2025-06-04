package virtualhealth.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import virtualhealth.dto.PubMedIdListResponse;
import virtualhealth.model.Article;
import virtualhealth.webresources.PubMedFetchResponse;
import virtualhealth.webresources.PubMedResponse;

import java.util.List;

@Service
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PubMedService {
    private static final String PUBMED_BASE_URL = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi";

    public List<Article> searchArticles(String query) {
        // 1. Сначала получаем список PMIDs
        String esearchUrl = UriComponentsBuilder.fromHttpUrl(PUBMED_BASE_URL)
                .queryParam("db", "pubmed")
                .queryParam("term", query)
                .queryParam("retmax", "10")
                .queryParam("retmode", "xml")
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        String esearchResponse = restTemplate.getForObject(esearchUrl, String.class);

        List<String> pmids = extractPmidsFromXml(esearchResponse);
        if (pmids.isEmpty()) return List.of();

        // 2. Получаем полные статьи по этим PMIDs
        String idList = String.join(",", pmids);
        String efetchUrl = UriComponentsBuilder.fromHttpUrl("https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi")
                .queryParam("db", "pubmed")
                .queryParam("id", idList)
                .queryParam("retmode", "xml")
                .toUriString();

        String efetchResponse = restTemplate.getForObject(efetchUrl, String.class);

        // 3. Парсим результат
        return parseFullArticles(efetchResponse);
    }


    private List<Article> parseArticles(String xmlResponse) {
        // Здесь нужно парсить XML, извлекая статьи
        // Например, с помощью библиотеки JAXB или простой регулярной выразительности
          // Возвращаем список статей
        try {
            XmlMapper xmlMapper = new XmlMapper();
            PubMedResponse pubMedResponse = xmlMapper.readValue(xmlResponse, PubMedResponse.class);

            // Преобразуем статьи из ответа в нужный формат
            return pubMedResponse.getArticles();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();  // Возвращаем пустой список в случае ошибки
        }
    }
    private List<String> extractPmidsFromXml(String xml) {
        try {
            XmlMapper mapper = new XmlMapper();
            PubMedIdListResponse response = mapper.readValue(xml, PubMedIdListResponse.class);
            return response.getIdList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private List<Article> parseFullArticles(String xml) {
        try {
            XmlMapper mapper = new XmlMapper();
            PubMedFetchResponse response = mapper.readValue(xml, PubMedFetchResponse.class);
            return response.getArticles(); // уже нормальный список с заголовками и т.д.
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

}

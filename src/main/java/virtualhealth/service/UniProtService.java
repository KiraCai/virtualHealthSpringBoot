package virtualhealth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import virtualhealth.webresources.uniprot.ProteinInfo;
import virtualhealth.webresources.uniprot.UniProtResponse;

import java.util.List;

@Service
@AllArgsConstructor
public class UniProtService {
    private static final String UNIPROT_BASE_URL = "https://rest.uniprot.org/uniprotkb/search";

    private RestTemplate restTemplate;
    public List<ProteinInfo> searchProteins(String query) {
        // Строим URL для запроса
        String url = UriComponentsBuilder.fromHttpUrl(UNIPROT_BASE_URL)
                .queryParam("query", query)
                .queryParam("format", "json")  // Ответ в формате JSON
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);
        System.out.println("____________________________________");
        System.out.println("UniProt JSON response:\n" + response);
        System.out.println("____________________________________");
        // Парсим JSON и извлекаем информацию о белках
        return parseProteins(response);
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
}

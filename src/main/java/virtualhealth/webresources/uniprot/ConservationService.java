package virtualhealth.webresources.uniprot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import virtualhealth.webresources.uniprot.*;
import org.slf4j.Logger;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConservationService {


    private final RestTemplate restTemplate;
    MsaConservationService service = new MsaConservationService();

    public ConservationService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public Map<Integer, Integer> fetchConservationScores(String uniprotId) {
        String url = "https://consurfdb.tau.ac.il/api/" + uniprotId + "/json";
        System.out.println(url);

        /*try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            System.out.println("здесь ответ с сервера" + response.getBody());
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return parseConservationScores(response.getBody());
            }
        } catch (Exception e) {
            System.err.println("⚠ Ошибка при подключении к ConSurf API: " + e.getMessage());
            System.err.println("⚠ Похоже, ConSurf временно недоступен. Возвращается пустая карта как заглушка.");
        }*/

        try {
            // сюда
            System.out.println("сюда зашел");
            Map<Integer, Double> entropyMap = service.getConservationByUniProtId(uniprotId);

            entropyMap.forEach((pos, entropy) -> {
                System.out.printf("Position %d: Entropy = %.3f\n", pos, entropy);
            });

            System.out.println("Шенон дал данных");
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        return Collections.emptyMap();
    }

    private Map<Integer, Integer> parseConservationScores(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("вошёл в парсер");
        JsonNode root = mapper.readTree(json);
        Map<Integer, Integer> scoreMap = new HashMap<>();

        JsonNode residues = root.path("residues");
        for (JsonNode node : residues) {
            int pos = node.path("position").asInt();
            int score = node.path("score").asInt(); // от 1 до 9
            if (score >= 0) {
                scoreMap.put(pos, score);
            }
        }
        System.out.println("итоговая карта успех" + scoreMap);
        return scoreMap;
    }


}


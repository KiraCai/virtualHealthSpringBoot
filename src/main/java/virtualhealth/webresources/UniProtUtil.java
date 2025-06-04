package virtualhealth.webresources;

import com.fasterxml.jackson.databind.ObjectMapper;
import virtualhealth.model.ProteinInfo;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class UniProtUtil {

    // Работать с UniProt API для получения информации о белках
    public static List<ProteinInfo> searchProteins(String query) {
        try {
            // Строим URL для запроса с параметром query
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
            String apiUrl = "https://rest.uniprot.org/uniprotkb/search?query=" + encodedQuery + "&format=json";

            // Создаем запрос
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();

            // Отправляем запрос и получаем ответ
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("UniProt response: " + response.body());

            // Проверяем, если код ответа не 200 (ОК), выбрасываем исключение
            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to fetch data from UniProt API: " + response.statusCode());
            }

            // Парсим JSON-ответ
            return parseProteins(response.body());

        } catch (Exception e) {
            e.printStackTrace();
            return List.of();  // Возвращаем пустой список в случае ошибки
        }
    }

    // Метод для парсинга JSON-ответа
    private static List<ProteinInfo> parseProteins(String jsonResponse) {
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


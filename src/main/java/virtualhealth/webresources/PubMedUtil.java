package virtualhealth.webresources;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import virtualhealth.model.Article;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class PubMedUtil {
    //взаимодействует с PubMed API и парсит XML-ответы для получения статей.

    private static final String PUBMED_BASE_URL = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi";

    public static List<Article> searchArticles(String query) {
        try {
            // Строим URL для запроса
            String url = String.format("%s?db=pubmed&term=%s&retmax=10&retmode=xml", PUBMED_BASE_URL, query);

            // Создаем запрос
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .build();

            // Отправляем запрос и получаем ответ
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Парсим XML-ответ
            return parseArticles(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();  // Возвращаем пустой список в случае ошибки
        }
    }

    private static List<Article> parseArticles(String xmlResponse) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            PubMedResponse pubMedResponse = xmlMapper.readValue(xmlResponse, PubMedResponse.class);
            return pubMedResponse.getArticles();  // Возвращаем список статей
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();  // Возвращаем пустой список в случае ошибки
        }
    }
}

